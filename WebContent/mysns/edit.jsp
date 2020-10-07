<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mysns.sns.*, mysns.member.*, java.util.*"%>
 <% request.setCharacterEncoding("utf-8"); %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="msg" class="mysns.sns.Message" />
<!DOCTYPE html>
<html>
<head>

<style>
	body {background-color:#FFF2E6;}
</style>
<meta charset="UTF-8">
<title>수정할 내용 작성</title>

</head>
<body>
<h2>수정할 내용을 입력하시오</h2>
<form action="sns_control.jsp?action=edit&mid=${mid}"> 
<input type=text size=50 id="child" /><input type=submit value="수정합니다" >

</form>
</body>
</html>