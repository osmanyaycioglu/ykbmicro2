package com.ykb.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.NoOpPing;

public class MyRibbonConfig {

    @Autowired
    private ILoadBalancer loab;


    @Bean
    public IRule getRule() {
        return new AvailabilityFilteringRule();
    }

    @Bean
    public IPing getIPing() {
        return new NoOpPing();
    }

}
