package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConenctionPool;
import org.example.connections.TransactionWrapper;
import org.example.models.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException, IOException {
        Logger logger = LogManager.getLogger(Main.class);
      /*  File imageFile = new File("E:\\photo.jpg");
        FileInputStream inputStream = new FileInputStream(imageFile);
        byte[] imageData = new byte[(int) imageFile.length()];
        inputStream.read(imageData);
        Product product = new Product(1,"aaaa", "abbb", 100,150, imageData);
        TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
        transactionWrapper.executeTransaction(connection -> {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, description, price, quantity_available, image) VALUES ( ?, ?, ?, ?, ?)");
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity_available());
            byte[] bytea = Base64.getDecoder().decode( product.getImageData());
            statement.setBytes(5,bytea);
            statement.executeUpdate();

            return null;
        });*/




    }
}