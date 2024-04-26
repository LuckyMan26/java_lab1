package org.example.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConnectionPool;
import org.example.DAOInterface.OrderDAO;
import org.example.connections.ConnectionWrapper;
import org.example.models.BasketItem;
import org.example.models.Order;
import org.example.models.Status;

import java.sql.*;
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
    private static Order resultToOrder(ResultSet resultSet) throws SQLException {
        Long order_id = resultSet.getLong("order_id");
        String client_id = resultSet.getString("client_id");
        Date order_date = resultSet.getDate("order_date");
        Long[] integerArray = (Long[]) resultSet.getArray("products_ids").getArray();

        ArrayList<Long> product_id = new ArrayList<Long>(Arrays.asList(integerArray));
        Status status = Status.valueOf(resultSet.getString("status"));
        double price = resultSet.getDouble("total_price");
        String full_name = resultSet.getString("full_name");
        String address = resultSet.getString("address");
        return new Order(order_id, client_id, order_date, status, product_id, price, full_name, address);
    }
    @Override
    public void addOrder(Order order, ConnectionWrapper connection) {
        try {
            String sql = "INSERT INTO orders (client_id, order_date, status, products_ids, total_price, full_name ,address) VALUES (?, ?, ?, ?, ?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, order.getClientId());
            statement.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));

            statement.setString(3, order.getStatus().toString());
            ArrayList<Long> items = order.getProducts();
            Long[] array = new Long[items.size()];
            int counter = 0;
            for (Long item : items) {
                array[counter] = item;
                counter += 1;
            }
            logger.info("addOrder");
            statement.setArray(4, connection.createArrayOf(array));
            statement.setDouble(5, order.getTotalPrice());
            statement.setString(6, order.getFullName());
            statement.setString(7, order.getAddress());
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order getOrderById(int id, ConnectionWrapper connection) {
        Order o = null;
        try {
            String sql = "SELECT * FROM orders WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            Order order = null;
            while (resultSet.next()) {
                order = resultToOrder(resultSet);
                logger.info(order.toString());
            }
            return order;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllOrders(ConnectionWrapper connection) {
        List<Order> listOfOrders = null;
        try {
            String sql = "SELECT * FROM orders";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            Order order = null;
            List<Order> list = new ArrayList<>();
            while (resultSet.next()) {
                order = resultToOrder(resultSet);
                logger.info(order.toString());
                list.add(order);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllOrdersByClient(String client_id, ConnectionWrapper connection) {
        List<Order> listOfOrders = null;
        try {
            String sql = "SELECT * FROM orders WHERE client_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            logger.info("dsafasdfds");
            statement.setString(1, client_id);
            ResultSet resultSet = statement.executeQuery();
            logger.info("resultSet");
            Order order = null;
            ArrayList<Order> list = new ArrayList<>();

            while (resultSet.next()) {
                logger.info("iter");
                order = resultToOrder(resultSet);
                logger.info(order.toString());
                list.add(order);
            }
            logger.info("almost success");
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteOrder(int id, ConnectionWrapper connection) {
        try {
            String sql = "DELETE  * FROM orders WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);


            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void changeOrderStatus(Long order_id, Status status,ConnectionWrapper connection) {
        try {
            String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            logger.info(order_id);
            statement.setString(1, status.toString());
            statement.setLong(2, order_id);
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
