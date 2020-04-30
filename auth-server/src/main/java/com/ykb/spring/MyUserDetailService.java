package com.ykb.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder b;

    @Override
    public UserDetails loadUserByUsername(final String usernameParam) throws UsernameNotFoundException {
        return new User("test",
                        this.b.encode("test12"),
                        AuthorityUtils.createAuthorityList("ROLE_KULLANICI"));
    }

}
