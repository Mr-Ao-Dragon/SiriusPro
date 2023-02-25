package cn.siriusbot.siriuspro.config;

import cn.siriusbot.siriuspro.uitls.SiriusLoggerUtil;
import lombok.SneakyThrows;
import org.fusesource.jansi.AnsiConsole;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

public class SiriusUtils {

    /**
     * 用于读取properties文件
     */
    public static Properties ppt = new Properties();

    public static String agreementFilePath = new File(new File("").getAbsolutePath()+"/eula").getAbsolutePath();

    /**
     * 开源协议验证是否通过
     */
    @SneakyThrows
    public static boolean authAgreement(){

        if(agreementFileExist()){
            ppt.load(new FileInputStream(agreementFilePath));
            return Boolean.parseBoolean(ppt.getProperty("eula"));
        }else{
            printAgreement();
            new File(agreementFilePath).createNewFile();
            FileWriter writer = new FileWriter("eula");
            writer.write("eula=false");
            writer.flush();
            writer.close();
            System.exit(0);
            return false;
        }
    }

    /**
     * 协议文件是否存在
     * @return 验证结果
     */
    public static boolean agreementFileExist(){
        return new File(agreementFilePath).exists();
    }

    public static void printAgreement(){

        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("SiriusPro是一款高度自定义的QQ频道机器人框架，由四川镜芯网络科技有限公司独立完成开发，如要使用，请同意以下开源协议(AGPL3.0)。",32,1));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("SiriusPro is a highly customized QQ channel robot framework developed independently by Sichuan Jingxin Network Technology Co., Ltd. If you want to use it, please agree to the following open source agreement (AGPL3.0).",32,1));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("\n这个最强的copyleft许可证的权限取决于在同一许可证下提供许可作品和修改的完整源代码，包括使用许可作品的较大作品。",31,1));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("Permissions of this strongest copyleft license are conditioned on making available complete source code of licensed works and modifications, which include larger works using a licensed work, under the same license.",31,3));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("\n版权和许可声明必须保留。",31,1));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("Copyright and license notices must be preserved.",31,3));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("\n贡献者明确授予专利权。",31,1));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("Contributors provide an express grant of patent rights. ",31,3));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("\n当修改版本用于通过网络提供服务时，必须提供修改版本的完整源代码。",31,1));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.",31,3));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("\n禁止用于一切商业化行为。",31,1));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("Prohibited for all commercial activities.",31,3));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("\n不得未经授权的情况下私自(包括但不限于,个人,个体,企业)运营任何相关社区。",31,1));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("It is not allowed to operate any relevant community without authorization (including but not limited to individuals, individuals and enterprises).",31,3));

        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("\n对框架本身做的一切修改,强制性要求开源。",31,1));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("All modifications made to the framework itself are mandatory to open source.",31,3));

        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("\n如有任何疑问，请加入QQ群进行提问376957298",31,1));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("If you have any questions, please join the QQ group to ask 376957298",31,3));

        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("\n修改目录下eula.txt文件的eula，为true时代表您同意此开源协议( GNU Affero General Public License v3.0)，保存重新运行后方可继续使用",34,4));
        AnsiConsole.out().println(SiriusLoggerUtil.getFormatLogString("Modify the eula of the eula file in the directory. If it is true, it means that you agree to this open source agreement (GNU Affero General Public License v3.0). Save it and re run it before continuing to use it",34,3));
    }
}
