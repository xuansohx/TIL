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
<style>
img {
	width: 100px;
	height: 100px;
}
</style>
<script>
	$(document).ready(function() {
		$('button:eq(0)').click(function() { // 첫 번째 버튼은 DELETE
			var c = confirm('삭제 하시겠습니까?');
			if (c == true) {
				location.href = 'req?type=product&cmd=delete&id=${pd.id}';
			}
		});

		$('button:eq(1)').click(function() { // 두 번째 버튼은 UPDATE
			var c = confirm('수정 하시겠습니까?');
			if (c == true) {
				location.href = 'req?type=product&cmd=update&id=${pd.id}';
			}
		});

	});
</script>

</head>
<body>
	<h1>Product Detail Page</h1>
	<table>
		<tbody>
			<tr>
				<td><img src="img/${pd.imgname }"></td>
			</tr>
			<tr>
				<td>상품번호</td>
				<td>${pd.id }</td>
			</tr>
			<tr>
				<td>상품명</td>
				<td>${pd.name }</td>
			</tr>
			<tr>
				<td>상품가격</td>
				<td>${pd.price }</td>
			</tr>
			<tr>
				<td>입고날짜</td>
				<td>${pd.regdate }</td>
			</tr>
			<tr>
				<td><button>DELETE</button></td>
				<td><button>UPDATE</button></td>
			</tr>
		</tbody>
	</table>
</body>
</html>




