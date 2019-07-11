<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.center_page > img {
	width:50px;
	height:50px; 
}

</style>

<div class="center_page">
<h1>Product Detail Page</h1>

<img src="img/${productdetail.imgname}">
<h5>Name : ${productdetail.name }</h5>
<h5>Price : ${productdetail.price }</h5>
<h5>Date : ${productdetail.regdate }</h5><br>

<a href="productdelete.mc?id=${productdetail.id}">DELETE</a> 
<a href="productupdate.mc?id=${productdetail.id }">UPDATE</a>
</div>