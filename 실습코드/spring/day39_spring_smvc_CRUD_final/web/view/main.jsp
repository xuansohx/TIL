<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5d298aa1116853ea9a4a7084400fa795"></script>


<style>
* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
	color: black;
}

header, nav, section, footer {
	margin: 0 auto;
}

header {
	width: 900px;
	height: 150px;
	background: red;
}

nav {
	width: 900px;
	height: 30px;
	background: pink;
}

section {
	width: 900px;
	height: 600px;
	background: gray;
}

footer {
	width: 900px;
	height: 50px;
	background: black;
}

.center_page {
	margin: 0 auto;
	width: 880px;
	height: 580px;
	background: white;
	width: 880px;
}
}
</style>
</head>

<body>
	<header>
		<h3>
			<c:choose>
				<c:when test="${loginuser != null }">
					<a href="">${loginuser.id }</a>
					<a href="login.mc">LOGOUT</a>
					<a href="aboutus.mc">ABOUT US</a>
				</c:when>
				<c:otherwise>
					<a href="login.mc">LOGIN</a>
					<a href="register.mc">REGISTER</a>
					<a href="aboutus.mc">ABOUT US</a>
				</c:otherwise>
			</c:choose>
		</h3>

		<h1>
			<a href="useradd.mc">USERADD</a>
			<!-- user와 관련 된 동작은 userservlet에서 진행하라는 의미  -->
			<!-- 단순 화면만 이동할 때 'view'를 사용 -->
			<a href="userlist.mc">USERLIST</a>
			<!-- 전체조회(selectAll) -->
			<!-- cmd는 data를 요구할 때 -->
			<a href="productadd.mc">PRODUCTADD</a> 
			<a href="productlist.mc">PRODUCTLIST</a>
		</h1>
	</header>
	<nav>
		<c:choose>
			<c:when test="${navi == null }">
				<a href="main.do">HOME</a>
			</c:when>
			<c:otherwise>
	${navi }
	</c:otherwise>
		</c:choose>
	</nav>
	<section>
		<c:choose>
			<c:when test="${center == null }">
				<jsp:include page="center.jsp"></jsp:include>
				<!-- ceter에 아무것도 없으면, 가운데에 center.jsp 넣기 -->
			</c:when>
			<c:otherwise>
				<jsp:include page="${center }.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
	</section>
	<footer></footer>
</body>
</html>



