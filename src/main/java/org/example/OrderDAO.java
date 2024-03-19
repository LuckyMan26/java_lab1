package org.example;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    Order getOrderById(int id);
    List<Order> getAllOrders();
    void deleteOrder(int id);
}
