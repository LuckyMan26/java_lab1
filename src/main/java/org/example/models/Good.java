package org.example.models;

public class Good {
    private final int good_id;
    private final String name;
    private final String description;
    private final double price;
    private final int quantity_available;

    public Good(int good_id, String name, String description, double price, int quantity_available){
        this.good_id = good_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity_available = quantity_available;

    }
    public int getGood_id(){
        return good_id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity_available(){
        return quantity_available;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + good_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity_available=" + quantity_available +
                '}';
    }
}
