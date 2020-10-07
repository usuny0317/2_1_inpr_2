<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
body{ background-color:#FFEAEA;}
</style>
</head>
<body>
<form name="form1" method="post" action="user_control.jsp?action=new">
<table>
<tr><th><h3>회원가입</h3></th></tr>
<tr><td>	아이디: </td><td><input type="text" name="uid" /></td></tr>
<tr><td>	이름: </td><td><input type="text" name="name" /></td></tr>
<tr><td>	비밀번호: </td><td><input type="text" name="passwd" /></td></tr>
<tr><td>	이메일: </td><td><input type="email" name="email" />
	<input type="submit" value="회원등록"></td></tr>
</table>
</form>

</body>
</html>