package com.zy.testAop;

import org.springframework.stereotype.Service;

@Service
public class DemoAnnotationService {
    @Action(name = "注解式拦截的add操作")
    public void add() {
        System.out.println("真正执行到了DemoAnnotationService的add方法");
        System.out.println("DemoAnnotationService.add方法结束");
    }
}
