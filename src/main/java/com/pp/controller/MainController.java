package com.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller

public class MainController {

    @GetMapping("/index")
        public String Main(){
        return "/index";
    }
}
