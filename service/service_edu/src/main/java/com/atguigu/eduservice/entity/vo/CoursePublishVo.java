package com.atguigu.eduservice.entity.vo;

import lombok.Data;

/**
 * @designer ks&taotao
 * @create 2023-03-31 12:25
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
