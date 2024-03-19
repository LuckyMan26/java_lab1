package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConenctionPool;
import org.example.DAOInterface.OrderItemsDAO;
import org.example.connections.TransactionWrapper;
import org.example.models.OrderItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderItemsDAOImpl implements OrderItemsDAO {
    private static final Logger logger = LogManager.getLogger(OrderItemsDAOImpl.class);


    private static OrderItemsDAOImpl instance;

    // Private constructor to prevent instantiation from outside
    private OrderItemsDAOImpl() {
    }

    // Static method to get the singleton instance
    public static synchronized OrderItemsDAOImpl getInstance() {
        if (instance == null) {
            instance = new OrderItemsDAOImpl();
        }
        return instance;
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO order_items (order_id, goods_id, quantity, price) VALUES ( ?, ?, ?, ?)");
                statement.setInt(1, orderItem.getOrder_id());
                statement.setInt(2, orderItem.getGood_id());
                statement.setInt(3, orderItem.getQuantity());
                statement.setDouble(4, orderItem.getPrice());
                statement.executeUpdate();
                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        OrderItem orderItem = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            orderItem = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM goods WHERE goods_id = ?");
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                OrderItem orderItem1 = null;
                while (resultSet.next()) {
                    int order_item_id = resultSet.getInt("order_item_id");
                    int orderId = resultSet.getInt("order_id");
                    int goodsId = resultSet.getInt("goods_id");
                    int quantity = resultSet.getInt("quantity");
                    double price = resultSet.getDouble("price");
                    orderItem1 = new OrderItem(order_item_id, orderId, goodsId, quantity, price);
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

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> listOfOrderItems = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            listOfOrderItems = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM order_items");


                ResultSet resultSet = statement.executeQuery();
                OrderItem orderItem1 = null;
                List<OrderItem> list = null;
                while (resultSet.next()) {
                    int order_item_id = resultSet.getInt("order_item_id");
                    int orderId = resultSet.getInt("order_id");
                    int goodsId = resultSet.getInt("goods_id");
                    int quantity = resultSet.getInt("quantity");
                    double price = resultSet.getDouble("price");
                    orderItem1 = new OrderItem(order_item_id, orderId, goodsId, quantity, price);
                    logger.info(orderItem1.toString());
                    list.add(orderItem1);
                }
                return list;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return listOfOrderItems;

    }

    @Override
    public void deleteOrderItem(int id) {
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
