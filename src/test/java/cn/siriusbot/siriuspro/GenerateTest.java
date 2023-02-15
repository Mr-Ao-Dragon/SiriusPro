package cn.siriusbot.siriuspro;

import cn.siriusbot.siriuspro.test.QTLanguageGenerate;
import org.junit.jupiter.api.Test;

public class GenerateTest {

    @Test
    void QT(){
        QTLanguageGenerate qtLanguageGenerate = new QTLanguageGenerate();
        System.out.println(qtLanguageGenerate.generateTypeInfos()); // 生成结构体
    }
}
