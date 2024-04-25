package org.example.DAOInterface;

import org.example.connections.ConnectionWrapper;
import org.example.models.Client;

import java.sql.Connection;
import java.util.List;

public interface ClientDAO {
    void addClient(Client client, ConnectionWrapper connection);
    Client getClientById(int id, ConnectionWrapper connection);
    List<Client> getAllClients(ConnectionWrapper connection);
    void deleteClient(int id, ConnectionWrapper connection);
}
