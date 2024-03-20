package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(IndexServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doget");
//        getServletContext().getRequestDispatcher("/hello").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/good");
    }
}