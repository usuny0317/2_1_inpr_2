<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ attribute name="mid" %>
<%@ attribute name="auid" %>
<%@ attribute name="curmsg" %>

<c:if test="${uid == auid or uid.equals('admin') }"> 
[<a href="sns_control.jsp?action=delmsg&mid=${mid}&curmsg=${curmsg}&cnt=${cnt}&suid=${suid}">삭제</a>] 
<!-- 기능추가 4 -->
[<a href="edit.jsp?action=edit&mid=${mid}&curmsg=${curmsg}&cnt=${cnt}&suid=${suid}">수정</a>]
</c:if>
[<a href="sns_control.jsp?action=fav&mid=${mid}&curmsg=${curmsg}&cnt=${cnt}&suid=${suid}">좋아요</a>]