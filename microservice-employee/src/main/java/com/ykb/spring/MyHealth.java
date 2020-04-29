package com.ykb.spring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealth implements HealthIndicator {

    @Override
    public Health health() {
        ErrorObj errorObjLoc = new ErrorObj();
        errorObjLoc.setErrorCause(1001);
        errorObjLoc.setErrorDesc("Şu anda servis veremiyoruz");
        return Health.up()
                     .withDetail("test",
                                 errorObjLoc)
                     .build();
    }

}
