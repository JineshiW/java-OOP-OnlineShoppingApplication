import java.util.ArrayList;

// ShoppingCart class represents a shopping cart that holds  products
public class ShoppingCart {

    // Private instance variable to store the list of items in the shopping cart
    private ArrayList<Product> items;

    // Default constructor initializes an empty shopping cart
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    // Method to add a product to the shopping cart
    public void addItem(Product item) {
        items.add(item);
    }

    // Method to retrieve a copy of the list of items in the shopping cart
    public ArrayList<Product> getItems() {
        // Return a copy to prevent direct modification of the original list
        return new ArrayList<>(items);
    }
}



