package com.remly.remly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String getLoginPage() {
        try{
            return "login";
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }

    }
}
