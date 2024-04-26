package org.example.models;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Product {
    private static final Logger logger = LogManager.getLogger(Product.class);
    private final Long product_id;
    private final String name;
    private final String description;
    private final double price;
    private final int quantity_available;

    private final String imageData;

    public Product(Long good_id, String name, String description, double price, int quantity_available, String imageData){
        this.product_id = good_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity_available = quantity_available;
        this.imageData = imageData;

    }
    public Long getProduct_id(){
        return product_id;
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
                "id=" + product_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity_available=" + quantity_available +
                '}';
    }
}
