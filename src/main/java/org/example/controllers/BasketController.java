package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConnectionPool;
import org.example.connections.ConnectionWrapper;
import org.example.models.BasketItem;
import org.example.repository.BasketDAOImpl;

import java.util.ArrayList;

public class BasketController {
    private static final Logger logger = LogManager.getLogger(BasketController.class);
    public static final BasketController INSTANCE = new BasketController();
    public void addProductToBasket(BasketItem basketItem){
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            BasketDAOImpl.getInstance().addProductToBasket(basketItem, connection);

        }

    }
    public BasketItem getBasketItemById(Long id){
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            return BasketDAOImpl.getInstance().getBasketItemById(id, connection);

        }
    }
    public BasketItem getBasketItemByClientId(String id){
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            return BasketDAOImpl.getInstance().getBasketItemByClientId(id, connection);

        }
    }
    public void addOneProductToBasket(Long product_id, String client_id){
        logger.info("addOneProductToBasket" + product_id);
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
             connection.doTransaction(() -> {
                BasketItem basketItem1 = BasketDAOImpl.getInstance().getBasketItemByClientId(client_id, connection);
                if (basketItem1 == null) {
                    logger.info("Here");
                    ArrayList< Long > arrayList = new ArrayList < > ();
                    arrayList.add(product_id);
                    BasketItem item = new BasketItem(arrayList, client_id);
                    BasketDAOImpl.getInstance().addProductToBasket(item, connection);
                }
                else {
                    BasketDAOImpl.getInstance().addOneProductToBasket(product_id, client_id, basketItem1, connection);
                }

                 return null;
             });
        }
    }
    public void deleteBasketItem(Long id){
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            BasketDAOImpl.getInstance().deleteBasketItem(id, connection);

        }
    }
    public void deleteProductInBasket(String client_id, Long product_id ){
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            connection.doTransaction(() -> {
                BasketItem basketItem1 = BasketDAOImpl.getInstance().getBasketItemByClientId(client_id, connection);
                BasketDAOImpl.getInstance().deleteProductInBasket(client_id, product_id, basketItem1, connection);
                if (basketItem1.getItems().size() == 1) {
                    logger.info("Here");
                    BasketDAOImpl.getInstance().deleteBasketItem(basketItem1.getBasketItemId(), connection);
                }
                else{

                }

                return null;
            });

        }
    }
    public void clearBasket(String client_id ){
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            BasketDAOImpl.getInstance().clearBasket(client_id, connection);

        }
    }
}
