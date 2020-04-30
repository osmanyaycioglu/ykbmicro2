package com.ykb.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class MyAuthServConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private JwtAccessTokenConverter     tc;

    @Autowired
    private JwtTokenStore               ts;

    @Autowired
    private BCryptPasswordEncoder       be;

    @Autowired
    private AuthenticationConfiguration authm;

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpointsParam) throws Exception {
        endpointsParam.authenticationManager(this.authm.getAuthenticationManager())
                      .tokenStore(this.ts)
                      .accessTokenConverter(this.tc)
                      .approvalStore(new InMemoryApprovalStore());
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer securityParam) throws Exception {
        securityParam.tokenKeyAccess("permitAll()")
                     .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clientsParam) throws Exception {
        clientsParam.inMemory()
                    .withClient("client1")
                    .secret(this.be.encode("1234"))
                    .authorizedGrantTypes("authorization_code",
                                          "password",
                                          "client_credentials",
                                          "refresh_token")
                    .authorities("ROLE_TRUSTED_CLIENT")
                    .scopes("xyz",
                            "abc")
                    .autoApprove(true);
    }
}
