package cn.siriusbot.siriuspro.web.controller.api;

import cn.siriusbot.siriuspro.bot.api.DMS_Api;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageMarkdown;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageReference;
import cn.siriusbot.siriuspro.bot.api.pojo.message.ark.MessageArk;
import cn.siriusbot.siriuspro.bot.api.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.uitls.ApplicationUtils;
import cn.siriusbot.siriuspro.web.R.R;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 私信Api
 */
@RestController
@RequestMapping("/api/dms")
public class DirectApiController {

    @Autowired
    DMS_Api dmsApi;

    /**
     * 创建私信会话
     * 机器人和用户存在共同频道才能创建私信会话。
     * 创建成功后，返回创建成功的频道 id ，子频道 id 和创建时间。
     *
     * @param bot_id 传入机器人ID
     * @param json   请求体
     * @return 私信会话对象
     */
    @PostMapping("/create-dms/{bot_id}")
    public R createDMS(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String recipient_id = json.getString("recipient_id");
            String source_guild_id = json.getString("source_guild_id");
            return new R().setData(dmsApi.createDMS(bot_id, recipient_id, source_guild_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 发送普通私信消息
     * 用于向 guild_id 指定的私信会话发送普通私信消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 私信的 guild_id 在创建私信会话时以及私信消息事件中获取。
     * 私信场景下，每个机器人每天可以对一个用户发 2 条主动消息。
     * 私信场景下，每个机器人每天累计可以发 200 条主动消息。
     * 私信场景下，被动消息没有条数限制
     * 传入msg_id或event_id其一，此条消息视为被动消息
     *
     * @param bot_id 传入机器人ID
     * @return 消息对象
     */
    @PostMapping("/sendMessage/{bot_id}")
    public R sendMessage(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String guild_id = json.getString("guild_id");
            String content = json.getString("content");
            String image_url = json.getString("image_url");
            String msg_id = json.getString("msg_id");
            String event_id = json.getString("event_id");
            return new R().setData(dmsApi.sendMessage(bot_id, guild_id, content, image_url, msg_id, event_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 发送私信引用消息
     * 用于向 guild_id 指定的私信会话发送引用私信消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 私信的 guild_id 在创建私信会话时以及私信消息事件中获取。
     * 私信场景下，每个机器人每天可以对一个用户发 2 条主动消息。
     * 私信场景下，每个机器人每天累计可以发 200 条主动消息。
     * 私信场景下，被动消息没有条数限制
     * 传入msg_id或event_id其一，此条消息视为被动消息
     *
     * @param bot_id 传入机器人ID
     * @param json   请求体
     * @return 返回消息对象
     */
    @PostMapping("/sendReference/{bot_id}")
    public R sendReference(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String guild_id = json.getString("guild_id");
            String content = json.getString("content");
            MessageReference reference = json.getObject("reference", MessageReference.class);
            return new R().setData(dmsApi.sendReferenceMessage(bot_id, guild_id, content, reference));
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
     * 用于向 guild_id 指定的私信会话发送Markdown富文本私信消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 私信的 guild_id 在创建私信会话时以及私信消息事件中获取。
     * 私信场景下，每个机器人每天可以对一个用户发 2 条主动消息。
     * 私信场景下，每个机器人每天累计可以发 200 条主动消息。
     * 私信场景下，被动消息没有条数限制
     * 传入msg_id或event_id其一，此条消息视为被动消息
     * @param bot_id 传入机器人ID
     * @return 返回消息对象
     */
    @PostMapping("/sendMarkdown/{bot_id}")
    public R sendMarkdown(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String guild_id = json.getString("guild_id");
            String msg_id = json.getString("msg_id");
            String event_id = json.getString("event_id");
            MessageMarkdown markdown = json.getObject("markdown", MessageMarkdown.class);
            return new R().setData(dmsApi.sendMarkdownMessage(bot_id, guild_id, msg_id, event_id, markdown));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 用于撤回机器人发送的，指定私信会话消息。
     *
     * @param bot_id     传入机器人ID
     * @param guild_id   私信场景下的私信会话ID
     * @param message_id 消息ID
     * @param hidetip    是否隐藏删除消息后的小灰条
     * @return 撤回结果
     */
    @DeleteMapping("/deleteMessage/{bot_id}/{guild_id}/{message_id}")
    public R deleteMessage(@PathVariable String bot_id, @PathVariable String guild_id, @PathVariable String message_id, @RequestParam boolean hidetip) {
        try {
            return new R().setData(dmsApi.deleteMessageById(bot_id, guild_id, message_id, hidetip).booleanValue());
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 通过指定 ark 字段发送模板消息。
     * 要求机器人具有发送消息和 对应ARK 模板 的权限。
     * 调用前需要先申请消息模板，这一步会得到一个模板 id，在请求时填在 ark.template_id 上。
     * 发送成功之后，会触发一个创建消息的事件。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id 传入机器人ID
     * @return 消息对象
     */
    @PostMapping("/sendArk/{bot_id}")
    public R sendArk(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String msg_id = json.getString("msg_id");
            String event_id = json.getString("event_id");
            String guild_id = json.getString("guild_id");
            MessageArk ark = json.getObject("ark", MessageArk.class);
            return new R().setData(dmsApi.sendArkMessage(bot_id, guild_id, ark, msg_id, event_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 发送embed模板消息
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id 传入机器人ID
     * @return 消息对象
     */
    @PostMapping("/sendEmbed/{bot_id}")
    public R sendEmbed(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String guild_id = json.getString("guild_id");
            String msg_id = json.getString("msg_id");
            String event_id = json.getString("event_id");
            MessageEmbed embed = json.getObject("embed", MessageEmbed.class);
            return new R().setData(dmsApi.sendEmbedMessage(bot_id, guild_id, embed, msg_id, event_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 发送图文消息
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 私信场景下私信会话ID
     * @param content  消息内容
     * @param file     图片数据
     * @param msg_id   消息ID
     * @param event_id 事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @PostMapping("/send-image-text/{bot_id}")
    public R sendImageAndText(@PathVariable String bot_id, @Nullable @RequestParam MultipartFile file, @RequestParam String guild_id, @Nullable @RequestParam String content, @Nullable @RequestParam String msg_id, @Nullable @RequestParam String event_id) {
        try {
            String imgPath = null;
            if (file != null) {
                imgPath = ApplicationUtils.imgCachePath + "/" + UUID.randomUUID().toString() + ".png";
                file.transferTo(new File(imgPath));
            }
            return new R().setData(dmsApi.sendImageAndTextMessage(bot_id, guild_id, content, imgPath, msg_id, event_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            e.printStackTrace();
            return new R().setMsg("error").setCode(500);
        }
    }
}
