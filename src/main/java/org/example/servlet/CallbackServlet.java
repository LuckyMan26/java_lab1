package org.example.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/callback"})
public class CallbackServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String referer = (String) request.getSession().getAttribute("Referer");
        String redirectTo = referer != null ? referer : "/";

        response.sendRedirect(redirectTo);
    }
}