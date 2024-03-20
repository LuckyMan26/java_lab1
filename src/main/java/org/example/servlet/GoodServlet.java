package org.example.servlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.GoodsDAOImpl;
import org.example.models.Good;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
public class GoodServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GoodServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("goodList.get(0).toString()");
        List<Good> goodList = GoodsDAOImpl.getInstance().getAllGoods();
        Gson gson = new Gson();
        String json = gson.toJson(goodList);
        logger.info("goodList.get(0).toString()");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("Hello!");
        printWriter.close();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("goodList.get(0).toString()");
        List<Good> goodList = GoodsDAOImpl.getInstance().getAllGoods();
        Gson gson = new Gson();
        String json = gson.toJson(goodList);
        logger.info("goodList.get(0).toString()");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(json);
        printWriter.close();
    }
}


