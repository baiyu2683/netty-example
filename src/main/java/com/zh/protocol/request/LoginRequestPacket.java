package com.zh.protocol.request;

import com.zh.protocol.Packet;
import lombok.Data;

import static com.zh.protocol.command.Command.LOGIN_REQUEST;

/**
 * @Author zh2683
 */
@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
