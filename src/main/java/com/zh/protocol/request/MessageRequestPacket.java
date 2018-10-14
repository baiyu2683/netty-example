package com.zh.protocol.request;

import com.zh.protocol.Packet;
import lombok.Data;

import static com.zh.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @Author zh2683
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
