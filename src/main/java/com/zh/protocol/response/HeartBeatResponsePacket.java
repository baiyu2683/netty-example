package com.zh.protocol.response;

import com.zh.protocol.Packet;

import static com.zh.protocol.command.Command.HEARTBEAT_RESPONSE;

/**
 * @Author zh2683
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
