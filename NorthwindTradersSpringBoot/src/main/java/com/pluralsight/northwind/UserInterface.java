package com.pluralsight.northwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserInterface {

    @Autowired
    private ProductDao productDao;

    public UserInterface() {

    }

    public void display() {
        Scanner scanner = new Scanner(System.in);

        boolean choosing = true;
        while (choosing) {
            System.out.print("""
                1. List products
                2. Add product
                3. Delete product by ID
                4. Update product by ID
                5. Search product by ID
                6. Exit
                Please choose an option: 
                """);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > 6) {
                System.out.println("Invalid input... please try again!");
            } else {
                switch (choice) {
                    case 1 -> productDao.getAll().forEach(System.out::println);
                    case 2 -> addProduct(scanner);
                    case 3 -> deleteProduct(scanner);
                    case 4 -> updateProduct(scanner);
                    case 5 -> searchProduct(scanner);
                    case 6 -> {
                        System.out.println("Exiting application...");
                        choosing = false;
                    }
                }
            }
        }
    }

    private void searchProduct(Scanner scanner) {
        System.out.println("Enter a product ID (int): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product p = productDao.searchProductById(id);
        if (p != null) {
            System.out.println("Found product: " + p);
        } else {
            System.out.println("There is no product with that ID... ");
        }
    }

    private void updateProduct(Scanner scanner) {
        System.out.println("Enter a product ID (int): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product productToUpdate = productDao.searchProductById(id);
        if (productToUpdate != null) {
            System.out.println("Enter a new name (String): ");
            String name = scanner.nextLine().trim();
            System.out.println("Enter a new category (String: ");
            String category = scanner.nextLine().trim();
            System.out.println("Enter a new price (double): ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            productDao.updateProduct(productToUpdate, name, category, price);
        }
    }

    private void deleteProduct(Scanner scanner) {
        System.out.println("Enter a product ID (int): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        productDao.deleteProductById(id);
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

        productDao.add(new Product(id, name, category, price));
    }
}
