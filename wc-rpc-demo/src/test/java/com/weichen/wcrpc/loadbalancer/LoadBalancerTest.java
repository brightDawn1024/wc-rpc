package com.weichen.wcrpc.loadbalancer;

import com.weichen.wcrpc.model.ServiceMetaInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  负载均衡器测试
 */
public class LoadBalancerTest {

//    final LoadBalancer loadBalancer = new RoundRobinLoadBalancer();
    final LoadBalancer loadBalancer = new RandomLoadBalancer();
//    final LoadBalancer loadBalancer = new ConsistentHashLoadBalancer();

    @Test
    public void select(){
        // 请求参数
        Map<String,Object> requestParams = new HashMap<>();
        requestParams.put("methodName","huawei");
        // 服务列表
        ServiceMetaInfo serviceMetaInfo1 = new ServiceMetaInfo();
        serviceMetaInfo1.setServiceName("Service");
        serviceMetaInfo1.setServiceVersion("1.0");
        serviceMetaInfo1.setServiceHost("localhost");
        serviceMetaInfo1.setServicePort(8080);
        ServiceMetaInfo serviceMetaInfo12 = new ServiceMetaInfo();
        serviceMetaInfo12.setServiceName("Service");
        serviceMetaInfo12.setServiceVersion("1.0");
        serviceMetaInfo12.setServiceHost("wc.edu");
        serviceMetaInfo12.setServicePort(80);
        List<ServiceMetaInfo> serviceMetaInfoList = Arrays.asList(serviceMetaInfo1, serviceMetaInfo12);
        // 连续调用 5 次
        ServiceMetaInfo serviceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        System.out.println(serviceMetaInfo);
        Assert.assertNotNull(serviceMetaInfo);

        serviceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        System.out.println(serviceMetaInfo);
        Assert.assertNotNull(serviceMetaInfo);

        serviceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        System.out.println(serviceMetaInfo);
        Assert.assertNotNull(serviceMetaInfo);

        serviceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        System.out.println(serviceMetaInfo);
        Assert.assertNotNull(serviceMetaInfo);

        serviceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        System.out.println(serviceMetaInfo);
        Assert.assertNotNull(serviceMetaInfo);
    }

}
