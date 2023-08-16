package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @designer ks&taotao
 * @create 2023-03-26 13:20
 */
public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
