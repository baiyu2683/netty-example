package com.zh.course6;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author zh2683
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    // 新的客户端连接时推送欢迎消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("新连接接入");
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "新的客户端，你好".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

        // 回复数据
//        System.out.println(new Date() + ": 服务端写出数据");
//        ByteBuf out = getByteBuf(ctx);
//        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，欢迎关注我的微信公众号，《闪电侠的博客》!".getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }

}
