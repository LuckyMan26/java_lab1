package org.example.servlet;

import org.example.security.Auth0JwtPrincipal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@WebServlet(urlPatterns = "")
public class HomeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Principal principal = request.getUserPrincipal();

        if (principal instanceof Auth0JwtPrincipal) {
            Auth0JwtPrincipal auth0JwtPrincipal = (Auth0JwtPrincipal) principal;
            request.setAttribute("profile", auth0JwtPrincipal.getIdToken().getClaims());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
