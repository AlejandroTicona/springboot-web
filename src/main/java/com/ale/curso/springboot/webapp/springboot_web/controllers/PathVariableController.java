package com.ale.curso.springboot.webapp.springboot_web.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ale.curso.springboot.webapp.springboot_web.models.User;
import com.ale.curso.springboot.webapp.springboot_web.models.dto.ParamDto;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @Value("${config.username}")
    private String username;
    // @Value("${config.message}")
    // private String message;
    @Value("${config.listOfValues}")
    private String[] listOfValues;
    @Value("${config.code}")
    private Integer code;
    @Value("#{ '${config.listOfValues}'.toUpperCase().split(',')}")
    private String[] valueList;
    @Value("#{ '${config.listOfValues}'.toUpperCase()}")
    private String valueString;

    @Value("#{ ${config.valuesMap}}")
    private Map<String, Object> valuesMap;

    @Value("#{ ${config.valuesMap}.product}")
    private String product;

    @Value("#{ ${config.valuesMap}.price}")
    private Long price;

    @Autowired
    private Environment environment;

    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable String message) {
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return param;
    }

    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {

        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);
        return json;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        // hacer algo con el usuario, un save en la BD
        user.setName(user.getName().toUpperCase());
        return user;
    }

    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.message}") String message) {
        Map<String, Object> json = new HashMap<>();
        json.put("username", username);
        json.put("code", code);
        json.put("message", message);
        json.put("message2", environment.getProperty("config.message"));
        json.put("code2", environment.getProperty("config.code", Long.class));
        json.put("listOfValues", listOfValues);
        json.put("valueList", valueList);
        json.put("valueString", valueString);
        json.put("valueMap", valuesMap);
        json.put("product", product);
        json.put("price", price);

        return json;
    }
}
