package org.example.DAOInterface;

import org.example.connections.ConnectionWrapper;
import org.example.models.Order;
import org.example.models.Status;

import java.sql.Connection;
import java.util.List;

public interface OrderDAO {
    public void addOrder(Order order, ConnectionWrapper connection);
    public Order getOrderById(int id, ConnectionWrapper connection);
    public List<Order> getAllOrders( ConnectionWrapper connection);
    public void deleteOrder(int id, ConnectionWrapper connection);
    public List<Order> getAllOrdersByClient(String client_id, ConnectionWrapper connection);

    public void changeOrderStatus(Long order_id, Status status, ConnectionWrapper connection);
}
