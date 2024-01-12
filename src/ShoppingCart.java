import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product item) {
        items.add(item);
    }

    public ArrayList<Product> getItems() {
        // Return a copy to prevent direct modification
        return new ArrayList<>(items);
    }
}


