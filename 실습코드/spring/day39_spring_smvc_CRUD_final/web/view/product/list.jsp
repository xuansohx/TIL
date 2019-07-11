<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.center_page > h5 > a> img {
	width:50px;
	height:50px; 
}

</style>

<div class="center_page">
	<h1>Product List Page</h1>
	<c:forEach var="p" items="${productlist}">
		<h5>
			<a href="productdetail.mc?id=${p.id }"><img src="img/${p.imgname}" > 
			${p.name } : ${p.price }won</a></h5>				
	</c:forEach>
</div>