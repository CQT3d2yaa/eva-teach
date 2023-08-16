package com.atguigu.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

import java.util.List;

/**
 * @designer ks&taotao
 * @create 2023-04-03 23:37
 */
public class TestVod {

    //1 根据视频iD获取视频播放地址
    @Test
    public void t1() throws ClientException {
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tCZE1TiixAeEGhLNoii", "jtTjWskykAkfyA2yFcIW3xEFABw1Wk");

        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request对象里面设置视频id
        request.setVideoId("8c17cfa0d22f71edb7156723a78f0102");

        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }

    //2 根据视频iD获取视频播放凭证
    @Test
    public void t2() throws ClientException {
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tCZE1TiixAeEGhLNoii", "jtTjWskykAkfyA2yFcIW3xEFABw1Wk");

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        request.setVideoId("8c17cfa0d22f71edb7156723a78f0102");

        response=client.getAcsResponse(request);
        System.out.println("playAuth:"+response.getPlayAuth());

    }

    //上传视频
    @Test
    public void t3(){
        String accessKeyId = "LTAI5tCZE1TiixAeEGhLNoii";
        String accessKeySecret = "jtTjWskykAkfyA2yFcIW3xEFABw1Wk";

        String title = "woshishuaige";   //上传之后文件名称
        String fileName = "D:/6 - What If I Want to Move Faster.mp4";  //本地文件路径和名称

        //上传视频的方法
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()){
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        }else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

}
