// Importing necessary packages
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.*;

// Class representing the WestminsterShoppingManager implementing the ShoppingManager interface
public class WestminsterShoppingManager implements ShoppingManager {

    // ArrayList to store products
    private ArrayList<Product> products;

    // Scanner for user input
    public Scanner input = new Scanner(System.in);

    // Maximum number of products allowed
    int maxProducts = 50;

    // Constructor for WestminsterShoppingManager
    public WestminsterShoppingManager(ArrayList<Product> products) {
        this.products = products;
    }

    // Method to display the main menu
    public void menueDisplay() {
        Scanner input = new Scanner(System.in);
        int option = -1;

        // Display menu until the user chooses to exit (option 0)
        do {
            try {
                System.out.println("\n---- Westminster Shopping Manager ----");
                System.out.println("1. Add a new product");
                System.out.println("2. Delete a product");
                System.out.println("3. Print the list of products");
                System.out.println("4. Save to file");
                System.out.println("0. Exit");
                System.out.print("Please enter your option: ");

                // Read user input
                option = input.nextInt();
                input.nextLine();

                // Switch based on user's choice
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
            } catch (InputMismatchException ex) {
                // Handling invalid input (non-integer)
                System.out.println("Invalid input. Please enter a valid integer option.");
                input.nextLine(); // Consume invalid input
            } catch (Exception e) {
                // Handling other exceptions
                System.out.println("Error: " + e.getMessage());
                input.nextLine(); // Consume newline to avoid infinite loop
            }
        } while (option != 0);
    }

    // Implementing the addProduct method from the ShoppingManager interface
    public void addProduct() {
        // Check if the maximum limit for adding products is reached
        if (products.size() >= maxProducts) {
            System.out.println("The maximum limit for adding products is reached. Therefore, you can no longer add any more products.");
        } else {
            // Prompt the user to select the type of product to add
            System.out.print(
                    "Select the type of product that you wish to add? \n" +
                            "1. Electronics \n" +
                            "2. Clothing"
                            + "\n" +
                            "Enter your choice: ");

            // Read user input for the product type
            int prodChoice = input.nextInt();

            // Switch based on user's choice
            switch (prodChoice) {
                case 1:
                    // Call the method to add an electronics product
                    addElectronics();
                    break;
                case 2:
                    // Call the method to add a clothing product
                    addClothing();
                    break;
                default:
                    // Display a message for an invalid input
                    System.out.println("The input is not valid, please enter a valid input.");
                    break;
            }
        }
    }


