package com.weichen.example.consumer;

import com.weichen.example.common.model.User;
import com.weichen.example.common.service.UserService;

/**
 *  简单服务消费者示例
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        // todo 需要获取 UserService 的实现类对象
        UserService userService = null;
        User user = new User();
        user.setName("weichen");

        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null){
            System.out.println(newUser.getName());
        } else {
            System.out.println("newUser == null ");
        }
    }

}
