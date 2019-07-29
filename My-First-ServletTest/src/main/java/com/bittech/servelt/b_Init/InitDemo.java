package com.bittech.servelt.b_Init;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:chaoqiwen
 * @Date:2019/7/27 18:22
 */
/*init方法的使用*/
@WebServlet(name = "InitDemo")
public class InitDemo extends HttpServlet {

    /*
    *servelt生命周期的初始化方法
    *config
    * servlet的配置信息：初始化参数，或者servlet的加载机制
    *
    * */
    @Override
    public void init(ServletConfig config) throws ServletException {

        //这个方法是获取配置信息用到的
    }

    /*
    * 是servlet初始化的简便方法（用户自定义初始化的时候去使用*/
    @Override
    public void init() throws ServletException {

        //获取用户session数据---->初始化用户的登录次数/登录状态
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
