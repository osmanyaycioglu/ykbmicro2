package com.ykb.spring;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ykb.spring.dao.IUserDao;

public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder b;

    @Autowired
    private IUserDao              ud;

    @Override
    public UserDetails loadUserByUsername(final String usernameParam) throws UsernameNotFoundException {
        if (usernameParam.equals("admin")) {
            return new User("admin",
                            this.b.encode("1234"),
                            AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
        }
        Optional<com.ykb.spring.model.User> findByIdLoc = this.ud.findById(usernameParam);
        if (findByIdLoc.isPresent()) {
            com.ykb.spring.model.User userLoc = findByIdLoc.get();
            String rolesLoc = userLoc.getRoles();
            String[] splitLoc = rolesLoc.split(rolesLoc);

            return new User(userLoc.getUsername(),
                            this.b.encode(userLoc.getPassword()),
                            AuthorityUtils.createAuthorityList(splitLoc));
        }
        throw new UsernameNotFoundException("Böyle bir user yok");
    }

}
