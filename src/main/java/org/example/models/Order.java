package org.example.models;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private final Long orderId;
    private final Long clientId;
    private final ArrayList<Long> products;
    private final Date order_date;

    private final Status status;
    public Order(Long orderId, Long clientId, Date order_date,  Status status,ArrayList<Long> products ){
        this.orderId = orderId;
        this.clientId = clientId;
        this.order_date = order_date;

        this.status = status;
        this.products = products;
    }
    public Order(Long orderId, Long clientId, Date order_date, ArrayList<Long> products){
        this.orderId = orderId;
        this.clientId = clientId;
        this.order_date = order_date;

        this.status = Status.Pending;
        this.products = products;
    }
    public Long getOrderId(){
        return orderId;
    }
    public Long getClientId(){
        return clientId;
    }
    public Date getOrder_date(){
        return order_date;
    }

    public Status getStatus(){
        return status;
    }
    public ArrayList<Long> getProducts(){
        return products;
    }
    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + orderId +
                ", clientId='" + clientId + '\'' +
                ", order_date='" + order_date + '\'' +

                ", status=" + status +
                '}';
    }
}
