package org.example.models;

import org.example.controllers.ProductController;
import org.example.repository.ProductDAOImpl;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private final Long orderId;
    private final String clientId;
    private final ArrayList<Long> products;
    private final Date orderDate;

    private final Status status;
    private final double totalPrice;
    private final String fullName;
    private final String address;
    public Order(Long orderId, String clientId, Date orderDate,  Status status,ArrayList<Long> products, String full_name, String address){
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;

        this.status = status;
        this.products = products;
        double res = 0;
        for(Long product : products){
            res += ProductController.INSTANCE.getGoodById(product).getPrice();
        }
        this.totalPrice = res;
        this.fullName = full_name;
        this.address = address;

    }
    public Order(Long orderId, String clientId, Date orderDate, ArrayList<Long> products, String full_name, String address){
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;

        this.status = Status.Pending;
        this.products = products;
        double res = 0;
        for(Long product : products){

            res += ProductController.INSTANCE.getGoodById(product).getPrice();
        }
        this.totalPrice = res;
        this.fullName = full_name;
        this.address = address;
    }
    public Order(Long orderId, String clientId, Date orderDate, Status status, ArrayList<Long> products, double totalPrice, String fullName, String address){
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;

        this.status = status;
        this.products = products;

        this.totalPrice = totalPrice;
        this.fullName = fullName;
        this.address = address;

    }
    public Long getOrderId(){
        return orderId;
    }
    public String getClientId(){
        return clientId;
    }
    public Date getOrderDate(){
        return orderDate;
    }

    public Status getStatus(){
        return status;
    }
    public ArrayList<Long> getProducts(){
        return products;
    }
    public double getTotalPrice(){
        return totalPrice;
    }
    public String getAddress(){
        return address;
    }
    public String getFullName(){
        return  fullName;
    }
    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + orderId +
                ", clientId='" + clientId + '\'' +
                ", order_date='" + orderDate + '\'' +

                ", status=" + status +
                '}';
    }
}
