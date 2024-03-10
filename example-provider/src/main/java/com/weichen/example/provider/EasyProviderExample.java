package com.weichen.example.provider;

import com.weichen.wcrpc.server.VertxHttpServer;

/**
 *  简单服务提供者示例
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        // 启动 web 服务
        VertxHttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }

}
