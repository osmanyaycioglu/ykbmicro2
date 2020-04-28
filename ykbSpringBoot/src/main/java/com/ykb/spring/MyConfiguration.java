package com.ykb.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import com.xyz.MyTestConfig;
import com.xyz.MyTestObj;

@Configuration
@Import(MyTestConfig.class)
public class MyConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public MyTestObj createMyTestObj() {
        MyTestObj hhMyTestObjLoc = new MyTestObj();
        String propertyLoc = this.env.getProperty("my.conf.test");
        if (propertyLoc.equals("test")) {
            hhMyTestObjLoc.setTest("test");
        } else if (propertyLoc.equals("live")) {
            hhMyTestObjLoc.setTest("live");
        } else {
            hhMyTestObjLoc.setTest("unkonwm");
        }
        return hhMyTestObjLoc;
    }

    @Bean
    @Qualifier("osman")
    public MyTestObj createMyTestObj2(@Value("${my.conf.test}") final String propertyLoc) {
        MyTestObj hhMyTestObjLoc = new MyTestObj();
        if (propertyLoc.equals("test")) {
            hhMyTestObjLoc.setTest("test");
        } else if (propertyLoc.equals("live")) {
            hhMyTestObjLoc.setTest("live");
        } else {
            hhMyTestObjLoc.setTest("unkonwm");
        }
        return hhMyTestObjLoc;
    }

}
