
<%@ tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form name="form2" method="post">
<c:choose>
<c:when test="${uid != null}">
     	<li><a href="#"> :: </a></li>
     	<li><a href="user_control.jsp?action=time&uid=${uid}">가입한 시간 나타내기</a></li>
</c:when>
<c:otherwise>
		<li><a href="#"> :: </a></li>
        <li><a href="#">로그인 시에만 이용할 수 있습니다!</a></li>
</c:otherwise>
</c:choose>
</form>