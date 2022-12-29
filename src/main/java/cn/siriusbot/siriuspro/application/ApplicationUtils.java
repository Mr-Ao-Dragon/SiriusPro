package cn.siriusbot.siriuspro.application;

import java.io.File;

/**
 * 应用工具类
 */
public class ApplicationUtils {

    public static String appsPath = new File(new File("").getAbsolutePath()+"\\apps").getAbsolutePath();
    public static String confPath = new File(new File("").getAbsolutePath()+"\\conf").getAbsolutePath();
    /**
     * 应用目录是否存在
     * @return
     */
    public static Boolean appsPathExist(String fileName){
        File file = new File(fileName);
        return file.exists();
    }

    /**
     * 创建应用目录
     */
    public static Boolean createAppsPath(String fileName){
        return new File(fileName).mkdir();
    }
    /**
     * 遍历应用目录
     */
    public void showApp(){
    }
    public static void initAppPath(){
        if(!appsPathExist(appsPath)){
            createAppsPath(appsPath);
        }
        if(!appsPathExist(confPath)){
            createAppsPath(confPath);
        }
    }
}
