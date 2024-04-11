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
        return instance;
    }



    @Override
    public BasketItem getBasketItemById(int id) {
        BasketItem basketItem = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            basketItem = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM basket WHERE basket_id = ?");
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                BasketItem basketItem1 = null;
                while (resultSet.next()) {
                    int basket_id = resultSet.getInt("basket_id");

                    Integer[] integerArray = (Integer[]) resultSet.getArray("product_items_id").getArray();

                    ArrayList<Integer> items = new ArrayList<Integer>(Arrays.asList(integerArray));

                    int client_id = resultSet.getInt("client_id");
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
    public BasketItem getBasketItemByClientId(int client_id) {
        BasketItem basketItem = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            basketItem = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM basket WHERE client_id = ?");
                statement.setInt(1, client_id);

                ResultSet resultSet = statement.executeQuery();
                BasketItem basketItem1 = null;
                while (resultSet.next()) {
                    int basket_id = resultSet.getInt("basket_id");

                    Integer[] integerArray = (Integer[]) resultSet.getArray("product_items_id").getArray();

                    ArrayList<Integer> items = new ArrayList<Integer>(Arrays.asList(integerArray));



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
    public void addOneProductToBasket(int product_id, int client_id){
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                Product product = ProductDAOImpl.getInstance().getGoodById(product_id);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO basket (product_items_id, client_id) VALUES ( ?, ?,)");
                BasketItem basketItem = BasketDAOImpl.getInstance().getBasketItemById(client_id);
                ArrayList<Integer> items = basketItem.getItems();
                Integer[] array = new Integer[items.size()+1];
                int counter = 0;


                for(Integer item : items){
                    array[counter] =  item;
                    counter+=1;
                }
                array[items.size()] = product_id;

                statement.setArray(1, connection.createArrayOf("INTEGER", array));
                statement.setInt(2, client_id);
                statement.executeUpdate();
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
