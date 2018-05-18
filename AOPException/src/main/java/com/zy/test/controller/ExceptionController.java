package com.zy.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zy.test.service.ExceptionService;


@Controller
@RequestMapping("/exception")
public class ExceptionController {
    @Autowired
    private ExceptionService service;
    
    @RequestMapping("/test")
    @ResponseBody
    public JSONObject test() throws Exception {
        JSONObject result = new JSONObject();
        service.test();
        
//      boolean flag = true;
//      if(flag) {
//          throw new BusinessException("我是业务异常-->contorller");
//      }
        
//      try {
//          boolean flag = false;
//          if(flag) {
//              throw new BusinessException("我是业务异常-->contorller");
//          }
//      } catch(BusinessException e) {
//          ans.put("msg", e.getMessage());
//      } 
        
        return result;
    }
}
