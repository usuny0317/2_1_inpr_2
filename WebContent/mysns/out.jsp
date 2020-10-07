<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<style>
body{background-color: #FFA7A7;}
</style>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>
<h3>확인해주세요!</h3>
<h3 style="color:red;">작성하신 게시글은 자동으로 삭제됩니다.</h3>
<h2>정말 탈퇴하시겠습니까?</h2>
<h3>탈퇴하시려면 아래 내용을 채워주세요</h3>
<form name="form2" method="post" action="user_control.jsp?action=delete">
<table>
<tr><th><h3>본인 확인 용</h3></th></tr>
<tr><td>	아이디: </td><td><input type="text" name="uid" /></td></tr>
<tr><td>	이름: </td><td><input type="text" name="name" /></td></tr>
<tr><td>	비밀번호: </td><td><input type="text" name="passwd" /></td></tr>
<tr><td>	이메일: </td><td><input type="text" name="email" />
	<input type="submit" value="탈퇴하겠습니다"></td></tr>
</table>
</form>
<form>
<input type="button" value="취소" onclick="location.href='sns_main.jsp'">
</form>
</body>
</html>