    // Method to add an electronics product
    private void addElectronics() {
        int pEID;

        // Prompt the user to enter the electronics ID
        do {
            System.out.print("Please enter electronics ID:");
            pEID = input.nextInt();

            // Check if the entered ID already exists
            if (isIdAlreadyExists(pEID)) {
                System.out.println("Electronics with ID " + pEID + " already exists. Please choose a different ID.");
            }
        } while (isIdAlreadyExists(pEID));

        String electronicName;

        // Prompt the user to enter the electronic name
        do {
            System.out.print("Please enter electronic name:");
            electronicName = input.next();

            // Check if the entered name already exists for electronic category
            if (isNameAlreadyExists(electronicName, "Electronic")) {
                System.out.println("Electronic with name '" + electronicName + "' already exists. Please type a new name.");
            }
        } while (isNameAlreadyExists(electronicName, "Electronic"));

        double price = 0;

        // Use try-catch to handle potential input mismatch exception for electronics price
        boolean validInput = false;
        do {
            try {
                // Prompt the user to enter the electronics price
                System.out.print("Enter electronics price:");
                price = input.nextDouble();

                if (price < 0) {
                    throw new IllegalArgumentException("Price cannot be negative.");
                }

                validInput = true; // If no exception occurs, set validInput to true to exit the loop
            } catch (InputMismatchException e) {
                // Handling invalid input for price
                System.out.println("Invalid input. Please enter a valid numeric value for electronics price.");
                input.next(); // Consume the invalid input to avoid an infinite loop
            } catch (IllegalArgumentException e) {
                // Handling negative price
                System.out.println(e.getMessage()); // Display the error message
            }
        } while (!validInput);

        String brand;

        // Prompt the user to enter the electronics brand
        System.out.print("Please enter electronics brand:");
        brand = input.next();

        int warrantyPeriod = 0;

        // Use try-catch to handle potential input mismatch exception for electronics warranty period
        validInput = false;
        do {
            try {
                // Prompt the user to enter the electronics warranty period
                System.out.print("Please enter electronics warranty period (in months):");
                warrantyPeriod = input.nextInt();

                if (warrantyPeriod < 0) {
                    throw new IllegalArgumentException("Warranty period cannot be negative.");
                }

                validInput = true; // If no exception occurs, set validInput to true to exit the loop
            } catch (InputMismatchException e) {
                // Handling invalid input for warranty period
                System.out.println("Invalid input. Please enter a valid integer for electronics warranty period.");
                input.next(); // Consume the invalid input to avoid an infinite loop
            } catch (IllegalArgumentException e) {
                // Handling negative warranty period
                System.out.println(e.getMessage()); // Display the error message
            }
        } while (!validInput);

        // Display success message and create a new Electronic object
        System.out.println("Electronics details added successfully.");
        Electronic electronics = new Electronic(pEID, electronicName, price, brand, warrantyPeriod);

        // Add the newly created Electronic object to the products list
        products.add(electronics);
    }

    // Method to add a clothing product
    private void addClothing() {
        int pCID;

        // Prompt the user to enter the clothing ID
        do {
            System.out.print("Please enter clothing ID:");
            pCID = input.nextInt();

            // Check if the entered ID already exists
            if (isIdAlreadyExists(pCID)) {
                System.out.println("Clothing with ID " + pCID + " already exists. Please choose a different ID.");
            }
        } while (isIdAlreadyExists(pCID));

        String clothingName;

        // Prompt the user to enter the clothing name
        do {
            System.out.print("Please enter clothing name:");
            clothingName = input.next();

            // Check if the entered name already exists for clothing category
            if (isNameAlreadyExists(clothingName, "Clothing")) {
                System.out.println("Clothing name '" + clothingName + "' already exists. Please type a new name.");
            }
        } while (isNameAlreadyExists(clothingName, "Clothing"));

        double price = 0;

        // Use try-catch to handle potential input mismatch exception for clothing price
        boolean validInput = false;
        do {
            try {
                // Prompt the user to enter the clothing price
                System.out.print("Enter clothing price:");
                price = input.nextDouble();

                if (price < 0) {
                    throw new IllegalArgumentException("Price cannot be negative.");
                }

                validInput = true; // If no exception occurs, set validInput to true to exit the loop
            } catch (InputMismatchException e) {
                // Handling invalid input for price
                System.out.println("Invalid input. Please enter a valid numeric value for clothing price.");
                input.next(); // Consume the invalid input to avoid an infinite loop
            } catch (IllegalArgumentException e) {
                // Handling negative price
                System.out.println(e.getMessage()); // Display the error message
            }
        } while (!validInput);

        int size = 0;

        // Use try-catch to handle potential input mismatch exception for clothing size
        validInput = false;
        do {
            try {
                // Prompt the user to enter the clothing size
                System.out.print("Enter clothing size:");
                size = input.nextInt();

                if (size < 0) {
                    throw new IllegalArgumentException("Size cannot be negative.");
                }

                validInput = true; // If no exception occurs, set validInput to true to exit the loop
            } catch (InputMismatchException e) {
                // Handling invalid input for size
                System.out.println("Invalid input. Please enter a valid integer for clothing size.");
                input.next(); // Consume the invalid input to avoid an infinite loop
            } catch (IllegalArgumentException e) {
                // Handling negative size
                System.out.println(e.getMessage()); // Display the error message
            }
        } while (!validInput);

        // Prompt the user to enter the clothing color
        System.out.print("Enter clothing color:");
        String color = input.next();

        // Display success message and create a new Clothing object
        System.out.println("Clothing details added successfully.");
        Clothing clothing = new Clothing(pCID, clothingName, price, size, color);

        // Add the newly created Clothing object to the products list
        products.add(clothing);
    }


