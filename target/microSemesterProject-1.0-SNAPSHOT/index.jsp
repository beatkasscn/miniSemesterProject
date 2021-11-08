<%--
  Created by IntelliJ IDEA.
  User: bestkasscn
  Date: 2021/10/25
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<%
    int i,j;
    for(i=1;i<10;i++)
    {
        for(j=1;j<=i;j++)
        {
            out.println(i+"*"+j+"="+(i*j));
            out.println("&nbsp");
        }
        out.println("<br>");
    }
    out.println("<br>");
%>
<%! int i=0;%>
<%

    if(session.isNew()){
        i=i+1;
    }
%>
访问人数为<%=i %>人。<br/>

<script language="javascript">
    var today;
    var yesterday;
    var tomorrow;
    if(new Date().getDay()==0) (today="星期日",yesterday="星期六",tomorrow="星期一")
    if(new Date().getDay()==1) (today="星期一",yesterday="星期天",tomorrow="星期二")
    if(new Date().getDay()==2) (today="星期二",yesterday="星期一",tomorrow="星期三")
    if(new Date().getDay()==3) (today="星期三",yesterday="星期二",tomorrow="星期四")
    if(new Date().getDay()==4) (today="星期四",yesterday="星期三",tomorrow="星期五")
    if(new Date().getDay()==5) (today="星期五",yesterday="星期四",tomorrow="星期六")
    if(new Date().getDay()==6) (today="星期六",yesterday="星期五",tomorrow="星期天")
    document.write("昨天是"+yesterday+",今天是"+today+",明天是"+tomorrow+"。");
</script>


</body>
</html>