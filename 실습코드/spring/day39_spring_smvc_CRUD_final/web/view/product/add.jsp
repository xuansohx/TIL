<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="center_page">
	<h1>Product Add Page</h1>
	<form enctype="multipart/form-data" action="productaddimpl.mc"
		method="POST">

		<table>
			<tr>
				<td>NAME</td>
				<td><input type="text" name="name"></td>
			</tr>

			<tr>
				<td>PRICE</td>
				<td><input type="number" name="price"></td>
			</tr>

			<tr>
				<td>IMG</td>
				<td><input type="file" name="mf"></td> <!-- product에 들어감 -->
			</tr>
		</table>
		<input type="submit" value="REGISTER"><br>
	</form>
</div>