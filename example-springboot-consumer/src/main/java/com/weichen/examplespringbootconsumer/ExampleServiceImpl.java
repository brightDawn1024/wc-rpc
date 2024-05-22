package com.weichen.examplespringbootconsumer;

import com.weichen.example.common.model.User;
import com.weichen.example.common.service.UserService;
import com.weichen.wcrpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public void test() {
        User user = new User();
        user.setName("weichen");
        User user1 = userService.getUser(user);
        System.out.println(user1.getName());
    }
}
