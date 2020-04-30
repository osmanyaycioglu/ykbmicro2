package com.ykb.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ykb.spring.model.User;

@Repository
public interface IUserDao extends CrudRepository<User, String> {

    List<User> findByUsernameAndPassword(String username,
                                         String password);

    @Query("select u from User u where u.username=:us and u.password=:pass")
    List<User> getUs(@Param("us") String username,
                     @Param("pass") String password);

    @Query(value = "SELECT * FROM USER WHERE USERNAME=:us AND PASSWORD=:pass", nativeQuery = true)
    List<User> getUs2(@Param("us") String username,
                      @Param("pass") String password);

}
