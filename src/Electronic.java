// Importing Scanner class from java.util package
import java.util.Scanner;

// Electronic class extends Product class
public class Electronic extends Product {

    // Private instance variables for Electronic class
    private String brand;
    private int warrantyPeriod;

    // Creating a static Scanner object for user input
    public static Scanner input = new Scanner(System.in);

    // Creating a  constructor for Electronic class
    public Electronic(int productID, String name, double price, String brand, int warrantyPeriod) {
        // Calling the constructor of the superclass (Product)
        super(productID, name, price);

        // Initializing brand and warrantyPeriod variables
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Getter method for retrieving the brand of the electronic product
    public String getBrand() {
        return brand;
    }

    // Setter method for setting the brand of the electronic product
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Getter method for retrieving the warranty period of the electronic product
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    // Setter method for setting the warranty period of the electronic product
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
