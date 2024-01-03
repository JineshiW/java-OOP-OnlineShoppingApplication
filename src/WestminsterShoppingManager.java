import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WestminsterShoppingManager implements ShoppingCart {
    private List<Product> products;

    public WestminsterShoppingManager() {
        this.products = new ArrayList<>();
    }


    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n---- Westminster Shopping Manager ----");
            System.out.println("1. Add a new product");
            System.out.println("2. Delete a product");
            System.out.println("3. Print the list of products");
            System.out.println("4. Save to file");
            System.out.println("0. Exit");
            System.out.print("Please enter your option: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    printProducts();
                    break;
                case 4:
                    saveToFile();
                    break;
                case 0:
                    System.out.println("Exiting Westminster Shopping Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (option != 0);
    }

    private void addProduct() {
        // Implement logic to add a new product (ElectronicProduct or ClothingProduct) to the system.
        // You can use Scanner to get input from the user.
        // Add the created product to the 'products' list.

    }

    private void deleteProduct() {
        // Implement logic to delete a product from the system based on product ID.
        // Display information about the deleted product and the total number of products left.
    }

    private void printProducts() {
        // Implement logic to print the list of products sorted alphabetically by product ID.
        // Display all information about each product and specify whether it is electronics or clothing.
        // You can use Collections.sort() to sort the products list.
    }

    private void saveToFile() {
        // Implement logic to save the list of products to a file.
        // You can use FileWriter to write to a file.
    }
}
