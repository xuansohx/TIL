<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<style>
#container {
	min-width: 320px;
	max-width: 600px;
	margin: 0 auto;
}
</style>
<div id="container"></div>
<button id="plain">Plain</button>
<button id="inverted">Inverted</button>
<button id="polar">Polar</button>
<script>
function chart1(pdata){
	var chart = Highcharts.chart('container', {
	    title: {
	        text: '지역별 범죄 분류'
	    },
	    subtitle: {
	        text: '서울'
	    },
	    xAxis: {
	        categories: ['강력범죄', '교통범죄', '기타', '노동범죄', '마약범죄', '병영범죄', '보건범죄', '선거범죄', '안전범죄','절도범죄' , '지능범죄', '경제범죄','폭력범죄', '풍속범죄', '환경범죄']
	    },
	    series: [{
	        type: 'column',
	        colorByPoint: true,
	        data: pdata,
	        showInLegend: false
	    }]
	});
	$('#plain').click(function () {
	    chart.update({
	        chart: {
	            inverted: false,
	            polar: false
	        },
	        subtitle: {
	            text: '서울'
	        }
	    });
	});
	$('#inverted').click(function () {
	    chart.update({
	        chart: {
	            inverted: true,
	            polar: false
	        },
	        subtitle: {
	            text: '서울'
	        }
	    });
	});
	$('#polar').click(function () {
	    chart.update({
	        chart: {
	            inverted: false,
	            polar: true
	        },
	        subtitle: {
	            text: '서울'
	        }
	    });
	});
}
$(document).ready(function(){
	$.ajax({
		url:"schart.mc",
		success:function(pdata){
			chart1(pdata);			
		}
	});
}); 
</script>