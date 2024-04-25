package org.example.servlet;

import com.auth0.AuthenticationController;
import com.auth0.SessionUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.AuthenticationControllerProvider;
import org.example.controllers.ProductDAOImpl;
import org.example.models.Product;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/GetAccessToken"})

public class GetAccessToken extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetAccessToken.class);
    private AuthenticationController authenticationController;
    private String domain;
    private String clientId;
    private String clientSecret;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        domain = config.getServletContext().getInitParameter("com.auth0.domain");
        clientId = config.getServletContext().getInitParameter("com.auth0.clientId");
        clientSecret = config.getServletContext().getInitParameter("com.auth0.clientSecret");

        try {
            authenticationController = AuthenticationControllerProvider.getInstance(config);
        } catch (UnsupportedEncodingException e) {
            throw new ServletException("Couldn't create the AuthenticationController instance. Check the configuration.", e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String url = String.format("https://%s/oauth/token", domain);
            String body =  String.format("grant_type=client_credentials&client_id=%s&client_secret=%s&audience=https://dev-zycrb3xraiz8ecye.us.auth0.com/api/v2/", clientId, clientSecret);
            logger.info(body);
            logger.info(url);
            HttpResponse<String> r = (Unirest.post(url)
                    .header("content-type", "application/x-www-form-urlencoded")
                    .body(body)
                    .asString());
            response.setContentType("application/json;charset=UTF-8");
            logger.info(r.getBody());
            try (PrintWriter writer = response.getWriter()) {
                Gson gson = new Gson();

                writer.write(r.getBody());
            }

        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        logger.info("success");
    }


}
