package cn.siriusbot.siriuspro.test;

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
     * 生成数据类型
     *
     * @return
     */
    public String generateTypeInfos() {
        StringBuilder sb = new StringBuilder();
        sb.append("""
                //
                // Created by 四川镜芯网络科技有限公司-Mr.01010011 on 2023/2/14.
                //
                                
                #ifndef SIRIUSPROQTSDK_STRUCTLIST_H
                #define SIRIUSPROQTSDK_STRUCTLIST_H
                                
                #include <QString>
                #include <QVector>
                                
                """);
        for (String key : map.keySet()) {
            sb.append(String.format("struct %s;", key)).append('\n');
        }
        for (String key : map.keySet()) {
            sb.append("""
                    struct Message {
                    public:
                        Message();
                    """).append('\n');
            for (Info info : map.get(key)) {
                sb.append(String.format("""
                                                                
                            /**
                             * %s
                             */
                            %s %s;
                        """, info.getJavaDoc(), info.getType() + (info.isObj() ? "&" : ""), info.getName()));
            }
            sb.append("}").append('\n').append('\n');
        }
        sb.append("""
                                
                #endif //SIRIUSPROQTSDK_STRUCTLIST_H""");
        return sb.toString();
    }

}
