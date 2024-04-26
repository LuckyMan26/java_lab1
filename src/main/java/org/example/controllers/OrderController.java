package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConnectionPool;
import org.example.connections.ConnectionWrapper;
import org.example.models.Order;
import org.example.models.Status;
import org.example.repository.ClientDAOImpl;
import org.example.repository.OrderDAOImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderController {
    private static final Logger logger = LogManager.getLogger(ClientController.class);
    public static final OrderController INSTANCE = new OrderController();
    public void addOrder(Order order ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            OrderDAOImpl.getInstance().addOrder(order, connection);

        }
    }

    public Order getOrderById(int id) {

        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            return OrderDAOImpl.getInstance().getOrderById(id, connection);

        }
    }

    public List<Order> getAllOrders() {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            return OrderDAOImpl.getInstance().getAllOrders(connection);

        }
    }


    public List<Order> getAllOrdersByClient(String clientId ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {

            return OrderDAOImpl.getInstance().getAllOrdersByClient(clientId, connection);

        }

    }

    public void deleteOrder(int id ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            OrderDAOImpl.getInstance().deleteOrder(id, connection);

        }
    }


    public void changeOrderStatus(Long orderId, Status status ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            OrderDAOImpl.getInstance().changeOrderStatus(orderId,status, connection);

        }
    }
}
