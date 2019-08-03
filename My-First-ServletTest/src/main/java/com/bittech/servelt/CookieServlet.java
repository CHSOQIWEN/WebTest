package com.bittech.servelt;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:chaoqiwen
 * @Date:2019/7/31 20:50
 */
public class CookieServlet extends HttpServlet {


    private Map<String, List<String>> cityMap = new HashMap<>();
    private Map<String, List<String>> countryMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        List<String> shannxi = new ArrayList<>();
        shannxi.add("西安市");
        shannxi.add("宝鸡市");
        shannxi.add("铜川市");
        shannxi.add("咸阳市");
        cityMap.put("陕西省", shannxi);
        List<String> xian = new ArrayList<>();
        xian.add("临潼区");
        xian.add("灞桥区");
        xian.add("长安区");
        countryMap.put("西安市", xian);

        //地址：/CookieServlet



    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String pro=null;
        String city=null;
        /*获取Cookie*/
        Cookie[] cookies=req.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("pro")){
                    pro=cookie.getValue();
                    continue;
                }
                if(cookie.getName().equals("city")){
                    city=cookie.getValue();
                    continue;
                }
            }

        }



        if(pro==null&&city==null){
            Cookie procookie=new Cookie("pro","陕西省");
            resp.addCookie(procookie);
            writer.println("省市为空，没数据");
        }else {
            if(pro==null){
                writer.println("省为空");
            }else {
                if(city==null){
                    //city提供默认的城市
                    Cookie cookie=new Cookie("city","西安市");
                    resp.addCookie(cookie);
                    //省
                    List<String> citys=cityMap.get(pro);
                    StringBuilder sb=new StringBuilder();
                    if(citys==null){
                        sb.append("未查询到"+pro);
                    }else {
                        for(String c:citys){
                            sb.append("<a href='CookieServlet").append(pro).append("&")
                                    .append("city=").append(c).append("'>")
                                    .append(pro)
                                    .append(" ")
                                    .append(c)
                                    .append("</a>")
                                    .append("<br/>");
                        }
                    }
                    writer.write(sb.toString());
                }else {
                    // 省 市
                    List<String> countrys=countryMap.get(city);
                    StringBuilder sb=new StringBuilder();
                    if(countrys==null){
                        sb.append("未查询到"+pro+" "+city);
                    }else {
                        for (String c : countrys) {
                            sb.append("<a href='CookieServlet").append(pro).append("&")
                                    .append("city=").append(city).append("'>")
                                    .append(pro)
                                    .append(" ")
                                    .append(city)
                                    .append(" ")
                                    .append(c)
                                    .append("</a>")
                                    .append("<br/>");
                        }
                    }
                        writer.write(sb.toString());

                }
            }
        }
        writer.flush();

    }
}
