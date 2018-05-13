package com.zy.testAop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.zy.testAop")
@EnableAspectJAutoProxy
public class AopConfig {

}
