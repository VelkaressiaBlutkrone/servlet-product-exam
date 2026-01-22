package com.example.prodwebapp;

import java.io.IOException;

import com.example.prodwebapp.lib.ViewResolver;
import com.example.prodwebapp.product.ProductController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    final ProductController pc = new ProductController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DBConnection.getConnection();
        String cmd = req.getParameter("cmd");

        if ("list".equals(cmd)) {
            String viewName = pc.list(req, resp);
            ViewResolver.render(viewName).forward(req, resp);
        } else if ("insert-form".equals(cmd)) {
            String viewName = pc.insertForm(req, resp);
            ViewResolver.render(viewName).forward(req, resp);
        } else if ("detail".equals(cmd)) {
            String viewName = pc.detail(req, resp);
            ViewResolver.render(viewName).forward(req, resp);
        }
    }

    // delete, post 두 요청을 이 메서드로 받을 예정
    // 이유 : form 태그는 post, get 요청만 할 수 있다.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");
        String id = req.getParameter("id");

        if ("insert".equals(cmd)) {
            String name = req.getParameter("name");
            int price = Integer.parseInt(req.getParameter("price"));
            int qty = Integer.parseInt(req.getParameter("qty"));
            pc.insert(req, resp, name, price, qty);
            resp.sendRedirect("product.do?cmd=list");
        } else if ("delete".equals(cmd)) {
            pc.delete(req, resp, Integer.parseInt(id));
            resp.sendRedirect("product.do?cmd=list");
        }

    }

}
