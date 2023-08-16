package com.atguigu.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @designer ks&taotao
 * @create 2023-04-19 9:14
 */
@Component
public class OrdersClientImpl implements OrdersClient{

    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
