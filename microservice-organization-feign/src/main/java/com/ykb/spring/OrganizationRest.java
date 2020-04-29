package com.ykb.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationRest {

    private final Logger   logger = LoggerFactory.getLogger(OrganizationRest.class);

    @Autowired
    private RabbitTemplate rt;

    @Autowired
    private IEmployeeRest  ier;

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
