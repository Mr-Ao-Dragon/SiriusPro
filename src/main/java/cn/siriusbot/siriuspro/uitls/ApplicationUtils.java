package cn.siriusbot.siriuspro.uitls;

import cn.siriusbot.siriuspro.config.Constant;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

/**
 * 应用工具类
 */
public class ApplicationUtils {

    /**
     * 应用目录
     */
    public static String appsPath = new File(new File("").getAbsolutePath() + Constant.SEPARATOR() + "apps").getAbsolutePath();

    /**
     * 配置目录
     */
    public static String confPath = new File(new File("").getAbsolutePath() + Constant.SEPARATOR() + "conf").getAbsolutePath();

    /**
     * 图片缓存目录
     */

    public static String imgCachePath = new File(new File("").getAbsolutePath() + Constant.SEPARATOR() + "cache").getAbsolutePath();

    /**
     * 应用目录是否存在
     *
     * @return
     */
    public static Boolean appsPathExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    /**
     * 创建应用目录
     */
    public static Boolean createAppsPath(String fileName) {
        return new File(fileName).mkdir();
    }

    /**
     * 遍历应用目录
     */
    public void showApp() {
    }

    public static void initAppPath() {
        if (!appsPathExist(appsPath)) {
            createAppsPath(appsPath);
        }
        if (!appsPathExist(confPath)) {
            createAppsPath(confPath);
        }
        if (!appsPathExist(imgCachePath)) {
            createAppsPath(imgCachePath);
        }

    }

    private static void sqlInit(String ip, String port, String databaseName, String username, String password) throws Exception {
        Scanner scanner = new Scanner(System.in);
        File file = new File(confPath + Constant.SEPARATOR() + "database.properties");
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        Connection connection;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s", ip, port), username, password);
        } catch (Exception e) {
            e.printStackTrace();
            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("连接数据库失败!", 31, 1));
            file.delete();
            System.in.read();
            System.exit(0);
            return;
        }
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("CREATE DATABASE `%s` CHARACTER SET utf8 COLLATE utf8_general_ci", databaseName));
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("创建数据失败!", 31, 1));
            file.delete();
            System.in.read();
            System.exit(0);
            return;
        }
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("创建数据库成功，正在导入表数据...", 32, 1));
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", ip, port, databaseName), username, password);
            ScriptRunner runner = new ScriptRunner(connection);
            Resources.setCharset(StandardCharsets.UTF_8);
            runner.setLogWriter(null);
            Resource resource = new ClassPathResource("sql/sql.sql");
            InputStream inputStream = resource.getInputStream();
            runner.runScript(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("导入表数据失败!", 31, 1));
            file.delete();
            System.in.read();
            System.exit(0);
            return;
        }

        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("创建数据库成功，请设置管理员账号密码!", 32, 1));
        String adminUser = "";
        String adminPasswd = "";
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("请输入管理员账号，例如:admin", 32, 1));
        adminUser = scanner.next();
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("请输入管理员密码，例如:admin", 32, 1));
        adminPasswd = scanner.next();
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", ip, port, databaseName), username, password);
            adminUser = adminUser.trim();
            adminPasswd = adminPasswd.trim();
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO `admin` (`account`, `passwd`, `power`) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, adminUser);
            ps.setString(2, adminPasswd);
            ps.setInt(3, 0);
            ps.execute();
            ps.close();
            statement.close();
            connection.close();
            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("添加管理员成功！", 32, 1));
        } catch (Exception e) {
            e.printStackTrace();
            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("添加管理员账号失败，请手动添加!", 31, 1));
            System.in.read();
            System.exit(0);
            return;
        }
    }


    private static void guidance() {
        String ip = "";
        String port = "";
        String databaseName = "";
        String username = "";
        String password = "";
        Scanner scanner = new Scanner(System.in);
        String next = "N";

        while (!next.equals("Y") && !next.equals("y")) {
            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("请输入数据库ip，例如:127.0.0.1", 32, 1));
            try {
                ip = scanner.next();
            } catch (Exception e) {
                continue;
            }

            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("请输入端口，例如:3306", 32, 1));
            port = scanner.next();
            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("请输入数据库名，例如:siriuspro", 32, 1));
            databaseName = scanner.next();
            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("请输入数据库用户名，例如:root", 32, 1));
            username = scanner.next();
            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("请输入数据库用户密码，例如:root", 32, 1));
            password = scanner.next();

            ip = ip.trim();
            port = port.trim();
            databaseName = databaseName.trim();
            username = username.trim();
            password = password.trim();

            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString(
                    String.format("""
                            请确认您的连接信息如下：
                            数据库地址：|%s:%s|
                            数据库名：|%s|
                            用户名：|%s|
                            密码：|%s|
                            """, ip, port, databaseName, username, password)
                    , 31, 1));
            AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("是否继续(Y/N)：", 32, 1));
            next = scanner.next();
        }
        try {
            Files.writeString(Path.of(confPath + Constant.SEPARATOR() + "database.properties"), String.format("""
                    jdbc.driver=com.mysql.cj.jdbc.Driver
                    jdbc.url=jdbc:mysql://%s:%s/%s
                    jdbc.username=%s
                    jdbc.password=%s
                    """, ip, port, databaseName, username, password));
            sqlInit(ip, port, databaseName, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 数据库引导
     */
    public static void databaseInit() {
        File file = new File(confPath + Constant.SEPARATOR() + "database.properties");
        if (!file.exists()) {
            // 引导数据库
            guidance();

        }
    }
}
