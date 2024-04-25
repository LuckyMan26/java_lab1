package org.example;

import com.auth0.SessionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = {"/portal/home"})
public class HomeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(HomeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final String accessToken = (String) SessionUtils.get(req, "accessToken");
        final String idToken = (String) SessionUtils.get(req, "idToken");
        logger.info(accessToken);
        logger.info(idToken);
        if (accessToken != null) {
            req.setAttribute("accessToken", accessToken);
        }
        if (idToken != null) {
            req.setAttribute("userId", idToken);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, res);
    }

}

