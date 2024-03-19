package org.example;

import java.util.List;

public interface OrderItemsDAO {
    void addOrderItem(OrderItem orderItem);
    OrderItem getOrderItemById(int id);
    List<OrderItem> getAllOrderItems();

    void deleteOrderItem(int id);
}
