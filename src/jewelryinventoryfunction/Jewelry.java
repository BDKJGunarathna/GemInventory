package jewelryinventoryfunction;

public class Jewelry {

    private int id;
    private String name;
    private String type;
    private String meterial;
    private double weight;
    private int quantity;
    private double price;

    public Jewelry(int id, String name, String type, String meterial, double weight, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.meterial = meterial;
        this.weight = weight;
        this.quantity = quantity;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMeterial() {
        return meterial;
    }

    public double getWeight() {
        return weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
