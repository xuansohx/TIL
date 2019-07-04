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

<script>
	$(document).ready(function() {
		$('button:eq(0)').click(function() { // 첫 번째 버튼은 DELETE
			var c = confirm('삭제 하시겠습니까?');
			if (c == true) {
				location.href = 'req?type=user&cmd=delete&id=${ud.id}';
			}
		});

		$('button:eq(1)').click(function() { // 두 번째 버튼은 UPDATE
			var c = confirm('수정 하시겠습니까?');
			/* 사용자 수정화면을 만듦 → 
			id값 이용하여 데이터베이스에서 정보 가져온 후, 
			request에 담아 수정화면으로 이동 */
			if (c == true) {
				location.href = 'req?type=user&cmd=update&id=${ud.id}';
			}
		});
	});
</script>

</head>
<body>
	<h1>User Update Page</h1>
	<form action="req?type=user&cmd=updateimpl" method="POST">
		ID<input readonly value="${uu.id }" type="text" name="id"><br>
		PWD<input value="${uu.pwd }" type="text" name="pwd"><br>
		NAME<input value="${uu.name }" type="text" name="name"><br>
		<input type="submit" value="UPDATE"><br>
	</form>
</body>
</html>




