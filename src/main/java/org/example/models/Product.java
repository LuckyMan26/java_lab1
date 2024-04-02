package org.example.models;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.servlet.AddGoodServlet;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Product {
    private static final Logger logger = LogManager.getLogger(Product.class);
    private final int good_id;
    private final String name;
    private final String description;
    private final double price;
    private final int quantity_available;

    private final String imageData;

    public Product(int good_id, String name, String description, double price, int quantity_available, String imageData){
        this.good_id = good_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity_available = quantity_available;
        this.imageData = imageData;



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

    public String getImageData(){
        return imageData;
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
