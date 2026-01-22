package com.example.prodwebapp;

import java.io.IOException;

import com.example.prodwebapp.lib.ViewResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DBConnection.getConnection();
        String cmd = req.getParameter("cmd");
        String id = req.getParameter("id");

        switch (cmd) {
            case "list":
                ViewResolver.render(cmd).forward(req, resp);
                break;
            case "insert-form":
                ViewResolver.render("insert").forward(req, resp);
                break;
            case "detail":
                ViewResolver.render(cmd).forward(req, resp);
                break;
            default:
                break;
        }
    }

    // delete, post 두 요청을 이 메서드로 받을 예정
    // 이유 : form 태그는 post, get 요청만 할 수 있다.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
