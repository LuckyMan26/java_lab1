package org.example;

import java.util.List;

public interface ClientDAO {
    void addClient(Client client);
    Client getClientById(int id);
    List<Client> getAllClients();
    void deleteClient(int id);
}
