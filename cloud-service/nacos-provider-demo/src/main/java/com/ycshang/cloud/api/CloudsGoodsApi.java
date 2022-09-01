package com.ycshang.cloud.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cloud-service
 * @description: 商品微服务接口
 * @author: ycshang
 * @create: 2022-09-01 08:49
 **/
@RestController
public class CloudsGoodsApi {
    /**
     * 读取当前应用启动端口
     */
    @Value("${server.port}")
    private String applicationServerPort;

    @GetMapping("/goodsServiceTest")
    public String goodsServiceTest() {
        //    返回信息给调用端
        return "this is goods service from port" + applicationServerPort;
    }
}