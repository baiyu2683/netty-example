package com.zh.server.handler;

import com.zh.protocol.PacketCodec;
import com.zh.protocol.request.LoginRequestPacket;
import com.zh.protocol.response.LoginResponsePacket;
import com.zh.session.Session;
import com.zh.util.LoginUtil;
import com.zh.util.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Author zh2683
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
//        System.out.println(new Date() + ": 收到客户端登录请求……");
//
//        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
//        // 登录校验
//        if (valid(loginRequestPacket)) {
//            // 校验成功
//            System.out.println("客户端登录成功!");
//            loginResponsePacket.setSuccess(true);
//            // 标记当前链接已经登陆过了
//            LoginUtil.markAsLogin(ctx.channel());
//        } else {
//            // 校验失败
//            System.out.println("客户端登录失败!");
//            loginResponsePacket.setReason("账号密码校验失败");
//            loginResponsePacket.setSuccess(false);
//        }

        // 编码
//        ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), loginResponsePacket);

//        ctx.channel().writeAndFlush(loginResponsePacket);

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUsername());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + loginRequestPacket.getUsername() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }


    // 校验逻辑
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
