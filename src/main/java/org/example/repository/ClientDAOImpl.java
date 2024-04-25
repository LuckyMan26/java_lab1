package org.example.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.DAOInterface.ClientDAO;
import org.example.connections.ConenctionPool;
import org.example.connections.ConnectionWrapper;
import org.example.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private static final Logger logger = LogManager.getLogger(ClientDAOImpl.class);

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
    public void addClient(Client client, ConnectionWrapper connection) {
        try {
            String sql = "INSERT INTO clients (name, email, address) VALUES ( ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getAddress());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Client getClientById(int id, ConnectionWrapper connection) {
        Client c = null;
        try {
            String sql = "SELECT * FROM clients WHERE client_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            Client client = null;
            while (resultSet.next()) {
                Long client_id = resultSet.getLong("client_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                client = new Client(client_id, name, email, address);
                logger.info(client.toString());
            }
            return client;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List < Client > getAllClients(ConnectionWrapper connection) {
        List < Client > listOfClients = null;
        try {
            String sql = "SELECT * FROM clients";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            Client client = null;
            List < Client > list = null;
            while (resultSet.next()) {
                Long client_id = resultSet.getLong("client_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                client = new Client(client_id, name, email, address);
                logger.info(client.toString());
                list.add(client);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteClient(int id, ConnectionWrapper connection) {
        try {
            String sql = "DELETE  * FROM clients WHERE client_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}