package com.zh.server.handler;

import com.zh.protocol.PacketCodec;
import com.zh.protocol.request.LoginRequestPacket;
import com.zh.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author zh2683
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
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
//        ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), loginResponsePacket);

        ctx.channel().writeAndFlush(loginResponsePacket);
    }


    // 校验逻辑
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
