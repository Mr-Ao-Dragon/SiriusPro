package cn.siriusbot.siriuspro.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class QTLanguageGenerate extends BaseGenerate {

    public QTLanguageGenerate() {
        super(s -> {
            switch (s) {
                case "String" -> {
                    return "QString";
                }
                case "Integer", "int" -> {
                    return "int";
                }
                case "Boolean", "boolean" -> {
                    return "bool";
                }
                case "double", "Double" -> {
                    return "double";
                }
                case "long", "Long" -> {
                    return "long long";
                }
                default -> {
                    return null;
                }
            }
        });
    }

    /**
     * 类型转换
     *
     * @param s
     * @return
     */
    private String typeConversion(String type, String s) {
        switch (type) {
            case "String" -> {
                return String.format("%s.toString()", s);
            }
            case "Integer", "int" -> {
                return String.format("%s.toInt()", s);
            }
            case "Boolean", "boolean" -> {
                return String.format("%s.toBool()", s);
            }
            case "double", "Double" -> {
                return String.format("%s.toDouble()", s);
            }
            case "long", "Long" -> {
                return String.format("QString::number(%s.toDouble()).toLongLong()", s);
            }
            default -> {
                return s;
            }
        }
    }

    /**
     * 首字母大写
     *
     * @param s
     * @return
     */
    private String capitalizeTheFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s
     * @return
     */
    private String lowercaseFirstLetter(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    /**
     * 生成数据类型
     *
     * @return
     */
    public void generateTypeInfos() {

        StringBuilder head = new StringBuilder();
        head.append("add_executable(\nSiriusProQTSDK main.cpp \nUI/SiriusUI.h \n");
        for (String key : map.keySet()) {
            String upperCase = key.toUpperCase(Locale.ROOT);
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("""
                    //
                    // Created by 四川镜芯网络科技有限公司-代码自动生成器 on 2023/2/15.
                    //
                    #ifndef %s_H
                    #define %s_H
                                        
                    #include <QString>
                    #include <QVector>
                    """, upperCase, upperCase));

            Set<String> librarys = new HashSet<>();
            for (Info info : map.get(key)) {
                if (info.isObj()) {
                    librarys.add(info.getType());
                }
            }
            for (String library : librarys) {
                sb.append(String.format("#include \"%s.h\"", library)).append('\n');
            }

            sb.append(String.format("""
                    class %s
                    {
                    public:
                        explicit %s();
                        ~%s();
                        
                    """, key, key, key));

            for (Info info : map.get(key)) {
                sb.append(String.format("    %s get%s() const;",
                        info.isList() ? String.format("QVector<%s>", info.getType()) : info.getType(),
                        capitalizeTheFirstLetter(info.getName()))).append('\n');
                sb.append(String.format("    %s& set%s(%s%s %s);",
                                key,
                                capitalizeTheFirstLetter(info.getName()),
                                info.isObj() ? "const " : "",
                                info.isList() ? String.format("QVector<%s>", info.getType()) : info.getType() + (info.isObj() ? "&" : ""),
                                info.getName()
                        )
                ).append('\n');
            }
            sb.append('\n');
            sb.append("private:").append('\n');
            for (Info info : map.get(key)) {
                sb.append(String.format("""
                            /**
                            * %s
                            */
                        """, info.getJavaDoc()));
                sb.append(String.format("    %s %s;",
                        info.isList() ? String.format("QVector<%s>", info.getType()) : info.getType(),
                        info.getName())).append('\n');
            }
            sb.append("""
                    };
                                        
                    #endif // PINSMESSAGE_H
                    """);
            try {
                Files.writeString(Path.of(String.format("D:\\cppsrc\\pojo\\%s.h", key)), sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 生成 cpp
            sb = new StringBuilder();
            sb.append(String.format("""
                    //
                    // Created by 四川镜芯网络科技有限公司-代码自动生成器 on 2023/2/15.
                    //
                    #include "%s.h"
                                        
                    """, key));
            sb.append(String.format("""
                    %s::%s() {
                    }
                                        
                    %s::~%s() {
                    }
                    """, key, key, key, key));
            for (Info info : map.get(key)) {
                sb.append(String.format("""
                                %s &%s::set%s(%s%s %s) {
                                    this->%s = std::move(%s);
                                    return *this;
                                }
                                """, key, key,
                        capitalizeTheFirstLetter(info.getName()),
                        info.isObj() ? "const " : "",
                        info.isList() ? String.format("QVector<%s>", info.getType()) : info.getType() + (info.isObj() ? "&" : ""),
                        info.getName(),
                        info.getName(),
                        info.getName()));
                sb.append(String.format("""
                                %s %s::get%s() const {
                                    return this->%s;
                                }
                                """,
                        info.isList() ? String.format("QVector<%s>", info.getType()) : info.getType(),
                        key,
                        capitalizeTheFirstLetter(info.getName()),
                        info.getName()
                ));
                try {
                    Files.writeString(Path.of(String.format("D:\\cppsrc\\pojo\\%s.cpp", key)), sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                head.append(String.format("./pojo/%s.cpp \n", key));
                head.append(String.format("./pojo/%s.h \n", key));
            }
        }
        head.append(")");
        System.out.println(head);
    }


    /**
     * 取随机变量名
     *
     * @return
     */
    private String randomVariableName() {
        int length = 8;
        Random random = new Random();
        StringBuilder valSb = new StringBuilder();
        String charStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int charLength = charStr.length();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charLength);
            valSb.append(charStr.charAt(index));
        }
        return valSb.toString();

    }

    /**
     * 生成解析
     */
    public void generationAnalysis() {
        // parseJson2Object.h
        StringBuilder sb = new StringBuilder();
        sb.append("""
                //
                // Created by 四川镜芯网络科技有限公司-代码自动生成器 on 2023/2/15.
                //
                                
                #ifndef SIRIUSPROQTSDK_PARSEJSON2OBJECT_H
                #define SIRIUSPROQTSDK_PARSEJSON2OBJECT_H
                #include <QJsonDocument>
                #include <QJsonObject>
                #include <QJsonArray>
                """);
        for (String key : map.keySet()) {
            sb.append(String.format("#include \"../pojo/%s.h\"", key)).append('\n');
        }
        sb.append("""
                class ParseJson2Object {
                public:
                    """);
        for (String key : map.keySet()) {
            sb.append(String.format("    static %s parse%s(const QString& data);",
                    key,
                    key
            )).append('\n');
        }
        sb.append("""
                };
                                
                                
                #endif //SIRIUSPROQTSDK_PARSEJSON2OBJECT_H""");
        try {
            Files.writeString(Path.of("D:\\cppsrc\\utils\\ParseJson2Object.h"), sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // parseJson2Object.cpp
        sb = new StringBuilder();
        sb.append("""
                //
                // Created by 四川镜芯网络科技有限公司-代码自动生成器 on 2023/2/15.
                //
                #include "ParseJson2Object.h"
                                
                """);
        for (String key : map.keySet()) {
            sb.append(String.format("%s ParseJson2Object::parse%s(const QString& data) {",
                    key,
                    key
            )).append('\n');
            // =========
            String name = lowercaseFirstLetter(key);
            sb.append(String.format("""
                        QJsonDocument doc = QJsonDocument::fromJson(data.toUtf8());
                        QJsonObject obj = doc.object();
                        %s %s;
                        
                    """, key, name));
            for (Info info : map.get(key)) {
                if (info.isList()) {
                    // 数组
                    String variate = randomVariableName();  // 随机变量名
                    String array = randomVariableName();  // 随机变量名
                    sb.append(String.format("    QJsonArray %s = obj.value(\"%s\").toArray();", array, info.getName())).append('\n');
                    sb.append(String.format("    QVector<%s> %s;", info.getType(), variate)).append('\n');
                    sb.append(String.format("    for (auto && item : %s) {", array)).append('\n');
                    if (info.isObj()) {
                        // 对象
                        String temp = String.format("ParseJson2Object::parse%s(doc.toJson(QJsonDocument::Compact))", info.getSrcType());
                        sb.append("        doc.setObject(item.toObject());").append('\n');
                        sb.append(String.format("        %s.append(%s);", variate, typeConversion(info.getSrcType(), temp))).append('\n');
                    } else {
                        // 基础类型
                        String temp = "item";
                        sb.append(String.format("        %s.append(%s);", variate, typeConversion(info.getSrcType(), temp))).append('\n');
                    }
                    sb.append("    }").append('\n');
                    sb.append(String.format("    %s.set%s(%s);",
                            name,
                            capitalizeTheFirstLetter(info.getName()),
                            variate
                    )).append('\n');

                } else {
                    // 单个
                    if (info.isObj()) {
                        // 对象
                        sb
                                .append("    doc.setObject(")
                                .append(String.format("obj.value(\"%s\").toObject()", info.getName()))
                                .append(");").append('\n');
                        String temp = String.format("ParseJson2Object::parse%s(doc.toJson(QJsonDocument::Compact))", info.getSrcType());
                        sb.append(String.format("    %s.set%s(%s);",
                                name,
                                capitalizeTheFirstLetter(info.getName()),
                                temp
                        )).append('\n');
                    } else {
                        // 基础类型
                        // QString::number(obj.value("num").toDouble()).toLongLong()
                        String temp = String.format("obj.value(\"%s\")", info.getName());
                        sb.append(String.format("    %s.set%s(%s);",
                                name,
                                capitalizeTheFirstLetter(info.getName()),
                                typeConversion(info.getSrcType(), temp)
                        )).append('\n');
                    }
                }
            }

            // =========
            sb.append(String.format("    return %s;", name)).append('\n');
            sb.append("}").append('\n').append('\n');
        }

        try {
            Files.writeString(Path.of("D:\\cppsrc\\utils\\ParseJson2Object.cpp"), sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成构建
     */
    public void generationBuild() {
        // BuildObject2Json.h
        StringBuilder sb = new StringBuilder();
        sb.append("""
                //
                // Created by 四川镜芯网络科技有限公司-代码自动生成器 on 2023/2/15.
                //
                                
                #ifndef SIRIUSPROQTSDK_BUILDOBJECT2JSON_H
                #define SIRIUSPROQTSDK_BUILDOBJECT2JSON_H
                #include <QString>
                #include <QJsonDocument>
                #include <QJsonObject>
                #include <QJsonArray>
                """);
        for (String key : map.keySet()) {
            sb.append(String.format("#include \"../pojo/%s.h\"", key)).append('\n');
        }
        sb.append("""
                class BuildObject2Json {
                public:
                    """);
        for (String key : map.keySet()) {
            sb.append(String.format("    static QString build%s(const %s& data);",
                    key,
                    key
            )).append('\n');
        }
        sb.append("""
                };
                                
                                
                #endif //SIRIUSPROQTSDK_BUILDOBJECT2JSON_H""");
        try {
            Files.writeString(Path.of("D:\\cppsrc\\utils\\BuildObject2Json.h"), sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // BuildObject2Json.cpp
        sb = new StringBuilder();
        sb.append("""
                //
                // Created by 四川镜芯网络科技有限公司-代码自动生成器 on 2023/2/15.
                //
                #include "BuildObject2Json.h"
                                
                """);
        for (String key : map.keySet()) {
            sb.append(String.format("QString BuildObject2Json::build%s(const %s& data) {",
                    key,
                    key
            )).append('\n');
            // =========

            sb.append("""
                        QJsonDocument doc;
                        QJsonObject obj;
                        
                    """);
            for (Info info : map.get(key)) {
                if (info.isList()) {
                    // 数组
                    String array = randomVariableName();  // 随机变量名
                    sb.append(String.format("    QJsonArray %s;", array)).append('\n');
                    sb.append(String.format("    for (%s item : data.get%s()) {",
                            info.getType(),
                            capitalizeTheFirstLetter(info.getName())
                    )).append('\n');


                    if (info.isObj()) {
                        // 对象
                        String temp = String.format("BuildObject2Json::build%s(item)", info.getSrcType());
                        sb.append(String.format("        doc = QJsonDocument::fromJson(%s.toUtf8());", temp)).append('\n');
                        sb.append(String.format("        %s.append(doc.object());",
                                array
                        )).append('\n');
                    } else {
                        // 基础类型
                        sb.append(String.format("        %s.append(item);",
                                array
                        )).append('\n');
                    }
                    sb.append("    }").append('\n');
                    sb.append(String.format("    obj.insert(\"%s\", %s);",
                            info.getName(),
                            array
                    )).append('\n');
                } else {
                    // 单个
                    if (info.isObj()) {
                        // 对象
                        String temp = String.format("BuildObject2Json::build%s(data.get%s())", info.getSrcType(), capitalizeTheFirstLetter(info.getName()));
                        sb.append(String.format("    doc = QJsonDocument::fromJson(%s.toUtf8());", temp)).append('\n');
                        sb.append(String.format("    obj.insert(\"%s\",doc.object());",
                                info.getName()
                        )).append('\n');
                    } else {
                        // 基础类型
                        // QString::number(obj.value("num").toDouble()).toLongLong()
                        sb.append(String.format("    obj.insert(\"%s\",data.get%s());",
                                info.getName(),
                                capitalizeTheFirstLetter(info.getName())

                        )).append('\n');
                    }
                }
            }

            // =========
            sb.append("""
                        doc.setObject(obj);
                        return doc.toJson(QJsonDocument::Compact);
                    """).append('\n');
            sb.append("}").append('\n').append('\n');
        }

        try {
            Files.writeString(Path.of("D:\\cppsrc\\utils\\BuildObject2Json.cpp"), sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void generateAPIMethod() {
        for (String apiName : apiMap.keySet()) {
            StringBuilder sb = new StringBuilder();
            String upperCase = apiName.toUpperCase(Locale.ROOT);
            sb.append(String.format("""
                    //
                    // Created by 四川镜芯网络科技有限公司-Mr.01010011 on 2023/2/17.
                    //
                                        
                    #ifndef %s_API_H
                    #define %s_API_H
                                        
                    #include "../serialize/BuildObject2Json.h"
                    #include "../serialize/ParseJson2Object.h"
                    #include "../httpclient/HttpUtils.h"
                    #include <QJsonDocument>
                    #include <QJsonObject>
                    #include <QJsonArray>
                    #include <QString>
                    #include <QVector>
                    #include "SiriusApi.h"
                    """, upperCase, upperCase));
            Set<String> librarys = new HashSet<>();
            for (MethodInfo info : apiMap.get(apiName)) {
                if (info.getType().isObj()) {
                    librarys.add(info.getType().getType());
                }
                for (Info paramInfo : info.getParams()) {
                    if (paramInfo.isObj()) {
                        librarys.add(paramInfo.getType());
                    }
                }
            }
            for (String library : librarys) {
                sb.append(String.format("#include \"../pojo/%s.h\"", library)).append('\n');
            }
            sb.append('\n').append('\n');
            sb.append(String.format("""
                    class %s {
                    public:
                    """, apiName));
            for (MethodInfo methodInfo : apiMap.get(apiName)) {
                sb.append(String.format("""
                             /**
                             * %s
                             * @param result 引用，消息对象
                             * @param source 引用，原始数据
                        """, methodInfo.getFormatName()));
                for (Info info : methodInfo.getParams()) {
                    sb.append(String.format("     * @param %s %s", info.getName(), info.getJavaDoc())).append('\n');
                }
                sb.append("     * @return").append('\n');
                sb.append("     */").append('\n');
                sb.append(String.format("    static int %s(", methodInfo.getName())).append('\n');
                if (methodInfo.getType().isList()) {
                    sb.append("            QVector<").append(methodInfo.getType().getType()).append("> &").append("result").append(',').append('\n');
                } else {
                    sb.append("            ").append(methodInfo.getType().getType()).append(" &").append("result").append(',').append('\n');
                }
                sb.append("            QString &source");
                for (Info info : methodInfo.getParams()) {
                    sb.append(String.format(",\n            %s %s", info.getType(), info.getName()));
                }
                sb.append(");\n");
                sb.append('\n');
            }
            sb.append("};").append('\n');

            sb.append(String.format("""
                                                    
                    #endif //%s_API_H""", upperCase));


            try {
                Files.writeString(Path.of(String.format("D:\\cppsrc\\api\\%s.h", apiName)), sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 生成cpp
        for (String apiName : apiMap.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("""
                    //
                    // Created by 四川镜芯网络科技有限公司-Mr.01010011 on 2023/2/17.
                    //
                                        
                    #include <QHttpMultiPart>
                    #include "../%s.h"
                    """, apiName));

            for (MethodInfo methodInfo : apiMap.get(apiName)) {
                sb.append(String.format("""
                        /**
                        * %s
                        * @param result 引用，消息对象
                        * @param source 引用，原始数据
                        """, methodInfo.getFormatName()));
                for (Info info : methodInfo.getParams()) {
                    sb.append(String.format("* @param %s %s", info.getName(), info.getJavaDoc())).append('\n');
                }
                sb.append("* @return").append('\n');
                sb.append("*/").append('\n');
                sb.append(String.format("int %s::%s(", apiName, methodInfo.getName())).append('\n');
                if (methodInfo.getType().isList()) {
                    sb.append("    QVector<").append(methodInfo.getType().getType()).append("> &").append("result").append(',').append('\n');
                } else {
                    sb.append("    ").append(methodInfo.getType().getType()).append(" &").append("result").append(',').append('\n');
                }
                sb.append("    QString &source");
                for (Info info : methodInfo.getParams()) {
                    if (info.isList()) {
                        sb.append(String.format(",\n    QVector<%s> %s", info.getType(), info.getName()));
                    } else {
                        sb.append(String.format(",\n    %s %s", info.getType(), info.getName()));
                    }

                }
                sb.append(")\n{\n");
                // ===
                sb.append(String.format("""
                            // 创建JSON文档流对象
                            QJsonDocument doc;
                            // 创建Json结构对象
                            QJsonObject obj;
                                                
                            obj.insert("api", "%s");
                            obj.insert("method", "%s");
                                                
                            // 创建请求参数结构对象
                            QJsonObject param;
                        """, apiName, methodInfo.getName()));

                for (Info info : methodInfo.getParams()) {
                    if (info.isList()) {
                        // 数组
                        String array = randomVariableName();  // 随机变量名
                        sb.append(String.format("    QVector<%s> %s;", info.getType(), array));
                        sb.append(String.format("    for (%s item : %s) {",
                                        info.getType(),
                                        info.getName()
                                )
                        ).append('\n');

                        if (info.isObj()) {
                            // 对象
                            sb.append(String.format("        doc = QJsonDocument::fromJson(BuildObject2Json::build%s(item).toUtf8());",
                                    info.getSrcType()
                            )).append('\n');
                            sb.append(String.format("        %s.append(doc.object());", array));
                        } else {
                            // 基础类型
                            sb.append(String.format("        %s.append(item);", array)).append('\n');
                        }
                        sb.append("    }").append('\n');
                        sb.append(String.format("    param.insert(\"%s\", %s);", info.getName(), array)).append('\n');
                    } else {
                        // 单个
                        if (info.isObj()) {
                            // 对象
                            sb.append(String.format("    doc = QJsonDocument::fromJson(BuildObject2Json::build%s(%s).toUtf8());",
                                    info.getSrcType(),
                                    info.getName()
                            )).append('\n');
                            sb.append(String.format("    param.insert(\"%s\", %s);", info.getName(), "doc.object()")).append('\n');
                        } else {
                            // 基础类型
                            sb.append(String.format("    param.insert(\"%s\", %s);", info.getName(), info.getName())).append('\n');
                        }
                    }
                }
                sb.append("    // 设置请求参数对象").append('\n');
                sb.append("    obj.insert(\"param\", param);").append('\n');
                sb.append("    doc.setObject(obj);").append('\n');
                sb.append("""
                            // 创建Http请求对象
                            QNetworkRequest request;
                            request.setUrl(QUrl(SiriusApi::url + "api/control"));
                            // 设置请求头
                            request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");
                            // 发送请求，并获取Http响应码
                            int resCode = HttpUtils::postRequest(request, source, doc.toJson(QJsonDocument::Compact));
                        """);
                if (methodInfo.getType().isList()) {
                    // 数组
                    sb.append(String.format("""
                                QVector<%s> vector;
                                QJsonArray array = QJsonDocument::fromJson(source.toUtf8()).object().value("data").toArray();
                            """, methodInfo.getType().getType()));
                    sb.append("    for (const auto &item: array) {").append('\n');
                    if (methodInfo.getType().isObj()) {
                        // 对象
                        sb.append("        doc.setObject(item.toObject());").append('\n');
                        sb.append(String.format("        vector.append(ParseJson2Object::parse%s(doc.toJson()));", methodInfo.getType().getType())).append('\n');
                    } else {
                        // 单个
                        sb.append(String.format("        vector.append(%s);", typeConversion(methodInfo.getType().getSrcType(), "item"))).append('\n');
                    }
                    sb.append("""
                                }
                                result = vector;
                            """);
                } else {
                    // 单个
                    if (methodInfo.getType().isObj()) {
                        sb.append(String.format("    result = ParseJson2Object::parse%s(QJsonDocument::fromJson(source.toUtf8()).object().value(\"data\").toString());",
                                methodInfo.getType().getType()
                        )).append('\n');
                    } else {
                        String json = "QJsonDocument::fromJson(source.toUtf8()).object().value(\"data\")";
                        sb.append(String.format("    result = %s;", typeConversion(methodInfo.getType().getSrcType(), json))).append('\n');
                    }
                }

                sb.append("    return resCode;").append('\n');
                // ===
                sb.append("}").append('\n');
                sb.append('\n');
            }

            try {
                Files.writeString(Path.of(String.format("D:\\cppsrc\\api\\impl\\%s.cpp", apiName)), sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("==========================================");
        StringBuilder sb = new StringBuilder();
        for (String apiName : apiMap.keySet()) {
            sb.append(String.format("siriusapi/%s.h", apiName)).append('\n');
            sb.append(String.format("siriusapi/impl/%s.cpp", apiName)).append('\n');
        }
    }

}
