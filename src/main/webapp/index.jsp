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
		display:inline_block;
	}
	.text100{
		width:100px;
		display:inline_block;
	}
	.magrin_left{
		margin-left:20px;
	}
</style>
//不缓存
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="0">
</head>
<body>
	<div style="height:638px;width:30%;overflow:scroll;">
	
			<div class="divid"></div>
			<h3>站点校正</h3>
			<button type="button" class="btn btn-default btn-info" id="getAllSiteToModify" >载入站点</button><br>
			<select id="siteNeedModify"></select><br>
			<span>ID</span><input class="input" id="siteId"><br>
			<span>名称</span><input class="input" id="siteName"><br>
			<span>编码</span><input class="input" id="siteCode"><br>
			<span>经度</span><input class="input" id="longitudeSite"><br>
			<span>纬度</span><input class="input" id="latitudeSite"><br>
			<button type="button" class="btn btn-default btn-info" id="saveSiteModify" >保存站点</button>
			
			<div class="divid"></div>
			<h3>站点添加</h3>
			<button type="button" class="btn btn-default btn-info" id="readSiteName" >读取站点列表</button>
			<button type="button" class="btn btn-default btn-info" id="search" >查找站点位置</button><br>
			<button type="button" class="btn btn-default btn-info" id="readSiteNoPoint" >读取未知站点</button><br>
			<span>名称</span><input class="input" id="siteName"><br>
			<span>编码</span><input class="input" id="siteCode"><br>
			<span>经度</span><input class="input" id="longitudeSite"><br>
			<span>纬度</span><input class="input" id="latitudeSite"><br>
			<button type="button" class="btn btn-default btn-info" id="createSite" >保存站点</button>
			
			
			<div class="divid"></div>
			<h3>路段与路线生成</h3>
			<span>续传序号</span><input class="input" id="continueOrder" value="0"><br>
			<button type="button" class="btn btn-default btn-info" id="createRouteNewFinall" >新路线路段生成</button><br>
			<span>开始站点</span><input class="input" id="startSiteCode"><br>
			<span>结束站点</span><input class="input" id="endSiteCode"><br>
			<button type="button" class="btn btn-default btn-info" id="createRouteTwo" >站点间路线生成</button><br>
			<span>站点编码</span><input class="input" id="showRouteBySiteCode"><br>
			<button type="button" class="btn btn-default btn-info" id="showRouteBySite" >显示轨迹</button>
			
			<div class="divid"></div>
			<h3>站点间路径展示</h3>
			<select class='select-st' id="siteStart"  onchange="showRouteSite(this.value)">
				<option>请选择起始站点</option>
			</select>
			<br>
			<select class='select-st' id="siteEnd"  onchange="showRouteSite(this.value)">
				<option>请选择终止站点</option>
			</select>
			<button type="button" class="btn btn-default btn-info" onclick="showRoute()" >显示路径</button>
			
			<div class="divid"></div>
			<h3>站点显示</h3>
			<button type="button" class="btn btn-default btn-info" id="showAllSite" >站点显示</button>请输入半径0-1：<input id="squareSide"  value="0.2"><br>
			<div style="margin-left:100px;">
				<input type="checkbox" name="test" value="1" /><a onclick="sb(this)">红色：不走高速</a><br>
				<input type="checkbox" name="test" value="2" /><a onclick="sb(this)">蓝色：常规路线</a><br>
				<input type="checkbox" name="test" value="3" /><a onclick="sb(this)">绿色：距离较短</a><br>
				<input type="checkbox" name="test" value="4" /><a onclick="sb(this)">黑色：躲避拥堵</a><br>
			</div>
			<button type="button" class="btn btn-default btn-info" id="clearRoute2" >清理线路</button><br>
					
			<div class="divid"></div>
			<h3>GPS在线</h3>
			<button type="button" class="btn btn-default btn-info" id="getPersonTail" >获取人员轨迹</button><br>
			<button type="button" class="btn btn-default btn-info" id="showAreaByName" >显示区域</button><br>
			<div class="text"><span >区域轮廓</span></div><input class="input" id="areaName" ><br>
			<div class="text"><span ><span >人员轨迹点</span></div><input class="input" id="tailPointList" ><span id="tailNum">0</span><br>
			<div class="text"><span ><span >巡查范围</span></div><input class="input" id="personName" ><br>
			
			<button type="button" class="btn btn-default btn-info" id="savePersonTail" >保存人员轨迹</button><br>
			<button type="button" class="btn btn-default btn-info" id="showPersonTail" >轨迹展示</button><br>
			
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
			<span>道路类型</span><input class="input" id="ROAD_TYPE" value="4"><span>2国省道，4高速</span><br>
			<span>道路描述</span><input class="input" id="ROAD_DESCRIPTION"><span>例如:浙江省104国道</span><br>
			<span>道路策略</span><input class="input" id="ROAD_POLICY" value="0"><span style="font-size:10px">0最短时间1最短距离2不走高速</span><br>
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
			<button type="button" class="btn btn-default btn-info" id="sectionMatchRoad" >新路段归属道路</button>
			<button type="button" class="btn btn-default btn-info" id="updateSiteToRoad" >站点归属道路</button><br>
			
			<div class="divid"></div>
			<h3>GPS</h3>
			<button type="button" class="btn btn-default btn-info" id="gpsUpdataBdPoint" >更新为百度点</button><br>
			<button type="button" class="btn btn-default btn-info" id="gpsDrawByVehPlate" >画出某车的线路</button><br>
			
			<div class="divid"></div>
			<h3>其他</h3>
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
	
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	map.addControl(top_left_control);
	
	function changeName(name){
		$("#ROAD_NAME").val(name);
		$("#ROAD_DESCRIPTION").val("贵州省"+name);
	}
