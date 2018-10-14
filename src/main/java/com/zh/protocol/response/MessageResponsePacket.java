package com.zh.protocol.response;

import com.zh.protocol.Packet;
import lombok.Data;

import static com.zh.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @Author zh2683
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
