package com.example.springexamples.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author VKT-HALILU
 * @date 3/5/2020
 */

@RestController
public class HomeController {
    @RequestMapping("/home")
    public String index(){
        return "Welcome to the home page";
    }
}
