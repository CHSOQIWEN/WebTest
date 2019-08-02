package com.bittech.servelt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:chaoqiwen
 * @Date:2019/7/31 21:39
 */
public class PersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        StringBuilder sb=new StringBuilder();
        if(id==null) {
            sb.append("<h1>").append("人员编号参数id不能为空");
        }else {
            HiddenServlet.Person person=HiddenServlet.personMap.get(id);
            if(person==null){
                sb.append("<h1>").append("找不到id="+id+"得人");
            }else {
                sb.append("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>修改人员信息</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<h1>修改人员信息</h1>");
                sb.append("<form action=\"/My-First-ServletTest/PersonServlet\" method=\"post\">\n" +
                        "    <input type=\"hidden\" name=\"id\" value=\"");
                sb.append(person.getId());
                sb.append("\">\n" +
                        "    <label for=\"name\">姓名：</label>\n" +
                        "    <input id=\"name\" name=\"name\" type=\"text\" value=\"");
                sb.append(person.getName());
                sb.append("\"/>\n" +
                        "    <label for=\"age\">年龄：</label>\n" +
                        "    <input id=\"age\" name=\"age\" type=\"text\" value=\"");
                sb.append(person.getAge());
                sb.append("\"/>\n" +
                        "    <input type=\"submit\" value=\"修改\">\n" +
                        "</form>");


                sb.append("\n" +
                        "</body>\n" +
                        "</html>");
            }
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id=req.getParameter("id");
        StringBuilder sb=new StringBuilder();
        if(id==null){
            sb.append("参数不能为空");
        }else {
            HiddenServlet.Person person=HiddenServlet.personMap.get(id);
            if(person==null){
                sb.append("找不到id="+id+"得人");
            }else {
                String name=req.getParameter("name");
                String age=req.getParameter("age");
                person.setName(name);
                person.setAge(Integer.parseInt(age));
            }
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.getWriter().write("<a href='HiddenServlet'>");
        resp.getWriter().write("查看所有人信息");
        resp.getWriter().write("</a>");
        resp.getWriter().flush();
    }
}
