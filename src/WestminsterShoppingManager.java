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
            }  catch (InputMismatchException ex){
            System.out.println("Invalid input. Please enter a valid integer option.");
            input.nextLine(); // Consume invalid input
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                input.nextLine(); // Consume newline to avoid infinite loop
            }
        } while (option != 0);
    }

    // Method to add a new product
    public void addProduct() {
        if (products.size() >= maxProducts) {
            System.out.println("The maximum limit for adding products is reached. Therefore, you can no longer add any more products.");
        } else {
            System.out.print(
                    "Select the type of product that you wish to add? \n" +
                            "1. Electronics \n" +
                            "2. Clothing"
                            + "\n" +
                            "Enter your choice: ");
            int prodChoice = input.nextInt();
            switch (prodChoice) {
                case 1:
                    addElectronics();
                    break;
                case 2:
                    addClothing();
                    break;
                default:
                    System.out.println("The input is not valid, please enter a valid input.");
                    break;
            }
        }
    }

    // Method to add electronics product
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
                System.out.println("Invalid input. Please enter a valid numeric value for electronics price.");
                input.next(); // Consume the invalid input to avoid an infinite loop
            } catch (IllegalArgumentException e) {
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
                System.out.println("Invalid input. Please enter a valid integer for electronics warranty period.");
                input.next(); // Consume the invalid input to avoid an infinite loop
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // Display the error message
            }
        } while (!validInput);

        // Display success message and create a new Electronic object
        System.out.println("Electronics details added successfully.");
        Electronic electronics = new Electronic(pEID, electronicName, price, brand, warrantyPeriod);

        // Add the newly created Electronic object to the products list
        products.add(electronics);
    }


    // Method to add clothing product
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
                System.out.println("Invalid input. Please enter a valid numeric value for clothing price.");
                input.next(); // Consume the invalid input to avoid an infinite loop
            } catch (IllegalArgumentException e) {
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
                validInput = true; // If no exception occurs, set validInput to true to exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for clothing size.");
                input.next(); // Consume the invalid input to avoid an infinite loop
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




    // Method to check if a product with given ID already exists
    private boolean isIdAlreadyExists(int id) {
        for (Product clothing : products) {
            if (clothing.getProductID() == id) {
                return true; // ID already exists in the arrayList
            }
        }
        return false; // ID does not exist in the arrayList
    }

    // Method to check if a product with given name already exists for the specified type
    private boolean isNameAlreadyExists(String name, String type) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(name) && product instanceof Clothing && type.equals("Clothing")) {
                return true;
            } else if (product.getProductName().equalsIgnoreCase(name) && product instanceof Electronic && type.equals("Electronic")) {
                return true;
            }
        }
        return false;
    }

    // Method to remove a product by ID
    public void removeProduct() {
        System.out.println("Enter the product ID to delete: ");
        int productId = input.nextInt();
        boolean found = false;
        Product deletedProduct = null;

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getProductID() == productId) {
                // Save the deleted product for displaying information
                deletedProduct = product;
                products.remove(i);
                found = true;
                break;
            }
        }

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
            System.out.println("Product with ID " + productId + " not found.");
        }
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
    }

    public void loadDataFromFile() {
        try {
            File file = new File("prodDetails.txt");

            if (!file.exists()) {
                // Create a new file if it doesn't exist
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("Failed to create the file.");
                    return;  // Exit the method if file creation fails
                }
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                int productId = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                String productName = scanner.nextLine().split(": ")[1];
                double price = Double.parseDouble(scanner.nextLine().split(": ")[1]);
                String itemType = scanner.nextLine().split(": ")[1];

                if ("Electronics".equals(itemType)) {
                    String brand = scanner.nextLine().split(": ")[1];
                    String warrantyPeriodString = scanner.nextLine().split(": ")[1];
                    int warrantyPeriod = Integer.parseInt(warrantyPeriodString.replaceAll("\\D", ""));
                    Electronic electronic = new Electronic(productId, productName, price, brand, warrantyPeriod);
                    products.add(electronic);
                } else if ("Clothing".equals(itemType)) {
                    int size = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                    String color = scanner.nextLine().split(": ")[1];
                    Clothing clothing = new Clothing(productId, productName, price, size, color);
                    products.add(clothing);
                }

                // Skip the empty line between product entries
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }

            System.out.println("Data loaded successfully from file.");

            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while processing the file.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data from file. Check the file format.");
        }
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }


