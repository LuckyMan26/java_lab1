/*
package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.DAOInterface.OrderItemDAO;
import org.example.connections.ConenctionPool;
import org.example.connections.TransactionWrapper;
import org.example.models.Client;
import org.example.models.Order;
import org.example.models.OrderItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemImpl implements OrderItemDAO {
    private static final Logger logger = LogManager.getLogger(OrderItemImpl.class);


    private static OrderItemImpl instance;

    // Private constructor to prevent instantiation from outside
    private OrderItemImpl() {
    }

    // Static method to get the singleton instance
    public static synchronized OrderItemImpl getInstance() {
        if (instance == null) {
            instance = new OrderItemImpl();
        }
        return instance;
    }
    @Override
    public void addOrderItem(OrderItem orderItem){
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO order_items (product_id, quantity) VALUES ( ?, ?)");
                statement.setInt(1,orderItem.getOrder_item_id());
                statement.setInt(1,orderItem.getQuantity());
                statement.executeUpdate();
                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }
    public OrderItem getOrderItemById(int id){
        OrderItem orderItem = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            orderItem = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM order_items WHERE order_item_id = ?");
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                OrderItem orderItem1 = null;
                while (resultSet.next()) {
                    int order_item_id = resultSet.getInt("order_item_id");
                    int product_id = resultSet.getInt("product_id");
                    int quantity = resultSet.getInt("quantity");

                    orderItem1 = new OrderItem(order_item_id, product_id, quantity);
                    logger.info(orderItem1.toString());
                }
                return orderItem1;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return orderItem;

    }


    public void deleteOrderItem(int id){
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("DELETE  * FROM order_items WHERE order_item_id = ?");

                statement.setInt(1, id);
                statement.executeUpdate();

                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }
}
*/
