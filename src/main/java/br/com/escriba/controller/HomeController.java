package br.com.escriba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    
    @GetMapping("")
    public  String getHome() {
        return "inicio";
    }
   
    
}
