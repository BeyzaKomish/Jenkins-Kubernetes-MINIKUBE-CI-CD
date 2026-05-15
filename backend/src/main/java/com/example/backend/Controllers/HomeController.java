package com.example.backend.Controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String getHome(Model model){
        return "Hello from Spring Boot! I am running on pod: ";
    }
//    @GetMapping("/about")
//    public String getAbout() {
//        return "about";
//    }
}
