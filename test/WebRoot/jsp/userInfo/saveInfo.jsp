<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'saveInfo.jsp' starting page</title>
    
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
  		<form action="<%=path%>/userMrg" method="post">
   			<input type="hidden" name="falg" value="save">
   			<table border="1" align="center">
   				<tr>
   					<th>账户名</th>
   					<td>
   						<input type="text" id="userName" name="userName"/>
   					</td>
   				</tr>
   				<tr>
   					<th>密码</th>
   					<td>
   						<input type="text" id="userPass" name="userPass"/>
   					</td>
   				</tr>
   				<tr>
   					<th>年龄</th>
   					<td>
	   					<input type="text" id="age" name="age"/>
   					</td>
   				</tr>
   				<tr>
   					<th>性别</th>
   					<td>
   						<input type="text" id="sex" name="sex"/>
   					</td>
   				</tr>
   				<tr >
   					<th colspan="2" align="center">
   						<input type="reset" value="重置"/>
   						<input type="submit" value="保存"/>
   					</th>
   				</tr>
   			</table>
   		</form>
  </body>
</html>
