package org.example.models;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketItem {
    private final int basketItemId;
    private final ArrayList<Integer> items;
    private final int client_id;

    public BasketItem(int basketItemId,  ArrayList<Integer> items, int client_id){
        this.basketItemId = basketItemId;
        this.items = items;
        this.client_id = client_id;


    }
    public int getBasketItemId(){
        return basketItemId;
    }
    public ArrayList<Integer> getItems(){
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

        for (Integer item : items){
            res += item.toString();
        }
        res+="}";
        return res;
    }
}
