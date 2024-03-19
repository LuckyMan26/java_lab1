package org.example.DAOInterface;

import org.example.models.OrderItem;

import java.util.List;

public interface OrderItemsDAO {
    void addOrderItem(OrderItem orderItem);
    OrderItem getOrderItemById(int id);
    List<OrderItem> getAllOrderItems();

    void deleteOrderItem(int id);
}
