package org.example.DAOInterface;

import org.example.models.Order;
import org.example.models.Status;

import java.util.List;

public interface OrderDAO {
    public void addOrder(Order order);
    public Order getOrderById(int id);
    public List<Order> getAllOrders();
    public void deleteOrder(int id);
    public List<Order> getAllOrdersByClient(Long client_id);

    public void changeOrderStatus(Long order_id, Status status);
}
