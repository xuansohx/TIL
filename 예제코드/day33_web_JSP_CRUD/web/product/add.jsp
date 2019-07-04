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
	<h1>Product Add Page</h1>
	<form action="req?type=product&cmd=addimpl" method="POST" enctype="multipart/form-data">
        <!-- GET방식은 파일전송이 안 되므로 반드시 POST방식을 사용해야 됨 -->	
		<!-- ID<input type="text" name="id"> -->
		<!-- 우리가 입력 안 하고 시퀀스로 자동 -->
		NAME<input type="text" name="name"><br>
		PRICE<input type="number" name="price"><br>
		IMG<input type="file" name="imgname"><br>
		<input type="submit" value="REGISTER">
	</form>
</body>
</html>



