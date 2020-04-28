package com.ykb.spring.component;

import org.springframework.stereotype.Component;

@Component
public class MyFirstComponent {

    private String name;


    public String getName() {
        return this.name;
    }


    public void setName(final String nameParam) {
        this.name = nameParam;
    }

}
