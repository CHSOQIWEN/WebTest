<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: chaoqiwen
  Date: 2019/8/8
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>这是一个jsp页面</title>
</head>
<body>
<h1>欢迎来到jsp页面</h1>
<%
   String datetime= LocalDateTime.now().toString();
   out.println(datetime);
%>
<div>
<%
     StringBuilder sb=new StringBuilder();
     sb.append("java");
     sb.append(" ");
     sb.append("PHP");
     out.println(sb.toString());
%>
</div>
<div>
    <%!
        String name="Javk";
        int add(int a,int b){
            return a+b;
        }
    %>
    <p>名称:
        <%
            out.println(name);
        %>

    </p>
    <p>
        计算求和（1+2）：
        <%
            out.println(add(1,2));
        %>
    </p>

</div>

<div>

    <table>
        <thead>
        <tr>
            <td>
                编号
            </td>
            <td>
                城市
            </td>
            <td>
                景点
            </td>
        </tr>
        </thead>
        <tbody>
        <%
            Map<String, List<String>> map=new HashMap<>();
            map.put("西安",Arrays.asList("大雁塔","兵马俑"));
            map.put("宝鸡",Arrays.asList("太白山","法门寺"));
            map.put("咸阳",Arrays.asList("乾陵","茂陵"));
            int i=0;
            for(Map.Entry<String,List<String>> entry:map.entrySet()){

                String city=entry.getKey();
                List<String> sphot=entry.getValue();
                for(String spot:sphot){
                    i++;
                    %>
        <tr>
            <td>
                <%
                    out.println(i);
                %>

            </td>
            <td>
                <%
                    out.println(city);
                %>

            </td>
            <td>
                <%
                    out.println(spot);
                %>

            </td>
        </tr>
        <%

                }
            }
        %>

        </tbody>
    </table>
</div>

<%!
    String css="color: cornflowerblue";
%>

<div>
    <p>
        表达式的用法
    </p>
    <p>姓名：<%=name%>
    </p>
    <p style="<%=css%>">
        计算：20+10=<%=add(20,10)%>
    </p>
</div>


</body>
</html>
