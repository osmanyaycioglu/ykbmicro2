package com.ykb.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceEmployeeApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MicroserviceEmployeeApplication.class,
                              args);
    }

}
