package com.bittech.servelt.a_life;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:chaoqiwen
 * @Date:2019/7/27 16:51
 */

/*Servelt生命周期
* */
public class LifeDemo extends HttpServlet {
    public LifeDemo(){
        System.out.println("servelt对象被创建");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Servlet被初始化");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet的业务服务方法");
    }

    @Override
    public void destroy() {
        System.out.println("servelt被销毁了");
    }

}
