// Importing Scanner class from java.util package
import java.util.Scanner;

// Clothing class extends Product class
public class Clothing extends Product {

    // Private instance variables for Clothing class
    private int size;
    private String color;

    // Creating a Scanner object for user input
    public Scanner input = new Scanner(System.in);

    // Creating a  constructor for Clothing class
    public Clothing(int productID, String name, double price, int size, String color) {
        // Calling the constructor of the superclass (Product)
        super(productID, name, price);

        // Initializing size and color variables
        this.size = size;
        this.color = color;
    }

    // Getter method for retrieving the size of the clothing
    public int getSize() {
        return size;
    }

    // Setter method for setting the size of the clothing
    public void setSize(int size) {
        this.size = size;
    }

    // Getter method for retrieving the color of the clothing
    public String getColor() {
        return color;
    }

    // Setter method for setting the color of the clothing
    public void setColor(String color) {
        this.color = color;
    }
}
