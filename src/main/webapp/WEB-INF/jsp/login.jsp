<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/demo/login" method="POST">  
    姓名：<input type="text" name="username"/><br/>  
    密码：<input type="text" name="password"/><br/>  
    <input type="submit" value="确认"/>
    记住我<input type="checkbox" name="rememberMe">  
</form> 

<a href="<%=request.getContextPath()%>/logout">退出</a>
</body>
</html>