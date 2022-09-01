package com.ycshang.cloud.api;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: cloud-service
 * @description: 商品微服务接口
 * @author: ycshang
 * @create: 2022-09-01 08:49
 **/
@RestController
public class ConsumerController {

    private final String SERVICE_URL = "http://cloud-goods-service";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumerTest")
    private String consumerTest() {
        String result = restTemplate.getForObject(SERVICE_URL + "/goodsServiceTest", String.class);
        return result;
    }


}