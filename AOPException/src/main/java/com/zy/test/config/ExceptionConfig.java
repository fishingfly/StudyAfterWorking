package com.zy.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 异常相关的bean注册类
 * @author user
 *
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.zy.test.aspect")
public class ExceptionConfig {
    
}
