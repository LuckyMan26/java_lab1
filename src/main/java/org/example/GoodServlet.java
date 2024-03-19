package org.example;
import org.example.controllers.GoodsDAOImpl;
import org.example.models.Good;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
@WebServlet("/goods")
public class GoodServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Good> goodList = GoodsDAOImpl.getInstance().getAllGoods();
        Gson gson = new Gson();
        String json = gson.toJson(goodList);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

}


