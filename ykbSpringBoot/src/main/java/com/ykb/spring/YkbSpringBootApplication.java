package com.ykb.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.xyz.MyTestObj;
import com.ykb.spring.component.MyFirstComponent;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(scanBasePackages = {
//                                            "com.ykb.spring",
//                                            "com.xyz"
//})
@SpringBootApplication
@EnableSwagger2
public class YkbSpringBootApplication implements ApplicationRunner {

    @Autowired
    @Qualifier("createMyTestObj")
    private MyTestObj       testObj;

    @Autowired
    @Qualifier("osman")
    private MyTestObj       testObj2;

    private final MyTestObj mto;


    public YkbSpringBootApplication(@Autowired @Qualifier("osman") final MyTestObj mto) {
        this.mto = mto;
        System.out.println("Const Wiring");
        System.out.println(this.testObj);
    }

    @Autowired
    public void yui(@Qualifier("osman") final MyTestObj tto) {
        System.out.println("Method Wiring");
    }

    @PostConstruct
    public void zxcv() {
        this.testObj.setTest("test str 1");
        System.out.println("Post Construct");
    }

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

    @Override
    public void run(final ApplicationArguments argsParam) throws Exception {
        System.out.println(this.testObj);
        System.out.println(this.testObj2);
    }

}
