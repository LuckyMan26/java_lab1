package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConenctionPool;
import org.example.connections.TransactionWrapper;
import org.example.models.Client;
import org.example.models.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException, IOException {
        Logger logger = LogManager.getLogger(Main.class);

/*
        Client client = new Client(1,"Artem","volikartem6@gmail.com","Kiev");
        TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
        transactionWrapper.executeTransaction(connection -> {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO clients (name, email, address) VALUES ( ?, ?, ?)");
            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getAddress());

            statement.executeUpdate();

            return null;
        });*/
    }
}