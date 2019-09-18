<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
/* 지도 상 현재 위치의 위도와 경도 정보 받음 */
/* 서버와 데이터를 주고 받을 때, 데이터 타입은 'String' 
∴ 숫자 데이터 사용하려면 데이터 타입 변환이 필요*/
double lat = Double.parseDouble(request.getParameter("lat"));
double lon = Double.parseDouble(request.getParameter("lon"));
%>

[
{"lat":<%=lat+0.01%>,"lon":<%=lon+0.01%>},
{"lat":<%=lat+0.02%>,"lon":<%=lon%>},
{"lat":<%=lat+0.01%>,"lon":<%=lon-0.01%>},
{"lat":<%=lat-0.01%>,"lon":<%=lon+0.01%>},
{"lat":<%=lat-0.009%>,"lon":<%=lon+0.03%>},
{"lat":<%=lat-0.001%>,"lon":<%=lon-0.01%>},
{"lat":<%=lat-0.01%>,"lon":<%=lon%>}
]