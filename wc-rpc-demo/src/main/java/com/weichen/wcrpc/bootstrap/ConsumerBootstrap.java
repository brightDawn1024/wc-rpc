package com.weichen.wcrpc.bootstrap;

import com.weichen.wcrpc.RpcApplication;

/**
 *  服务消费者初始化（启动类）
 */
public class ConsumerBootstrap {

    public static void init(){
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }
}