</script>
	<script src="js/gis/CheLiangFenXi.js"></script>
<script>
    function sb(obj){
        if(obj.previousElementSibling.checked==true){
            obj.previousElementSibling.checked=false;
        }else{
            obj.previousElementSibling.checked=true;
        }
    }
</script>
<script>
	$("#siteStart").children().remove();
	$("#siteEnd").children().remove();
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "getroad/getSiteCodeOld",
		success : function(data) {
			for(var i=0;i<data.length;i++){
				var option = $('<option value="'+data[i].startSite+'">'+data[i].startSite+'</option>')
				option.appendTo($("#siteStart"));
			}
			for(var i=0;i<data.length;i++){
				var option = $('<option value="'+data[i].startSite+'">'+data[i].startSite+'</option>')
				option.appendTo($("#siteEnd"));
			}
		}
	})
</script>
<script>
$("#readSiteName").click(function(){
	getGuiZhouSite("1");
})
$("#readSiteNoPoint").click(function(){
	readSiteNoPoint();
})
$("#search").click(function(){
	search();
})
$("#siteCodeReGet").click(function(){
	getRouteSectionBySiteCode($("#siteCodeReGetNum").val());
})

// 输入站点编码，显示该站点与其他站点的所有路径
$("#showRouteBySite").click(function(){
	$.ajax({
		url : "direction/showRouteBySite",
		type : "post",
		dataType : "json",
		data : {siteCode : $("#showRouteBySiteCode").val()},
		success : function(data){
			map.clearOverlays();
			for(var i=0;i<data.length;i++){
				var points = data[i];
				var overlay = new BMap.Polyline(points, {
					strokeColor : "blue",
					strokeWeight : 5,
					strokeOpacity : 1
				});
				map.addOverlay(overlay);
			}
			$.ajax({
				type : "POST",
				dataType : 'json',
				data : {orgCode : $("#showRouteBySiteCode").val()},
				async : false, // 同步查询
				url : 'site/getSiteByCode',
				success : function(data2){
					console.log(data2)
					var icon_blue = new BMap.Symbol(BMap_Symbol_SHAPE_POINT, {scale: 1.5,fillColor: "#00baff",fillOpacity: 1});
					var point = new BMap.Point(data2[0].longitude,data2[0].latitude);
					var marker = new BMap.Marker(point,{icon:icon_blue}, {imageOffset: new BMap.Size(0, 0)});  // 创建标注
					map.addOverlay(marker);
				}
			})
		}
	})
})

$("#insertRouteSection").click(function(){
	$.ajax({
		url : "getroad/insertRouteSection",
		type : "post"
	})
})


$("#deleteRouteWhichNotFull").click(function(){
	$.ajax({
		url : "direction/deleteRouteWhichNotFull",
		type : "post"
	})
})

