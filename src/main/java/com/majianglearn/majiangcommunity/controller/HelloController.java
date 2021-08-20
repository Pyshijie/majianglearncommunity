package com.majianglearn.majiangcommunity.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/index")
    public String index(@RequestParam(name = "name",defaultValue = "World")String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
