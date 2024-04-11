package com.weichen.wcrpc.loadbalancer;

import com.weichen.wcrpc.spi.SpiLoader;

/**
 *  负载均衡器工厂
 *  用于获取负载均衡器对象
 */
public class LoadBalancerFactory {

    static {
        SpiLoader.load(LoadBalancer.class);
    }

    /**
     *  默认的负载均衡器
     */
    private static final LoadBalancer DEFAULT_LOAD_BALANCER = new RoundRobinLoadBalancer();

    public static LoadBalancer getInstance(String key){
        return SpiLoader.getInstance(LoadBalancer.class,key);
    }

}
