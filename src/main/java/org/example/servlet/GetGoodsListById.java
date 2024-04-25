package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controllers.ProductController;
import org.example.dto.ServletJsonMapper;
import org.example.models.Product;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/GetGoodsListById"})
public class GetGoodsListById extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetGoodsListById.class);
    private static class Request {
        public List<Long> products;

    }

    private static class Response {
        public List<Product> productArrayList = new ArrayList<>();;

        Response(List<Product> productArrayList) {
            this.productArrayList = productArrayList;
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Request request = ServletJsonMapper.objectFromJsonRequest(req, Request.class);



        List<Long> products = request.products;
        logger.info(products.size());
        List<Product> productArrayList = new ArrayList<>();
        logger.info(products);
        for (int i = 0; i < products.size(); i++) {
            Long id = products.get(i);

            logger.info(id);
            Product p = ProductController.INSTANCE.getGoodById(id);
            productArrayList.add(p);
        }
            logger.info("write here");
            ServletJsonMapper.objectToJsonResponse(new Response(productArrayList), res);

            logger.info(productArrayList.toString());

    }
}
