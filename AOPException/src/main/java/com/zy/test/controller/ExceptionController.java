package com.zy.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zy.test.annotation.ArchivesLog;
import com.zy.test.service.ExceptionService;


@Controller
@RequestMapping("/exception")
public class ExceptionController {
    @Autowired
    private ExceptionService service;
    
    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    @ArchivesLog(operationType = "测试", operationName = "测试异常或者测试返回")
    public JSONObject test(@PathVariable Integer id) throws Exception {
        JSONObject result = new JSONObject();
        result.put("zhouyu", "asdasdasdasd");
//        try {
//            service.test();
//        } catch (Exception ex) {
//            throw new Exception("controller 层 异常");
//        }
        return result;
    }
}
