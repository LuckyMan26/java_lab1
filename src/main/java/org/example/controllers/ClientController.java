package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConnectionPool;
import org.example.connections.ConnectionWrapper;
import org.example.models.Client;
import org.example.repository.BasketDAOImpl;
import org.example.repository.ClientDAOImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientController {
    private static final Logger logger = LogManager.getLogger(ClientController.class);
    public static final ClientController INSTANCE = new ClientController();
    public void addClient(Client client ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            ClientDAOImpl.getInstance().addClient(client, connection);

        }
    }

    public Client getClientById(int id ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            return ClientDAOImpl.getInstance().getClientById(id, connection);

        }
    }
    public List< Client > getAllClients() {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            return ClientDAOImpl.getInstance().getAllClients(connection);

        }
    }

    public void deleteClient(int id ) {
        try (ConnectionWrapper connection = ConnectionPool.INSTANCE.getConnection()) {
            ClientDAOImpl.getInstance().deleteClient(id,connection);

        }
    }
}
