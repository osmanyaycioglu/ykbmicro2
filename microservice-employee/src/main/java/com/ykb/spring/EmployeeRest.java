package com.ykb.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RefreshScope
public class EmployeeRest {

    @Value("${server.port}")
    private int    port;

    @Value("${my.property}")
    private String dynName;

    @GetMapping("/test_prop")
    public String testProp() {
        return this.dynName;
    }

    @GetMapping("/greet")
    public String greetEmployee() {
        return "hello Employee " + this.port;
    }

}
