package com.weichen.wcrpc.registry;

import com.weichen.wcrpc.config.RegistryConfig;
import com.weichen.wcrpc.model.ServiceMetaInfo;

import java.util.List;

/**
 *  注册中心
 */
public interface Registry {

    /**
     *  初始化
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     *  注册服务
     * @param serviceMetaInfo
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     *  注销服务
     * @param serviceMetaInfo
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     *  服务发现(获取某服务的所以节点)
     * @param serviceKey 服务键名
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     *  服务销毁
     */
    void destroy();

    /**
     *  心跳检测
     */
    void heartBeat();

    /**
     *  监听
     * @param serviceNodeKey
     */
    void watch(String serviceNodeKey);

}
