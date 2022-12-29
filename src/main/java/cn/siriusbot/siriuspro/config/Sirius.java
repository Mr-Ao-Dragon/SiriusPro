package cn.siriusbot.siriuspro.config;

import cn.siriusbot.siriuspro.application.ApplicationUtils;
import org.fusesource.jansi.AnsiConsole;

public class Sirius {

    /**
     * 启动时
     */
    public static void Start(){

    }

    /**
     * 初始化函数
     */
    public static void SiriusInit(){
        AnsiConsole.systemInstall();
        ApplicationUtils.initAppPath();
        if(!SiriusUtils.authAgreement()){
            SiriusUtils.printAgreement();
            System.exit(0);
        }

    }
}
