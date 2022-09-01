package com.ycshang.provider.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: provider
 * @description:
 * @author: ycshang
 * @create: 2022-08-30 15:43
 **/
@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String Hello() {
        return "恭喜你！";
    }
}