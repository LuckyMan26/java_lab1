package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.DAOInterface.ClientDAO;
import org.example.connections.ConenctionPool;
import org.example.connections.TransactionWrapper;
import org.example.models.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class);

    private static ClientDAOImpl instance;

    // Private constructor to prevent instantiation from outside
    private ClientDAOImpl() {

    }

    // Static method to get the singleton instance
    public static synchronized ClientDAOImpl getInstance() {
        if (instance == null) {
            instance = new ClientDAOImpl();
        }
        return instance;
    }
    @Override
    public void addClient(Client client) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO clients (name, email, address) VALUES ( ?, ?, ?)");
                statement.setString(1, client.getName());
                statement.setString(2, client.getEmail());
                statement.setString(3, client.getAddress());

                statement.executeUpdate();
                return null;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public Client getClientById(int id) {
        Client c = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            c = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients WHERE client_id = ?");
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                Client client = null;
                while (resultSet.next()) {
                    int client_id = resultSet.getInt("client_id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    client = new Client(client_id, name, email, address);
                    logger.info(client.toString());
                }
                return client;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return c;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> listOfClients = null;
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            listOfClients = transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients");


                ResultSet resultSet = statement.executeQuery();
                Client client = null;
                List<Client> list = null;
                while (resultSet.next()) {
                    int client_id = resultSet.getInt("client_id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    client = new Client(client_id, name, email, address);
                    logger.info(client.toString());
                    list.add(client);
                }
                return list;
            });
        }
        catch (InterruptedException | SQLException e){
            logger.error(e.getMessage());
        }
        return listOfClients;
    }

    @Override
    public void deleteClient(int id) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
            transactionWrapper.executeTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement("DELETE  * FROM clients WHERE client_id = ?");

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
