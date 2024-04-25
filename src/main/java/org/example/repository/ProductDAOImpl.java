package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConnectionPool;
import org.example.DAOInterface.ProductDAO;
import org.example.connections.ConnectionWrapper;
import org.example.models.Product;
import java.util.Base64;

public class ProductDAOImpl implements ProductDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class);
    private static ProductDAOImpl instance;

    // Private constructor to prevent instantiation from outside
    private ProductDAOImpl() {}

    // Static method to get the singleton instance
    public static synchronized ProductDAOImpl getInstance() {
        if (instance == null) {
            instance = new ProductDAOImpl();
        }
        return instance;
    }

    @Override
    public void addGood(Product product, ConnectionWrapper connection) {
        System.out.println(product.toString());
        logger.info("Adding good" + product.toString());
        try {
            logger.info("Here");
            String sql = "INSERT INTO products (name, description, price, quantity_available, image) VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity_available());
            byte[] bytea = Base64.getDecoder().decode(product.getImageData());

            statement.setBytes(5, bytea);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getGoodById(Long id, ConnectionWrapper connection) {
        Product g = null;
        logger.info(id);
        try {
            logger.info(id);
            String sql = "SELECT * FROM products WHERE goods_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            logger.info(id);

            statement.setLong(1, id);
            logger.info("id " + id);
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List < Product > getAllGoods(ConnectionWrapper connection) {
        List < Product > listOfProducts = null;
        try {
            String sql = "SELECT * FROM products";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            Product product = null;
            List < Product > list = new ArrayList < Product > ();
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
                list.add(product);
                logger.info("list size:" + String.valueOf(list.size()));
            }
            logger.info("list size:" + String.valueOf(list.size()));
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void decreaseQuantity(Long product_id, int num, ConnectionWrapper connection) {

        logger.info("Decreasing number of: " + product_id + " " + num);
        try {
            logger.info("Here");
            String sql = "SELECT * FROM products WHERE goods_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, product_id);

            ResultSet resultSet = statement.executeQuery();
            Product product = null;
            List < Product > list = new ArrayList < Product > ();
            while (resultSet.next()) {

                int quantity = resultSet.getInt("quantity_available");

                if (quantity < num) {
                    throw new RuntimeException("quantity < num" + quantity + "<" + num);
                } else {
                    PreparedStatement statement1 = connection.prepareStatement("UPDATE products SET quantity_available = ? WHERE product id ?");
                    statement1.setInt(1, quantity - num);
                    statement1.setLong(2, product_id);
                    statement1.executeUpdate();
                }
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteGood(Long id, ConnectionWrapper connection) {
        try {
            String sql = "DELETE  * FROM products WHERE goods_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}