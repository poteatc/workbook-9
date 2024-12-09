package com.pluralsight.NorthwindTradersAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(String country) {
        return country == null ? "Hello World" : "Hello " + country;
    }
}
