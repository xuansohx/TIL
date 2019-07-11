<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="center_page">
		<!-- 이거 설정해주어야 페이지 바뀌어도 center 유지 -->
		<h1>Login Page</h1>
		<form action="loginimpl.mc" method="POST">
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<td>PWD</td>
					<td><input type="password" name="pwd"></td>
				</tr>
			</table>
			<input type="submit" value="LOGIN"><br>
		</form>
	</div>
</body>
</html>