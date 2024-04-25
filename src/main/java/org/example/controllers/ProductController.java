package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConnectionPool;
import org.example.connections.ConnectionWrapper;
import org.example.models.Product;
import org.example.repository.OrderDAOImpl;
import org.example.repository.ProductDAOImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);
    public static final ProductController INSTANCE = new ProductController();
    public void addGood(Product product ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            ProductDAOImpl.getInstance().addGood(product, connection);

        }
    }

    public Product getGoodById(Long id ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            return ProductDAOImpl.getInstance().getGoodById(id, connection);

        }
    }

    public List< Product > getAllGoods() {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            return ProductDAOImpl.getInstance().getAllGoods(connection);

        }
    }

    public void decreaseQuantity(Long product_id, int num) {

        logger.info("Decreasing number of: " + product_id + " " + num);
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
             ProductDAOImpl.getInstance().decreaseQuantity(product_id,num, connection);

        }
    }

    public void deleteGood(Long id ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            ProductDAOImpl.getInstance().deleteGood(id, connection);

        }
    }
}
