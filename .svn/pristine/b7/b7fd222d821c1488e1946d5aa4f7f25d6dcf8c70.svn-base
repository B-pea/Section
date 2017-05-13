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
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>改变地图中心点及缩放级别</title>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://webapi.amap.com/maps?v=1.3&key=97ff1498fd4bfc2670febc14fb1bd4db"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
<div id="container" style="width:100%;height:100%"></div>
<div class="button-group">
    <input id="setCenter" type="button" class="button" value="改变地图中心点及缩放级别"/>
</div>
<div style="background-color:#fff;position:absolute;top:10%;left:10%;width:200px;height:400px">
	<button id="route_back" style="width:100%;heigth:40px">生成</button>
	<button id="updateLinePoint" style="width:100%;heigth:40px">Setion-更新点</button>
	<button id="drawAllSetion" style="width:100%;heigth:40px">Setion-绘制</button>
</div>
<script>
    var map = new AMap.Map('container', {
        resizeEnable: true,
        center: [116.397428, 39.90923],
        zoom: 13
    });
</script>
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/gaoDe.js"></script>
	<script>
		var key_ak = "428a2119acd95cb0ca4128f94a607d4d";
	
		$("#route_back").click(function() {
			route_back();
		})
		
		$("#updateLinePoint").click(function() {
			updateLinePoint();
		})
		
		$("#drawAllSetion").click(function() {
			drawAllSetion();
		})
		
		/****************************************生成路线路段********************************************/
		// 生成
		function route_back() {
			// 获取站点的首尾点
			var site_list = getSiteList();
			// 配置相互间的组合
			for (var i = 0; i < site_list.length; i++) {
				for(var j=i+1;j < site_list.length;j++){
					var  start  = site_list[i].longitude+","+site_list[i].latitude;
					var  end  = site_list[j].longitude+","+site_list[j].latitude;
					var startorg = site_list[i].orgCode;
					var endorg = site_list[j].orgCode;
					morePolicy(start,end,startorg,endorg,'6');
				}
			}
		}
		
		// 获取所有的站点信息
		function getSiteList() {
			var site_list = [];
			$.ajax({
				type : 'POST',
				dataType : 'json',
				url : 'direction/getSiteInfo',
				async : false,
				success : function(data) {
					site_list = data;
				}
			});
			return site_list;
		}
		key_ak
		// 整理高德URL	
		function morePolicy(start, end, startorg, endorg, type) {
			var url = 'http://restapi.amap.com/v3/direction/driving?origin='
					+ start + '&destination=' + end + '&strategy=' + type
					+ '&output=json&extensions=all&key=' +key_ak+"";
			sendGaoDe(start, end, startorg, endorg, url, type);// 12-最短 11-常规 10-不走高速 13-躲避拥堵
			url = 'http://restapi.amap.com/v3/direction/driving?origin=' + end
					+ '&destination=' + start + '&strategy=' + type
					+ '&output=json&extensions=all&key' +key_ak+"";
			sendGaoDe(end, start, endorg, startorg, url, type);
		}
		
		var num_sendGaoDe = 1;
		// 获取点集并处理
		function sendGaoDe(start,end,startorg,endorg,url,type){
			$.ajax({
				 type:"POST",
				 dataType:'jsonp',
				 url : url,
				 async: false,
				 success : function(data){
					 var miles = data.route.paths[0].distance;
					 var arr = data.route.paths[0].steps;
					 var map = new Array();
					 var map2 = new Array();
					 for(var i=0;i<arr.length;i++){
							 var map1 = new Array();
							 map1.push(arr[i].distance);
							 var polyline = arr[i].polyline;
							 var polyline_list = polyline.split(";");
							 var start_lng = polyline_list[0].split(",")[0];
							 var start_lat = polyline_list[0].split(",")[1];
							 var end_lng = polyline_list[polyline_list.length-1].split(",")[0];
							 var end_lat = polyline_list[polyline_list.length-1].split(",")[1];
							 map1.push(start_lng);
							 map1.push(start_lat);
							 map1.push(end_lng);
							 map1.push(end_lat);
							 map1.push(type);
							 map1.push("");
							 map2.push(map1);
					 }
					 map.push(map2);
					 map.push(miles);
					 map.push(start);
					 map.push(end);
					 map.push(startorg);
					 map.push(endorg);
					 var array = JSON.stringify(map);
					 map2.length = 0;
					 map.length = 0;
					 //console.log(array);
					 $.ajax({
						 type:"POST",
						 //dataType:'json',
						 async: false,
		 				 data : {
								'array':array,
								type:type
							 },
		 				 url : 'direction/getDirectionList',
						 success : function(data){
							 console.log(num_sendGaoDe+"/20");
							 num_sendGaoDe++;
						 },
						 error : function(){
							 alert("失败了")
						 }
					 });
				 }
			})
		}
		
		/****************************************更新setion点集********************************************/
		// 更新路段的点集line_points
		function updateLinePoint(){
			$.ajax({
				url:"getroad/getroutInfo",
				type:'post',
				dataType:"json",
				async:false,
				success:function(data){
					for(var i=0;i<data.length;i++){
						if(data[i].line_points == null){
							var id = data[i].id;
							var start = data[i].StartLongtude+","+data[i].StartLatitude;
							var end = data[i].EndLongtude+","+data[i].EndLatitude;
							var type = 0;
							$.ajax({
								url:"getroad/getSetionPolicy",
								type:'post',
								async:false,
								data:{
									setion_id:id
								},
								dataType:'json',
								success:function(data){
									type = data;
								}
							})
							var url = 'http://restapi.amap.com/v3/direction/driving?origin='
								+ start + '&destination=' + end + '&strategy=' + type
								+ '&output=json&extensions=all&key=a008837b3765ede6b03b6087022b0162';
						apiGetLinePoint(url,id,data.length);
						}				
					}
				}
			});
		}
		var num_apiGetLinePoint = 0;
		// 百度API获取路段的点集
		function apiGetLinePoint(url,id,num){
			$.ajax({
				 type:"POST",
				 dataType:'jsonp',
				 url : url,
				 async: false,
				 success : function(data){
					 
					 console.log(num_apiGetLinePoint+"/"+num);
					 num_apiGetLinePoint++;
					 
					 var arr = data.route.paths[0].steps;
					 var str = arr[0].polyline;
					 updateLabel(id,str);			 
				 }
			});
		}

		// 更新路段的点集line_point
		function updateLabel(id, str) {
			$.ajax({
				url : "getroad/updateLinePoint",
				type : 'post',
				dataType : "json",
				async : false,
				data : {
					id : id,
					line_points : str
				}
			})
		}
		
		/*************************************画路段***********************************************/
		// 画出全部路段，利用点集
		function drawAllSetion(){
			$.ajax({
				url:'getroad/getAllLinePoint',
				type:'post',
				async:false,
				dataType:'json',
				success:function(data){
					for(var i=0;i<data.length;i++){
						var points = data[i].line_points;
						if(points == null){
							console.log(data[i].id);
						}else{
							drawRoadByValue(points,"blue",6);
						}
					}			
				}
			})
		}
		
		// 根据点集画出线路
		function drawRoadByValue(point_arr,color,weight){
			var point_arr_list = point_arr.split(";");
			var point_arr_all = new Array();			
			for(var i=0;i<point_arr_list.length;i++){
				var point_arr = new Array();
				point_arr.push(point_arr_list[i].split(",")[0]);	
				point_arr.push(point_arr_list[i].split(",")[1]);	
				point_arr_all.push(point_arr);
			}
			var polyline = new AMap.Polyline({
		        path: point_arr_all,          //设置线覆盖物路径
		        strokeColor: "#3366FF", //线颜色
		        strokeOpacity: 1,       //线透明度
		        strokeWeight: 5,        //线宽
		        strokeStyle: "solid",   //线样式
		        strokeDasharray: [10, 5] //补充线样式
		    });
		    polyline.setMap(map);
		}
	</script>
</body>
</html>