import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        WestminsterShoppingManager manager = new WestminsterShoppingManager(products);
        manager.loadDataFromFile(); // Load data from the file before starting

        int option;

        do {
            try {
                System.out.println("Do you want to open the GUI or the console?");
                System.out.println("1. GUI");
                System.out.println("2. Console");
                System.out.println("0. Exit");
                System.out.print("Enter your option: ");

                option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (option) {
                    case 1:
                        // Open GUI
                        WestminsterShoppingManager.westminsterFrame frame = new WestminsterShoppingManager.westminsterFrame("My Frame", manager.getProducts());
                        frame.setSize(550, 700);
                        frame.setVisible(true);
                        break;
                    case 2:
                        // Open console-based application
                        manager.menueDisplay();
                        break;
                    case 0:
                        System.out.println("Thank you for using the Westminster shopping Application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a valid option.");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter a valid integer option.");
                scanner.nextLine(); // Consume invalid input
                option = -1; // Set option to -1 to continue the loop
            }
        } while (option != 0);

    }
}

