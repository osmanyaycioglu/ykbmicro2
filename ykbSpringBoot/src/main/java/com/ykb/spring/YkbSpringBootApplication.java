package com.ykb.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ykb.spring.component.MyFirstComponent;

@SpringBootApplication
public class YkbSpringBootApplication {

    @Autowired
    private MyFirstComponent component;

    public static void main(final String[] args) {
        SpringApplication.run(YkbSpringBootApplication.class,
                              args);
    }

}
