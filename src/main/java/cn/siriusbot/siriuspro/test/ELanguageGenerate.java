package cn.siriusbot.siriuspro.test;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.aspectj.util.FileUtil;

import java.io.File;
import java.util.List;
import java.util.Locale;

/**
 * 易语言代码通用解析器生成
 */
//@Component
@Log4j2
public class ELanguageGenerate extends BaseGenerate{

    public ELanguageGenerate() {
        super(s -> {
            switch (s) {
                case "String" -> {
                    return "文本型";
                }
                case "Integer", "int" -> {
                    return "整数型";
                }
                case "Boolean", "boolean" -> {
                    return "逻辑型";
                }
                case "double", "Double" -> {
                    return "双精度小数型";
                }
                case "long", "Long" -> {
                    return "长整数型";
                }
                default -> {
                    return s;
                }
            }
        });
    }


    /**
     * 生成易语言数据类型代码
     */
    public String generateTypeInfos() {
        StringBuilder sb = new StringBuilder();
        sb.append(".版本 2").append('\n').append('\n');
        for (String key : map.keySet()) {
            sb.append(".数据类型 ").append(key).append(", 公开").append('\n');
            for (Info info : map.get(key)) {
                sb.append("    .成员 ").append(info.getName()).append(',').append(info.getType());
                if (info.list) {
                    sb.append(", , \"1\" , ").append(info.getJavaDoc()).append('\n');
                } else {
                    sb.append(", , , ").append(info.getJavaDoc()).append('\n');
                }

            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 生成解析方法
     */
    public String generateAnalyticMethod() {
        StringBuilder sb = new StringBuilder();
        String methodName = "通用Json解析";
        String transition = "强制转换";
        String paramMethod = "method";
        String paramSource = "source";
        // 添加代码头
        sb.append("""
                .版本 2
                .支持库 spec

                """);
        // 添加所有待解析局部变量
        for (String key : map.keySet()) {
            sb
                    .append(".局部变量 ")
                    .append(key.substring(0, 1).toLowerCase(Locale.ROOT))
                    .append(key.substring(1))
                    .append(',')
                    .append(key)
                    .append('\n');
        }
        // 定义需要用到的局部变量
        sb.append(".局部变量 json, zyJsonDocument").append('\n');
        sb.append(".局部变量 i, 整数型").append('\n');
        sb.append(".局部变量 length, 整数型").append('\n');
        sb.append(".局部变量 item, zyJsonValue").append('\n');
        sb.append('\n');
        // 共有方法
        sb.append(String.format("json.解析 (%s, , , )", paramSource)).append('\n');
        for (String key : map.keySet()) {
            sb.append(String.format(".如果真 (%s ＝ “%s”)", paramMethod, key)).append('\n');
            // ==== 如果事件 ====
            String o = key.substring(0, 1).toLowerCase(Locale.ROOT) + key.substring(1);
            List<Info> infos = map.get(key);
            for (Info info : infos) {
                if (info.isList()) {
                    // 数组事件
                    sb.append("    ").append(String.format("item ＝ json.取属性 (, “%s”)", info.getName())).append('\n');
                    sb.append("    ").append("length ＝ item.取成员数 ()").append('\n');
                    sb.append("    ").append(String.format("重定义数组 (%s.%s, 假, length)", o, info.getName())).append('\n');
                    String eType = info.getType();
                    if (eType.equals(info.getSrcType())) {
                        // 对象类型成员
                        sb.append("    ").append("i ＝ 0").append('\n');
                        sb.append("    ").append(".判断循环首 (i < length)").append('\n');
                        sb.append("    ").append("    ").append("i ＝ i + 1").append('\n');
                        sb.append("    ").append("    ").append(String.format(
                                "%s(%s.%s[i], %s(“%s“, item.取成员 (, i - 1).到文本()))",
                                transition,
                                o,
                                info.getName(),
                                methodName,
                                info.getType()
                        )).append('\n');
                        sb.append("    ").append(".判断循环尾 ()").append('\n');
                    } else {
                        // 普通类型成员
                        sb.append("    ").append(".计次循环首 (length, i)").append('\n');
                        String jsonType;
                        switch (eType) {
                            case "文本型" -> jsonType = "取文本";
                            case "整数型" -> jsonType = "取整数";
                            case "逻辑型" -> jsonType = "取逻辑";
                            case "双精度小数型" -> jsonType = "取双精度";
                            case "长整数型" -> jsonType = "取长整数";
                            default -> jsonType = "取文本";
                        }
                        sb.append("    ").append(String.format("    %s.%s[i] ＝ item.取成员 (, i - 1).%s ()", o, info.getName(), jsonType)).append('\n');
                        sb.append("    ").append(".计次循环尾 ()").append('\n');
                    }

                } else {
                    // 单属性事件
                    String eType = info.getType();
                    if (eType.equals(info.getSrcType())) {
                        // 解析对象类型
                        sb.append("    ").append(String.format(
                                "%s(%s.%s, %s(“%s“,json.取属性 (, “%s”).到文本()))",
                                transition,
                                o,
                                info.getName(),
                                methodName,
                                info.getType(),
                                info.getName()
                        )).append('\n');

                    } else {
                        // 解析基础类型
                        String jsonType;
                        switch (eType) {
                            case "文本型" -> jsonType = "取文本";
                            case "整数型" -> jsonType = "取整数";
                            case "逻辑型" -> jsonType = "取逻辑";
                            case "双精度小数型" -> jsonType = "取双精度";
                            case "长整数型" -> jsonType = "取长整数";
                            default -> jsonType = "取文本";
                        }
                        sb.append("    ").append(String.format(
                                "%s.%s ＝ json.%s (“%s”)",
                                o,
                                info.getName(),
                                jsonType,
                                info.getName()
                        )).append('\n');
                    }
                }
            }
            sb.append("    ").append(String.format("返回 (%s)", o)).append('\n');
            // ==== 如果事件 ====
            sb.append(".如果真结束").append('\n');
        }
        sb.append("返回 (“”)").append('\n');
        return sb.toString();
    }

    private String eTypeToJsonName(String eType) {
        String jsonType;
        switch (eType) {
            case "整数型" -> jsonType = "取整数";
            case "逻辑型" -> jsonType = "取逻辑";
            case "双精度小数型" -> jsonType = "取双精度";
            case "长整数型" -> jsonType = "取长整数";
            default -> jsonType = "取文本";
        }
        return jsonType;
    }

    private String jsonNameToeType(String eType) {
        String jsonType;
        switch (eType) {
            case "整数型" -> jsonType = "置整数";
            case "逻辑型" -> jsonType = "置逻辑";
            case "双精度小数型" -> jsonType = "置双精度";
            case "长整数型" -> jsonType = "置长整数";
            default -> jsonType = "置文本";
        }
        return jsonType;
    }

    /**
     * 生成解析对象
     *
     * @return
     */
    public String generateAnalyticClass() {
        StringBuilder sb = new StringBuilder();
        String paramMethodHead = "parse";
        String paramSource = "source";
        // 添加代码头
        sb.append("""
                .版本 2

                """);
        for (String key : map.keySet()) {
            String obj = key.substring(0, 1).toLowerCase(Locale.ROOT) + key.substring(1);
            // 生成方法
            sb.append(String.format(".子程序 %s%s, %s, 公开", paramMethodHead, key, key)).append('\n');
            sb.append(String.format(".参数 %s, 文本型", paramSource)).append('\n');
            // 生成局部变量
            sb.append(".局部变量 i, 整数型").append('\n');
            sb.append(".局部变量 length, 整数型").append('\n');
            sb.append(".局部变量 json, zyJsonDocument").append('\n');
            sb.append(".局部变量 item, zyJsonValue").append('\n');
            sb.append(String.format(".局部变量 %s, %s", obj, key)).append('\n');
            sb.append('\n');
            // 生成方法
            sb.append(String.format("json.解析 (%s, , , )", paramSource)).append('\n');
            List<Info> infos = map.get(key);
            for (Info info : infos) {
                if (info.isList()) {
                    // 数组解析
                    sb.append(String.format("item ＝ json.取属性 (, “%s”)", info.getName())).append('\n');
                    sb.append("length ＝ item.取成员数 ()").append('\n');
                    sb.append(String.format("重定义数组 (%s.%s, 假, length)", obj, info.getName())).append('\n');
                    sb.append(".计次循环首 (length, i)").append('\n');
                    if (info.getType().equals(info.getSrcType())) {
                        // 对象类型
                        sb.append("    ").append(String.format("%s.%s[i] ＝ %s%s(item.取成员 (, i - 1).到文本 ())",
                                        obj,
                                        info.getName(),
                                        paramMethodHead,
                                        info.getType()
                                )
                        ).append('\n');
                    } else {
                        // 基础类型
                        sb.append("    ").append(String.format("%s.%s[i] ＝ item.取成员 (, i - 1).%s ()", obj, info.getName(), eTypeToJsonName(info.getType()))).append('\n');
                    }
                    sb.append(".计次循环尾 ()").append('\n');
                } else {
                    // 单独对象解析
                    if (info.getType().equals(info.getSrcType())) {
                        // 对象类型
                        sb.append(String.format(
                                        "%s.%s ＝ %s%s(json.取属性 (, “%s”).到文本())",
                                        obj,
                                        info.getName(),
                                        paramMethodHead,
                                        info.getType(),
                                        info.getName()
                                )
                        ).append('\n');
                    } else {
                        // 基础类型
                        sb.append(String.format(
                                        "%s.%s ＝ json.%s (“%s”)",
                                        obj,
                                        info.getName(),
                                        eTypeToJsonName(info.getType()),
                                        info.getName()
                                )
                        ).append('\n');
                    }
                }
            }
            // 生成返回
            sb.append(String.format("返回 (%s)", obj)).append('\n');
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 生成构建对象
     *
     * @return
     */
    public String generateBuildObject() {
        StringBuilder sb = new StringBuilder();
        String paramMethodHead = "build";
        String paramSource = "source";
        // 添加代码头
        sb.append("""
                .版本 2

                """);
        for (String key : map.keySet()) {
            // 生成方法
            sb.append(String.format(".子程序 %s%s, 文本型, 公开", paramMethodHead, key)).append('\n');
            sb.append(String.format(".参数 %s, %s", paramSource, key)).append('\n');
            // 生成局部变量
            sb.append(".局部变量 i, 整数型").append('\n');
            sb.append(".局部变量 length, 整数型").append('\n');
            sb.append(".局部变量 json, zyJsonDocument").append('\n');
            sb.append('\n');
            // 生成方法
            sb.append("json.创建 ()").append('\n');
            List<Info> infos = map.get(key);
            for (Info info : infos) {
                if (info.isList()) {
                    // 数组构建
                    sb.append(String.format("length ＝ 取数组成员数 (%s.%s)", paramSource, info.getName())).append('\n');
                    sb.append(".计次循环首 (length, i)").append('\n');
                    if (info.getType().equals(info.getSrcType())) {
                        // 对象类型
                        // json.置JSON (“id [i - 1]”, build (source.id [i]))
                        sb.append(String.format("json.置JSON (“%s[” ＋ 到文本 (i － 1) ＋ “]”, %s%s (%s.%s [i]))",
                                        info.getName(),
                                        paramMethodHead,
                                        info.getType(),
                                        paramSource,
                                        info.getName()
                                )
                        ).append('\n');
                    } else {
                        // 普通类型
                        // json.置文本 (“id[0]”, source.id[1])
                        sb.append("    ").append(String.format("json.%s (“%s[” ＋ 到文本 (i － 1) ＋ “]”, %s.%s [i])",
                                        jsonNameToeType(info.getType()),
                                        info.getName(),
                                        paramSource,
                                        info.getName()
                                )
                        ).append('\n');
                    }
                    sb.append(".计次循环尾 ()").append('\n');
                } else {
                    // 普通构建
                    if (info.getType().equals(info.getSrcType())) {
                        // 对象类型
                        // json.置JSON (“id”, build (source.id))
                        sb.append(String.format("json.置JSON (“%s”, %s%s (%s.%s))",
                                        info.getName(),
                                        paramMethodHead,
                                        info.getType(),
                                        paramSource,
                                        info.getName()
                                )
                        ).append('\n');
                    } else {
                        // 基础类型
                        // json.置文本 (“id”, source.id)
                        sb.append(String.format("json.%s (“%s”, %s.%s)",
                                        jsonNameToeType(info.getType()),
                                        info.getName(),
                                        paramSource,
                                        info.getName()
                                )
                        ).append('\n');
                    }
                }
            }

            sb.append("返回 (json.到文本 ())").append('\n');
            sb.append('\n');
        }
        return sb.toString();
    }


    /**
     * 生成API方法
     *
     * @return
     */
    public String generateAPIMethod() {
        StringBuilder sb = new StringBuilder();
        String paramSource = "source";
        String url = "global_url_head";
        String build = "build_util.build";
        String parse = "parse_util.parse";
        String jsonApi = "api";
        String jsonMethod = "method";
        String jsonParam = "param";
        for (String apiName : apiMap.keySet()) {
            // 构建代码头
            sb.append(String.format("""
                    .版本 2
                                        
                    .程序集 %s
                    .程序集变量 %s, 文本型
                                        
                    .子程序 _初始化, , , 当基于本类的对象被创建后，此方法会被自动调用
                                        
                    .子程序 _销毁, , , 当基于本类的对象被销毁前，此方法会被自动调用
                                        
                    """, apiName, url));

            List<MethodInfo> methodInfos = apiMap.get(apiName);
            for (MethodInfo methodInfo : methodInfos) {
                // 构建方法
                sb.append(String.format(".子程序 %s, 整数型, 公开", methodInfo.getFormatName())).append('\n');
                sb.append(String.format(".参数 %s, %s, 参考 可空 %s, 返回结果",
                        methodInfo.getType().getSrcType(),
                        methodInfo.getType().getType(),
                        methodInfo.getType().isList() ? " 数组" : ""
                )).append('\n');
                sb.append(String.format(".参数 %s, 文本型, 参考 可空, http源消息，包括错误消息", paramSource)).append('\n');
                // 构造参数
                for (Info param : methodInfo.getParams()) {
                    sb.append(String.format(".参数 %s, %s,%s%s, %s",
                                    param.getName(),
                                    param.getType(),
                                    !param.isNonNull() ? " 可空" : "",
                                    param.isList() ? " 数组" : "",
                                    param.getJavaDoc()
                            )
                    ).append('\n');
                }
                // 构造局部变量
                sb.append(".局部变量 i, 整数型").append('\n');
                sb.append(".局部变量 code, 整数型").append('\n');
                sb.append(".局部变量 length, 整数型").append('\n');
                sb.append(".局部变量 json, zyJsonDocument").append('\n');
                sb.append(".局部变量 data, zyJsonDocument").append('\n');
                sb.append('\n');
                // 构造方法代码
                sb.append("json.创建 ()").append('\n');
                sb.append(String.format("json.置文本 (“%s”, “%s”)", jsonApi, apiName)).append('\n');
                sb.append(String.format("json.置文本 (“%s”, “%s”)", jsonMethod, methodInfo.getName())).append('\n');
                // 构建参数json
                for (Info param : methodInfo.getParams()) {
                    if (!param.isNonNull()) {
                        // 可空
                        sb.append(String.format(".如果真 (是否为空 (%s) ＝ 假)", param.getName())).append('\n');
                    }
                    // =====
                    if (param.isList()) {
                        // 处理数组
                        sb.append(String.format("length ＝ 取数组成员数 (%s)", param.getName())).append('\n');
                        sb.append(".计次循环首 (length, i)").append('\n');
                        if (param.getType().equals(param.getSrcType())) {
                            // 对象类型
                            sb.append(String.format("json.置JSON (“%s.%s[” ＋ 到文本 (i － 1) ＋ “]”, %s%s(%s [i]))",
                                    jsonParam,
                                    param.getName(),
                                    build,
                                    param.getType(),
                                    param.getName()
                            )).append('\n');
                        } else {
                            // 基础类型
                            sb.append(String.format("json.%s (“%s.%s[” ＋ 到文本 (i － 1) ＋ “]”, %s [i])",
                                    jsonNameToeType(param.getType()),
                                    jsonParam,
                                    param.getName(),
                                    param.getName()
                            )).append('\n');
                        }
                        sb.append(".计次循环尾 ()").append('\n');
                    } else {
                        // 处理单个
                        if (param.getType().equals(param.getSrcType())) {
                            // 对象类型
                            sb.append(String.format("json.置JSON (“%s.%s”, %s%s(%s))",
                                    jsonParam,
                                    param.getName(),
                                    build,
                                    param.getType(),
                                    param.getName()
                            )).append('\n');
                        } else {
                            // 基础类型
                            sb.append(String.format("json.%s (“%s.%s”, %s)",
                                    jsonNameToeType(param.getType()),
                                    jsonParam,
                                    param.getName(),
                                    param.getName()
                            )).append('\n');
                        }
                    }
                    // =====
                    if (!param.isNonNull()) {
                        // 可空
                        sb.append(".如果真结束").append('\n');
                    }
                }
                // 构建http请求代码
                StringBuilder build_return = new StringBuilder();
                if (methodInfo.getType().isList()) {
                    // 处理数组
                    build_return.append("length ＝ data.取成员数 (“data”)").append('\n');
                    build_return.append(String.format("重定义数组 (%s, 假, length)", methodInfo.getType().getSrcType())).append('\n');
                    build_return.append(".计次循环首 (length, i)").append('\n');
                    if (methodInfo.getType().getType().equals(methodInfo.getType().getSrcType())) {
                        // 对象类型
                        build_return.append(String.format("%s [i] ＝ %s%s (data.取成员 ( “data”,i-1).到文本 ())",
                                methodInfo.getType().getSrcType(),
                                parse,
                                methodInfo.getType().getType()
                        )).append('\n');
                    } else {
                        // 基础类型
                        build_return.append(String.format("%s [i] ＝ data.%s (“data[” ＋ 到文本 (i － 1) ＋ “]”)",
                                methodInfo.getType().getSrcType(),
                                eTypeToJsonName(methodInfo.getType().getType())
                        )).append('\n');
                    }
                    build_return.append(".计次循环尾 ()");
                } else {
                    // 处理对象
                    if (methodInfo.getType().getType().equals(methodInfo.getType().getSrcType())) {
                        // 对象类型
                        build_return.append(String.format("%s ＝ %s%s (data.取属性 (, “data”).到文本 ())",
                                methodInfo.getType().getSrcType(),
                                parse,
                                methodInfo.getType().getType()
                        ));
                    } else {
                        // 基础类型
                        build_return.append(String.format("%s ＝ data.%s (“data”)",
                                methodInfo.getType().getSrcType(),
                                eTypeToJsonName(methodInfo.getType().getType())
                        ));
                    }
                }
//                json.置文本 (“session”, apiToken)
                sb.append("json.置文本 (“session”, apiToken)").append("\n");

                sb.append(String.format("""              
                        source ＝ 编码_Utf8到Ansi (网页_访问_对象 (%s, 1, , , , “Content-Type: application/json;charset=utf-8”, , , , 编码转换 (到字节集 (json.到文本 (, , , )), #编码_GB18030, #编码_UTF_8, ), , , , , , , , , ))
                        data.解析 (source)
                        code ＝ data.取整数 (“code”)
                        .如果真 (code ＝ 0)
                        %s
                        .如果真结束
                        返回 (code)
                        """, url, build_return));
            }

            // 类分隔符
            sb.append('\n');
            sb.append("======================================================").append('\n');
            sb.append("======================================================").append('\n');
            sb.append('\n');
        }
        return sb.toString();
    }


    @SneakyThrows
    public void exec(Class<?> clazz, List<Info> infos) {
        String url = System.getProperty("user.dir") + "\\src\\main\\java\\";
        url = url + clazz.getName().replace('.', '\\') + ".java";
        String str = FileUtil.readAsString(new File(url));
        int index = str.indexOf("class");
        ;
        for (Info info : infos) {
            try {
                int a = str.indexOf("/**", index);
                int b = str.indexOf(" " + info.getName() + ";");
                index = b;
                String substring = str.substring(a, b);
                int c = substring.indexOf("\n");
                int d = substring.indexOf("*/");
                substring = substring.substring(c, d);
                String[] split = substring.split("\n");
                StringBuilder doc = new StringBuilder();
                for (String s : split) {
                    String replace = s.replace(" ", "");
                    if (!replace.isEmpty()) {
                        if (replace.charAt(0) == '*') {
                            replace = replace.substring(1);
                        }
                        doc.append(replace);
                    }
                }
                info.setJavaDoc(doc.toString());
            } catch (Exception e) {
                info.setJavaDoc("");
            }

        }
    }
}
