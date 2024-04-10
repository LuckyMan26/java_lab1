package org.example.DAOInterface;

import org.example.models.BasketItem;

import java.util.List;

public interface BasketDAO {
    void addProductToBasket(BasketItem basketItem);
    BasketItem getBasketItemById(int id);
    BasketItem getBasketItemByClientId(int id);

    void deleteBasketItem(int id);
}
