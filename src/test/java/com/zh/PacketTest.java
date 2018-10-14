package com.zh;

import com.alibaba.fastjson.JSON;
import com.zh.protocol.request.LoginRequestPacket;
import com.zh.protocol.Packet;
import com.zh.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;

/**
 * @Author zh2683
 */
public class PacketTest {

    public static void main(String[] args) {
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setPassword("123");
        packet.setUserId(10);
        packet.setUsername("zhangheng");
        System.out.println(JSON.toJSON(packet));
        PacketCodec packetCodec = new PacketCodec();
        ByteBuf byteBuf = packetCodec.encode(packet);
        Packet packet1 = packetCodec.decode(byteBuf);
        System.out.println(JSON.toJSON(packet1));
    }
}
