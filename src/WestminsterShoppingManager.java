import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    private ArrayList<Product> products;
    public Scanner input = new Scanner(System.in);

    int maxProducts = 50;

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
        if (products.size() >= maxProducts) {
            //if so printing the below message to the user
            System.out.println("The maximum limit for adding products is reached. Therefore, you can no longer add any more products.");
        }
        // if not, allowing the user to proceed with adding products
        else {

            // displaying a message to the user by asking which type of product to add
            System.out.print(
                    "Select the type of product that you wish to add? \n" +
                            "1. Electronics \n" +
                            "2. Clothing"
                            + "\n" +
                            "Enter your choice: ");
            //storing the result in an int data type variable
            int prodChoice = input.nextInt();

            //using switch case to proceed with either adding electronics or clothing depending on the user entered choice
            switch (prodChoice) {
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
//        for(int i=0;i<products.size();i++){
//            System.out.println(products.get(i).getProductID() +" " +products.get(i).getProductName());
//
//        }


    }

    private void addElectronics() {
        int pEID;
        do {
            System.out.print("Please enter electronics ID:");
            pEID = input.nextInt();

            // Check if the ID already exists
            if (isIdAlreadyExists(pEID)) {
                System.out.println("Electronics with ID " + pEID + " already exists. Please choose a different ID.");
            }
        } while (isIdAlreadyExists(pEID));

        String electronicName;
        do {
            System.out.print("Please enter electronic name:");
            electronicName = input.next();

            // Check if the name already exists for electronic category
            if (isNameAlreadyExists(electronicName, "Electronic")) {
                System.out.println("Electronic with name '" + electronicName + "' already exists. Please type a new name.");
            }
        } while (isNameAlreadyExists(electronicName, "Electronic"));

        System.out.print("Please enter electronics price:");
        double price = input.nextDouble();

        System.out.print("Please enter electronics brand:");
        String brand = input.next();

        System.out.print("Please enter electronics warranty period (in months):");
        int warrantyPeriod = input.nextInt();

        System.out.println("Electronics details added successfully.");
        Electronic electronics = new Electronic(pEID, electronicName, price, brand, warrantyPeriod);
        products.add(electronics);
    }

    private void addClothing() {
        int pCID;
        do {
            System.out.print("Please enter clothing ID:");
            pCID = input.nextInt();

            // Check if the ID already exists
            if (isIdAlreadyExists(pCID)) {
                System.out.println("Clothing with ID " + pCID + " already exists. Please choose a different ID.");
            }
        } while (isIdAlreadyExists(pCID));

        String clothingName;
        do {
            System.out.print("Please enter clothing name:");
            clothingName = input.next();

            // Check if the name already exists for clothing category
            if (isNameAlreadyExists(clothingName, "Clothing")) {
                System.out.println("Clothing name '" + clothingName + "' already exists. Please type a new name.");
            }
        } while (isNameAlreadyExists(clothingName, "Clothing"));


        System.out.print("Enter clothing price:");
        double price = input.nextDouble();

        System.out.print("Enter clothing size:");
        int size = input.nextInt();

        System.out.print("Enter clothing color:");
        String color = input.next();

        System.out.println("Clothing details added successfully.");
        Clothing clothing = new Clothing(pCID, clothingName, price, size, color);
        products.add(clothing);

    }

    //method created to check if the product id already exists in the array list or not
    private boolean isIdAlreadyExists(int id) {
        for (Product clothing : products) {
            if (clothing.getProductID() == id) {
                return true; // ID already exists in the arrayList
            }
        }
        return false; // ID does not exist in the arrayList
    }


    private boolean isNameAlreadyExists(String name, String type) {
        for (Product product : products) {
            //checks if the product name is a already available under the instance of clothing class in clothing type by ignoring the case
            if (product.getProductName().equalsIgnoreCase(name) && product instanceof Clothing && type.equals("Clothing")) {
                return true;
                //else checks if the product name is a already available under the instance of electronic class in electronic type by ignoring the case
            } else if (product.getProductName().equalsIgnoreCase(name) && product instanceof Electronic && type.equals("Electronic")) {
                return true;
            }
        }
        return false;
    }


    public void removeProduct() {

        System.out.println("Enter the product ID to delete: ");
        int productId = input.nextInt();
        boolean found = false;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getProductID() == productId) {
//                printProducts(); // Display product information before deletion
                products.remove(i);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("The selected item has been deleted successfully.");
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }

        System.out.println("Total number of products left in stock : " + products.size());
    }


    public void printProducts() {
        // Sorting the 'products' list using Collections.sort method.
        // A comparator is created using Comparator.comparingInt, which compares the Product objects based on their product IDs.
        // The method reference Product::getProductID is used to extract the product ID for comparison.
        Collections.sort(products, Comparator.comparingInt(Product::getProductID));

        for (Product product : products) {
            System.out.println();
            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Price: " + product.getPriceOfProduct());

            // Checking if the category of the product items are clothing or electronic
            if (product instanceof Electronic) {
                Electronic electronic = (Electronic) product;
                System.out.println("Item Type: Electronics");
                System.out.println("Brand: " + electronic.getBrand());
                System.out.println("Warranty Period: " + electronic.getWarrantyPeriod() + " months");
            } else if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                System.out.println("Item Type: Clothing");
                System.out.println("Size: " + clothing.getSize());
                System.out.println("Color: " + clothing.getColor());
            }

            System.out.println();  // Add a blank line between products for better readability.
        }
    }


    public void saveToFile() {
        if (!products.isEmpty()) {
            try {
                File file = new File("prodDetails.txt");

                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists");
                }

                FileWriter fileWriter = new FileWriter(file);
                PrintWriter printWriter = new PrintWriter(fileWriter);

                for (Product product : products) {
                    printWriter.println("Product ID: " + product.getProductID());
                    printWriter.println("Product Name: " + product.getProductName());
                    printWriter.println("Price: " + product.getPriceOfProduct());

                    if (product instanceof Electronic) {
                        Electronic electronic = (Electronic) product;
                        printWriter.println("Item Type: Electronics");
                        printWriter.println("Brand: " + electronic.getBrand());
                        printWriter.println("Warranty Period: " + electronic.getWarrantyPeriod() + " months");
                    } else if (product instanceof Clothing) {
                        Clothing clothing = (Clothing) product;
                        printWriter.println("Item Type: Clothing");
                        printWriter.println("Size: " + clothing.getSize());
                        printWriter.println("Color: " + clothing.getColor());
                    }
                    printWriter.println(); //add a line to separate data
                }

                // Close PrintWriter
                printWriter.close();
                // Close FileWriter
                fileWriter.close();
                System.out.println("Successfully wrote data to a file");


                // for creation
            } catch (IOException e) {
                System.out.println("An error has occurred");
                //print the error message
                e.printStackTrace();
            }

        }
//        public void calculateTotalCost () {
//
//        }
    }

}

