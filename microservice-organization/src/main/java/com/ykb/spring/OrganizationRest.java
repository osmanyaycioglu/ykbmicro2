package com.ykb.spring;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping("/organization")
public class OrganizationRest {

    private final Logger logger = LoggerFactory.getLogger(OrganizationRest.class);

    @Autowired
    private RestTemplate rt;

    @Autowired
    private EurekaClient ec;

    @PostMapping("/employee/create")
    public String createEmployee(@RequestBody final Employee employeeParam) {
        String forObjectLoc = this.rt.getForObject("http://EMPLOYEE/employee/greet",
                                                   String.class);
        return forObjectLoc;
    }

    @PostMapping("/employee/create2")
    public String createEmployee2(@RequestBody final Employee employeeParam) {
        List<InstanceInfo> instancesLoc = this.ec.getApplication("EMPLOYEE")
                                                 .getInstances();
        for (InstanceInfo instanceInfoLoc : instancesLoc) {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("[OrganizationRest][createEmployee2]-> " + instanceInfoLoc);
            }
        }
        InstanceInfo nextServerFromEurekaLoc = this.ec.getNextServerFromEureka("EMPLOYEE",
                                                                               false);
        RestTemplate restTemplateLoc = new RestTemplate();

        String forObjectLoc = restTemplateLoc.getForObject("http://"
                                                           + nextServerFromEurekaLoc.getIPAddr()
                                                           + ":"
                                                           + nextServerFromEurekaLoc.getPort()
                                                           + "/employee/greet",
                                                           String.class);
        return forObjectLoc;
    }


}
