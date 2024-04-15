package com.weichen.example.consumer;

import com.weichen.example.common.model.User;
import com.weichen.example.common.service.UserService;
import com.weichen.wcrpc.bootstrap.ConsumerBootstrap;
import com.weichen.wcrpc.proxy.ServiceProxyFactory;

/**
 *  简单服务消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {
        // 服务提供者初始化
        ConsumerBootstrap.init();

        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("weichen");
        // 调用
        User newUser = (User) userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        long number = userService.getNumber();
        System.out.println("number is " + number);
    }
}
