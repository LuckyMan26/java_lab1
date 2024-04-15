package org.example.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConenctionPool;
import org.example.DAOInterface.ProductDAO;
import org.example.connections.TransactionWrapper;
import org.example.models.Product;
import java.util.Base64;

public class ProductDAOImpl implements ProductDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class);
    private static ProductDAOImpl instance;

    // Private constructor to prevent instantiation from outside
    private ProductDAOImpl() {
    }

    // Static method to get the singleton instance
    public static synchronized ProductDAOImpl getInstance() {
        if (instance == null) {
            instance = new ProductDAOImpl();
        }
        return instance;
    }

    @Override
    public void addGood(Product product) {
        System.out.println(product.toString());
        logger.info("Adding good" + product.toString());
        try {
            logger.info("Here");
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {

                PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, description, price, quantity_available, image) VALUES ( ?, ?, ?, ?, ?)");
                statement.setString(1, product.getName());
                statement.setString(2, product.getDescription());
                statement.setDouble(3, product.getPrice());
                statement.setInt(4, product.getQuantity_available());
                byte[] bytea = Base64.getDecoder().decode( product.getImageData());

                statement.setBytes(5, bytea);
                statement.executeUpdate();

                return null;
            });
            transactionWrapper.close();
        }
        catch (InterruptedException | SQLException e){
        logger.error(e.getMessage());
        }
    }

    @Override
    public Product getGoodById(Long id) {
        Product g = null;
        logger.info(id);
        try {
            logger.info(id);
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            logger.info(id);
            g = transactionWrapper.executeTransaction(connection -> {
                logger.info(id);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE goods_id = ?");
                statement.setLong(1, id);
                logger.info("id " +  id);
                ResultSet resultSet = statement.executeQuery();
                Product product = null;
                logger.info("product");
                while (resultSet.next()) {

                    Long good_id = resultSet.getLong("goods_id");
                    String name = resultSet.getString("name");

                    String description = resultSet.getString("description");

                    double price = resultSet.getDouble("price");

                    int quantity = resultSet.getInt("quantity_available");
                    byte[] imageData = resultSet.getBytes("image");
                    String base64String = Base64.getEncoder().encodeToString(imageData);
                    product = new Product(good_id, name, description, price, quantity, base64String);
                    logger.info(product.toString());
                }
                return product;
            });
            transactionWrapper.close();
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Product> getAllGoods() {
        List<Product> listOfProducts = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            listOfProducts = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM products");


                ResultSet resultSet = statement.executeQuery();
                Product product = null;
                List<Product> list =  new ArrayList<Product>();
                while (resultSet.next()) {
                    Long good_id = resultSet.getLong("goods_id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    double price = resultSet.getDouble("price");
                    int quantity = resultSet.getInt("quantity_available");
                    byte[] imageData = resultSet.getBytes("image");
                    String base64String  = Base64.getEncoder().encodeToString(imageData);
                    product = new Product(good_id, name, description, price, quantity,base64String );
                    logger.info(product.toString());
                    list.add(product);
                    logger.info("list size:" + String.valueOf(list.size()));
                }
                logger.info("list size:" + String.valueOf(list.size()));
                return list;
            });
            transactionWrapper.close();
            logger.info(listOfProducts.size());
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }

        return listOfProducts;
    }

    @Override
    public void decreaseQuantity(Long product_id, int num){

        logger.info("Decreasing number of: " + product_id + " "+ num);
        try {
            logger.info("Here");
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {

                PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE goods_id = ?");
                statement.setLong(1, product_id);

                ResultSet resultSet = statement.executeQuery();
                Product product = null;
                List<Product> list =  new ArrayList<Product>();
                while (resultSet.next()) {

                    int quantity = resultSet.getInt("quantity_available");

                    if(quantity < num){
                        throw new RuntimeException("quantity < num" + quantity + "<"+num);
                    }
                    else {
                        PreparedStatement statement1 = connection.prepareStatement("UPDATE products SET quantity_available = ? WHERE product id ?");
                        statement1.setInt(1,quantity-num);
                        statement1.setLong(2,product_id);
                        statement1.executeUpdate();
                    }
                }
                statement.executeUpdate();

                return null;
            });
            transactionWrapper.close();
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }
    @Override
    public void deleteGood(Long id) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
           transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("DELETE  * FROM products WHERE goods_id = ?");

                statement.setLong(1, id);
                statement.executeUpdate();

                return null;
            });
            transactionWrapper.close();
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }

    }
}
