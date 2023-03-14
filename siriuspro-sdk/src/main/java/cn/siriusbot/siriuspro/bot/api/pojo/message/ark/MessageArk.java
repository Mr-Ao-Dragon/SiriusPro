package cn.siriusbot.siriuspro.bot.api.pojo.message.ark;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
/**
 * ark消息对象
 */
public class MessageArk {
    /**
     * ark模板ID
     */
    private Integer template_id;
    /**
     * kv值列表
     */
    private List<MessageArkKv> kv;


    /**
     * 创建发送文字+链接Ark消息对象
     *
     * @param description        描述
     * @param prompt             提示文本
     * @param textAndLinkObjects 文字链接对象列表
     * @return ark消息对象
     */
    public static MessageArk createLinkAndTextArkTemplate(String description, String prompt, List<TextAndLinkObject> textAndLinkObjects) {
        MessageArk ark = new MessageArk();
        ark.setTemplate_id(23);
        List<MessageArkKv> arkKvList = new ArrayList<>();
        arkKvList.add(new MessageArkKv().setKey("#DESC#").setValue(description));
        arkKvList.add(new MessageArkKv().setKey("#PROMPT#").setValue(prompt));
        List<MessageArkObj> arkObjs = new ArrayList<>();
        JSONObject json = new JSONObject();
        for (int i = 0; i < textAndLinkObjects.size(); i++) {
            MessageArkObj arkObj = new MessageArkObj();
            List<MessageArkObjKv> arkObjKvs = new ArrayList<>();
            arkObjKvs.add(
                    new MessageArkObjKv()
                            .setKey("desc")
                            .setValue(textAndLinkObjects.get(i).desc)
            );
            if (textAndLinkObjects.get(i).link != null || textAndLinkObjects.get(i).link != "")
                arkObjKvs.add(new MessageArkObjKv().setKey("link").setValue(textAndLinkObjects.get(i).link));

            arkObj.setObj_kv(arkObjKvs);
            arkObjs.add(arkObj);
        }
        arkKvList.add(new MessageArkKv().setKey("#LIST#").setObj(arkObjs));
        ark.setKv(arkKvList);
        return ark;
    }

    /**
     * 创建文字+缩略图Ark消息对象
     *
     * @param description 描述
     * @param prompt      提示文本
     * @param title       标题
     * @param metaDesc    详情描述
     * @param image_url   图片链接
     * @param link        跳转链接
     * @param subtitle    来源
     * @return 返回ark消息对象
     */
    public static MessageArk createTextThumbnailArkTemplate(String description, String prompt, String title, String metaDesc, String image_url, String link, String subtitle) {
        MessageArk ark = new MessageArk();
        ark.setTemplate_id(24);
        List<MessageArkKv> arkKvList = new ArrayList<>();
        arkKvList.add(new MessageArkKv().setKey("#DESC#").setValue(description));

        arkKvList.add(new MessageArkKv().setKey("#PROMPT#").setValue(prompt));

        arkKvList.add(new MessageArkKv().setKey("#TITLE#").setValue(title));

        arkKvList.add(new MessageArkKv().setKey("#METADESC#").setValue(metaDesc));

        arkKvList.add(new MessageArkKv().setKey("#IMG#").setValue(image_url));

        arkKvList.add(new MessageArkKv().setKey("#LINK#").setValue(link));

        arkKvList.add(new MessageArkKv().setKey("#SUBTITLE#").setValue(subtitle));
        ark.setKv(arkKvList);
        return ark;
    }

    /**
     * 创建大图Ark消息对象
     * @param prompt 提示消息
     * @param meta_title 标题
     * @param meta_subtitle 子标题
     * @param meta_cover_url 大图链接
     * @param meta_url 跳转链接
     * @return
     */
    public static MessageArk createBigImageArkTemplate(String prompt,String meta_title,String meta_subtitle,String meta_cover_url,String meta_url){
        MessageArk ark = new MessageArk();
        ark.setTemplate_id(37);
        List<MessageArkKv> kvList = new ArrayList<>();

        kvList.add(new MessageArkKv().setKey("#PROMPT#").setValue(prompt));

        kvList.add(new MessageArkKv().setKey("#METATITLE#").setValue(meta_title));

        kvList.add(new MessageArkKv().setKey("#METASUBTITLE#").setValue(meta_subtitle));

        kvList.add(new MessageArkKv().setKey("#METACOVER#").setValue(meta_cover_url));

        kvList.add(new MessageArkKv().setKey("#METAURL#").setValue(prompt));

        ark.setKv(kvList);
        return ark;
    }
}
