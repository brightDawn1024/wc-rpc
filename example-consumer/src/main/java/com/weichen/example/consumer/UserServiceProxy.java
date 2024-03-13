package com.weichen.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.weichen.example.common.model.User;
import com.weichen.example.common.service.UserService;

import com.weichen.wcrpc.model.RpcRequest;
import com.weichen.wcrpc.model.RpcResponse;
import com.weichen.wcrpc.serializer.JdkSerializer;
import com.weichen.wcrpc.serializer.Serializer;

import java.io.IOException;

/**
 *  静态代理
 */
public class UserServiceProxy implements UserService {

    @Override
    public User getUser(User user) {
        // 指定序列化器
        final Serializer serializer = new JdkSerializer();

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 发送请求
            try (
                HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                        .body(bodyBytes)
                        .execute()){
                    byte[] result = httpResponse.bodyBytes();
                    // 反序列化
                    RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                    return (User) rpcResponse.getData();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
    }

}
