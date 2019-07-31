package com.bittech.servelt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:chaoqiwen
 * @Date:2019/7/31 16:08
 */

/*景点查询
    接口：/QueryServlet?city=<参数>
    分析：
    1、如果city参数没有传递，返回所有景点
    2、如果city参数没有传递，返回该城市对应景点

    效果：table

    编号      所在城市       景点名称
    1           西安           大雁塔
    2           宝鸡           太白山

    * */
@WebServlet(urlPatterns = {"/queryServlet"})
public class QueryServlet extends HttpServlet {
    //key-value
    //xian->西安
    private Map<String, String> cityMap = new HashMap<String, String>();
    //key-value[list]
    //xian->[大雁塔，钟楼]
    private Map<String, List<String>> scenicSpot = new HashMap<String, List<String>>();

    @Override
    public void init() throws ServletException {
        List<String> xian = new ArrayList<String>();
        xian.add("华清池");
        xian.add("兵马俑");
        xian.add("大雁塔");
        scenicSpot.put("xian", xian);
        cityMap.put("xian", "西安");

        List<String> baoJi = new ArrayList<String>();
        baoJi.add("太白山");
        baoJi.add("法门寺");
        baoJi.add("关山牧场");
        scenicSpot.put("baoJi", baoJi);
        cityMap.put("baoJi", "宝鸡");

        List<String> xianyang = new ArrayList<String>();
        xianyang.add("乾陵");
        xianyang.add("袁家村");
        scenicSpot.put("xianyang", xianyang);
        cityMap.put("xianyang", "咸阳");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String city=req.getParameter("city");
        List<ScenicSpotDto>  list=new ArrayList<ScenicSpotDto>();

        if(city==null||city.isEmpty()){
            for(Map.Entry<String,List<String>> entry:scenicSpot
            .entrySet()){
                String cityPinyin=entry.getKey();
                List<String> spots=entry.getValue();
                String cityName=cityMap.get(cityPinyin);
/*
               list.addAll( spots
                                  .stream()
                                  .map(s->new ScenicSpotDto(cityName,s))
                                  .collect(Collectors.toList())
               );*/

             for(String spot:spots){
                 ScenicSpotDto dto=new ScenicSpotDto(cityName,spot);
                 list.add(dto);
             }

            }

        }else {
           List<String> spots= scenicSpot.get(city);
           if(spots!=null){
               String cityName=cityMap.get(city);
               for(String spot:spots){
                   ScenicSpotDto dto=new ScenicSpotDto(cityName,spot);
                   list.add(dto);
               }

           }

        }

        StringBuilder sb=new StringBuilder();
        sb.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>城市景点</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>欢迎来到陕西</h1>\n" +
                "<table id=\"spot\">\n" +
                "    <thead>\n" +
                "        <tr>\n" +
                "            <td>编号</td>\n" +
                "            <td>城市</td>\n" +
                "            <td>景点</td>\n" +
                "        </tr>\n" +
                "\n" +
                "    </thead>");
        sb.append("<tbody>");
        int id=0;
        for(ScenicSpotDto dto:list){
            id++;
            sb.append("<tr>");
            sb.append("<td>").append(id).append("</td>");
            sb.append("<td>").append(dto.getCity()).append("</td>");
            sb.append("<td>").append(dto.getSpot()).append("</td>");
            sb.append("</tr>");
        }


        sb.append("</tbody>");

        sb.append("<tfoot>\n" +
                "        哈哈哈\n" +
                "    </tfoot>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.getWriter().println(sb.toString());
        resp.getWriter().flush();


    }



    //DTO：数据传输对象：把公共的需要的传输的对象放到一个类
    public static  class ScenicSpotDto{
        //城市
        private String city;
        //景点
        private String spot;

        public ScenicSpotDto(String city, String spot) {
            this.city = city;
            this.spot = spot;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSpot() {
            return spot;
        }

        public void setSpot(String spot) {
            this.spot = spot;
        }

        @Override
        public String toString() {
            return "ScenicSpotDto{" +
                    "city='" + city + '\'' +
                    ", spot='" + spot + '\'' +
                    '}';
        }
    }
}
