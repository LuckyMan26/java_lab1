package org.example;

import java.util.Date;

public class Order {
    private final int orderId;
    private final int clientId;
    private final Date order_date;
    private final int total_amount;
    private final String status;
    public Order(int orderId, int clientId, Date order_date, int total_amount, String status){
        this.orderId = orderId;
        this.clientId = clientId;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.status = status;
    }
    int getOrderId(){
        return orderId;
    }
    int getClientId(){
        return clientId;
    }
    Date getOrder_date(){
        return order_date;
    }
    int getTotal_amount(){
        return total_amount;
    }
    String getStatus(){
        return status;
    }
    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + orderId +
                ", clientId='" + clientId + '\'' +
                ", order_date='" + order_date + '\'' +
                ", total_amount=" + total_amount +
                ", status=" + status +
                '}';
    }
}
