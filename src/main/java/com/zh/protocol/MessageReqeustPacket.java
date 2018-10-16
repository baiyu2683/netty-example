package com.zh.protocol;

import lombok.Data;

/**
 * @Author zh2683
 */
@Data
public class MessageReqeustPacket {

    private String toUserId;
    private String message;
}
