package org.example;
import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConenctionPool;
import org.example.connections.TransactionWrapper;
import org.example.controllers.GoodsDAOImpl;
import org.example.controllers.ClientDAOImpl;
import org.example.models.Client;
import org.example.models.Good;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Main.class);

        ClientDAOImpl.getInstance().addClient(new Client(1,"Example good", "Description","A"));


    }
}