var order = 0;
var local = new BMap.LocalSearch(map,{renderOptions:{map:map}});
function search(){
	map.map.clearOverlays();
	local.search($("#siteName").val());
}

function readSiteNoPoint(){
	$.ajax({
		url:"site/addSite",
		type:'post',
		dataType:"json",
		async:false,
		success:function(data){
			console.log(data);
			if(autoFlag == "1"){
				getGuiZhouSite(autoFlag);
			}
		}
	})
}

function searchSite(autoFlag){
	var url = 'http://api.map.baidu.com/place/v2/search?query='
		+$("#siteName").val()+'&tag=收费站&region=贵州&output=json&ak=y9A9WkT3Y1jadGiMZwLEN7itWTS9oaQW';
	$.ajax({
		 type:"POST",
		 dataType:'jsonp',
		 url : url,
		 async: false,
		 success : function(data){
			 console.log(data);
			 if(data.message == "ok" && data.results.length > 0  &&  typeof(data.results[0].location) != "undefined"){
					 $("#longitudeSite").val(data.results[0].location.lng);
					 $("#latitudeSite").val(data.results[0].location.lat);
					 var marker = new BMap.Marker(data.results[0].location);
					 map.addOverlay(marker);
					 if(autoFlag == "1"){
						 createSite(autoFlag);
					 }
			 }else{
				 $("#longitudeSite").val("");
  				 $("#latitudeSite").val("");
  				 if(autoFlag == "1"){
					 createSite(autoFlag);
				 }
			 }
		 }
	})
}

function getGuiZhouSite(autoFlag){
	map.addEventListener("click",function(e){
		$("#longitudeSite").val(e.point.lng);
		$("#latitudeSite").val(e.point.lat);
	});
	$.ajax({
		url:"data/jsonGuiZhou.json",
		dataType:"json",
		success:function(data){
			console.log(data[order]);
			$("#siteName").val(data[order].收费站名称.split("站")[0]+"收费站");
			$("#siteCode").val(data[order].收费站代码);
			$("#longitudeSite").val("");
			$("#latitudeSite").val("");
			searchSite(autoFlag);
			order++;
		}
	})
}

function createSite(autoFlag){
	$.ajax({
		url:"site/addSite",
		type:'post',
		data:{
			orgCode : $("#siteCode").val(),
			name : $("#siteName").val(),
			longitude : $("#longitudeSite").val(),
			latitude : $("#latitudeSite").val()
		},
		dataType:"json",
		async:false,
		success:function(data){
			console.log(data);
			if(autoFlag == "1"){
				getGuiZhouSite(autoFlag);
			}
		}
	})
}

// 生产站点和路径
var createOrder = 0;	// 第几个路径 0-376
var thisRouteFullNum = 0;	// 这个路径的匹配次数满了吗 1-376
var totalNum = 0; 	// 需要多少次匹配 376
$("#createRouteNewFinall").click(function(){
	createOrder = $("#continueOrder").val();
	createRouteNewFinall(createOrder);
})

// 按序号生产该站点对其他所有站点的路径
function createRouteNewFinall(i){
	$.ajax({
		type:'POST',
		dataType:'json',
		url : 'direction/getSiteInfo',
		async: false,
		success : function(data){
			totalNum = data.length-1;
			for(var j=0;j<data.length;j++){
				if(i == j){
					continue;
				}
				$.ajax({
					type:'POST',
					url : 'direction/isHaveOurRoute',
					async: false,
					data : {
						startSite : data[i].orgCode,
						endSite : data[j].orgCode,
						startLongtude : data[i].longitude,
						startLatitude : data[i].latitude,
						endLongtude : data[j].longitude,
						endLatitude : data[j].latitude
						},
					dataType : "json",
					success : function(data2){
						if(data2.result == "false"){
							getRouteByBMapAPI(data2.startSite,data2.endSite,data2.startPoint,data2.endPoint,"12");
						}else{
							thisRouteFullNum++;
							console.log(thisRouteFullNum)
							if(thisRouteFullNum == totalNum){
								thisRouteFullNum = 0;
								createOrder++;
								createRouteNewFinall(createOrder);
							}
						}
					}
				})
			}
		}
	})
}

