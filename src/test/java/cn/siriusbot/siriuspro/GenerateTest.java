package cn.siriusbot.siriuspro;

import cn.siriusbot.siriuspro.test.QTLanguageGenerate;
import org.junit.jupiter.api.Test;

public class GenerateTest {


    void QT(){
        QTLanguageGenerate qtLanguageGenerate = new QTLanguageGenerate();
        qtLanguageGenerate.generateTypeInfos(); // 生成结构体
        qtLanguageGenerate.generationAnalysis(); // 生成工具类
        qtLanguageGenerate.generationBuild(); // 生成工具类
    }
}