    // Method to check if a product with the given ID already exists in the products list
    private boolean isIdAlreadyExists(int id) {
        // Iterate through the products list
        for (Product product : products) {
            // Check if the product's ID matches the given ID
            if (product.getProductID() == id) {
                return true; // ID already exists in the arrayList
            }
        }
        return false; // ID does not exist in the arrayList
    }


    // Method to check if a product with the given name already exists for the specified type in the products list
    private boolean isNameAlreadyExists(String name, String type) {
        // Iterate through the products list
        for (Product product : products) {
            // Check if the product's name matches the given name and is of the specified type (Clothing or Electronic)
            if (product.getProductName().equalsIgnoreCase(name) && product instanceof Clothing && type.equals("Clothing")) {
                return true; // Name already exists for the specified type in the arrayList
            } else if (product.getProductName().equalsIgnoreCase(name) && product instanceof Electronic && type.equals("Electronic")) {
                return true; // Name already exists for the specified type in the arrayList
            }
        }
        return false; // Name does not exist for the specified type in the arrayList
    }

    // Method to remove a product by ID
    // Method to remove a product based on the provided product ID
    public void removeProduct() {
        // Prompt the user to enter the product ID to delete
        System.out.println("Enter the product ID to delete: ");
        int productId = input.nextInt();

        // Initialize variables to track if the product is found, and store the deleted product
        boolean found = false;
        Product deletedProduct = null;

        // Iterate through the products list to find and remove the product with the specified ID
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getProductID() == productId) {
                // Save the deleted product for displaying information
                deletedProduct = product;
                // Remove the product from the list
                products.remove(i);
                found = true;
                break; // Exit the loop once the product is found and removed
            }
        }

        // Check if the product with the specified ID was found and removed
        if (found) {
            // Display information about the deleted product
            System.out.println("Product with ID " + productId + " has been deleted successfully.");

            // Display additional information based on the type of the deleted product
            if (deletedProduct instanceof Electronic) {
                System.out.println("Deleted Product Type: Electronics");
            } else if (deletedProduct instanceof Clothing) {
                System.out.println("Deleted Product Type: Clothing");
            }

            // Display the total number of products left in stock
            System.out.println("Total number of products left in stock: " + products.size());
        } else {
            // Display a message if the product with the specified ID was not found
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    // Method to print and display information about all products in the 'products' list
    public void printProducts() {
        // Sorting the 'products' list using Collections.sort method.
        // A comparator is created using Comparator.comparingInt, which compares the Product objects based on their product IDs.
        // The method reference Product::getProductID is used to extract the product ID for comparison.
        Collections.sort(products, Comparator.comparingInt(Product::getProductID));

        // Iterate through the sorted products list to print information about each product
        for (Product product : products) {
            System.out.println();
            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Price: " + product.getPriceOfProduct());

            // Checking if the category of the product items are clothing or electronic
            if (product instanceof Electronic) {
                // If the product is an instance of Electronic, cast it to Electronic type
                Electronic electronic = (Electronic) product;
                System.out.println("Item Type: Electronics");
                System.out.println("Brand: " + electronic.getBrand());
                System.out.println("Warranty Period: " + electronic.getWarrantyPeriod() + " months");
            } else if (product instanceof Clothing) {
                // If the product is an instance of Clothing, cast it to Clothing type
                Clothing clothing = (Clothing) product;
                System.out.println("Item Type: Clothing");
                System.out.println("Size: " + clothing.getSize());
                System.out.println("Color: " + clothing.getColor());
            }

            System.out.println();  // Add a blank line between products for better readability.
        }
    }

    // Method to save product details to a file named "prodDetails.txt"
    public void saveToFile() {
        // Check if the 'products' list is not empty
        if (!products.isEmpty()) {
            try {
                // Create a new File object representing the "prodDetails.txt" file
                File file = new File("prodDetails.txt");

                // Check if the file already exists or if a new file is created
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists");
                }

                // Create a FileWriter and PrintWriter for writing to the file
                FileWriter fileWriter = new FileWriter(file);
                PrintWriter printWriter = new PrintWriter(fileWriter);

                // Iterate through the products list to write details to the file
                for (Product product : products) {
                    printWriter.println("Product ID: " + product.getProductID());
                    printWriter.println("Product Name: " + product.getProductName());
                    printWriter.println("Price: " + product.getPriceOfProduct());

                    // Check if the product is an instance of Electronic or Clothing
                    if (product instanceof Electronic) {
                        // If Electronic, cast it to Electronic type
                        Electronic electronic = (Electronic) product;
                        printWriter.println("Item Type: Electronics");
                        printWriter.println("Brand: " + electronic.getBrand());
                        printWriter.println("Warranty Period: " + electronic.getWarrantyPeriod() + " months");
                    } else if (product instanceof Clothing) {
                        // If Clothing, cast it to Clothing type
                        Clothing clothing = (Clothing) product;
                        printWriter.println("Item Type: Clothing");
                        printWriter.println("Size: " + clothing.getSize());
                        printWriter.println("Color: " + clothing.getColor());
                    }
                    printWriter.println(); // Add a line to separate data for better readability
                }

                // Close PrintWriter
                printWriter.close();
                // Close FileWriter
                fileWriter.close();
                System.out.println("Successfully wrote data to a file");

            } catch (IOException e) {
                // Handle IOException
                System.out.println("An error has occurred");
                // Print the error message and stack trace
                e.printStackTrace();
            }
        }
    }


    // Method to load product data from the "prodDetails.txt" file
    public void loadDataFromFile() {
        try {
            // Create a new File object representing the "prodDetails.txt" file
            File file = new File("prodDetails.txt");

            // Check if the file exists
            if (!file.exists()) {
                // Create a new file if it doesn't exist
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("Failed to create the file.");
                    return;  // Exit the method if file creation fails
                }
            }

            // Create a Scanner to read from the file
            Scanner scanner = new Scanner(file);

            // Read data from the file while there is a next line
            while (scanner.hasNextLine()) {
                // Parse product details from each line of the file
                int productId = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                String productName = scanner.nextLine().split(": ")[1];
                double price = Double.parseDouble(scanner.nextLine().split(": ")[1]);
                String itemType = scanner.nextLine().split(": ")[1];

                // Check if the product is of type Electronics or Clothing
                if ("Electronics".equals(itemType)) {
                    String brand = scanner.nextLine().split(": ")[1];
                    String warrantyPeriodString = scanner.nextLine().split(": ")[1];
                    int warrantyPeriod = Integer.parseInt(warrantyPeriodString.replaceAll("\\D", ""));
                    // Create an Electronic object and add it to the products list
                    Electronic electronic = new Electronic(productId, productName, price, brand, warrantyPeriod);
                    products.add(electronic);
                } else if ("Clothing".equals(itemType)) {
                    int size = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                    String color = scanner.nextLine().split(": ")[1];
                    // Create a Clothing object and add it to the products list
                    Clothing clothing = new Clothing(productId, productName, price, size, color);
                    products.add(clothing);
                }

                // Skip the empty line between product entries
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }

            System.out.println("Data loaded successfully from file.");

            // Close the Scanner
            scanner.close();
        } catch (IOException e) {
            // Handle IOException
            System.out.println("An error occurred while processing the file.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle NumberFormatException (e.g., if data in the file is not in the expected format)
            System.out.println("Error parsing data from file. Check the file format.");
        }
    }


    // Method to retrieve the 'products' list
    public ArrayList<Product> getProducts() {
        // Return the 'products' list
        return this.products;
    }


    public static class westminsterFrame extends JFrame {
        private JTable productTable; // Table to display product information
        private DefaultTableModel tableModel; // Default table model for the product table
        private ArrayList<Product> products; // Reference to the list of products
        private JPanel productDetailsPanel; // Panel to display details of selected product

        private JButton addToCartButton; // Button to add selected product to the shopping cart
        private JButton viewCartButton; // Button to view the contents of the shopping cart
        private ShoppingCart shoppingCart; // Shopping cart object to manage added products

        // Constructor for WestminsterFrame class, representing a JFrame for Westminster Shopping Manager GUI
        public westminsterFrame(String myFrame, ArrayList<Product> products) {
            // Assigning the list of products to the local field
            this.products = products;

            // Set frame title and default close operation
            setTitle("Westminster Shopping Center");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a JComboBox for selecting product categories
            JComboBox<String> productType = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});

            // Set layout to FlowLayout with left and right margins
            setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

            // Create a label on the left side for margin
            JLabel leftMargin = new JLabel("Select Product Category");
            add(leftMargin);

            // Add the JComboBox to the frame
            add(productType);

            // Create an empty label on the right side for margin
            JLabel rightMargin = new JLabel(" ");
            add(rightMargin);

            // Initialize the table model with column headers
            tableModel = new DefaultTableModel(new Object[]{"Product ID", "Product Name", "Price", "Category", "Brand", "Size", "Color"}, 0);

            // Creating the JTable with the populated table model
            productTable = new JTable(tableModel);

            // Creating a JScrollPane to add the table and enable scrolling
            JScrollPane scrollPane = new JScrollPane(productTable);

            // Adding the JScrollPane to the frame at the CENTER position
            add(scrollPane, BorderLayout.CENTER);

            // Initialize the product details panel
            productDetailsPanel = new JPanel();
            productDetailsPanel.setLayout(new GridLayout(0, 1));
            add(productDetailsPanel, BorderLayout.SOUTH);

            // Attach a listener to the table to update the details panel when a row is selected
            productTable.getSelectionModel().addListSelectionListener(e -> {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    updateProductDetailsPanel(selectedRow);
                }
            });

            // Create a new shopping cart
            shoppingCart = new ShoppingCart();

            // Create a panel for the top-right corner
            JPanel topRightPanel = new JPanel();
            topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

            // Create the "Add to Shopping Cart" button
            addToCartButton = new JButton("Add to Cart");
            addToCartButton.addActionListener(new ActionListener() {
                //adding method to ActionLister
                public void actionPerformed(ActionEvent e) {

                    Product selectedProduct = getSelectedProduct();

                    // Add the selected product to the shopping cart
                    shoppingCart.addItem(selectedProduct);
                }
            });

            // Add the "Add to Shopping Cart" button to the top-right panel
            topRightPanel.add(addToCartButton);

            // Create the "Shopping Cart" button
            viewCartButton = new JButton("Shopping Cart");
            viewCartButton.addActionListener(new ActionListener() {
                //adding method to ActionLister
                public void actionPerformed(ActionEvent e) {

                }
            });

            // Add the top-right panel to the frame at the NORTH position
            add(topRightPanel, BorderLayout.NORTH);

            // Add the "Shopping Cart" button to the EAST position of the top-right panel
            topRightPanel.add(viewCartButton);

            // Invoking method call to load data from file to GUI
            loadDataFromFileToGUI();
        }

        // Method to load data from a file and update the GUI
        private void loadDataFromFileToGUI() {
            try {
                // Create a File object representing the data file
                File file = new File("prodDetails.txt");

                // Create a Scanner to read data from the file
                Scanner scanner = new Scanner(file);

                // Clear existing data from the table model
                tableModel.setRowCount(0);

                // Read data from the file and update the GUI
                while (scanner.hasNextLine()) {
                    // Extract product ID from the file
                    int productId = Integer.parseInt(scanner.nextLine().split(": ")[1]);

                    // Extract product name from the file
                    String productName = scanner.nextLine().split(": ")[1];

                    // Extract product price from the file
                    double price = Double.parseDouble(scanner.nextLine().split(": ")[1]);

                    // Extract product type (Electronics or Clothing) from the file
                    String itemType = scanner.nextLine().split(": ")[1];

                    // Create Electronic or Clothing object based on itemType
                    if ("Electronics".equals(itemType)) {
                        // Extract brand information for Electronics
                        String brand = scanner.nextLine().split(": ")[1];

                        // Extract warranty period information for Electronics
                        String warrantyPeriodString = scanner.nextLine().split(": ")[1];
                        int warrantyPeriod = Integer.parseInt(warrantyPeriodString.replaceAll("\\D", ""));

                        // Create an Electronic object and add it to the products list
                        Electronic electronic = new Electronic(productId, productName, price, brand, warrantyPeriod);
                        products.add(electronic);
                    } else if ("Clothing".equals(itemType)) {
                        // Extract size information for Clothing
                        int size = Integer.parseInt(scanner.nextLine().split(": ")[1]);

                        // Extract color information for Clothing
                        String color = scanner.nextLine().split(": ")[1];

                        // Create a Clothing object and add it to the products list
                        Clothing clothing = new Clothing(productId, productName, price, size, color);
                        products.add(clothing);
                    }



                // Add the loaded data to the table model
                    Object[] row = new Object[7];
                    row[0] = productId;
                    row[1] = productName;
                    row[2] = price;

                    // Populate table columns based on the product type
                    if ("Electronics".equals(itemType)) {
                        row[3] = "Electronics";
                        row[4] = ((Electronic) products.get(products.size() - 1)).getBrand();
                        row[5] = "";  // Placeholder for Size
                        row[6] = "";  // Placeholder for Color
                    } else if ("Clothing".equals(itemType)) {
                        row[3] = "Clothing";
                        row[4] = "";  // Placeholder for Brand
                        row[5] = ((Clothing) products.get(products.size() - 1)).getSize();
                        row[6] = ((Clothing) products.get(products.size() - 1)).getColor();
                    }

                    // Add the row to the table model
                    tableModel.addRow(row);

                    // Skip the empty line between product entries
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    }
                }

                // Display success message
                System.out.println("Data loaded successfully from file.");

                // Close the scanner
                scanner.close();
            } catch (FileNotFoundException e) {
                // Handle file not found exception
                System.out.println("File not found. No data loaded.");
            } catch (NumberFormatException e) {
                // Handle number format exception
                System.out.println("Error parsing data from file. Check the file format.");
            }
        }


        private void updateProductDetailsPanel(int rowIndex) {
            // Clear the existing components in the details panel
            productDetailsPanel.removeAll();

            // Get the selected product from the products list based on the provided row index
            Product selectedProduct = products.get(rowIndex);
            JLabel titleLabel = new JLabel("Select Product Details");

        // Create JLabels to display basic product details
            JLabel idLabel = new JLabel("Product ID: " + selectedProduct.getProductID());
            JLabel nameLabel = new JLabel("Product Name: " + selectedProduct.getProductName());
            JLabel priceLabel = new JLabel("Price: " + selectedProduct.getPriceOfProduct());

            // Add the basic product details JLabels to the details panel
            productDetailsPanel.add(titleLabel);
            productDetailsPanel.add(idLabel);
            productDetailsPanel.add(nameLabel);
            productDetailsPanel.add(priceLabel);

            // Additional logic based on the type of product (Electronic or Clothing)
            if (selectedProduct instanceof Electronic) {
                Electronic electronic = (Electronic) selectedProduct;
                JLabel brandLabel = new JLabel("Brand: " + electronic.getBrand());
                JLabel warrantyLabel = new JLabel("Warranty Period: " + electronic.getWarrantyPeriod() + " months");
                productDetailsPanel.add(brandLabel);
                productDetailsPanel.add(warrantyLabel);
            } else if (selectedProduct instanceof Clothing) {
                Clothing clothing = (Clothing) selectedProduct;
                JLabel sizeLabel = new JLabel("Size: " + clothing.getSize());
                JLabel colorLabel = new JLabel("Color: " + clothing.getColor());
                productDetailsPanel.add(sizeLabel);
                productDetailsPanel.add(colorLabel);
            }

            // Repaint the details panel to reflect the changes
            productDetailsPanel.revalidate();
            productDetailsPanel.repaint();

        }


        // Method to retrieve the selected product from the JTable
        private Product getSelectedProduct() {

            // Get the index of the selected row in the JTable
            int selectedRow = productTable.getSelectedRow();

            // Check if a valid row is selected
            if (selectedRow >= 0) {

                // Extract relevant data from the selected row in the JTable
                int productId = (int) productTable.getValueAt(selectedRow, 0);
                String productName = (String) productTable.getValueAt(selectedRow, 1);
                double price = (double) productTable.getValueAt(selectedRow, 2);
                String category = (String) productTable.getValueAt(selectedRow, 3);

                // Check the category to determine the type of product (Electronics or Clothing)
                if ("Electronics".equals(category)) {
                    String brand = (String) productTable.getValueAt(selectedRow, 4);
                    int warrantyPeriod = (int) productTable.getValueAt(selectedRow, 7);
                    // Return a new Electronic object with extracted data
                    return new Electronic(productId, productName, price, brand, warrantyPeriod);
                } else if ("Clothing".equals(category)) {
                    int size = (int) productTable.getValueAt(selectedRow, 5);
                    String color = (String) productTable.getValueAt(selectedRow, 6);
                    // Return a new Clothing object with extracted data
                    return new Clothing(productId, productName, price, size, color);
                } else {
                    // If the category is neither Electronics nor Clothing, return null or handle accordingly
                    return null;
                }
            } else {
                // If no row is selected, return null or handle the case accordingly
                return null;
            }
        }




    }

    // Class definition for ShoppingCartGUI, extending JFrame
    public class ShoppingCartGUI extends JFrame {

        // Instance variable to hold a reference to the ShoppingCart
        private ShoppingCart shoppingCart;

        // Constructor for ShoppingCartGUI, taking a ShoppingCart parameter
        public ShoppingCartGUI(ShoppingCart shoppingCart) {
            // Assign the provided ShoppingCart to the instance variable
            this.shoppingCart = shoppingCart;

            // Set the title of the JFrame
            setTitle("Shopping Cart");

            // Set the default close operation to close only this window, not the entire application
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a panel for displaying shopping cart contents, using GridLayout with variable rows
            JPanel contentPanel = new JPanel(new GridLayout(0, 1));

            // Get the items from the shopping cart
            ArrayList<Product> items = shoppingCart.getItems();

            // Iterate through the items in the shopping cart
            for (Product item : items) {
                // Create a JLabel for each item, displaying the product name and price
                JLabel itemLabel = new JLabel(item.getProductName() + " - $" + item.getPriceOfProduct());

                // Add the JLabel to the content panel
                contentPanel.add(itemLabel);
            }

            // Add the content panel to the frame
            add(contentPanel);

            // Pack the frame to fit its contents and set its location relative to the main frame (centered)
            pack();
            setLocationRelativeTo(null);
        }
    }


    public void calculateTotalCost(){

    }

}
