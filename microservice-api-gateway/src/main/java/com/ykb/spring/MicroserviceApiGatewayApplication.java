package com.ykb.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MicroserviceApiGatewayApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MicroserviceApiGatewayApplication.class,
                              args);
    }

}
