package com.weichen.example.consumer;

import com.weichen.wcrpc.config.RpcConfig;
import com.weichen.wcrpc.utils.ConfigUtils;

/**
 *  简单服务消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
