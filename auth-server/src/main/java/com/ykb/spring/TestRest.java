package com.ykb.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {


    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
