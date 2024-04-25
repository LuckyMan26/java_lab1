package org.example.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.BasketController;
import org.example.dto.ServletJsonMapper;
import org.example.repository.BasketDAOImpl;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@WebServlet(name = "AddItemToBasket", urlPatterns = {"/AddItemToBasket"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddItemToBasket extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AddItemToBasket.class);
    private static class Request {
        @JsonProperty("product_id")
        public Long product_id;
        @JsonProperty("client_id")
        public String client_id;




    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        logger.info("doPost");
        Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);

        logger.info(String.valueOf(request.product_id), (request.client_id));
        BasketController.INSTANCE.addOneProductToBasket((request.product_id), (request.client_id));
        logger.info("success");
    }
}
