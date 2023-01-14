package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.uitls.ApplicationUtils;
import cn.siriusbot.siriuspro.bot.api.MessageApi;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageMarkdown;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageReference;
import cn.siriusbot.siriuspro.bot.api.pojo.message.ark.MessageArk;
import cn.siriusbot.siriuspro.bot.api.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.bot.api.pojo.message.requestPack.RequestCustomKeyboard;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/api/message")
public class MessageApiController {


    @Autowired
    MessageApi messageApi;

    /**
     * 用于向 channel_id 指定的子频道发送消息。
     * 要求机器人在该子频道具有发送消息的权限。
     * 主动消息在频道主或管理设置了情况下，按设置的数量进行限频。在未设置的情况遵循如下限制:
     * 主动推送消息，默认每天往每个子频道可推送的消息数是 20 条，超过会被限制。
     * 主动推送消息在每个频道中，每天可以往 2 个子频道推送消息。超过后会被限制。
     * 不论主动消息还是被动消息，在一个子频道中，每 1s 只能发送 5 条消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 发送消息接口要求机器人接口需要连接到 websocket 上保持在线状态
     * 有关主动消息审核，可以通过 Intents 中审核事件 MESSAGE_AUDIT 返回 MessageAudited 对象获取结果。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id 传入机器人ID
     * @param json   请求体对象
     * @return 消息对象
     */
    @PostMapping("/sendMessage/{bot_id}")
    public R sendMessage(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String channel_id = json.getString("channel_id");
            String content = json.getString("content");
            String image_url = json.getString("image_url");
            String msg_id = json.getString("msg_id");
            String event_id = json.getString("event_id");
            return new R().setData(messageApi.sendMessage(bot_id, channel_id, content, image_url, msg_id, event_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 获取指定子频道的指定消息详情
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 返回消息对象
     */
    @GetMapping("/get-message-info/{bot_id}/{channel_id}/{message_id}")
    public R getMessageById(@PathVariable String bot_id, @PathVariable String channel_id, @PathVariable String message_id) {
        try {
            return new R().setData(messageApi.getMessageById(bot_id, channel_id, message_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 发送引用消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param json       请求体对象
     * @return 返回消息对象
     */
    @PostMapping("/sendReference/{bot_id}/{channel_id}")
    public R sendReferenceMessage(@PathVariable String bot_id, @PathVariable String channel_id, @RequestBody JSONObject json) {
        try {
            String content = json.getString("content");
            MessageReference reference = json.getObject("reference", MessageReference.class);
            return new R().setData(messageApi.sendReferenceMessage(bot_id, channel_id, content, reference));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 发送markdown消息(富文本)
     * 要求操作人在该子频道具有发送消息和对应 Markdown 模版 的权限。
     * 调用前开发者需要先在“QQ开放平台-机器人-发布设置-消息模板”入口为对应机器人创建申请Markdown消息模板，得到模板 id ，在请求时填在对应的 markdown.template_id 上。
     * 模板参数暂不支持数组。
     * 消息体中所包含的URL需要报备并通过验证，方可使用。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param json       请求体对象
     * @return 返回消息对象
     */
    @PostMapping("/sendMarkdown/{bot_id}/{channel_id}")
    public R sendMarkdownMessage(@PathVariable String bot_id, @PathVariable String channel_id, @RequestBody JSONObject json) {
        try {
            String msg_id = json.getString("msg_id");
            String event_id = json.getString("event_id");
            MessageMarkdown markdown = json.getObject("markdown", MessageMarkdown.class);
            return new R().setData(messageApi.sendMarkdownMessage(bot_id, channel_id, msg_id, event_id, markdown));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 用于撤回子频道 channel_id 下的消息 message_id。
     * 管理员可以撤回普通成员的消息。
     * 频道主可以撤回所有人的消息。
     * !!注意!!
     * 公域机器人暂不支持申请，仅私域机器人可用，选择私域机器人后默认开通。
     * 注意: 开通后需要先将机器人从频道移除，然后重新添加，方可生效
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param hidetip    是否隐藏删除消息后的小灰条
     * @return 撤回结果
     */

    @DeleteMapping("/deleteMessage/{bot_id}/{channel_id}")
    public R deleteMessageById(@PathVariable String bot_id, @PathVariable String channel_id, @RequestParam String message_id, @RequestParam boolean hidetip) {
        try {
            return new R().setData(messageApi.deleteMessageById(bot_id, channel_id, message_id, hidetip));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");

        }
    }

    /**
     * 通过指定 ark 字段发送模板消息。
     * 要求操作人在该子频道具有发送消息和 对应ARK 模板 的权限。
     * 调用前需要先申请消息模板，这一步会得到一个模板 id，在请求时填在 ark.template_id 上。
     * 发送成功之后，会触发一个创建消息的事件。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @return 消息对象
     */
    @PostMapping("/sendArk/{bot_id}/{channel_id}")
    public R sendArkMessage(@PathVariable String bot_id, @PathVariable String channel_id, @RequestBody JSONObject json) {
        try {
            MessageArk ark = json.getObject("ark", MessageArk.class);
            String msg_id = json.getString("msg_id");
            String event_id = json.getString("event_id");
            return new R().setData(messageApi.sendArkMessage(bot_id, channel_id, ark, msg_id, event_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 发送embed模板消息
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param json       请求体对象
     * @return 消息对象
     */
    @PostMapping("/sendEmbed/{bot_id}/{channel_id}")
    public R sendEmbedMessage(@PathVariable String bot_id, @PathVariable String channel_id, @RequestBody JSONObject json) {
        try {
            MessageEmbed embed = json.getObject("embed", MessageEmbed.class);
            String msg_id = json.getString("msg_id");
            String event_id = json.getString("event_id");
            return new R().setData(messageApi.sendEmbedMessage(bot_id, channel_id, embed, msg_id, event_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 发送图文消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param content    消息内容
     * @param file       图片数据
     * @param msg_id     消息ID
     * @param event_id   事件ID
     * @return 消息对象
     */
    @PostMapping("/send-image-text/{bot_id}/{channel_id}")
    public R sendImageAndTextMessage(@PathVariable String bot_id, @PathVariable String channel_id, @Nullable @RequestParam MultipartFile file, @Nullable @RequestParam String content, @Nullable @RequestParam String msg_id, @Nullable @RequestParam String event_id) {
        try {
            String imgPath = null;
            if (file != null) {
                imgPath = ApplicationUtils.imgCachePath + "/" + UUID.randomUUID().toString() + ".png";
                file.transferTo(new File(imgPath));
            }
            return new R().setData(messageApi.sendImageAndTextMessage(bot_id, channel_id, content, imgPath, msg_id, event_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            e.printStackTrace();
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 发送自定义按钮模板对象
     *
     * @param bot_id                传入机器人ID
     * @param channel_id            子频道ID
     * @param requestCustomKeyboard 自定义按钮请求对象
     * @return 返回消息对象
     */
    @PostMapping("/send-custom-inline-keyword/{bot_id}/{channel_id}")
    public R sendCustomInLineKeyword(@PathVariable String bot_id, @PathVariable String channel_id, @RequestBody RequestCustomKeyboard requestCustomKeyboard) {
        try {
            return new R().setData(messageApi.sendCustomInLineKeyword(bot_id, channel_id, requestCustomKeyboard));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

}