// 输入站点编码、坐标、策略，调用百度API获取路径并保存
function getRouteByBMapAPI(startSite,endSite,startPoint,endPoint,policy){
	var url = 'http://api.map.baidu.com/direction/v1?mode=driving&origin='
		+startPoint.split(",")[1]+","+startPoint.split(",")[0]+'&destination='
		+endPoint.split(",")[1]+","+endPoint.split(",")[0]+'&origin_region=贵州&destination_region=贵州'
		+'&tactics='+policy+'&output=json&ak=53oVIOgmSIejwV7EfphPgTynOZbIiVYu';
	 $.ajax({
		 type:"POST",
		 dataType:'jsonp',
		 url : url,
		 cache:false, 
		 async:false,
		 success : function(data2){
			 $.ajax({
				 type:"POST",
				 cache:false, 
				 data : {
					startSiteCode: startSite,
					endSiteCode: endSite,
					api : JSON.stringify(data2),
					policy:policy
				 },
				 url : 'direction/createRouteAndSection',
				 success : function(data){
					 thisRouteFullNum++;
					 console.log(thisRouteFullNum)
					 if(thisRouteFullNum == totalNum){
						 thisRouteFullNum = 0;
						 createOrder++;
						 createRouteNewFinall(createOrder);
					 }
				 },
				 error : function(){
					 console.log("失败了")
				 }
			 });
		 }
	})
}

$("#createRouteTwo").click(function(){
	createRouteTwo($("#startSiteCode").val(),$("#endSiteCode").val());
})

// 跑批两个站点的路径
function createRouteTwo(startSiteCode,endSiteCode){
	$.ajax({
		type:'POST',
		dataType:'json',
		url : 'direction/getSiteInfo',
		async: false,
		success : function(data){
			for(var i=0;i<data.length;i++){
				if(data[i].orgCode != startSiteCode){
					continue;
				}
				for(var j=0;j<data.length;j++){
					if(data[j].orgCode != endSiteCode){
						continue;
					}
					var startPoint = data[i].longitude+","+data[i].latitude;
					var endPoint = data[j].longitude+","+data[j].latitude;
					getRouteByBMapAPI(data[i].orgCode,data[j].orgCode,startPoint,endPoint,"12");
				}
			}
		}
	})
}

$("#getAllSiteToModify").click(function(){
	getAllSiteToModify();
})

var allSite;
var orderNow = 0;
function getAllSiteToModify(){
	$.ajax({
		type:'POST',
		dataType:'json',
		url : 'direction/getSiteInfo',
		async: false,
		success : function(data){
			allSite = data;
			for(var i=0;i<data.length;i++){
				var option = $('<option value="'+i+'">'+data[i].name+'</option>');
				option.appendTo($("#siteNeedModify"));
			}
			map.addEventListener("click",function(e){
				$("#longitudeSite").val(e.point.lng);
				$("#latitudeSite").val(e.point.lat);
				var marker = new BMap.Marker(e.point);
				map.addOverlay(marker);
			});
			handleModify(0);
		}
	})
}

// 显示每个站点的信息
function handleModify(order){
	$("#siteId").val(allSite[order].id);
	$("#siteName").val(allSite[order].name);
	$("#siteCode").val(allSite[order].orgCode);
	$("#longitudeSite").val(allSite[order].longitude);
	$("#latitudeSite").val(allSite[order].latitude);
	var point = new BMap.Point(allSite[order].longitude,allSite[order].latitude);
	var marker = new BMap.Marker(point);
	map.addOverlay(marker);
	map.centerAndZoom(point,19);
	$("#siteNeedModify option[value='"+order+"']").remove();
}

$("#saveSiteModify").click(function(){
	$.ajax({
		url:"site/saveSite",
		type:'post',
		data:{
			id : $("#siteId").val(),
			orgCode : $("#siteCode").val(),
			name : $("#siteName").val(),
			longitude : $("#longitudeSite").val(),
			latitude : $("#latitudeSite").val()
		},
		success : function(data){
			if(data == "true"){
				orderNow++;
				handleModify(orderNow);
			}
		}
	})
})

$("#sectionMatchRoad").click(function(){
	$.ajax({
		url : "direction/sectionMatchRoad",
		type : "post"
	})
})
</script>
</body>
</html>
