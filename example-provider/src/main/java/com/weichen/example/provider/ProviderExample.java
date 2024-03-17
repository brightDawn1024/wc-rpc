package com.weichen.example.provider;

import com.weichen.example.common.service.UserService;
import com.weichen.wcrpc.RpcApplication;
import com.weichen.wcrpc.registry.LocalRegistry;
import com.weichen.wcrpc.server.HttpServer;
import com.weichen.wcrpc.server.VertxHttpServer;

/**
 *  简单服务提供者示例
 */
public class ProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
