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
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column" >
				<h3 class="text-center" style="margin-bottom:40px;margin-top:30px">路段划分</h3>
				<a href="gaoDe.jsp">点我</a>
				<div class="col-md-8 column">
					<button id="create" type="button" class="btn btn-default btn-info">生成</button>
					<button id="clear" type="button" class="btn btn-default btn-info">清理重复路段</button>
					<button id="clearRoute" type="button" class="btn btn-default btn-info">清理重复路线</button>
					<button id="drawSetion" type="button" class="btn btn-default btn-info">画路段</button>
					<button id="drawSetion2" type="button" class="btn btn-default btn-info">画路段2</button>
					<button id="changeColor" type="button" class="btn btn-default btn-info">换颜色</button>
					<button id="showSite" type="button" class="btn btn-default btn-info">显示站点</button>
					<button id="testMate" type="button" class="btn btn-default btn-info">路段匹配路线</button>
					<button id="drawMate" type="button" class="btn btn-default btn-info">画--已匹配路段</button>
					<button id="drawAllSetion" type="button" class="btn btn-default btn-info">画--全部路段</button>
					<button id="drawAllRoute" type="button" class="btn btn-default btn-info">画--全部线路</button>
					<button id="updateSection" type="button" class="btn btn-default btn-info">更新路段属性</button>
					<button id="updateRoute" type="button" class="btn btn-default btn-info">更新站点线路属性</button>
					<button id="drawTwenty" type="button" class="btn btn-default btn-info">20站点归属</button>
					<button id="updateLinePoint" type="button" class="btn btn-default btn-info">20站点更新点集</button>
					<button id="updateRailwayLinePoint" type="button" class="btn btn-default btn-info">54路线路段更新点集</button>
					<button id="openClickNear" type="button" class="btn btn-default btn-info">启用点击寻最近路段</button>
					<button id="liangtiaolu" type="button" class="btn btn-default btn-info">两条路</button>
					<button id="quyuluduan" type="button" class="btn btn-default btn-info">区域路段</button>
					<button id="luduanguishu" type="button" class="btn btn-default btn-info">路段归属市</button>
					<button id="guzhangzhandian" type="button" class="btn btn-default btn-info">207or227</button>
					
					
					<div style="text-align: center;margin-top:10px">
						<div id="mapall" style="width: 100%; height: 650px"></div>
					</div>
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
	<script type="text/javascript">	
    var map = new BMap.Map("mapall"); 
    map.enableScrollWheelZoom(true); //启用滚轮放大缩小
    var point = new BMap.Point(120.252178,30.269275);
	map.centerAndZoom(point,8);
</script>
	<script src="js/gis/CheLiangFenXi.js"></script>

</body>
</html>
