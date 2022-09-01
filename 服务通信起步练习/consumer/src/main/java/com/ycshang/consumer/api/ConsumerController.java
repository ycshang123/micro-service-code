package com.ycshang.consumer.api;

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
 * @description:
 * @author: ycshang
 * @create: 2022-08-30 15:52
 **/
@RestController
public class ConsumerController {
    private final String SERVICE_URL = "http://ufys6qe0.dnat.tech:39647/api";
    @Resource
    private RestTemplate restTemplate;

    private WebClient webClient = WebClient.builder()
            .baseUrl(SERVICE_URL)
            .build();

    @GetMapping("/httpClientTest")
    public String httpClientTest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(SERVICE_URL + "/hello");
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpClient.execute(httpGet);
            //判断状态码
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }

        return result + "请求成功";
    }

    @GetMapping("/restTemplateTest")
    public String restTemplateTest() {
        String result = restTemplate.getForObject(SERVICE_URL + "/hello", String.class);
        System.out.println(result);
        return result;
    }


    @GetMapping("/webClientTest")
    public String webClientTest() {
        Mono<String> mono = webClient.get().uri("/hello").retrieve().bodyToMono(String.class);
        mono.subscribe(System.out::println
        );
        return "请求成功";

    }
}