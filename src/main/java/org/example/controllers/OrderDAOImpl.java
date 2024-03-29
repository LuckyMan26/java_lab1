package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConenctionPool;
import org.example.DAOInterface.OrderDAO;
import org.example.connections.TransactionWrapper;
import org.example.models.Order;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger logger = LogManager.getLogger(OrderDAOImpl.class);

    private static OrderDAOImpl instance;

    // Private constructor to prevent instantiation from outside
    private OrderDAOImpl() {
    }

    // Static method to get the singleton instance
    public static synchronized OrderDAOImpl getInstance() {
        if (instance == null) {
            instance = new OrderDAOImpl();
        }
        return instance;
    }

    @Override
    public void addOrder(Order order) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (client_id, order_date, total_amount, status) VALUES ( ?, ?, ?, ?, ?)");
                statement.setInt(1, order.getClientId());
                statement.setDate(2, (Date) order.getOrder_date());
                statement.setInt(3, order.getTotal_amount());
                statement.setString(4, order.getStatus());
                statement.executeUpdate();
                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public Order getOrderById(int id) {
        Order o = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            o = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                Order order = null;
                while (resultSet.next()) {
                    int order_id = resultSet.getInt("order_id");
                    int client_id = resultSet.getInt("client_id");
                    Date order_date = resultSet.getDate("order_date");
                    int total_amount = resultSet.getInt("total_amount");
                    String status = resultSet.getString("status");
                    order = new Order(order_id, client_id, order_date, total_amount, status);
                    logger.info(order.toString());
                }
                return order;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return o;

    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> listOfOrders = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            listOfOrders = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");


                ResultSet resultSet = statement.executeQuery();
                Order order = null;
                List<Order> list = null;
                while (resultSet.next()) {
                    int order_id = resultSet.getInt("order_id");
                    int client_id = resultSet.getInt("client_id");
                    Date order_date = resultSet.getDate("order_date");
                    int total_amount = resultSet.getInt("total_amount");
                    String status = resultSet.getString("status");
                    order = new Order(order_id, client_id, order_date, total_amount, status);
                    logger.info(order.toString());
                    list.add(order);
                }
                return list;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return listOfOrders;
    }

    @Override
    public void deleteOrder(int id) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("DELETE  * FROM orders WHERE order_id = ?");

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
