package com.ykb.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableWebSecurity
@EnableAuthorizationServer
public class AuthServerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AuthServerApplication.class,
                              args);
    }

}
