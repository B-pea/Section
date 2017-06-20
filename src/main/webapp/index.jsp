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
<!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->
</head>
<body>
	<div class="container" style="z-index:401;">
		<div class="row clearfix" style="z-index:401;"> 
			<div class="col-md-12 column" style="z-index:401;">
				<!-- <h3 class="text-center" style="margin-bottom:40px;margin-top:30px;z-index:401;">路段划分</h3> -->
				<!-- <a href="gaoDe.jsp">点我</a> -->
				<div class="col-md-8 column" style="z-index:401;">
					<button id="create" >生成</button>
					<button id="clear" >清理重复路段</button>
					<button id="clearRoute" >清理重复路线</button>
					<button id="drawSetion" >画路段</button>
					<button id="drawSetion2" >画路段2</button>
					<button id="changeColor" >换颜色</button>
					<button id="showSite" >显示站点</button>
					<button id="testMate" >路段匹配路线</button>
					<button id="drawMate" >画--已匹配路段</button>
					<button id="drawAllSetion" >画--全部路段</button>
					<button id="drawAllRoute" >画--全部线路</button>
					<button id="updateSection" >更新路段属性</button>
					<button id="updateRoute" >更新站点线路属性</button>
					<button id="drawTwenty" >20站点归属</button>
					<button id="updateLinePoint" >路段更新点集</button>
					<button id="updateRailwayLinePoint" >54路线路段更新点集</button>
					<button id="openClickNear" >启用点击寻最近路段</button>
					<button id="liangtiaolu" >两条路</button>
					<button id="quyuluduan" >区域路段</button>
					<button id="luduanguishu" >路段归属市</button>
					<button id="guzhangzhandian" >207or227</button>
					<button id="roadLinePoints" >道路点集</button>
					<button id="drawMyLine" >道路点集-画</button>
					<button id="createScreenData" >造大屏数据</button>
					<button id="matchRoad" >路段匹配道路</button>
					<button id="showRoadSetionCompare" >路段匹配道路-画</button>
					
					
				</div>
				
				<div class="col-md-4 column">
					<h3>输入:</h3>
					<p class="text-info ">站点位置(pub_sit_info)</p>
					<h3>输出:</h3>
					<p class="text-info ">站点与站点的路线信息(pub_road_route)；</p>
					<p class="text-info ">路段信息(pub_road_setion)；</p>
					<h3>操作:</h3>
					<p class="text-info "><em>1、</em>生成</p>
					<p class="text-info "><em>2、</em>清理重复路段</p>
					<p class="text-info "><em>3、</em>清理重复路线</p>
					<h3>注意:</h3>
					<p class="text-info ">
						<em>1、</em> 去重（相同路段，相同路线，首尾点间距小于10米的路段）
					</p>
					<p class="text-info ">
						<em>2、</em> 路线策略（不走高速、常规路线、距离较短、躲避拥堵）
					</p>
					<p class="text-info ">
						<em>3、</em> 去重路段或路线有延迟
					</p>
				</div>
				<div id="divv">
					<p id="showResult"></p>
				</div>
			</div>
		</div>
	</div>
	
	<div style="z-index:0;position:absolute;top:60px;left:0px;right:0px;height:574px;text-align: center;">
		<div id="mapall" style="width: 100%;height:100%;z-index:0;"></div>
	</div>
				
	<script type="text/javascript" src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
	<script type="text/javascript">	
    var map = new BMap.Map("mapall"); 
    map.enableScrollWheelZoom(true); //启用滚轮放大缩小
    var point = new BMap.Point(120.252178,30.269275);
	map.centerAndZoom(point,8);
</script>
	<script src="js/gis/CheLiangFenXi.js"></script>

</body>
</html>
