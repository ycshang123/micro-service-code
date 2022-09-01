package com.ycshang.consumer.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ycshang.consumer.entity.NodeObject;
import com.ycshang.consumer.entity.PythonUser;
import com.ycshang.consumer.entity.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @program: consumer
 * @description: 小组合作
 * @author: ycshang
 * @create: 2022-08-30 20:12
 **/
@RestController
public class CooperationController {
    @Resource
    private RestTemplate restTemplate;

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://5ghe9f.natappfree.cc/api")
            .build();

    @GetMapping("/pythonController")
    public PythonUser getUserInfo() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://rqvxtc.natappfree.cc/get");
        CloseableHttpResponse response = null;
        PythonUser user = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            response = httpClient.execute(httpGet);
            //判断状态码
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                user = objectMapper.readValue(result, PythonUser.class);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }

        return user;
    }

    @GetMapping("/nodeController")
    public NodeObject nodeController() {
        NodeObject result = restTemplate.getForObject("http://mr38d7.natappfree.cc/index", NodeObject.class);
        System.out.println(result);
        return result;
    }

    @GetMapping("/javaController")
    public String webClientTest() {
        Mono<User> mono = webClient.get().uri("/hello").retrieve().bodyToMono(User.class);
        mono.subscribe(System.out::println
        );
        return "请求成功";

    }
}