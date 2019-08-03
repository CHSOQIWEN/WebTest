package com.bittech.servelt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @Author:chaoqiwen
 * @Date:2019/8/3 20:48
 */
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession httpSession=req.getSession();
        Object value=httpSession.getAttribute("name");
        if(value==null){
            httpSession.setAttribute("name","张三"+ LocalDateTime.now());
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        resp.getWriter().write("<a href='SessionServlet'>");
        resp.getWriter().write("刷新");
        resp.getWriter().write("</a>");
        resp.getWriter().write((String) httpSession.getAttribute("name"));
        resp.getWriter().flush();
    }
}
