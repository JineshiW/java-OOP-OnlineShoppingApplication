//Product class is abstract
public abstract class Product {

    // Private instance variables for the Product class
    private int productID;
    private String productName;
    private int noOfAvailableItems;
    private double priceOfProduct;

    // creating a constructor for the Product class
    public Product(int productID, String productName, double priceOfProduct) {
        // Initializing the productID, productName, and priceOfProduct variables
        this.productID = productID;
        this.productName = productName;
        this.priceOfProduct = priceOfProduct;
    }

    // Getter method for retrieving the product ID
    public int getProductID() {
        return productID;
    }

    // Getter method for retrieving the product name
    public String getProductName() {
        return productName;
    }

    // Setter method for setting the product name
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter method for retrieving the number of available items
    public int getNoOfAvailableItems() {
        return noOfAvailableItems;
    }

    // Setter method for setting the number of available items
    public void setNoOfAvailableItems(int noOfAvailableItems) {
        this.noOfAvailableItems = noOfAvailableItems;
    }

    // Getter method for retrieving the price of the product
    public double getPriceOfProduct() {
        return priceOfProduct;
    }

    // Setter method for setting the price of the product
    public void setPriceOfProduct(double priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }
}

