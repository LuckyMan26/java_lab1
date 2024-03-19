package org.example;

public class Good {
    private final int good_id;
    private final String name;
    private final String description;
    private final int price;
    private final int quantity_available;

    public Good(int good_id, String name, String description, int price, int quantity_available){
        this.good_id = good_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity_available = quantity_available;

    }
    int getGood_id(){
        return good_id;
    }
    String getName(){
        return name;
    }
    String getDescription(){
        return description;
    }
    int getPrice(){
        return price;
    }
    int getQuantity_available(){
        return quantity_available;
    }
}
