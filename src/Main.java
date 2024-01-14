import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    // Main method where the program execution begins
    public static void main(String[] args) {
        // Creating a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Creating an ArrayList to store Product objects
        ArrayList<Product> products = new ArrayList<>();

        // Creating an instance of WestminsterShoppingManager with the product list
        WestminsterShoppingManager manager = new WestminsterShoppingManager(products);

        // Loading data from the file before starting
        manager.loadDataFromFile();

        // Variable to store the user's menu option
        int option;

        // Do-while loop for the main menu
        do {
            try {
                // Displaying menu options for the user
                System.out.println("Do you want to open the GUI or the console?");
                System.out.println("1. GUI");
                System.out.println("2. Console");
                System.out.println("0. Exit");
                System.out.print("Enter your option: ");

                // Reading the user's menu option
                option = scanner.nextInt();

                // take the newline character
                scanner.nextLine();

                // Switch statement to handle different menu options
                switch (option) {
                    case 1:
                        // Open GUI
                        WestminsterShoppingManager.westminsterFrame frame =
                                new WestminsterShoppingManager.westminsterFrame("My Frame", manager.getProducts());
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
                // Handling input mismatch exception
                System.out.println("Invalid input. Please enter a valid integer option.");

                // take invalid input
                scanner.nextLine();

                // Set option to -1 to continue the loop
                option = -1;
            }
        } while (option != 0); // Continue the loop until the user chooses to exit
    }
}


