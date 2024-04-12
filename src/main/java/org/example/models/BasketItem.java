package org.example.models;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketItem {
    private Long basketItemId;
    private final ArrayList<Long> items;
    private final Long client_id;

    public BasketItem(Long basketItemId,  ArrayList<Long> items, Long client_id){
        this.basketItemId = basketItemId;
        this.items = items;
        this.client_id = client_id;

    }
    public BasketItem(ArrayList<Long> items, Long client_id){

        this.items = items;
        this.client_id = client_id;


    }
    public Long getBasketItemId(){
        return basketItemId;
    }
    public ArrayList<Long> getItems(){
        return items;
    }
    public Long getClient_id(){
        return client_id;
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
