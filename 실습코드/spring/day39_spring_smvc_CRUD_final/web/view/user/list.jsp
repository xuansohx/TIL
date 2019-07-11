<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="center_page">
<h1>User List Page</h1><br>
<c:forEach var="u" items="${userlist}">
	<h5><a href="userdetail.mc?id=${u.id}">아이디 : ${u.id} 이름 : ${u.name }</a></h5>
</c:forEach> 
<!-- controller에 ? 이후 아이디 '전송'
여기서 전송을 해주어야 Controller에서 받아 화면에 표시할 수 있음 -->
</div>