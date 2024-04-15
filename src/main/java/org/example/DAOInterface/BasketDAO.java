package org.example.DAOInterface;

import org.example.models.BasketItem;

import java.util.List;

public interface BasketDAO {

    BasketItem getBasketItemById(Long id);
    BasketItem getBasketItemByClientId(Long id);

    void addOneProductToBasket(Long product_id, Long client_i);

    void deleteBasketItem(Long id);
    void deleteProductInBasket(Long client_id,Long product_id);
    public void addProductToBasket(BasketItem basketItem);
}
