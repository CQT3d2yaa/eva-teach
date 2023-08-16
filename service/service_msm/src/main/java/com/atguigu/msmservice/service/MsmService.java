package com.atguigu.msmservice.service;

import java.util.HashMap;

/**
 * @designer ks&taotao
 * @create 2023-04-08 20:08
 */
public interface MsmService {

    //发送短信的方法
    boolean send(HashMap<String, Object> param, String phone);
}