//        public void calculateTotalCost () {
//
//        }

    public static class westminsterFrame extends JFrame {
        private JTable productTable;
        private DefaultTableModel tableModel;
        private ArrayList<Product> products;  // New field to store the reference to the list of products
        private JPanel productDetailsPanel;

        private JButton addToCartButton;
        private JButton viewCartButton;
        private ShoppingCart shoppingCart;

        public westminsterFrame(String myFrame, ArrayList<Product> products) {
            // Assigning the list of products to the local field
            this.products = products;

            setTitle("Westminster Shopping Center");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JComboBox<String> productType = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});

            // Set layout to FlowLayout with left and right margins
            setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

            // Create an  label on the left side for margin
            JLabel leftMargin = new JLabel("Select Product Category");
            add(leftMargin);

            // Add the JComboBox to the frame
            add(productType);

            // Create an empty label on the right side for margin
            JLabel rightMargin = new JLabel(" ");
            add(rightMargin);

            tableModel = new DefaultTableModel(new Object[]{"Product ID", "Product Name", "Price", "Category", "Brand", "Size", "Color"}, 0);

            // Creating the JTable with the populated table model
            productTable = new JTable(tableModel);

            // Creating a JScrollPane to add the table to and enable scrolling
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

                public void actionPerformed(ActionEvent e) {
                    // Handle the logic to add the selected product to the shopping cart
                    // You need to implement this part
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

                public void actionPerformed(ActionEvent e) {
                    // Handle the logic to open the shopping cart
                    // You need to implement this part
                }
            });

            // Add the top-right panel to the frame at the NORTH position
            add(topRightPanel, BorderLayout.NORTH);

            // Add the "Shopping Cart" button to the EAST position of the top-right panel
            topRightPanel.add(viewCartButton);


            //invoking method call
            loadDataFromFileToGUI();


        }
        private void loadDataFromFileToGUI() {
            try {
                File file = new File("prodDetails.txt");
                Scanner scanner = new Scanner(file);

                // Clear existing data from the table model
                tableModel.setRowCount(0);

                while (scanner.hasNextLine()) {
                    int productId = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                    String productName = scanner.nextLine().split(": ")[1];
                    double price = Double.parseDouble(scanner.nextLine().split(": ")[1]);
                    String itemType = scanner.nextLine().split(": ")[1];

                    if ("Electronics".equals(itemType)) {
                        String brand = scanner.nextLine().split(": ")[1];
                        String warrantyPeriodString = scanner.nextLine().split(": ")[1];
                        int warrantyPeriod = Integer.parseInt(warrantyPeriodString.replaceAll("\\D", ""));
                        Electronic electronic = new Electronic(productId, productName, price, brand, warrantyPeriod);
                        products.add(electronic);
                    } else if ("Clothing".equals(itemType)) {
                        int size = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                        String color = scanner.nextLine().split(": ")[1];
                        Clothing clothing = new Clothing(productId, productName, price, size, color);
                        products.add(clothing);
                    }

                    // Add the loaded data to the table model
                    Object[] row = new Object[7];
                    row[0] = productId;
                    row[1] = productName;
                    row[2] = price;

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

                System.out.println("Data loaded successfully from file.");

                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found. No data loaded.");
            } catch (NumberFormatException e) {
                System.out.println("Error parsing data from file. Check the file format.");
            }
        }

        private void updateProductDetailsPanel(int rowIndex) {
            // Clear the existing components in the details panel
            productDetailsPanel.removeAll();

            // Get the selected product
            Product selectedProduct = products.get(rowIndex);
            JLabel titleLabel=new JLabel("Select Product Details");

            // Create JLabels to display product details
            JLabel idLabel = new JLabel("Product ID: " + selectedProduct.getProductID());
            JLabel nameLabel = new JLabel("Product Name: " + selectedProduct.getProductName());
            JLabel priceLabel = new JLabel("Price: " + selectedProduct.getPriceOfProduct());

            // Add the JLabels to the details panel
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

            // Repaint the details panel
            productDetailsPanel.revalidate();
            productDetailsPanel.repaint();
        }

//        private Product getSelectedProduct() {
//            // Implement the logic to get the selected product from your table or wherever it's displayed
//            // This method should return the selected product
//            // For demonstration purposes, you can return a sample product
//            return ;
//        }

        private Product getSelectedProduct() {
            // Implement the logic to get the selected product from your table or wherever it's displayed
            // This method should return the selected product
            // For demonstration purposes, you can return a sample product

            int selectedRow = productTable.getSelectedRow();

            if (selectedRow >= 0) {
                int productId = (int) productTable.getValueAt(selectedRow, 0);
                String productName = (String) productTable.getValueAt(selectedRow, 1);
                double price = (double) productTable.getValueAt(selectedRow, 2);
                String category = (String) productTable.getValueAt(selectedRow, 3);

                if ("Electronics".equals(category)) {
                    String brand = (String) productTable.getValueAt(selectedRow, 4);
                    int warrantyPeriod = (int) productTable.getValueAt(selectedRow, 7);
                    return new Electronic(productId, productName, price, brand, warrantyPeriod);
                } else if ("Clothing".equals(category)) {
                    int size = (int) productTable.getValueAt(selectedRow, 5);
                    String color = (String) productTable.getValueAt(selectedRow, 6);
                    return new Clothing(productId, productName, price, size, color);
                } else {
                    // If it's neither Electronics nor Clothing, return null or handle accordingly
                    return null;
                }
            } else {
                // Return null or handle the case where no row is selected
                return null;
            }
        }



    }

    public class ShoppingCartGUI extends JFrame {
        private ShoppingCart shoppingCart;

        public ShoppingCartGUI(ShoppingCart shoppingCart) {
            this.shoppingCart = shoppingCart;

            setTitle("Shopping Cart");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window, not the entire application

            // Create a panel for displaying shopping cart contents
            JPanel contentPanel = new JPanel(new GridLayout(0, 1));

            // add the panel with shopping cart items
            ArrayList<Product> items = shoppingCart.getItems();
            for (Product item : items) {
                JLabel itemLabel = new JLabel(item.getProductName() + " - $" + item.getPriceOfProduct());
                contentPanel.add(itemLabel);
            }

            // Add the content panel to the frame
            add(contentPanel);

            // Pack and set location relative to the main frame (you can adjust this)
            pack();
            setLocationRelativeTo(null);
        }
    }

}
