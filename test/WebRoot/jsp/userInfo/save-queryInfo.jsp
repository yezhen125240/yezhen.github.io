<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	import="com.yz.entity.UserInfo"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'save-queryInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<form action="">
   			<table border="1" align="center">
   				<%
   					Object obj = request.getAttribute("userList");
   					if(obj != null)
   					{
	   					List<UserInfo> userList = (List<UserInfo>)obj;
	   					for(UserInfo userInfo:userList)
	   					{
	   						%>
	   						<tr>
	   							<th>账户名</th>
	   							<td><%=userInfo.getUserName()%></td>
	   						</tr>
	   						<tr>
	   							<th>密码</th>
	   							<td><%=userInfo.getUserPass()%></td>
	   						</tr>
	   						<tr>
	   							<th>年龄</th>
	   							<td><%=userInfo.getAge()%></td>
	   						</tr>
	   						<tr>
	   							<th>性别</th>
	   							<td><%=userInfo.getSex()%></td>
	   						</tr>
	   						<tr>
	   							<th colspan="2" align="center">
									<a href="jsp/main.jsp">${msg}</a>
								</th>
	   						</tr>
	   						<%
	   					}
	   				}
   				%>
   			</table>
   		</form>
  </body>
</html>
