package com.ycshang.provider.dao;

import lombok.Data;

/**
 * @program: provider
 * @description:
 * @author: ycshang
 * @create: 2022-08-30 16:45
 **/
@Data
public class User {
    private String nickname;
    private String phone;
    private String address;
    private Integer age;
    private String sex;
}