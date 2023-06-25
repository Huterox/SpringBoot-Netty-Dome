package com.huterox.whitehole.whiteholemessage.NettyServer;


import com.huterox.whitehole.whiteholemessage.config.HoleNettyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务器启动类
 */
@Component
@Slf4j
public class ServerBoot {
    @Autowired
    ServerBootstrap serverBootstrap;
    @Resource
    NioEventLoopGroup boosGroup;
    @Resource
    NioEventLoopGroup workerGroup;
    @Autowired
    HoleNettyProperties holeNettyProperties;

    /**
     * 开机启动
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        // 绑定端口启动
        serverBootstrap.bind(holeNettyProperties.getPort()).sync();
        log.info("启动Netty端口服务器: {}",holeNettyProperties.getPort());
    }

    /**
     * 关闭线程池
     */
    @PreDestroy
    public void close() throws InterruptedException {
        log.info("优雅的关闭Netty服务器");
        boosGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
