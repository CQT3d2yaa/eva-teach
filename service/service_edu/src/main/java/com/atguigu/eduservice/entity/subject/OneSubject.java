package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @designer ks&taotao
 * @create 2023-03-28 20:50
 */
//一级分类
@Data
public class OneSubject {

    private String id;
    private String title;

    //一个一级分类有多个二级分类
    private List<TwoSubject> children=new ArrayList<>();
}
