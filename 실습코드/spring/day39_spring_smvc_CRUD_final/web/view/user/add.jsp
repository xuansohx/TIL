<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="center_page">
<h1>User Add Page</h1>
<form action="useraddimpl.mc" method="POST">

			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="id"></td>
				</tr>
				
				<tr>
					<td>PWD</td>
					<td><input type="password" name="pwd"></td>
				</tr>
				
				<tr>
				<td>NAME</td>
				<td><input type="text" name="name"></td>
				</tr>

		</table>
			<input type="submit" value="REGISTER"><br>
		</form>
</div>