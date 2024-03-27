package org.example.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConenctionPool;
import org.example.DAOInterface.GoodsDAO;
import org.example.connections.TransactionWrapper;
import org.example.models.Good;

public class GoodsDAOImpl implements GoodsDAO {
    private static final Logger logger = LogManager.getLogger(GoodsDAOImpl.class);
    private static GoodsDAOImpl instance;

    // Private constructor to prevent instantiation from outside
    private GoodsDAOImpl() {
    }

    // Static method to get the singleton instance
    public static synchronized GoodsDAOImpl getInstance() {
        if (instance == null) {
            instance = new GoodsDAOImpl();
        }
        return instance;
    }

    @Override
    public void addGood(Good good) {
        System.out.println(good.toString());
        logger.info("Adding good" + good.toString());
        try {
            logger.info("Here");
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Goods (name, description, price, quantity_available) VALUES ( ?, ?, ?, ?)");
                statement.setString(1, good.getName());
                statement.setString(2, good.getDescription());
                statement.setDouble(3, good.getPrice());
                statement.setInt(4, good.getQuantity_available());
                statement.executeUpdate();

                return null;
            });
        }
        catch (InterruptedException | SQLException e){
        logger.error(e.getMessage());
        }
    }

    @Override
    public Good getGoodById(int id) {
        Good g = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            g = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM goods WHERE goods_id = ?");
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                Good good = null;
                while (resultSet.next()) {
                    int good_id = resultSet.getInt("goods_id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    double price = resultSet.getDouble("price");
                    int quantity = resultSet.getInt("quantity_available");
                    good = new Good(good_id, name, description, price, quantity);
                   logger.info(good.toString());
                }
                return good;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return g;
    }

    @Override
    public List<Good> getAllGoods() {
        List<Good> listOfGoods = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            listOfGoods = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM goods");


                ResultSet resultSet = statement.executeQuery();
                Good good = null;
                List<Good> list =  new ArrayList<Good>();
                while (resultSet.next()) {
                    int good_id = resultSet.getInt("goods_id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    double price = resultSet.getDouble("price");
                    int quantity = resultSet.getInt("quantity_available");
                    good = new Good(good_id, name, description, price, quantity);
                    logger.info(good.toString());
                    list.add(good);
                    logger.info("list size:" + String.valueOf(list.size()));
                }
                logger.info("list size:" + String.valueOf(list.size()));
                return list;
            });
            logger.info(listOfGoods.size());
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }

        return listOfGoods;
    }

    @Override
    public void deleteGood(int id) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
           transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("DELETE  * FROM goods WHERE goods_id = ?");

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
