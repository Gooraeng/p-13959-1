package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        System.out.println("index 호출됨");
        return "SBB";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println("hello() 호출됨");
        return "안녕하세요!";
    }
}
