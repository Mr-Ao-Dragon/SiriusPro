package cn.siriusbot.siriuspro.application;

import java.io.File;

/**
 * 应用工具类
 */
public class ApplicationUtils {

    public static String appsPath = new File(new File("").getAbsolutePath()+"\\apps").getAbsolutePath();
    /**
     * 应用目录是否存在
     * @return
     */
    public static Boolean appsPathExist(){
        File file = new File(appsPath);
        return file.exists();
    }

    /**
     * 创建应用目录
     */
    public static Boolean createAppsPath(){
        return new File(appsPath).mkdir();
    }
    /**
     * 遍历应用目录
     */
    public void showApp(){
    }
    public static void initAppPath(){
        System.out.println(appsPath);
        if(!appsPathExist()){
            createAppsPath();
        }
    }
}
