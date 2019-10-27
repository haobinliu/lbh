package com.lbh.blog.bullet_screen_server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * serber
 * @Author lbh
 * @TODO 实现一个在线多人并且进行聊天的功能
 */
public enum BulletScreenServer {
    BULLET_SCREEN_SERVER;
    public static BulletScreenServer getBulletScreenServer(){
        return BULLET_SCREEN_SERVER;
    }
    private EventLoopGroup LEADERGROUP;
    private EventLoopGroup WORKERGROUP;

    private ServerBootstrap SERVER;
    private ChannelFuture FUTURE;

    BulletScreenServer(){
        LEADERGROUP = new NioEventLoopGroup();
        WORKERGROUP = new NioEventLoopGroup();
        SERVER = new ServerBootstrap();
        SERVER.group(LEADERGROUP,WORKERGROUP)
                .channel(NioServerSocketChannel.class)
                .childHandler(new BulletScreenInitialler());
    }
    public void SERVERStart(){
        this.FUTURE = SERVER.bind(8888);
        System.out.println("SERVER IS STARTING SUCCESS...");
    }
}
