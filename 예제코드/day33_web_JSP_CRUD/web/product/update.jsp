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
	<h1>Product Update Page</h1>
	<form action="req?type=product&cmd=updateimpl" method="POST" enctype="multipart/form-data">
	    ID:${up.id }<br>
	    <input type = "hidden" name = "id" value = "${up.id }">
		NAME<input value="${up.name }" type="text" name="name"><br>
		PRICE<input value="${up.price }" type="number" name="price"><br>
		IMG<input type="file" name="newimgname"><br>
		<input type="hidden" name="imgname" value="${up.imgname }">
		<input type="submit" value="UPDATE"><br>
	</form>
</body>
</html>




