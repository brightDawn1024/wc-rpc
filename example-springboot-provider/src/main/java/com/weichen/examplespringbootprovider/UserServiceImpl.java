package com.weichen.examplespringbootprovider;

import com.weichen.example.common.model.User;
import com.weichen.example.common.service.UserService;
import com.weichen.wcrpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 *  用户服务实现类
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println("用户" + user.getName());
        return user;
    }
}
