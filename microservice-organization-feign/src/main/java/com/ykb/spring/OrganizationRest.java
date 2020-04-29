package com.ykb.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bus.SpringCloudBusClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ykb.spring.event.MyRemoteEvent;

@RestController
@RequestMapping("/organization")
@RefreshScope
public class OrganizationRest {

    private final Logger         logger = LoggerFactory.getLogger(OrganizationRest.class);

    @Autowired
    private RabbitTemplate       rt;

    @Autowired
    private IEmployeeRest        ier;

    @Value("${my.property}")
    private String               dynName;

    @Autowired
    private SpringCloudBusClient scc;

    @Autowired
    private ApplicationContext   app;

    @GetMapping("/test_prop")
    public String testProp() {
        MyRemoteEvent remoteEventLoc = new MyRemoteEvent(this,
                                                         this.app.getId(),
                                                         "remote test event");
        this.scc.springCloudBusOutput()
                .send(MessageBuilder.withPayload(remoteEventLoc)
                                    .build());
        return this.dynName;
    }


    @PostMapping("/employee/create")
    public String createEmployee(@RequestBody final Employee employeeParam) {
        String forObjectLoc = this.ier.greetEmployee();

        Department departmentLoc = new Department();
        departmentLoc.setName("IT");
        departmentLoc.setEmployeeCount(30);
        departmentLoc.setEmailHost("ykb.it");

        this.rt.convertAndSend("dq_direct_exchange",
                               "department_key",
                               departmentLoc);
        return forObjectLoc;
    }


}
