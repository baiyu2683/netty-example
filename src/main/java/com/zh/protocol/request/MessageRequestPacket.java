package com.zh.protocol.request;

import com.zh.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.zh.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @Author zh2683
 */
@Data
@AllArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
