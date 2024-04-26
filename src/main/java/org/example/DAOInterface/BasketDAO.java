package org.example.DAOInterface;

import org.example.connections.ConnectionWrapper;
import org.example.models.BasketItem;

import java.sql.Connection;
import java.util.List;

public interface BasketDAO {

    BasketItem getBasketItemById(Long id, ConnectionWrapper connection);
    BasketItem getBasketItemByClientId(String id, ConnectionWrapper connection);

    void addOneProductToBasket(Long product_id, String client_i, BasketItem basketItem1, ConnectionWrapper connection);

    void deleteBasketItem(Long id, ConnectionWrapper connection);
    void deleteProductInBasket(String client_id,Long product_id, BasketItem basketItem1, ConnectionWrapper connection);
    public void addProductToBasket(BasketItem basketItem, ConnectionWrapper connection);

    public void clearBasket(String client_id, ConnectionWrapper connection);
}
