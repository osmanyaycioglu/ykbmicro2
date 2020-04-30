package com.ykb.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ykb.spring.dao.IUserDao;
import com.ykb.spring.model.User;

@RestController
@RequestMapping("/user")
public class UserRest {

    @Autowired
    private IUserDao ud;

    @PostMapping("/add")
    public String addUser(@RequestBody final User user) {
        this.ud.save(user);
        return "OK";
    }

    @GetMapping("/remove/{username}")
    public String addUser(@PathVariable("username") final String username) {
        this.ud.deleteById(username);
        return "OK";
    }

    @GetMapping("/getall")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Iterable<User> findAllLoc = this.ud.findAll();
        for (User userLoc : findAllLoc) {
            users.add(userLoc);
        }
        return users;
    }


}
