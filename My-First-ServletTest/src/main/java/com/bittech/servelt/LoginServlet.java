package com.bittech.servelt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:chaoqiwen
 * @Date:2019/7/29 18:52
 */
@WebServlet(name = "loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        if("admin".equals(username)&&"admin".equals(password)){
            resp.getWriter().append("<h1>").append("恭喜，登录成功").append("</h1>");
        }else {
            resp.getWriter().append("<h1>").append("恭喜，用户名或者密码错误").append("</h1>");
        }
        resp.getWriter().flush();
    }
}
