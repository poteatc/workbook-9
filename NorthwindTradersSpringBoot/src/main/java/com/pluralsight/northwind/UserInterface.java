package com.pluralsight.northwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserInterface {

    @Autowired
    private SimpleProductDao simpleProductDao;

    public UserInterface() {

    }

    public void display() {
        Scanner scanner = new Scanner(System.in);

        boolean choosing = true;
        while (choosing) {
            System.out.print("""
                1. List products
                2. Add product
                3. Exit
                Please choose an option: 
                """);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid input... please try again!");
            } else {
                switch (choice) {
                    case 1 -> simpleProductDao.getAll().forEach(System.out::println);
                    case 2 -> addProduct(scanner);
                    case 3 -> {
                        System.out.println("Exiting application...");
                        choosing = false;
                    }
                }
            }
        }
    }

    private void addProduct(Scanner scanner) {
        System.out.println("Enter a product ID (int): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter a product name (String): ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter a product category (String): ");
        String category = scanner.nextLine().trim();

        System.out.println("Enter a price (double): ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        simpleProductDao.add(new Product(id, name, category, price));
    }
}
