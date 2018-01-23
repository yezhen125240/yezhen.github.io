<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <script type="text/javascript">
  	function _onS()
  	{
  		var userName = document.getElementById("userName").value;
  		var userPass = document.getElementById("userPass").value;
  		
  		if(userName == "" |userName == null)
  		{
  			alert("账户不允许为空");
  			return false;
  		}
  		if(userPass == "" |userPass == null)
  		{
  			alert("密码不允许为空");
  			return false;
  		}
  		
  		return true;
  	}
  </script>
  
  <body>
    	<form action="<%=path%>/userMrg" method="post" onsubmit="return _onS()">
    		${msg}
    		<input type="hidden" name="falg" value="login"/>
    		<table border="1" align="center">
    			<tr>
    				<th>账户</th>
    				<td>
    					<input type="text" id="userName" name="userName">
    				</td>
    			</tr>
    			<tr>
    				<th>密码</th>
    				<td>
    					<input type="password" id="userPass" name="userPass">
    				</td>
    			</tr>
    			<tr>
    				<th colspan="2" align="center">
	    				<input type="reset" value="重置"/>
    					<input type="submit" value="登录"/>
    				</th>
    			</tr>
    		</table>
    	</form>
  </body>
</html>
