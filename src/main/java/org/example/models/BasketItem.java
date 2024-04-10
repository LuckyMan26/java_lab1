package org.example.models;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketItem {
    private final int basketItemId;
    private final ArrayList<OrderItem> items;
    private final int client_id;

    public BasketItem(int basketItemId,  ArrayList<OrderItem> items, int client_id){
        this.basketItemId = basketItemId;
        this.items = items;
        this.client_id = client_id;


    }
    public int getBasketItemId(){
        return basketItemId;
    }
    public ArrayList<OrderItem> getItems(){
        return items;
    }
    public int getClient_id(){
        return client_id;
    }



    @Override
    public String toString() {
        String res = "";
        res += "BasketItem{" +
                "basketItemId=" + basketItemId;

        for (OrderItem item : items){
            res += item.toString();
        }
        res+="}";
        return res;
    }
}
