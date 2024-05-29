package com.mycompany.jeel;

import org.springframework.web.bind.annotation.GetMapping;

public class MainCOntroller {
    @GetMapping("")
    public String showHomePage()
    {
        System.out.println("main controller");
        return "index";
    }
}
