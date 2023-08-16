package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.commontvo.CommontUcenter;
import com.atguigu.eduservice.client.UcenterClient;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-04-16
 */
@RestController
@RequestMapping("/eduservice/comment")
//@CrossOrigin
public class EduCommentController {

    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcenterClient ucenterClient;

    //根据课程id查询评论列表
    @GetMapping("/{page}/{limit}/{id}")
    public R getCommont(@PathVariable long page,@PathVariable long limit,
                        @PathVariable String id){
        Page<EduComment> commentPage=new Page<>(page,limit);

        QueryWrapper<EduComment> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",id);

        commentService.page(commentPage,wrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("items",commentPage.getRecords());
        map.put("current", commentPage.getCurrent());
        map.put("pages", commentPage.getPages());
        map.put("size", commentPage.getSize());
        map.put("total", commentPage.getTotal());
        map.put("hasNext", commentPage.hasNext());
        map.put("hasPrevious", commentPage.hasPrevious());

        return R.ok().data(map);
    }

    //添加评论,要根据用户id查询用户信息后再添加评论
    @PostMapping("/save")
    public R saveCommont(@RequestBody EduComment comment, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        comment.setMemberId(memberId);

        //根据用户id远程调用service_ucenter获取用户信息对象
        CommontUcenter commontUcenter = ucenterClient.getInfo(memberId);

        //获取昵称添加进评论
        comment.setNickname(commontUcenter.getNickname());
        //获取头像添加进评论
        comment.setAvatar(commontUcenter.getAvatar());

        commentService.save(comment);

        return R.ok();

    }

}

