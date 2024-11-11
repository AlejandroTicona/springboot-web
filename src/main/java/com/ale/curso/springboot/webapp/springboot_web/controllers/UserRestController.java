package com.ale.curso.springboot.webapp.springboot_web.controllers;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ale.curso.springboot.webapp.springboot_web.models.User;
import com.ale.curso.springboot.webapp.springboot_web.models.dto.UserDto;


@RestController
@RequestMapping("/api")
public class UserRestController {
    @GetMapping(path="/details")
    public UserDto details() {
        UserDto userDto = new UserDto();
        User user = new User("Alejandro","Ticona");
        userDto.setUser(user);
        userDto.setTitle("Hola mundo Spring");
        return userDto;
    }

    @GetMapping("/list")
    public List<User> list(){
        User juan = new User("Juan","Noseque");
        User pepe = new User("Pepe","Doe");
        User jhon = new User("Jhon","Dou");

        List<User> users = Arrays.asList(juan, pepe, jhon);
        // List<User> users = new ArrayList<>();
        // users.add(juan);
        // users.add(pepe);
        // users.add(jhon);

        return users;
    }

    @GetMapping(path="/details-map")
    public Map<String, Object> detailsMap() {
        User user = new User("Alejandro","Ticona");
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola mundo Spring");
        body.put("user", user);
        return body;
    }
}
