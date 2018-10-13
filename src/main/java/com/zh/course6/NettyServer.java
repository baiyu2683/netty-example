package com.zh.course6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Author zh2683
 */
public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup(); // 接受连接线程
        NioEventLoopGroup worker = new NioEventLoopGroup(); // 处理链接线程

        ServerBootstrap serverBootstrap = new ServerBootstrap(); // 启动类
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class) // io模型， nio, 对应bio中的ServerSocket
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    // 这里的niosocketChannel是上面NioServerSocketChannel接受到的socker
                    // 对应bio中的Socket(ServerSocket接受到的连接)
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline()
                                .addLast(new FirstServerHandler());
                    }
                });
        bind(serverBootstrap, 8000);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口: [" + port + "]绑定成功");
                } else {
                    System.err.println("端口: [" + port + "]绑定失败");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
