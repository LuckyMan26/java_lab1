package org.example.DAOInterface;

import org.example.models.BasketItem;

import java.util.List;

public interface BasketDAO {

    BasketItem getBasketItemById(Long id);
    BasketItem getBasketItemByClientId(String id);

    void addOneProductToBasket(Long product_id, String client_i);

    void deleteBasketItem(Long id);
    void deleteProductInBasket(String client_id,Long product_id);
    public void addProductToBasket(BasketItem basketItem);

    public void clearBasket(String client_id);
}
