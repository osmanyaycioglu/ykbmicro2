package com.ykb.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bep;

    @Autowired
    private MyUserDetailService   mu;

    @Override
    protected void configure(final AuthenticationManagerBuilder authParam) throws Exception {
        authParam.userDetailsService(this.mu)
                 .passwordEncoder(this.bep);
    }

    @Override
    protected void configure(final HttpSecurity httpParam) throws Exception {
        httpParam.cors()
                 .disable()
                 .csrf()
                 .disable()
                 .authorizeRequests()
                 .antMatchers("/hello")
                 .permitAll()
                 .antMatchers("/user/**")
                 .hasRole("ADMIN")
                 .antMatchers("/**")
                 .authenticated()
                 .and()
                 .httpBasic();
    }
}
