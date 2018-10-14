package com.zh.codec;

import com.zh.protocol.Packet;
import com.zh.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 * @Author zh2683
 */
public class PacketEncoder2 extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        System.out.println("asdfasdfasdfasdfasdfasdf");
        ctx.fireChannelRead(msg);
    }
}
