package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.GoodsDAOImpl;
import org.example.models.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AddGood", urlPatterns = {"/AddGood"})

public class AddGoodServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GoodServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        }
    }

