package com.zh.server;

import com.zh.protocol.request.LoginRequestPacket;
import com.zh.protocol.Packet;
import com.zh.protocol.PacketCodec;
import com.zh.protocol.request.MessageRequestPacket;
import com.zh.protocol.response.LoginResponsePacket;
import com.zh.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Author zh2683
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        // 解码
        Packet packet = PacketCodec.INSTANCE.decode(requestByteBuf);

        // 判断是否是登录请求数据包
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            // 登录校验
            if (valid(loginRequestPacket)) {
                // 校验成功
                System.out.println("客户端登录成功!");
                loginResponsePacket.setSuccess(true);
            } else {
                // 校验失败
                System.out.println("客户端登录失败!");
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
            }

            // 编码
            ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket) {
            // 处理消息
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复[" + messageRequestPacket.getMessage() + "]");
            ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    // 校验逻辑
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
