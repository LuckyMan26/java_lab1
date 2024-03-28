package org.example;
import java.io.IOException;
import java.sql.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connections.ConenctionPool;
import org.example.connections.TransactionWrapper;
import org.example.controllers.GoodsDAOImpl;
import org.example.controllers.ClientDAOImpl;
import org.example.models.Client;
import org.example.models.Good;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    public static void main(String[] args)  {
        Logger logger = LogManager.getLogger(Main.class);
        logger.info("hello world!");
       /* Good good = new Good(1,"Good 2", "Description",50, 5);
        GoodsDAOImpl.getInstance().addGood(good);*/
       /* GoodsDAOImpl.getInstance().addGood(new Good(1,"Good 3", "Description",50, 5));
        GoodsDAOImpl.getInstance().addGood(new Good(1,"Good 4", "Description",50, 5));
        GoodsDAOImpl.getInstance().addGood(new Good(1,"Good 5", "Description",50, 5));
        GoodsDAOImpl.getInstance().addGood(new Good(1,"Good 6", "Description",50, 5));*/





    }
}