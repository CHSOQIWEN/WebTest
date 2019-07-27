package com.bittech.myfirst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * @author CHAOQIWEN
 * @data 2019/7/23 17:07
 */
public class IndexServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer=resp.getWriter();
       writer.write("<html>");
           writer.write("<head>");
              writer.write("<title>");
              writer.write("Servlet程序");
              writer.write("</title>");
           writer.write("</head>");
           writer.write("<body>");
              writer.append("<h1>").append("当前时间").append("</h2>");
              writer.append("<p>")
                      .append(LocalDateTime.now().toString())
                      .append("</p>");
           writer.write("</body>");
        writer.write("</html>");

        //writer.print("<html><head><title>this is a title</title></head><body>这是我第一个servlet</body></html>");
    }
}
