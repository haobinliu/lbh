package com.lbh.blog.bullet_screen_server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;


public class BulletScreenHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChannelGroup CLIENTS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame txt) throws Exception {
        String msg = txt.text();
        System.out.println("****:"+msg);
        for (Channel channel:CLIENTS) {
            channel.writeAndFlush(
                    new TextWebSocketFrame(BulletScreenRoom.showMessage(
                            ctx.channel().id().asShortText(), msg))
            );
        }
    }
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        Channel channel = ctx.channel();
        CLIENTS.add(channel);
        System.out.println(ctx.channel().id().asShortText()+"=========JOIN THE ROOM");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println(ctx.channel().id().asShortText()+"=========LEAVE THE ROOM");

    }
}
