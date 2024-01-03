public class Clothing extends Product{
    private int size;
    private String color;

    public Clothing(String name, double price, int size, String color) {
        super(name, price);
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
