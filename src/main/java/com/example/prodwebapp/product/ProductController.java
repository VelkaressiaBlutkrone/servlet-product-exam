package com.example.prodwebapp.product;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductController {

    ProductService service = new ProductService();

    public String list(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> models = service.list();
        req.setAttribute("models", models);
        req.setAttribute("what", "뭐?");
        return "forward:list";
    }

    public String detail(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product model = service.detail(id);
        req.setAttribute("model", model);
        return "forward:detail";
    }

    public String insertForm(HttpServletRequest req, HttpServletResponse resp) {
        return "forward:insert";
    }

    public void insert(HttpServletRequest req, HttpServletResponse resp, String name, int price, int qty) {
        service.add(name, price, qty);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp, int id) {
        service.del(id);
    }

}
