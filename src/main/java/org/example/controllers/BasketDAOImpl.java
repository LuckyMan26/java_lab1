package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConenctionPool;
import org.example.DAOInterface.BasketDAO;
import org.example.connections.TransactionWrapper;
import org.example.models.BasketItem;
import org.example.models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasketDAOImpl implements BasketDAO {
    private static final Logger logger = LogManager.getLogger(BasketDAOImpl.class);


    private static BasketDAOImpl instance;

    // Private constructor to prevent instantiation from outside
    private BasketDAOImpl() {
    }

    // Static method to get the singleton instance
    public static synchronized BasketDAOImpl getInstance() {
        if (instance == null) {
            instance = new BasketDAOImpl();
        }
        logger.info("get instance");
        return instance;
    }


    @Override
    public void addProductToBasket(BasketItem basketItem) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO basket (product_items_id, client_id) VALUES ( ?, ?)");
                ArrayList<Long> items = basketItem.getItems();
                Long[] array = new Long[items.size()];
                int counter = 0;
                for(Long item : items){
                    array[counter] = item;
                    counter+=1;
                }
                statement.setArray(1, connection.createArrayOf("INTEGER", array));
                statement.setLong(2, basketItem.getClient_id());
                statement.executeUpdate();
                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }
    @Override
    public BasketItem getBasketItemById(Long id) {
        BasketItem basketItem = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            basketItem = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Basket WHERE basket_id = ?");
                statement.setLong(1, id);

                ResultSet resultSet = statement.executeQuery();
                BasketItem basketItem1 = null;
                while (resultSet.next()) {
                    Long basket_id = resultSet.getLong("basket_id");

                    Long[] integerArray = (Long[]) resultSet.getArray("product_items_id").getArray();

                    ArrayList<Long> items = new ArrayList<Long>(Arrays.asList(integerArray));

                    Long client_id = resultSet.getLong("client_id");
                    basketItem1 = new BasketItem(basket_id,items,client_id);
                }
                return basketItem1;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return basketItem;
    }

    @Override
    public BasketItem getBasketItemByClientId(Long client_id) {
        BasketItem basketItem = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            basketItem = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Basket WHERE client_id = ?");
                statement.setLong(1, client_id);

                ResultSet resultSet = statement.executeQuery();
                BasketItem basketItem1 = null;
                logger.info("basketItem1");
                while (resultSet.next()) {
                    Long basket_id = resultSet.getLong("basket_id");
                    logger.info(basket_id);
                    Long[] integerArray = (Long[]) resultSet.getArray("product_items_id").getArray();
                    logger.info(basket_id);
                    ArrayList<Long> items = new ArrayList<Long>(Arrays.asList(integerArray));



                    basketItem1 = new BasketItem(basket_id,items,client_id);
                }
                return basketItem1;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return basketItem;

    }

    @Override
    public void addOneProductToBasket(Long product_id, Long client_id){
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                Product product = ProductDAOImpl.getInstance().getGoodById(product_id);
                logger.info("HERE");
                BasketItem basketItem1 = null;
                PreparedStatement statement = null;
                logger.info(basketItem1);
                if(basketItem1 == null){
                    logger.info("Here");
                    ArrayList<Long> arrayList = new ArrayList<>();
                    arrayList.add(product_id);
                    BasketItem item = new BasketItem(arrayList,client_id);
                    addProductToBasket(item);
                }
                else {
                    statement = connection.prepareStatement("UPDATE Basket SET product_items_id = ? WHERE client_id = ?");
                    BasketItem basketItem = BasketDAOImpl.getInstance().getBasketItemById(client_id);
                    ArrayList<Long> items = basketItem.getItems();
                    Long[] array = new Long[items.size() + 1];
                    int counter = 0;


                    for (Long item : items) {
                        array[counter] = item;
                        counter += 1;
                    }
                    array[items.size()] = product_id;

                    statement.setArray(1, connection.createArrayOf("INTEGER", array));
                    statement.setLong(2, client_id);
                    statement.executeUpdate();
                }

                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }
    @Override
    public void deleteBasketItem(int id) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("DELETE  * FROM basket_items WHERE basket_item_id = ?");

                statement.setInt(1, id);
                statement.executeUpdate();

                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }


}
