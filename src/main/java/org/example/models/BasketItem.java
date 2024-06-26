package org.example.models;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketItem {
    private Long basketItemId;
    private final ArrayList<Long> items;
    private final String clientId;

    public BasketItem(Long basketItemId,  ArrayList<Long> items, String client_id){
        this.basketItemId = basketItemId;
        this.items = items;
        this.clientId = client_id;

    }
    public BasketItem(ArrayList<Long> items, String client_id){

        this.items = items;
        this.clientId = client_id;


    }
    public Long getBasketItemId(){
        return basketItemId;
    }
    public ArrayList<Long> getItems(){
        return items;
    }
    public String getClientId(){
        return clientId;
    }



    @Override
    public String toString() {
        String res = "";
        res += "BasketItem{" +
                "basketItemId=" + basketItemId+" ";

        for (Long item : items){
            res += item.toString() + " ";
        }
        res+="}";
        return res;
    }
}
