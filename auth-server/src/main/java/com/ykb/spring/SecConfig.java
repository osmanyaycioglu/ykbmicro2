package com.ykb.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class SecConfig {

    @Value("${my.jwt.private.key}")
    private String privateKey;

    @Value("${my.jwt.public.key}")
    private String publicKey;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverterLoc = new JwtAccessTokenConverter();
        accessTokenConverterLoc.setSigningKey(this.privateKey);
        accessTokenConverterLoc.setVerifierKey(this.publicKey);
        return accessTokenConverterLoc;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        JwtTokenStore jwtTokenStoreLoc = new JwtTokenStore(this.accessTokenConverter());
        return jwtTokenStoreLoc;
    }

    @Bean
    public BCryptPasswordEncoder cryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyUserDetailService myUserDetailService() {
        return new MyUserDetailService();
    }

}
