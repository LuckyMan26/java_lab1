package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.example.controllers.GoodsDAOImpl;
import org.example.models.Good;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GoodServlet", urlPatterns = {"/GoodServlet"})
public class GoodServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            Gson gson = new Gson();
            ArrayList<Good> listOfGoods = (ArrayList<Good>) GoodsDAOImpl.getInstance().getAllGoods();
            JsonElement element = gson.toJsonTree(listOfGoods);
            writer.write(element.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            Gson gson = new Gson();
            ArrayList<Good> listOfGoods = (ArrayList<Good>) GoodsDAOImpl.getInstance().getAllGoods();
            JsonElement element = gson.toJsonTree(listOfGoods);
            writer.write(element.toString());
        }
    }
}
