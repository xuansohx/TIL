<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="center_page">
	<h1>Product Update Page</h1>

	<form action="productupdateimpl.mc" method="POST"
		enctype="multipart/form-data">

		<table>
			<tr>
				<td>NAME</td>
				<td><input type="hidden" name="id" value="${productupdate.id }">
					<input type="text" name="name" value="${productupdate.name }"></td>
			</tr>

			<tr>
				<td>PRICE</td>
				<td><input type="number" name="price"
					value="${productupdate.price }"></td>
			</tr>

			<tr>
				<td>IMG</td>
				<td><input type="hidden" name="imgname" value="${productupdate.imgname }"> 
				<input type="file" name="mf"></td>
			</tr>
		</table>

		<input type="submit" value="UPDATE"><br>

	</form>

</div>