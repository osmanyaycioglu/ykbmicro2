package com.ykb.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class MyAuthServConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpointsParam) throws Exception {
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer securityParam) throws Exception {
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clientsParam) throws Exception {
    }
}
