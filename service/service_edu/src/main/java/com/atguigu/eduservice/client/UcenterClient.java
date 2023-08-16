package com.atguigu.eduservice.client;

import com.atguigu.commonutils.commontvo.CommontUcenter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @designer ks&taotao
 * @create 2023-04-16 10:37
 */
@Component
@FeignClient(name = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    //根据用户id获取用户信息
    @GetMapping("/educenter/member/getInfoUc/{id}")
    public CommontUcenter getInfo(@PathVariable("id") String id);
}
