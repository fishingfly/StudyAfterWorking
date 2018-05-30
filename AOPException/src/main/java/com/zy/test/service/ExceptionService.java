package com.zy.test.service;

import org.springframework.stereotype.Service;

@Service
public class ExceptionService implements ExceptionServiceBase{
    public void test() throws Exception {
        boolean flag = true;
        if(flag) {
            throw new Exception("service 异常");
        }
    }
}