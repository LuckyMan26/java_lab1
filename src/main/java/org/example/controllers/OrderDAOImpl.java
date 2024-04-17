package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConenctionPool;
import org.example.DAOInterface.OrderDAO;
import org.example.connections.TransactionWrapper;
import org.example.models.Order;
import org.example.models.Status;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
                PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (client_id, order_date, status, products_ids, total_price) VALUES ( ?, ?, ?, ?, ?)");
                statement.setString(1, order.getClientId());
                statement.setDate(2, new java.sql.Date(order.getOrder_date().getTime()));

                statement.setString(3, order.getStatus().toString());
                ArrayList<Long> items = order.getProducts();
                Long[] array = new Long[items.size()];
                int counter = 0;
                for(Long item : items){
                    array[counter] = item;
                    counter+=1;
                }
                logger.info("addOrder");
                statement.setArray(4, connection.createArrayOf("INTEGER", array));
                statement.setDouble(5, order.getTotalPrice());
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
                    Long order_id = resultSet.getLong("order_id");
                    String client_id = resultSet.getString("client_id");
                    Date order_date = resultSet.getDate("order_date");
                    Long[] integerArray = (Long[]) resultSet.getArray("products_ids").getArray();

                    ArrayList<Long> product_id = new ArrayList<Long>(Arrays.asList(integerArray));
                    Status status = Status.valueOf(resultSet.getString("status"));
                    double price = resultSet.getDouble("total_price");
                    order = new Order(order_id, client_id, order_date, status, product_id, price);
                    logger.info(order.toString());
                }
                return order;
            });
            transactionWrapper.close();
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
                List<Order> list = new ArrayList<>();
                while (resultSet.next()) {
                    Long order_id = resultSet.getLong("order_id");
                    String client_id = resultSet.getString("client_id");
                    Date order_date = resultSet.getDate("order_date");
                    Long[] integerArray = (Long[]) resultSet.getArray("products_ids").getArray();

                    ArrayList<Long> product_id = new ArrayList<Long>(Arrays.asList(integerArray));
                    String status = resultSet.getString("status");
                    order = new Order(order_id, client_id, order_date,Status.valueOf(status),product_id);
                    logger.info(order.toString());
                    list.add(order);
                }
                return list;
            });
            transactionWrapper.close();
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return listOfOrders;
    }
    @Override
    public List<Order> getAllOrdersByClient(String client_id) {
        List<Order> listOfOrders = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            listOfOrders = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE client_id = ?");
                logger.info("dsafasdfds");
                statement.setString(1,client_id);
                ResultSet resultSet = statement.executeQuery();
                logger.info("resultSet");
                Order order = null;
                ArrayList<Order> list = new ArrayList<>();

                while (resultSet.next()) {
                    logger.info("iter");
                    Long order_id = resultSet.getLong("order_id");
                    logger.info("pizda 1");
                    Date order_date = resultSet.getDate("order_date");
                    Long[] integerArray = (Long[]) resultSet.getArray("products_ids").getArray();

                    ArrayList<Long> product_id = new ArrayList<Long>(Arrays.asList(integerArray));
                    logger.info("pizda 2");
                    String status = resultSet.getString("status");
                    logger.info("pizda 3");
                    order = new Order(order_id, client_id, order_date,Status.valueOf(status),product_id);
                    logger.info(order.toString());
                    list.add(order);
                }
                logger.info("almost success");
                return list;
            });
            transactionWrapper.close();
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        logger.info("finished");
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
            transactionWrapper.close();
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void changeOrderStatus(Long order_id, Status status){
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
             transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("UPDATE orders SET status = ? WHERE order_id = ?");
                logger.info(order_id);
                statement.setString(1,status.toString());
                statement.setLong(2,order_id);
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
