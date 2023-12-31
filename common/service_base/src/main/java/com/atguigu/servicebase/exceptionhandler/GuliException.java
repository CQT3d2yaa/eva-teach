package com.atguigu.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @designer ks&taotao
 * @create 2023-03-15 21:43
 */
@Data
@AllArgsConstructor//生成有参数构造方法
@NoArgsConstructor //生成无参数构造
public class GuliException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;//状态码

    @ApiModelProperty(value = "异常信息")
    private String msg;//异常信息
}
