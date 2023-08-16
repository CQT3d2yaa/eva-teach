package com.atguigu.educms.controller;

import com.atguigu.commonutils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @designer ks&taotao
 * @create 2023-04-06 23:33
 */
//后台banner管理接口
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    //1 分页查询banner
    @GetMapping("/pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page,
                        @PathVariable long limit){
        Page<CrmBanner> bannerPage=new Page<>(page,limit);
        bannerService.page(bannerPage,null);

        return R.ok().data("items",bannerPage.getRecords()).data("total",bannerPage.getTotal());
    }

    //2 添加banner
    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return R.ok();
    }

    //根据id获取banner
    @GetMapping("/get/{id}")
    public R get(@PathVariable String id){
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item",banner);
    }
    //3修改banner
    @PutMapping("/update")
    public R updateById(@RequestBody CrmBanner crmBanner){
        bannerService.updateById(crmBanner);
        return R.ok();
    }

    //删除banner
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable String id){
        bannerService.removeById(id);
        return R.ok();
    }

}
