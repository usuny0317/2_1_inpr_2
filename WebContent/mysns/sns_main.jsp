<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="sns"%>

<%--기능 추가 1 위함 --%>
<jsp:useBean id="msgdao" class="mysns.sns.MessageDAO" />
<% 
	String s= msgdao.find();
%>
<%--기능 추가 1 위함 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>20190967 유선영</title>
<style type='text/css'>
@import url("style.css");
</style>
<link rel="stylesheet" href="style.css">
<script>
    $(function() {
        $("#accordion").accordion({
            heightStyle : "content",
            active : parseInt("${curmsg == null ? 0:curmsg}")
        });
    });

    function newuser() {
        window
                .open(
                        "new_user.jsp",
                        "newuser",
                        "titlebar=no,location=no,scrollbars=no,resizeable=no,menubar=no,toolbar=no,width=300,height=240");
    }
</script>
</head>
<body>
<nav>
        <div class="menu">
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="javascript:newuser()">New User</a></li>
                <li><a href="sns_control.jsp?action=getall">전체글보기</a>
                <li><sns:login /></li>
            </ul>
        </div>
    </nav>
    <div id="wrapper">
		<section id="main">
			<section id="content">
				<b>내소식 업데이트</b>
				<form class="m_form" method="post"
					action="sns_control.jsp?action=newmsg">
					<input type="hidden" name="uid" value="${uid}">
					<sns:write type="msg" />
					<button class="submit" type="submit">등록</button>
				</form>
				<br> <br>
				<h3>친구들의 최신 소식</h3>
				<div id="accordion">
					<c:forEach varStatus="mcnt" var="msgs" items="${datas}">
						<c:set var="m" value="${msgs.message}" />
						<h3>[${m.uid}]${m.msg} :: [좋아요 ${m.favcount} | 댓글
							${m.replycount}]</h3>
						<div>
							<p></p>
							<p>
								<sns:smenu mid="${m.mid}" auid="${m.uid}" curmsg="${mcnt.index}" />
								/ ${m.date}에 작성된 글입니다.
							</p>

							<ul class="reply">
								<c:forEach var="r" items="${msgs.rlist}">
									<li>${r.uid }:: ${r.rmsg} - ${r.date} <sns:rmenu
											curmsg="${mcnt.index}" rid="${r.rid}" ruid="${r.uid}" /></li>
								</c:forEach>
							</ul>
							
							<form action="sns_control.jsp?action=newreply&cnt=${cnt}"
								method="post">
								<input type="hidden" name="mid" value="${m.mid}"> <input
									type="hidden" name="uid" value="${uid}"> <input
									type="hidden" name="suid" value="${suid}"> <input
									type="hidden" name="curmsg" value="${mcnt.index}">
								<sns:write type="rmsg" />
							</form>
						</div>
						<hr />
					</c:forEach>
				</div>

				<div align="center">
					<a href="sns_control.jsp?action=getall&cnt=${cnt+5}&suid=${suid}">더보기&gt;&gt;</a>
				</div>

			</section>
			<aside id="sidebar2">
				<!-- sidebar2 -->
				<h2>새로운 친구들.!!</h2>
				<c:forEach items="${nusers}" var="n">
					<ul>
						<li><a href="sns_control.jsp?action=getall&suid=${n}">${n}</a></li>
					</ul>
				</c:forEach>

				<br>
				<h3>주목할 만한 사용자(기능 추가1)</h3>
					<ul>
						<li><a href="sns_control.jsp?action=getall&suid=<%= s%> "><%  out.println(s); %></a></li>
					</ul>
					<br> 
				<h3>Links</h3>
				<ul>
					<li><a href="#">한빛미디어</a></li>
					<li><a href="#">덕성여자대학교</a></li>
					<li><a href="#">덕성여자대학교 부속 유치원</a></li>
				</ul>

			</aside>
			<!-- end of sidebar -->
		</section>
	</div>
	<footer>
		<div class="container1">
			<section id="footer-area">

			<section id="footer-outer-block">
					<aside class="footer-segment">
							<ul>기능추가2</ul>
							<ul><sns:out /></ul>
							<ul>기능추가3</ul>
							<ul><sns:time /></ul>
					</aside><!-- end of #first footer segment -->
					</section></section></div></footer>

</body>
</html>