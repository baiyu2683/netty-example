package com.zh.handler;

import com.zh.protocol.request.MessageRequestPacket;
import com.zh.protocol.response.MessageResponsePacket;
import com.zh.session.Session;
import com.zh.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author zh2683
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        // 1. 拿到发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 2. 通过消息发送方的绘画信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(msg.getMessage());

        // 3. 拿到消息接收方的channel
        Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());

        // 4. 将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.out.println("[" + session.getUserId() + "]不在线；发送失败");
        }
    }
}
