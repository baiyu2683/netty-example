package com.zh.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author zh2683
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    /**
     * 指令
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
