package org.example.servlet;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.AuthenticationControllerProvider;
import org.example.controllers.ClientDAOImpl;
import org.example.controllers.ProductDAOImpl;
import org.example.models.Client;
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
import java.util.Objects;

@WebServlet(urlPatterns = {"/custom_login"})
public class CustomLogin extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(CustomLogin.class);
    private AuthenticationController authenticationController;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);


        try {
            authenticationController = AuthenticationControllerProvider.getInstance(config);
        } catch (UnsupportedEncodingException e) {
            throw new ServletException("Couldn't create the AuthenticationController instance. Check the configuration.", e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do get");
        request.getRequestDispatcher("/WEB-INF/jsp/custom_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("do Post");


        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String token = request.getParameter("token");
        Client client = new Client(1,name,email,address);
        logger.info(name);
        ClientDAOImpl.getInstance().addClient(client);
        Tokens tokens = null;

        try {
            tokens = authenticationController.handle(request, response);
            SessionUtils.set(request, "accessToken", tokens.getAccessToken());
            SessionUtils.set(request, "idToken", tokens.getIdToken());
        } catch (IdentityVerificationException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/portal/home");



    }
}
