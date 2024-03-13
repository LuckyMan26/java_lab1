package org.example;
import java.sql.*;

import org.example.ConenctionPool;
import org.example.TransactionWrapper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    public static void main(String[] args) {

        try {

            Class.forName("org.postgresql.Driver");

            // Connect to the database

            ConenctionPool pool = ConenctionPool.getInstance();
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            TransactionWrapper transactionWrapper = new TransactionWrapper(pool);
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                executorService.submit(()->transactionWrapper.executeTransaction(connection -> {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO Goods (name, description, price, quantity_available) VALUES ( ?, ?, ?, ?)");
                    statement.setString(1, "Artem");
                    statement.setString(2, "Very good!");
                    statement.setInt(3, 100);
                    statement.setInt(4, finalI);
                    statement.executeUpdate();
                    return null;
                }));

            }
            executorService.shutdown();
        } catch (ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (InterruptedException | RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}