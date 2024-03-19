package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.DAOInterface.BlackListDAO;
import org.example.connections.ConenctionPool;
import org.example.connections.TransactionWrapper;
import org.example.models.BlackListElement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BlackListDAOImpl implements BlackListDAO {
    private static final Logger logger = LogManager.getLogger(BlackListDAOImpl.class);

    @Override
    public void addClientToBlackList(BlackListElement element) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO blacklist (client_id, reason) VALUES ( ?, ?,)");
                statement.setInt(1, element.getClientid());
                statement.setString(2, element.getReason());

                statement.executeUpdate();
                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public BlackListElement getBlackListElementId(int id) {

        BlackListElement g = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            g = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM blacklist WHERE blacklist_id = ?");
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                BlackListElement e = null;
                while (resultSet.next()) {
                    int blacklistid = resultSet.getInt("blacklist_id");
                    int clientId = resultSet.getInt("client_id");
                    String reason = resultSet.getString("reason");

                    e = new BlackListElement(blacklistid, clientId, reason);
                    logger.info(e.toString());
                }
                return e;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return g;
    }

    @Override
    public List<BlackListElement> getAllBlackList() {
        List<BlackListElement> listOfElements = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            listOfElements = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM blacklist");


                ResultSet resultSet = statement.executeQuery();
                BlackListElement e = null;
                List<BlackListElement> list = null;
                while (resultSet.next()) {
                    int blacklistid = resultSet.getInt("blacklit_id");
                    int cliendid = resultSet.getInt("client_id");
                    String reason = resultSet.getString("reason");

                    e = new BlackListElement(blacklistid, cliendid, reason);
                    logger.info(e.toString());
                    list.add(e);
                }
                return list;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return listOfElements;
    }

    @Override
    public void deleteBlackListElement(int id) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("DELETE  * FROM blacklist WHERE blacklist_id = ?");

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
