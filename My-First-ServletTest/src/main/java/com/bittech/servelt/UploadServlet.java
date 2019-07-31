package com.bittech.servelt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author:chaoqiwen
 * @Date:2019/7/31 17:30
 */
//@WebServlet(urlPatterns = {"/uploadServlet"})
public class UploadServlet extends HttpServlet {
    //String uploadpath="D:\\upload";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //file =>二进制流
        Part part=req.getPart("file");



        String uploadpath=req.getServletContext().getRealPath("/upload");


        File uploadDirectory=new File(uploadpath);
        if(!uploadDirectory.exists()){
            uploadDirectory.mkdirs();
        }
        String fileName=part.getSubmittedFileName();
        File uploadFile =new File(uploadDirectory,fileName);
        //获取文件的输入流
        try(InputStream in=part.getInputStream();
            FileOutputStream out=new FileOutputStream(uploadFile)){
            byte[] buff=new byte[1024];
            int len=-1;
            while ((len=in.read(buff))!=-1){
                out.write(buff,0,len);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        //在网页中展示图片
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        StringBuilder sb=new StringBuilder();
        sb.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>展示图片</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>显示上传的图片</h1>\n" +
                "<img src=\"");
        sb.append("upload/").append(fileName);
        sb.append("\" alt=\"无法显示\">\n" +
                "\n" +
                "</body>\n" +
                "</html>");

        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();




    }
}
