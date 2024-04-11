package org.example.DAOInterface;

import org.example.models.BasketItem;

import java.util.List;

public interface BasketDAO {

    BasketItem getBasketItemById(int id);
    BasketItem getBasketItemByClientId(int id);

    void addOneProductToBasket(int product_id, int client_i);

    void deleteBasketItem(int id);
}
