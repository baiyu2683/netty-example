package com.zh.client;

import com.zh.protocol.Packet;
import com.zh.protocol.request.LoginRequestPacket;
import com.zh.protocol.PacketCodec;
import com.zh.protocol.response.LoginResponsePacket;
import com.zh.protocol.response.MessageResponsePacket;
import com.zh.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Author zh2683
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId((int)Math.floor(Math.random() * 10));
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        //编码
        ByteBuf buffer = PacketCodec.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }

}
