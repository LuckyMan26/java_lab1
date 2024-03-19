package org.example;

public class OrderItem {
    private final int order_itemId;
    private final int orderId;
    private final int goodId;
    private final int quantity;
    private final double price;
    OrderItem(int order_item_id, int order_id, int good_id, int quantity, double price){
        this.order_itemId = order_item_id;
        this.orderId = order_id;
        this.goodId = good_id;
        this.quantity = quantity;
        this.price = price;
    }
    int getOrder_item_id(){
        return order_itemId;
    }
    int getOrder_id(){
        return orderId;
    }
    int getGood_id(){
        return goodId;
    }
    int getQuantity(){
        return quantity;
    }
    double getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "order_itemId=" + order_itemId +
                ", orderId=" + orderId +
                ", goodId=" + goodId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
