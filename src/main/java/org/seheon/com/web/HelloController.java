package org.seheon.com.web;

import org.seheon.com.domain.users.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/user")
    public User user(@RequestParam("id") String id,
                     @RequestParam("password") String password){
        return new User(id, password);
    }
}
