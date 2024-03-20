package org.example.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConenctionPool {
    private static ConenctionPool instance;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgres";
    private String password = "q1w2e3r4t5";


    private static int MAX_NUMBER_OF_CONNECTIONS = 15;
    private  ArrayList<Connection> connectionPool= new ArrayList<>();
    private final ArrayList<Connection> usedConnections = new ArrayList<>();
    private ConenctionPool() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connectionPool = new ArrayList<>(MAX_NUMBER_OF_CONNECTIONS);
        try {
            for (int i = 0; i < MAX_NUMBER_OF_CONNECTIONS; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                connectionPool.add(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public synchronized Connection getConnection() throws SQLException, InterruptedException {
        if (connectionPool.isEmpty()) {

            wait();
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }
    public synchronized void releaseConnection(Connection connection) {
        usedConnections.remove(connection);
        connectionPool.add(connection);
        notifyAll();
    }
    public synchronized int getSizeConnection(){
        return connectionPool.size();
    }
    public synchronized void closeAllConnections() throws SQLException {
        for (Connection connection : usedConnections) {
            connection.close();
        }
        for (Connection connection : connectionPool) {
            connection.close();
        }
        usedConnections.clear();
        connectionPool.clear();
    }
    public static ConenctionPool getInstance() {
        ConenctionPool result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ConenctionPool.class) {
            try {
                if (instance == null) {
                    instance = new ConenctionPool();
                }
            }
            catch (ClassNotFoundException e){
                System.out.println(e.toString());
            }
            return instance;
        }
    }
}
