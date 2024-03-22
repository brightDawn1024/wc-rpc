package com.weichen.wcrpc.proxy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.weichen.wcrpc.RpcApplication;
import com.weichen.wcrpc.config.RpcConfig;
import com.weichen.wcrpc.constant.RpcConstant;
import com.weichen.wcrpc.model.RpcRequest;
import com.weichen.wcrpc.model.RpcResponse;
import com.weichen.wcrpc.model.ServiceMetaInfo;
import com.weichen.wcrpc.registry.Registry;
import com.weichen.wcrpc.registry.RegistryFactory;
import com.weichen.wcrpc.serializer.JdkSerializer;
import com.weichen.wcrpc.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 *  JDK 动态代理
 */
public class ServiceProxy implements InvocationHandler {

    /**
     *  调用代理
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        Serializer serializer = new JdkSerializer();
        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }
            // todo 从注册中心获取到的服务节点地址可能是多个，暂时先取第一个
            ServiceMetaInfo selectedServiceMetaInfo = serviceMetaInfoList.get(0);

            // 发送请求
            try (
                    HttpResponse httpResponse = HttpRequest.post(selectedServiceMetaInfo.getServiceAddress())
                            .body(bodyBytes)
                            .execute()){
                    byte[] result = httpResponse.bodyBytes();
                    // 反序列化
                    RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                    return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
