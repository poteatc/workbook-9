package com.pluralsight.northwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Gives error can't inject User Interface Bean without this annotation
@SpringBootApplication
public class NorthwindApplication implements CommandLineRunner {
    //@Autowired
    //private ProductDao productDao;

    @Autowired
    UserInterface ui;

    @Override
    public void run(String... args) throws Exception {
        ui.display();
    }
}
