package com.bittech.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * @Author:chaoqiwen
 * @Date:2019/8/3 21:30
 */
public class LogginFilter implements Filter {
    //1、指定初始化参数   记录的前缀   记录的文件名
    //2、每个请求的URL记录到文件中
    //3、web.xml配置（Filter，拦截的地址，初始化参数）


    private String prefix="";
    private String loggingFile;
    private PrintWriter writer;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       this.prefix= filterConfig.getInitParameter("logging_prefix");
       this.loggingFile=filterConfig.getInitParameter("logging_filename");
        try {
            //获取web程序的根目录
            String webAppRoot=filterConfig.getServletContext().getRealPath("/");
            this.writer=new PrintWriter(new File(webAppRoot,this.loggingFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Loggin Filter init called");


    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("Loggin Filter doFilter called");

       String url= ((HttpServletRequest)servletRequest).getRequestURI();

        this.writer.println(this.prefix+" "+ LocalDateTime.now()+" "+url);

        this.writer.flush();

        //request->filter(filterChain.doFilter)->Servlet

        filterChain.doFilter(servletRequest,servletResponse);

    }


    @Override
    public void destroy() {
        System.out.println("Loggin Filter destroy called");


    }
}
