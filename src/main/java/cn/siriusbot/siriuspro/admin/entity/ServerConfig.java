package cn.siriusbot.siriuspro.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServerConfig {
    Integer id;

    @TableField("`key`")
    String key;

    String val;
}
