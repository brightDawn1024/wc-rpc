package com.weichen.wcrpc.bootstrap;

import com.weichen.wcrpc.RpcApplication;
import com.weichen.wcrpc.config.RegistryConfig;
import com.weichen.wcrpc.config.RpcConfig;
import com.weichen.wcrpc.model.ServiceMetaInfo;
import com.weichen.wcrpc.model.ServiceRegisterInfo;
import com.weichen.wcrpc.registry.LocalRegistry;
import com.weichen.wcrpc.registry.Registry;
import com.weichen.wcrpc.registry.RegistryFactory;
import com.weichen.wcrpc.server.HttpServer;
import com.weichen.wcrpc.server.VertxHttpServer;

import java.util.List;

/**
 *  服务提供者初始化（启动类）
 */
public class ProviderBootstrap {

    /**
     *  初始化
     */
    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList){
        // RPC 框架初始化
        RpcApplication.init();

        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 注册服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            // 本地注册
            LocalRegistry.register(serviceName,serviceRegisterInfo.getImplClass());

            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + "服务注册失败",e);
            }
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(rpcConfig.getServerPort());
    }
}
