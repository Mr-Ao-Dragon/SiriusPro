package cn.siriusbot.siriuspro.config;

public interface Constant {

    /**
     * session-管理员对象
     */
    String SESSION_ADMIN = "SESSION_ADMIN";

    /**
     * 分隔符
     * @return
     */
    static String SEPARATOR() {
        String os = System.getProperty("os.name");
        //Windows操作系统
        if (os != null && os.toLowerCase().startsWith("windows")) {
            return "\\";
        } else if (os != null && os.toLowerCase().startsWith("linux")) {//Linux操作系统
            return "/";
        } else { //其它操作系统
            return "/";
        }
    }
}
