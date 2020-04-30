package com.ykb.spring;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {


    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping(value = {
                          "/user",
                          "/me"
    })
    public Principal getUser(final Principal user) {
        return user;
    }

}
