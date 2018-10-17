package com.zh.protocol.request;

import com.zh.protocol.Packet;

import static com.zh.protocol.command.Command.HEARTBEAT_REQUEST;

/**
 * @Author zh2683
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
