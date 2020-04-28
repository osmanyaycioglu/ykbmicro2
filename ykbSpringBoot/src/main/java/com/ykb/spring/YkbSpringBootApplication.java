package com.ykb.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ykb.spring.component.MyFirstComponent;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class YkbSpringBootApplication {

    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      .apis(RequestHandlerSelectors.any())
                                                      .paths(PathSelectors.any())
                                                      .build()
                                                      .apiInfo(new ApiInfoBuilder().title("TKB Micro")
                                                                                   .description("YKB Microservices")
                                                                                   .contact(new Contact("osman",
                                                                                                        "github",
                                                                                                        "osman.yaycioglu@gmail.com"))
                                                                                   .build());
    }


    @Autowired
    private MyFirstComponent component;

    public static void main(final String[] args) {
        SpringApplication.run(YkbSpringBootApplication.class,
                              args);
    }

}
