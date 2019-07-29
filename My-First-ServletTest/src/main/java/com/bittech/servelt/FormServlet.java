package com.bittech.servelt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author:chaoqiwen
 * @Date:2019/7/29 16:39
 */

public class FormServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>\n"+
"<html lang=\"en\">\n"+
"<head>\n"+
"    <meta charset=\"UTF-8\">\n"+
 "   <title>表单提交</title>\n"+
"</head>\n"+
"<body>\n"+
"<from method=\"post\" action=\"/FormServlet\">\n"+
"    <label for=\"name\">姓名：</label>\n"+
    "<input id=\"name\" name=\"name\" type=\"text \" placeholder=\"请输入您的姓名\" value=\"\"/>\n"+
   " <input id=\"submit\"type=\"submit\" value=\"提交\"/>\n"+
"</from>\n"+

"</body>\n"+
"</html>");
        response.getWriter().flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String name=req.getParameter("name");

        PrintWriter writer=resp.getWriter();
        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>表单提交</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>欢迎，");
        writer.append(name);
        writer.append("</h1>\n" +
                "\n" +
                "</body>\n" +
                "</html>");

        resp.getWriter().flush();

    }
}
