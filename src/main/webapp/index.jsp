<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="<%=basePath %>">
<title>路段生成工具</title>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
	button{
		margin:5px;
		padding:5px;
	}
	.divid{
		border-bottom:2px solid blue;
		margin:5px;
	}
	h3{
		margin:5px;
	}
	h5{
		font-size:18px;
		margin:5px;
	}
	span{
		margin:5px;
	}
	.input{
		width:150px;
	}
</style>
</head>
<body>
	<div style="height:638px;width:30%;overflow:scroll;">
	
			<div class="divid"></div>
			<h3>路段与路线生成</h3>
			<button type="button" class="btn btn-default btn-info" id="create" >路线路段生成</button>
			<button type="button" class="btn btn-default btn-info" id="clear" >清理重复路段</button>
			<button type="button" class="btn btn-default btn-info" id="clearRoute" >清理重复路线</button>
			
			<div class="divid"></div>
			<h3>点集保存</h3>
			<button type="button" class="btn btn-default btn-info" id="updateLinePoint" >路段点集保存</button><br>
			<button type="button" class="btn btn-default btn-info" >道路点集保存</button><!-- id="roadLinePoints"  -->
			<button type="button" class="btn btn-default btn-info" id="openCreateRoad" >开始拾点</button>
			<button type="button" class="btn btn-default btn-info" id="addPassPoint" >增加途经点</button><br>
			<span>道路编码</span><input class="input" id="ROAD_CODE" onchange="changeName(this.value)"><span>例如:G104</span><br>
			<span>道路名称</span><input class="input" id="ROAD_NAME"><span>例如:104国道</span><br>
			<span>道路起点</span><input class="input" id="ROAD_START"><span id="span_start">点击开始拾点</span><br>
			<span>道路终点</span><input class="input" id="ROAD_END"><span id="span_end">点击结束拾点</span><br>
			<span>道路类型</span><input class="input" id="ROAD_TYPE" value="2"><span>2国省道，4高速</span><br>
			<span>道路描述</span><input class="input" id="ROAD_DESCRIPTION"><span>例如:浙江省104国道</span><br>
			<span>道路策略</span><input class="input" id="ROAD_POLICY" value="2"><span style="font-size:10px">0最短时间1最短距离2不走高速</span><br>
			<span>途径点集</span><input class="input" id="ROAD_PASS"><span id="span_append" style="font-size:10px">点击增加途经点</span><br>
			<button  type="button" class="btn btn-default btn-info" id="roadTryToDraw" >绘制</button>
			<button  type="button" class="btn btn-default btn-info" id="raodSsave" >保存</button>
			<button  type="button" class="btn btn-default btn-info" id="raodUpdate" >更新</button>
			
			<div class="divid"></div>
			<h3>线路绘制</h3>
			<button type="button" class="btn btn-default btn-info" id="drawMyLine" >全部道路</button>
			<button type="button" class="btn btn-default btn-info" id="drawAllSetion" >全部路段</button>
			<button type="button" class="btn btn-default btn-info" id="showRoadSetionCompare" >与道路匹配的路段</button>
			<button type="button" class="btn btn-default btn-info" id="clearMap" >清</button>
			
			<div class="divid"></div>
			<h3>匹配计算</h3>
			<button type="button" class="btn btn-default btn-info" id="luduanguishu" >路段归属市</button>
			<button type="button" class="btn btn-default btn-info" id="matchRoad" >路段归属道路</button>
			
			<div class="divid"></div>
			<h3>GPS</h3>
			<button type="button" class="btn btn-default btn-info" id="gpsUpdataBdPoint" >更新为百度点</button><br>
			<button type="button" class="btn btn-default btn-info" id="gpsDrawByVehPlate" >画出某车的线路</button><br>
			
			<div class="divid"></div>
			<h3>其他</h3>
			<button type="button" class="btn btn-default btn-info" id="updateSiteToRoad" >更新站点所在道路</button><br>
			<button type="button" class="btn btn-default btn-info" id="drawSetion" >画路段</button><br>
			<button type="button" class="btn btn-default btn-info" id="drawSetion2" >画路段2</button><br>
			<button type="button" class="btn btn-default btn-info" id="changeColor" >换颜色</button><br>
			<button type="button" class="btn btn-default btn-info" id="showSite" >显示站点</button><br>
			<button type="button" class="btn btn-default btn-info" id="testMate" >路段匹配路线</button><br>
			<button type="button" class="btn btn-default btn-info" id="drawMate" >画--已匹配路段</button><br>
			<button type="button" class="btn btn-default btn-info" id="drawAllRoute" >画--全部线路</button><br>
			<button type="button" class="btn btn-default btn-info" id="updateSection" >更新路段属性</button><br>
			<button type="button" class="btn btn-default btn-info" id="updateRoute" >更新站点线路属性</button><br>
			<button type="button" class="btn btn-default btn-info" id="drawTwenty" >20站点归属</button><br>
			<button type="button" class="btn btn-default btn-info" id="updateRailwayLinePoint" >54路线路段更新点集</button><br>
			<button type="button" class="btn btn-default btn-info" id="openClickNear" >启用点击寻最近路段</button><br>
			<button type="button" class="btn btn-default btn-info" id="liangtiaolu" >两条路</button><br>
			<button type="button" class="btn btn-default btn-info" id="quyuluduan" >区域路段</button><br>
			<button type="button" class="btn btn-default btn-info" id="guzhangzhandian" >207or227</button><br>
			<button type="button" class="btn btn-default btn-info" id="createScreenData" >造大屏数据</button><br>
	</div>
	
	<div style="z-index:0;position:absolute;top:0px;left:30%;right:0px;height:638px;text-align: center;">
		<div id="mapall" style="width: 100%;height:100%;z-index:0;"></div>
	</div>
				
	<script type="text/javascript" src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
	<script type="text/javascript">	
    var map = new BMap.Map("mapall"); 
    map.enableScrollWheelZoom(true); //启用滚轮放大缩小
    var point = new BMap.Point(120.252178,30.269275);
	map.centerAndZoom(point,8);
	
	function changeName(name){
		$("#ROAD_NAME").val(name);
		$("#ROAD_DESCRIPTION").val("浙江省义乌市"+name);
	}
</script>
	<script src="js/gis/CheLiangFenXi.js"></script>

</body>
</html>
