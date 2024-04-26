package org.example.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConnectionPool;
import org.example.DAOInterface.BasketDAO;
import org.example.connections.ConnectionWrapper;
import org.example.models.BasketItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BasketDAOImpl implements BasketDAO {
    private static final Logger logger = LogManager.getLogger(BasketDAOImpl.class);

    private static BasketDAOImpl instance;

    // Private constructor to prevent instantiation from outside
    private BasketDAOImpl() {}

    // Static method to get the singleton instance
    public static synchronized BasketDAOImpl getInstance() {
        if (instance == null) {
            instance = new BasketDAOImpl();
        }
        logger.info("get instance");
        return instance;
    }
    private static BasketItem resultToBasket(ResultSet resultSet) throws SQLException {
        Long basket_id = resultSet.getLong("basket_id");

        Long[] integerArray = (Long[]) resultSet.getArray("product_items_id").getArray();
        String client_id = resultSet.getString("client_id");
        ArrayList < Long > items = new ArrayList < Long > (Arrays.asList(integerArray));
        return new BasketItem(basket_id, items, client_id);
    }
    @Override
    public void addProductToBasket(BasketItem basketItem, ConnectionWrapper connection) {
        try {
            String sql = "INSERT INTO basket (product_items_id, client_id) VALUES ( ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            ArrayList < Long > items = basketItem.getItems();
            Long[] array = new Long[items.size()];
            int counter = 0;
            for (Long item: items) {
                array[counter] = item;
                counter += 1;
            }
            statement.setArray(1, connection.createArrayOf(array));
            statement.setString(2, basketItem.getClient_id());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public BasketItem getBasketItemById(Long id, ConnectionWrapper connection) {
        BasketItem basketItem = null;
        try {

            String sql = "SELECT * FROM Basket WHERE basket_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long basket_id = resultSet.getLong("basket_id");

                Long[] integerArray = (Long[]) resultSet.getArray("product_items_id").getArray();

                ArrayList < Long > items = new ArrayList < Long > (Arrays.asList(integerArray));

                String client_id = resultSet.getString("client_id");
                basketItem = new BasketItem(basket_id, items, client_id);
            }

        } catch (SQLException e) {

            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return basketItem;
    }

    @Override
    public BasketItem getBasketItemByClientId(String client_id, ConnectionWrapper connection) {

        logger.info("getBasketItemByClientId");
        try {
            String sql = "SELECT * FROM Basket WHERE client_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, client_id);
            logger.info("set long");
            ResultSet resultSet = statement.executeQuery();
            BasketItem basketItem1 = null;
            logger.info("basketItem1");
            while (resultSet.next()) {

                basketItem1 = resultToBasket(resultSet);
            }
            return basketItem1;
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void addOneProductToBasket(Long product_id, String client_id, BasketItem basketItem1, ConnectionWrapper connection) {
        try {
            String sql = "UPDATE Basket SET product_items_id = ? WHERE client_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            logger.info("HERE");

            logger.info(basketItem1);

                logger.info(basketItem1.toString());
                ArrayList < Long > items = basketItem1.getItems();
                Long[] array = new Long[items.size() + 1];
                int counter = 0;

                for (Long item: items) {
                    array[counter] = item;
                    counter += 1;
                }
                array[items.size()] = product_id;

                statement.setArray(1, connection.createArrayOf(array));
                statement.setString(2, client_id);
                statement.executeUpdate();


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void deleteBasketItem(Long id, ConnectionWrapper connection) {
        try {
            String sql = "DELETE FROM Basket WHERE basket_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            logger.info("delete " + id);

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteProductInBasket(String client_id, Long product_id, BasketItem basketItem1, ConnectionWrapper connection)   {
        try {
            String sql = "UPDATE Basket SET product_items_id = ? WHERE client_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            logger.info("HERE");

            logger.info(basketItem1);

                logger.info(client_id);
                logger.info(basketItem1.toString());
                ArrayList < Long > items = basketItem1.getItems();
                ArrayList < Long > list_without_element = new ArrayList < > ();

                int counter = 0;
                for (Long item: items) {
                    if (!Objects.equals(item, product_id)) {
                        list_without_element.add(item);
                    }
                }
                Long[] array = new Long[list_without_element.size()];
                for (Long item: list_without_element) {
                    array[counter] = item;
                    counter += 1;
                }

                statement.setArray(1, connection.createArrayOf(array));
                statement.setString(2, client_id);
                statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearBasket(String client_id, ConnectionWrapper connection) {
        try {
            logger.info("clearBasket");
            String sql = "DELETE FROM Basket WHERE client_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, client_id);
            logger.info(client_id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}