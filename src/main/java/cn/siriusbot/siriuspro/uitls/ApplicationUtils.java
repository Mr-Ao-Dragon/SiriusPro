package cn.siriusbot.siriuspro.uitls;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 应用工具类
 */
public class ApplicationUtils {

    /**
     * 应用目录
     */
    public static String appsPath = new File(new File("").getAbsolutePath()+"\\apps").getAbsolutePath();

    /**
     * 配置目录
     */
    public static String confPath = new File(new File("").getAbsolutePath()+"\\conf").getAbsolutePath();

    /**
     * 图片缓存目录
     */

    public static String imgCachePath = new File(new File("").getAbsolutePath()+"\\imgCache").getAbsolutePath();
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
        if(!appsPathExist(imgCachePath)){
            createAppsPath(imgCachePath);
        }
        File file = new File(confPath + "\\database.properties");
        if (!file.exists()){
            try {
                Files.writeString(Path.of(confPath + "\\database.properties"), """
                        jdbc.driver=com.mysql.cj.jdbc.Driver
                        jdbc.url=jdbc:mysql://localhost:3306/siriuspro
                        jdbc.username=root
                        jdbc.password=root
                        """);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
