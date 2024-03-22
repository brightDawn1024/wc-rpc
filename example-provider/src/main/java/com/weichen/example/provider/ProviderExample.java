package com.weichen.example.provider;

import com.weichen.example.common.service.UserService;
import com.weichen.wcrpc.RpcApplication;
import com.weichen.wcrpc.config.RegistryConfig;
import com.weichen.wcrpc.config.RpcConfig;
import com.weichen.wcrpc.model.ServiceMetaInfo;
import com.weichen.wcrpc.registry.LocalRegistry;
import com.weichen.wcrpc.registry.Registry;
import com.weichen.wcrpc.registry.RegistryFactory;
import com.weichen.wcrpc.server.HttpServer;
import com.weichen.wcrpc.server.VertxHttpServer;

/**
 *  服务提供者示例
 */
public class ProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName,UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceAddress(rpcConfig.getServerHost() + ":" + rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
