package com.weichen.wcrpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 *  配置工具类
 */
public class ConfigUtils {

    /**
     *  加载配置对象
     * @param tClass
     * @param prefix
     * @param <T>
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass,String prefix){
        return loadConfig(tClass,prefix,"");
    }

    /**
     *  加载配置对象，支持区分环境
     * @param tClass
     * @param prefix
     * @param environment
     * @param <T>
     * @return
     */
    private static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)){
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
//        System.out.println(configFileBuilder.toString());  //测试
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass,prefix);

    }
}
