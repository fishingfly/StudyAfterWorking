package com.zy.SpringBootDemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController等价于@RequestBody和@Controller
 * @author fishing
 *
 */
@RestController
public class HelloController {

    /**
     * 使用@RequestMapping建立请求映射
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
