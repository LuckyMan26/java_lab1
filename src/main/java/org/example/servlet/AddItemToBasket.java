package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketDAOImpl;
import org.example.controllers.ReviewDAOImpl;
import org.example.models.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddItemToBasket", urlPatterns = {"/AddItemToBasket"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddItemToBasket extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AddItemToBasket.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("doPost");


        String product_id = request.getParameter("product_id");
        String client_id = request.getParameter("client_id");

        BasketDAOImpl.getInstance().addOneProductToBasket(Integer.parseInt(product_id),Integer.parseInt(client_id));
    }
}
