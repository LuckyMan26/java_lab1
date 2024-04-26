package org.example.models;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Product {
    private static final Logger logger = LogManager.getLogger(Product.class);
    private final Long productId;
    private final String name;
    private final String description;
    private final double price;
    private final int quantityAvailable;

    private final String imageData;

    public Product(Long good_id, String name, String description, double price, int quantityAvailable, String imageData){
        this.productId = good_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.imageData = imageData;

    }
    public Long getProductId(){
        return productId;
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
    public int getQuantityAvailable(){
        return quantityAvailable;
    }

    public String getImageData(){
        return imageData;
    }
    @Override
    public String toString() {
        return "Good{" +
                "id=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity_available=" + quantityAvailable +
                '}';
    }
}
