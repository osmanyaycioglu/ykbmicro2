package com.xyz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyTestConfig {

    @Bean
    public MyTestObj myTestObj() {
        MyTestObj hhMyTestObjLoc = new MyTestObj();
        return hhMyTestObjLoc;
    }


}
