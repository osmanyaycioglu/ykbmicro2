package com.ykb.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

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

    private int counter = 0;

    @HystrixCommand(fallbackMethod = "greetEmployeeFallback",
                    commandProperties = {
                                          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                                                           value = "500")
                    })
    @GetMapping("/greet")
    public String greetEmployee() throws MyException {
        this.counter++;
        if ((this.counter % 3) == 0) {
            throw new MyException();
        }
        if ((this.counter % 5) == 0) {
            try {
                Thread.sleep(1000);
            } catch (Exception eLoc) {
                // TODO: handle exception
            }
        }
        return "hello Employee " + this.port;
    }

    public String greetEmployeeFallback() {
        return "Fallback hello";
    }

}
