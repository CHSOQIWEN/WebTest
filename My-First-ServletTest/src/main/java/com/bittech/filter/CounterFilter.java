package com.bittech.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:chaoqiwen
 * @Date:2019/8/4 11:59
 */
public class CounterFilter implements Filter {
    private File logFile;
    //适合任务是有序的
    private ExecutorService executorService= Executors.newSingleThreadExecutor();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String filename=filterConfig.getInitParameter("logging_filename");
        String webAppRoot=filterConfig.getServletContext().getRealPath("/");
        this.logFile=new File(webAppRoot,filename);
        if(!this.logFile.exists()){
            try {
                this.logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String url=((HttpServletRequest)servletRequest).getRequestURI();
        final File propertiesFile=this.logFile;

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Properties properties=new Properties();
                try {
                    properties.load(new FileInputStream(propertiesFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String value= properties.getProperty(url);
                if(value==null){
                    value="1";
                }else {
                    value=String.valueOf(Integer.parseInt(value)+1);
                }
                properties.setProperty(url,value);

                try {
                    properties.store(new FileOutputStream(propertiesFile),"update");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
