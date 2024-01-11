import java.util.Scanner;

public class Clothing extends Product{
    private int size;
    private String color;

    public Scanner input=new Scanner(System.in);

    public Clothing(int productID,String name, double price, int size, String color) {
        super(productID, name, price);
        this.size = size;
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
