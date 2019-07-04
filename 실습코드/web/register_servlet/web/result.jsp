<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<h1>Register Completed!</h1>
	<h3>${name }님환영합니다:)</h3>
	<br>

	<h4>등록한 정보를 확인해주세요</h4>
	<table border="1">
		<tbody>
			<tr>
				<td>UserType</td>
				<td>${type }</td>
			</tr>
			<tr>
				<td>Academic</td>
				<td>${academic }</td>
			</tr>


			<tr>
				<td>Language</td>
					<td><c:forEach var="it" items="${language }">${it } </c:forEach></td>
			</tr>


		</tbody>
	</table>
</body>
</html>