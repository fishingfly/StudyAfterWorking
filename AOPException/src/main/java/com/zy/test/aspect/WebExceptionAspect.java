package com.zy.test.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * web 异常切面
 * @author user
 *
 */
@Aspect
@Component
public class WebExceptionAspect {
    
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void webPointcut() {}
    
    @AfterThrowing(pointcut = "webPointcut()", throwing = "e")
    public void handleThrowing(Exception e) {
        //开始打log
        System.out.println("ssssssssssssssssssssssssssssssssssssss");
        System.out.println("controller层开始抛异常:" + e.getMessage());
        //向前端返回友好的响应
        
    }
}