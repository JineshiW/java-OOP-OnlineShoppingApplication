import java.util.Scanner;

public class Electronic extends Product {
    private String brand;
    private int warrantyPeriod;

    public static Scanner input=new Scanner(System.in);

    public Electronic(String name, double price, String brand, int warrantyPeriod) {
        super(name, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }


}
