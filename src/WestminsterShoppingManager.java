import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    private List<Product> products;
    public  Scanner input=new Scanner(System.in);

    int maxProducts=50;

    public WestminsterShoppingManager() {
        this.products = new ArrayList<>();
    }


    public void menueDisplay() {
        Scanner input = new Scanner(System.in);
        int option = -1;

        do {
            try {
                System.out.println("\n---- Westminster Shopping Manager ----");
                System.out.println("1. Add a new product");
                System.out.println("2. Delete a product");
                System.out.println("3. Print the list of products");
                System.out.println("4. Save to file");
                System.out.println("0. Exit");
                System.out.print("Please enter your option: ");

                option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        removeProduct();
                        break;
                    case 3:
                        printProducts();
                        break;
                    case 4:
                        saveToFile();
                        break;
                    case 0:
                        System.out.println("Thank you for using the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                input.nextLine(); // Consume newline to avoid infinite loop
            }
        } while (option != 0);
    }


    // creating addProduct method that will add the electronic and clothing details
    public void addProduct() {
        //checking if the size of the products list is greater than ot equal to the product max limit
        if(products.size()>=maxProducts){
            //if so printing the below message to the user
            System.out.println("The maximum limit for adding products is reached. Therefore, you can no longer add any more products.");
        }
        // if not, allowing the user to proceed with adding products
        else {

            // displaying a message to the user by asking which type of product to add
            System.out.println(
                    "Select the type of product that you wish to add? \n" +
                    "1. Electronics \n" +
                    "2. Clothing"
                    +")");
            //storing the result in a int data type variable
            int prodChoice= input.nextInt();
            input.nextLine();

            //using switch case to proceed with either adding electronics or clothing depending on the user entered choice
            switch (prodChoice){
                case 1:
                    addElectronics();
                    break;
                case 2:
                    addClothing();
                    break;
                // if neither of the above 2 options were entered by the user the default message wil be displayed
                default:
                    System.out.println("The input is not valid, please enter a valid input.");
                    break;
            }
        }

    }

    private void  addElectronics(){
        System.out.println("Please enter electronics name:");
        String name = input.nextLine();

        System.out.println("Please enter electronics price:");
        double price = input.nextDouble();
        input.nextLine(); // Consume newline

        System.out.println("Please enter electronics brand:");
        String brand = input.nextLine();

        System.out.println("Please enter electronics warranty period (in months):");
        int warrantyPeriod = input.nextInt();

        System.out.println("Electronics details added successfully.");
        Electronic electronics = new Electronic(name, price, brand, warrantyPeriod);
        products.add(electronics);
    }

    private void addClothing() {

        System.out.println("Enter clothing name:");
        String name = input.nextLine();

        System.out.println("Enter clothing price:");
        double price = input.nextDouble();
        input.nextLine(); // Consume newline

        System.out.println("Enter clothing size:");
        int size = input.nextInt();

        System.out.println("Enter clothing color:");
        String color = input.nextLine();

        System.out.println("Clothing details added successfully.");
        Clothing clothing = new Clothing(name, price, size, color);
        products.add(clothing);

    }



    public void removeProduct() {
        // Implement logic to delete a product from the system based on product ID.
        // Display information about the deleted product and the total number of products left.
    }

    public void printProducts() {
        // Implement logic to print the list of products sorted alphabetically by product ID.
        // Display all information about each product and specify whether it is electronics or clothing.
        // You can use Collections.sort() to sort the products list.
    }

    public void saveToFile() {
        // Implement logic to save the list of products to a file.
        // You can use FileWriter to write to a file.
    }
    public void calculateTotalCost(){

    }
}
