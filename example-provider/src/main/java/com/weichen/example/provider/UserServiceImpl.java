package com.weichen.example.provider;

import com.weichen.example.common.model.User;
import com.weichen.example.common.service.UserService;

/**
 *  用户服务实现类
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println("用户名： " + user.getName());
        return user;
    }

}
