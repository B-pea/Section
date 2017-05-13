/**
 * 
 */
var id_point_map = new Map(); //
var id_same_map = new Map();	// id和与其重复的路段id列表
var id_near_map = new Map();	//	id和相似点id列表
var id_overlay_map = new Map(); // id和路段线路
var sid_slng_map = new Map(); // setionID和起点经度
var sid_slat_map = new Map();
var sid_elng_map = new Map();
var sid_elat_map = new Map();
var sid_dis_map = new Map();	// setionID所在路段和点击点的最近距离 
var g_dis_list_num = 0; // 点击点与路段计算距离的次数，用于判断计算完毕

var div = $("#divv");

$("#create").click(function (){
	clear();
	createRoute();
})

$("#clear").click(function (){
	clear();
	checkSame();
})

$("#clearRoute").click(function (){
	clear();
	clearRouteSetion();
})

$("#drawSetion").click(function (){
	clear();
	getAllSetionId();
})

$("#drawSetion2").click(function (){
	clear();
	showSite();
	showSetion();
})

$("#changeColor").click(function (){
	clear();
	getSetionData();
})

$("#testMate").click(function (){
	clear();
	testMateMain();
})

$("#drawMate").click(function (){
	clear();
	drawMate();
})

$("#updateSection").click(function (){
	clear();
	updateSection();
})

$("#drawAllSetion").click(function (){
	clear();
	drawAllSetion();
})

$("#drawAllRoute").click(function (){
	clear();
	drawAllRoute();
})

$("#showSite").click(function (){
	clear();
	showSite();
})

$("#updateRoute").click(function (){
	clear();
	updateRoute();
})

$("#drawTwenty").click(function (){
	clear();
	drawTwenty();
})

$("#updateLinePoint").click(function (){
	clear();
	updateLinePoint();
})

$("#updateRailwayLinePoint").click(function (){
	clear();
	updateRailwayLinePoint();
})

$("#openClickNear").click(function (){
	clear();
	openClickNear();
})

$("#liangtiaolu").click(function (){
	clear();
	liangtiaolu();
})


function clear(){
	id_point_map.clear(); //
	id_same_map.clear();	// id和与其重复的路段id列表
	id_near_map.clear();	//	id和相似点id列表
	id_overlay_map.clear(); // id和路段线路
	sid_slng_map.clear(); // setionID和起点经度
	sid_slat_map.clear();
	sid_elng_map.clear();
	sid_elat_map.clear();
	sid_dis_map.clear();	// setionID所在路段和点击点的最近距离 
}
/****************************创建路段和站点***********************************/
// 获取百度传回的路段信息
function createRoute(){
	$.ajax({
		type:'POST',
		dataType:'json',
		url : 'direction/getSiteInfo',
		async: false,
		success : function(data){
			//console.log(data.length)
			for(var i=0;i<data.length;i++){
				for(var j=i+1;j<data.length;j++){
					var  start  = data[i].latitude+","+data[i].longitude;
					var  end  = data[j].latitude+","+data[j].longitude;
					var startorg = data[i].orgCode;
					var endorg = data[j].orgCode;
					morePolicy(start,end,startorg,endorg,'10');
					//morePolicy(start,end,startorg,endorg,"11");
					//morePolicy(start,end,startorg,endorg,"12");
					//morePolicy(start,end,startorg,endorg,"13");
				}				
			}
		}
	});	
}

// 多策略
function morePolicy(start,end,startorg,endorg,type){
	var url = 'http://api.map.baidu.com/direction/v1?mode=driving&origin='
				+start+'&destination='
				+end+'&origin_region=浙江&destination_region=浙江'
				+'&tactics='+type+'&output=json&ak=y9A9WkT3Y1jadGiMZwLEN7itWTS9oaQW';
		ddd(start,end,startorg,endorg,url,type);// 12-最短 11-常规 10-不走高速 13-躲避拥堵
		url = 'http://api.map.baidu.com/direction/v1?mode=driving&origin='
			+end+'&destination='
			+start+'&origin_region=浙江&destination_region=浙江'
			+'&tactics='+type+'&output=json&ak=y9A9WkT3Y1jadGiMZwLEN7itWTS9oaQW';
		ddd(end,start,endorg,startorg,url,type);
}

var times = 1;
var all_times = 380;
// 创建路段和站点插入数据库
function ddd(start,end,startorg,endorg,url,type){
	$.ajax({
		 type:"POST",
		 dataType:'jsonp',
		 url : url,
		 async: false,
		 success : function(data){
			 console.log(times+"/"+all_times);
			 times++;
			 var miles = data.result.routes[0].distance;
			 var start1 = data.result.routes[0]
			 var arr = data.result.routes[0].steps;
			 var map = new Array();
			 var map2 = new Array();
			 for(var i=0;i<arr.length;i++){
					 var map1 = new Array();
					 map1.push(arr[i].distance);
					 map1.push(arr[i].stepOriginLocation.lng);
					 map1.push(arr[i].stepOriginLocation.lat);
					 map1.push(arr[i].stepDestinationLocation.lng);
					 map1.push(arr[i].stepDestinationLocation.lat);
					 map1.push(type);
					 var line_points = arr[i].path.split(";");
					 var str = "";
					 var size = line_points.length;
					 /*for(var j=0;j<size;j++){
						 var lng = line_points[j].split(",")[0];
						 var lat = line_points[j].split(",")[1];						 
						 if(j != size-1){
							 str+=lng+"-"+lat+"+";	//	本来是，和；因为转JSON出问题，用了-和#
						 }else{
							 str+=lng+"-"+lat;
						 }
					 }*/
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
					 //alert("成功")
				 },
				 error : function(){
					 alert("失败了")
				 }
			 });
		 }
	})
}

/*******************************************更新setion路段的点集**********************/

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
					var start = data[i].StartLatitude+","+data[i].StartLongtude;
					var end = data[i].EndLatitude+","+data[i].EndLongtude;
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
					var url = 'http://api.map.baidu.com/direction/v1?mode=driving&origin='
						+start+'&destination='
						+end+'&origin_region=浙江&destination_region=浙江'
						+'&tactics='
						+type+'&output=json&ak=y9A9WkT3Y1jadGiMZwLEN7itWTS9oaQW';
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
			 
			 var miles = data.result.routes[0].distance;
			 var start1 = data.result.routes[0]
			 var arr = data.result.routes[0].steps;
			 var str = arr[0].path;
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

/********************************在路线表中清理重复站点********************************/

// 检测所有路段，整理出相同路段和相似路段
function checkSame(){
	var same_num = 0; // 相同路段的种类数
	var same_count = 0;// 相同路段的重复数，除了第一次
	var close_num = 0;	// 相似路段的数量
	var same_list = [];	// 总的重复路段ID集合
	var near_list = [];	// 总的相似路段ID集合
	$.ajax({
		type:"POST",
		url:"getroad/getroutInfo",
		async: false,
		dataType:'json',
		success:function(data){// 获取全部路段的记录
			for(var i=0,size = data.length;i<size;i++){
				console.log(i+"/"+size);
				var continue_flag = false;
				for(var k=0;k<same_list.length;k++){// 如果记录在重复列表中，则continue
					if(i == same_list[k]){
						continue_flag = true;
						same_count++;
						break;
					}
				}
				if(continue_flag){
					continue;
				}
				
				var first_flag = false;		// 第一次出现的标记
				var same_list_one = [];	// 相同的路段ID列表
				var near_list_one = [];	// 相似的路段ID列表
				for(var j=i+1;j<data.length;j++){
					/*var continueee_flag = false;	// 防止比较之前的被重复点，目的是为节省速度，但是更慢了
					for(var k=0;k<same_list.length;k++){
						if(j == same_list[k]){
							continueee_flag = true;
							break;
						}
					}
					if(continueee_flag){
						continue;
					}*/
					
					if(data[i].StartLatitude == data[j].StartLatitude &&
						data[i].StartLongtude == data[j].StartLongtude &&
						data[i].EndLatitude == data[j].EndLatitude &&
						data[i].EndLongtude == data[j].EndLongtude &&
						data[i].miles == data[i].miles){	// 如果路段的所有坐标一致
						/*var point_list = [];
						point_list.push(data[i].StartLatitude);
						point_list.push(data[i].StartLongtude);
						point_list.push(data[i].EndLatitude);
						point_list.push(data[i].EndLongtude);*/
						//alert(i+"-"+j +"-"+data[i].StartLatitude+"-"+data[j].StartLatitude);
						//id_point_map.put(j,i);
						same_list.push(j);			// 放入总的重复列表
						same_list_one.push(j);	// 放入这个i下面的重复列表
						if(!first_flag){
							same_num++;	// 计算重复种类的数量
						}
						first_flag = true;	// 如果第二次出现，就不在放入重复种类的数量中
					}
					
					if(data[i].miles != data[j].miles){
						if( get_distance(data[i].StartLatitude,data[i].StartLongtude,data[j].StartLatitude,data[j].StartLongtude)<0.01
								&& get_distance(data[i].EndLatitude,data[i].EndLongtude,data[j].EndLatitude,data[j].EndLongtude)<0.01){
							close_num++;	// 计算相近距离小于10米的近似路段
							near_list.push(j);	// 放入总得相似路段ID列表
							near_list_one.push(j);	// 放入这个i下面的近似路段列表
							var content = "<p>"+i+"-"+j+"</p>";
							div.append(content);
							//break;
						}
					}
					
				}
				if(same_list_one.length != 0){
					id_same_map.put(i,same_list_one); // 得到和i重复的其他路段的id列表
				}
				if(near_list_one.length !=0){
					id_near_map.put(i,near_list_one);	// 得到和i近似的路段id列表
				}
				
			}
			sql_kill_same();	// 将重复ID统一化
			sql_kill_near();	// 将近似ID统一化
			
			sql_kill_useless(same_list,near_list);	// 删除重复ID和近似ID(路段表)
		}
	})
	$("#showResult").html(same_num);
	alert(same_count);
}

// 清理相同路段(线路表)
function sql_kill_same(){
	var id_list_key = id_same_map.keys();// 1837种重复点+若干不重复点
	
	for(var i=0,size = id_list_key.length;i<size;i++){
		console.log("线路表去重："+i+"/"+size);	//验证了处理相同路段的数量
		var target_id = id_list_key[i];
		
		var need_change_list = id_same_map.get(target_id);
		for(var j=0;j<need_change_list.length;j++){
			$.ajax({
				url:"getroad/updataRouteInfo",
				type:"POST",
				async: false,
				data:{
					targetId:target_id+1,
					ncId:need_change_list[j]+1
				}
			})
		}
	}
}

// 清理相似路段(线路表)
function sql_kill_near(){
var id_list_key = id_near_map.keys();// 6个相似重复类,不会因为去重而更改ID，因为这是首ID
	
	for(var i=0,size=id_list_key.length;i<size;i++){
		console.log("线路表去相似："+i+"/"+size);	//验证了处理相似数量
		var target_id = id_list_key[i];
		
		var need_change_list = id_near_map.get(target_id);
		
		for(var j=0;j<need_change_list.length;j++){
			
			$.ajax({
				url:"getroad/updataRouteInfoNear",
				type:"POST",
				async: false,
				data:{
					targetId:target_id+1,
					ncId:need_change_list[j]+1
				}
			})
		}
	}
}

// 删除相同和相似的路段(路段表)
function sql_kill_useless(same_list,near_list){
	for(var i=0,size=same_list.length;i<size;i++){
		console.log("路段表删除重复:"+i+"/"+size);
		$.ajax({
			url:"getroad/deleteSetionByID",
			type:'post',
			async:false,
			data:{
				id:same_list[i]+1	// 以前push的是j从0开始，表中id从1开始
			}
		})		
	}
	for(var j=0,size=near_list.length;j<size;j++){
		console.log("路段表删除相似:"+j+"/"+size);
		$.ajax({
			url:"getroad/deleteSetionByID",
			type:'post',
			async:false,
			data:{
				id:near_list[j]+1
			}
		})
	}
}

// 从pub_road_route的setions中找到不重复的路段id
function getAllSetionId(){
	$.ajax({
		url:"getroad/getAllRouteId",
		type:"POST",
		async: false,
		dataType:'json',
		success:function(data){ // data为路段id集合
			for(var i=0;i<data.length;i++){
				$.ajax({
					url:"getroad/selectByPrimaryKey",
					type:'post',
					async:false,
					dataType:'json',
					data:{
						id:data[i]
					},
					success:function(data_id){
						var pt1 = new BMap.Point(data_id.startLongtude,data_id.startLatitude);
						var pt2 = new BMap.Point(data_id.endLongtude,data_id.endLatitude);
						setMarker(pt1);
						setMarker(pt2);
						showRoadSetion(i,pt1,pt2,"#000000",3);
					}
				})
			}
		}
	})
}

/********************************在路线表中清理路线********************************/

// 清理相同setion的路线，优先级12>11>10>13
function clearRouteSetion(){
	$.ajax({
		url:"getroad/clearRouteSetion",
		type:"POST",
		async:false
	});
}

/********************************展示GIS路段********************************/

// 将站点画到地图上
function showSite(){
	$.ajax({
		url:"getroad/ssss",
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			for(var i=0;i<data.length;i++){
				var spoint = new BMap.Point(data[i].longitude,data[i].latitude);
				setMarker(spoint);
			}
		}
	})
}

// 将处理后的路段画到地图上
function showSetion(){
	$.ajax({
		url:"getroad/getroutInfo",
		type:'post',
		dataType:"json",
		async:false,
		success:function(data){
			for(var i=0;i<data.length;i++){
				var sLongtudu = data[i].StartLongtude;
				var sLatitude = data[i].StartLatitude;
				var eLongtudu = data[i].EndLongtude;
				var eLatitude = data[i].EndLatitude;
				var spoint = new BMap.Point(sLongtudu,sLatitude);
				var epoint = new BMap.Point(eLongtudu,eLatitude);
				//setMarker(spoint);
				//setMarker(epoint);
				showRoadSetion(data[i].id,spoint, epoint, "#000000", 2);
			}
		}
	})
}

/*// 得到所有路段数据（车流量），为路段赋值颜色
*//**这个不用了，颜色直接在搜完路径就去大数据读取显示**//*
function getSetionData(){	
	$.ajax({
		url:"getroad/getroutInfo",
		type:'post',
		dataType:"json",
		async:false,
		success:function(data){
			for(var i=0;i<data.length;i++){
				var id = data[i].id;
				bigDataSetionColor(id);				
			}
		}
	});
}*/

var num_bigDataSetionColor = 0;
// 根据路段的sid，去大数据查询相关数据，并展示颜色
function bigDataSetionColor(id){
	$.ajax({
		url:"getroad/getSetionCarNum",
		data:{
			id:id
		},
		type:'post',
		dataType:'json',
		async:false,
		success:function(data){
			
			console.log(num_bigDataSetionColor);
			num_bigDataSetionColor++;
			
			var veh_num = data.data[0].veh;
			var color = "";
			if(veh_num < 5000){
				color="#7FFF00";
			}else if(veh_num >= 5000 || veh_num < 10000){
				color="#A2CD5A";
			}else if(veh_num >= 10000 || veh_num < 15000){
				color ="#EEEE00";
			}else if(veh_num >= 15000 || veh_num < 20000){
				color ="#FFF68F";
			}else if(veh_num>= 20000 || veh_num < 25000){
				color = "#FFFF00";
			}else if(veh_num >= 25000 || veh_num < 30000){
				color="#EE7600";
			}else if(veh_num >= 30000 || veh_num < 35000){
				color="#CD8500";
			}else if(veh_num >= 35000 || veh_num < 40000){
				color="#CD3700";
			}else{
				color ="#8B3A3A";
			}
			changeColor(id,color);
		}
	})
}

// 根据路段ID，改变其颜色
function changeColor(id,color){
	var id_keys = id_overlay_map.keys();
	for(var i=0;i<id_keys.length;i++){
		var temp_id = id_keys[i];
		if(temp_id == id){
			var ply = id_overlay_map.get(temp_id);
			ply.setStrokeColor(color);
		}		
	}
}

/*******************************点击显示最近路段********************************/

function openClickNear(){
	// 点击事件
	map.addEventListener("click",function(e){
		// alert(e.point.lng + "," + e.point.lat);
		g_dis_list_num = 0;
		sid_slng_map.clear();
		sid_slat_map.clear();
		sid_elng_map.clear();
		sid_elat_map.clear();
		sid_dis_map.clear();
		//map.clearOverlays();
		
		var id_list = searchRound(e.point);
		searchedIDPoints(id_list,e.point);
	});
}



// 寻找20公里范围内的路段
function searchRound(point){
	var id_list = [];
	// 取出所有路段
	$.ajax({
		url:"getroad/getroutInfo",
		type:'post',
		dataType:"json",
		async:false,
		success:function(data){
			for(var i=0;i<data.length;i++){
				var sLongtude = data[i].StartLongtude;
				var sLatitude = data[i].StartLatitude;
				var eLongtude = data[i].EndLongtude;
				var eLatitude = data[i].EndLatitude;
				// 计算收尾点与point距离都小于20公里的
				var s_dis = get_distance(sLatitude,sLongtude,point.lat,point.lng);
				var e_dis = get_distance(eLatitude,eLongtude,point.lat,point.lng);
				if(e_dis<20 && s_dis<20){
					id_list.push(data[i].id);// 将ID返回
					sid_slng_map.put(data[i].id,sLongtude);	//	根据id存入相应点的经纬度，用于计算所有点
					sid_slat_map.put(data[i].id,sLatitude);
					sid_elng_map.put(data[i].id,eLongtude);
					sid_elat_map.put(data[i].id,eLatitude);
				}
			}	
		}
	});
	return id_list;	
}

// 搜索这些范围内路段的所有点集
function searchedIDPoints(id_list,point){
	// 从范围内的ID搜索
	for(var i=0;i<id_list.length;i++){
		// 从map中读取对应的首尾点坐标
		var sid = id_list[i];
		var data = getSetionByID(sid); // 获得该路段的所有点集，表中已存
		var points = data.line_points.split(";");
		var arrPois = [];
		for(var j=0,size=points.length;j<size;j++){
			var point_each = new BMap.Point(points[j].split(",")[0],points[j].split(",")[1]);
			arrPois.push(point_each);
		}
		// 计算点击点与这个路段的最小距离
		minDistance(arrPois,point,sid,id_list.length,i);
	}	
}

// 计算点击点与路段点集的最小距离
function minDistance(arrPois,point,sid,m_dis_num,current_i){
	// 计算路段点集和点击点的距离
	var temp_dis = 20;
	for(var i=0;i<arrPois.length;i++){
		var dis = get_distance(arrPois[i].lat,arrPois[i].lng,point.lat,point.lng);
		if(dis<temp_dis){	// 得到最小距离
			temp_dis = dis;
		}
	}	
	sid_dis_map.put(sid,temp_dis);
	current_i++;
	// 如果是最后一次计算，那么就推送出最近的那个路段ID，上下行
	if(m_dis_num == current_i){
		var min_id = minDisSetionID();
		console.log("最近的："+min_id);
		sid_dis_map.remove(min_id);
		var sec_min_id = minDisSetionID();
		console.log("第二近的："+sec_min_id);
		
		showMinSetion(min_id, "green", 9);
		showMinSetion(sec_min_id, "green", 9);		
	}
}

// 当计算最后一次时，找到最小距离的那个sid
function minDisSetionID(){
	var target_id = -1;
	var min_dis = 20;
	var dis_values = sid_dis_map.values();
	for(var i=0;i<dis_values.length;i++){
		if(dis_values[i]<min_dis){
			min_dis = dis_values[i];
		}
	}
	var dis_keys = sid_dis_map.keys();
	for(var i=0;i<dis_keys.length;i++){
		var sid = dis_keys[i];
		var value = sid_dis_map.get(sid);
		if(value == min_dis){
			target_id = sid;
			break;
		}
	}
	return target_id;
}

// 显示最小的路段
function showMinSetion(sid,color, weight){
	var slng = sid_slng_map.get(sid);
	var slat = sid_slat_map.get(sid);
	var elng = sid_elng_map.get(sid);
	var elat = sid_elat_map.get(sid);
	var s_point = new BMap.Point(slng,slat);
	var e_point = new BMap.Point(elng,elat);
	//showRoadSetion(sid,s_point, e_point, color, weight);
	var data = getSetionByID(sid);
	var points = data.line_points;
	drawRoadByValueID(points,color,weight,sid);
}

// 返回id和line_points
function getSetionByID(id){
	var dataSetion;
	$.ajax({
		url:"getroad/getSingleLinePoint",
		type:'post',
		data:{
			id:id
		},
		dataType:"json",
		async:false,
		success:function(data){
			dataSetion = data;
		}
	})
	return dataSetion;
}

/*******************************测试路段匹配路线**********************************/

var setion_id_line_map = new Map(); // 路段ID及点集
function testMateMain(){
	// 所有路段的点集放入map：setion_id_line_map
	getSetionList();
	// 选出路线的每段的点集，与所有的路段的点集匹配
	getRailwayPoints();
	// 匹配成功的，在pub_road_setion表增加所属G104线路路段ID（匹配内部实现）
}

// 选出路线的每段的点集，与所有的路段的点集匹配
function getRailwayPoints(){
	$.ajax({
		url:'point/getAllRoadRailway',
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			for(var i=0;i<data.length;i++){
				/*var sp = new BMap.Point(data[i].startLocation.split(",")[0],data[i].startLocation.split(",")[1]);
				var ep = new BMap.Point(data[i].endLocation.split(",")[0],data[i].endLocation.split(",")[1]);
				getPoints(sp, ep,data[i].id,i);
				getPoints(ep, sp,data[i].id,i);*/
				var line_point = data[i].line_point.split(";");
				var line_point_back = data[i].line_point_back.split(";");
				mateEverypoint(point_array,data[i].id);	// 正向匹配
				mateEverypoint(line_point_back,data[i].id);	// 反向匹配
			}			
		}
	})
}

// 获取两点间所有点集
/*function getPoints(sp, ep,railway_id,i) {
	var  start  = sp.lat+","+sp.lng;
	var  end  = ep.lat+","+ep.lng;
	var url = 'http://api.map.baidu.com/direction/v1?mode=driving&origin='
			+start+'&destination='
			+end+'&origin_region=浙江&destination_region=浙江'
			+'&tactics=10&output=json&ak=y9A9WkT3Y1jadGiMZwLEN7itWTS9oaQW';
	getTiny(url,railway_id,i);// 12-最短 11-常规 10-不走高速 13-躲避拥堵
}*/

// 得到线路的每个点
/*function getTiny(url,railway_id,i){
	var list = [];
	$.ajax({
		 type:"POST",
		 dataType:'jsonp',
		 url : url,
		 async: false,
		 success : function(data){
			 var miles = data.result.routes[0].distance;
			 var start1 = data.result.routes[0]
			 var arr = data.result.routes[0].steps;
			 for(var i=0;i<arr.length;i++){
				 var point_array = arr[i].path.split(";");
				 for(var j=0;j<point_array.length;j++){
					 list.push(point_array[j]);
				 }
			 }
			 mateEverypoint(list,railway_id);
		 }
	})	
}*/

var num_mateEverypoint = 1;
// 将路段的每个起始点，去和计算得到的点集匹配
function mateEverypoint(arrPois,railway_id){

	console.log(num_mateEverypoint+"/54");
	num_mateEverypoint++;
	
	var keys = setion_id_sp_map.keys();	
	for(var i=0,size = keys.length;i<size;i++){	
		console.log(i+"/"+size);
		var id = keys[i];
		 var data = setion_id_line_map.get(id); // 获得该路段的所有点集，map中已存
		 var points = data.split(";");
		 var ratio = sameRatio(points,arrPois);
		 if(ratio>0.5){
			 updateRailwayID(railway_id,keys[i]);			 
		 }	
	}
		/*var all_in_flag = 0;
		var nn = setion_id_sp_map.get(keys[i]).lng+"";
		var aa = setion_id_sp_map.get(keys[i]).lat+"";
		for(var j=0;j<arrPois.length;j++){			
			if(all_in_flag == 0){				
				var n = arrPois[j].split(",")[0];
				var a = arrPois[j].split(",")[1];
				var dis = get_distance(a,n,aa,nn);
				if(dis<0.1){
				//if(nn == n	&& aa == a){
					//setMarker(setion_id_sp_map.get(keys[j]), "20站点");
					//setMarker(arrPois[i], "路线");
					all_in_flag = 1;
					break;
				}
			}
		}
		 if(all_in_flag == 1){ // 如果首点匹配
			var nnn = setion_id_ep_map.get(keys[i]).lng+"";
			var aaa = setion_id_ep_map.get(keys[i]).lat+"";
			for(var j=0;j<arrPois.length;j++){		
					var n = arrPois[j].split(",")[0];
					var a = arrPois[j].split(",")[1];
					var dis = get_distance(a,n,aaa,nnn);
					if(dis<0.1){
					//if(nnn == n	&& aaa == a){
						all_in_flag = 2;
						break;
				}
			}
		 }
		if(all_in_flag == 2){	// 如果尾点也匹配
				updateRailwayID(railway_id,keys[i]);				
		}
	}*/	
}

// 将路段点集和路线点集进行匹配，返回重合度（0-1）
function sameRatio(setion_points,route_points){
	// 总的路段点的数量
	var num_setion = setion_points.length;
	var num_same = 0;
	// 比较两个有多少点，在100米内
	for(var i=0;i<num_setion;i++){
		var setion_lng = setion_points[i].split(",")[0];
		var setion_lat = setion_points[i].split(",")[1];
		for(var j=0,size=route_points.length;j<size;j++){
			var route_lng = route_points[j].split(",")[0];
			var route_lat = route_points[j].split(",")[1];
			var dis = get_distance(setion_lat,setion_lng,route_lat,route_lng);
			if(dis<0.1){
				num_same++;
				break;
			}
		}
	}
	var ratio = num_same/num_setion;
	return ratio;
}

// 查到匹配路段，将数据更新到pub_road_setion
function updateRailwayID(railway_id,setion_id){
	$.ajax({
		url:'getroad/updateRailwayID',
		type:'post',
		async:false,
		data:{
			railway_id:railway_id,
			setion_id:setion_id
		}
	})
}

// 得到所有路段收尾点，放入两个map
function getSetionList(){
	$.ajax({
		url:'getroad/getroutInfo',
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			for(var i=0;i<data.length;i++){
				var sp = new BMap.Point(data[i].StartLongtude,data[i].StartLatitude);
				var ep = new BMap.Point(data[i].EndLongtude,data[i].EndLatitude);
				setion_id_line_map.put(data[i].id,data[i].line_points);
			}
		}
	})
}

/*******************************画出已经匹配的路段**********************************/

// 画匹配路段，利用点集
function drawMate(){
	// 获取roadRailway_id不为0的路段集合
	$.ajax({
		url:'getroad/getroutMated',
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			for(var i=0;i<data.length;i++){
				var points = data[i].line_points;
				drawRoadByValue(points,"black",2);
			}
		}
	})	
}

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
				drawRoadByValue(points,"blue",6);
			}			
		}
	})
}

// 画出全部路线，利用点集
function drawAllRoute(){
	$.ajax({
		url:'point/getAllRoadRailwayLinePoint',
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			for(var i=0;i<data.length;i++){
				var points = data[i].line_point;
				drawRoadByValue(points,"red",4);
			}			
		}
	})
}

/*******************************更新路段数据**********************************/

// 更新路段的数据，2017-5-6 09:48:29,临时任务，调整随机字段值
function updateSection(){
	// 读取所有setion
	$.ajax({
		url:"getroad/updateSection",
		async:false,
		type:'post'
	})
	// 按策略更新每个setion
}

/*******************************更新站点线路属性**********************************/

// 更新站点线路的平均速度 pub_road_route
function updateRoute(){
	$.ajax({
		url:"getroad/updateRoute",
		async:false,
		type:'post'
	})
}

/*******************************更新20站点归属**********************************/

// 临时任务，GIS画出16线路，20站点，找到站点所属的线路
function drawTwenty(){
	// 画出站所有线路，首位点加marker
	/*$.ajax({
		url:'point/getAllRoadRailway',
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			for(var i=0;i<data.length;i++){
				var sp = new BMap.Point(data[i].startLocation.split(",")[0],data[i].startLocation.split(",")[1]);
				var ep = new BMap.Point(data[i].endLocation.split(",")[0],data[i].endLocation.split(",")[1]);
				showRoad(sp,ep,"red",4,data[i].railwayName,data[i].id);
				setMarker(sp, data[i].railwayName);
				setMarker(ep, data[i].railwayName);
			}			
		}
	})*/
	// 画出所有站点，站点名字显示在label上
	$.ajax({
		url:'getroad/getAllSite',
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			for(var i=0;i<data.length;i++){
				var sp = new BMap.Point(data[i].longitude,data[i].latitude);
				setMarker(sp, data[i].orgCode);
			}			
		}
	})
}

/*******************************获得线路路段的line_point**********************************/

// 更新54线路路段的点集
function updateRailwayLinePoint(){
	// 获取路段的首尾点
	// 百度API计算路径点
	// 更新数据库
	$.ajax({
		url:'point/getAllRoadRailway',
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			for(var i=0;i<data.length;i++){
				if(data[i].line_points == null){	// 只更新路段点集为空的
					var start = data[i].startLocation.split(",")[1]+","+data[i].startLocation.split(",")[0];
					var end = data[i].endLocation.split(",")[1]+","+data[i].endLocation.split(",")[0];
					var url = 'http://api.map.baidu.com/direction/v1?mode=driving&origin='
						+start+'&destination='
						+end+'&origin_region=浙江&destination_region=浙江'
						+'&tactics=10&output=json&ak=y9A9WkT3Y1jadGiMZwLEN7itWTS9oaQW';
					//apiGetLinePointRailway(url,data[i].id,0);
					url = 'http://api.map.baidu.com/direction/v1?mode=driving&origin='
						+end+'&destination='
						+start+'&origin_region=浙江&destination_region=浙江'
						+'&tactics=10&output=json&ak=y9A9WkT3Y1jadGiMZwLEN7itWTS9oaQW';
					apiGetLinePointRailway(url,data[i].id,1);
				}				
			}			
		}
	})
}

// 利用百度API获得线路点集，flag=0,正向；flag=1,反向
function apiGetLinePointRailway(url,id,flag){
	$.ajax({
		 type:"POST",
		 dataType:'jsonp',
		 url : url,
		 async: false,
		 success : function(data){
			 var miles = data.result.routes[0].distance;
			 var start1 = data.result.routes[0]
			 var arr = data.result.routes[0].steps;
			 var str = "";
			 for(var i=0,size = arr.length;i<size;i++){
				 var line_points = arr[i].path;
				 if(i != size-1){
					 str+= line_points +";";	//	本来是，和；因为转JSON出问题，用了-和#
				 }else{
					 str+= line_points;
				 }
			 };
			 if(flag == 0){
				 updateLabelRailway(id,str);	
			 }else if(flag == 1){
				 updateLabelRailwayBack(id,str);	
			 }			 		 
		 }
	});
}

//更新线路的正向路线点集
function updateLabelRailway(id, str) {
	$.ajax({
		url : "point/updateLinePointRailway",
		type : 'post',
		dataType : "json",
		async : false,
		data : {
			id : id,
			line_point : str
		}
	})
}

// 更新线路的反向路线点集
function updateLabelRailwayBack(id, str) {
	$.ajax({
		url : "point/updateLinePointRailwayBack",
		type : 'post',
		dataType : "json",
		async : false,
		data : {
			id : id,
			line_point_back : str
		}
	})
}
/*******************************两条路显示**********************************/

var line_207_104_10 =[];
var line_207_104_12 =[];
var line_410_409_11 =[];
var line_410_409_12 =[];
function liangtiaolu(){
	var start = "28.409488,119.963083";
	var end = "28.143123,120.329787";
	liangtiaolu2(start,end,10,"black",4);
	liangtiaolu2(start,end,12,"red",4);
	
	start = "28.588493,121.319502";
	end = "28.584663,121.409638";
	//liangtiaolu2(start,end,11,"black",4);
	//liangtiaolu2(start,end,12,"red",4);
	
	start = "28.588493,121.319502";
	end = "28.544087,121.336067";
	liangtiaolu2(start,end,12,"red",4);
}

function liangtiaolu2(start,end,type,color,weight){
	
	var url = 'http://api.map.baidu.com/direction/v1?mode=driving&origin='
		+start+'&destination='
		+end+'&origin_region=浙江&destination_region=浙江'
		+'&tactics='
		+type+'&output=json&ak=y9A9WkT3Y1jadGiMZwLEN7itWTS9oaQW';
	$.ajax({
		 type:"POST",
		 dataType:'jsonp',
		 url : url,
		 async: false,
		 success : function(data){
			 var miles = data.result.routes[0].distance;
			 var start1 = data.result.routes[0]
			 var arr = data.result.routes[0].steps;
			 var str = "";
			 for(var i=0,size = arr.length;i<size;i++){
				 var line_points = arr[i].path;
				 if(i != size-1){
					 str+= line_points +";";	//	本来是，和；因为转JSON出问题，用了-和#
				 }else{
					 str+= line_points;
				 }
			 }
			 drawRoadByValue(str,color,weight);
		 }
	})	
}


/*******************************工具函数**********************************/

/**
 * 显示标签
 * @param point 位置
 * @param text 内容
 */
function setMarker(bPoint, text) {
	var marker = new BMap.Marker(bPoint);
	var opts = {
		position : bPoint, // 指定文本标注所在的地理位置
		offset : new BMap.Size(-18,-25)
	// 设置文本偏移量
	}
	if(text != null){
		var label = new BMap.Label(text, opts); // 创建文本标注对象
		label.setStyle({
			color : "black",
			fontSize : "16px",
			//border:"1px", 
			borderColor:"#BEBEBE",
			//fontWeight :"bold",
			//backgroundColor :"0",
			//height : "20px",
			//lineHeight : "20px",
			fontFamily : "微软雅黑",
		});
		marker.setLabel(label);
	}
	map.addOverlay(marker);	
}

/**
 * 用路径搜索，显示路线
 * @param sp
 * @param ep
 * @param color
 * @param weight
 */
function showRoad(sp,ep,color,weight,name,id){
	var DrvUtil = new BMap.DrivingRoute('浙江', {
		policy:BMAP_DRIVING_POLICY_AVOID_HIGHWAYS,
		onSearchComplete : function(res) {
			if (DrvUtil.getStatus() == BMAP_STATUS_SUCCESS) {
				var plan = res.getPlan(0);
				var arrPois = [];
				for (var j = 0; j < plan.getNumRoutes(); j++) {
					var route = plan.getRoute(j);
					arrPois = arrPois.concat(route.getPath());
					//map.setViewport(arrPois);
				}
				
				var overlay = new BMap.Polyline(arrPois, {
					strokeColor : color,
					strokeWeight : weight,
					strokeOpacity : 1
				});
				overlay.addEventListener("click",function(e){
					alert(name+"_"+id);
				});
				map.addOverlay(overlay);
			}
		}
	});
	DrvUtil.disableAutoViewport();
	DrvUtil.search(sp, ep);
}

/**
 * 根据多个百度点集进行画线
 * @param point_arr -- "120.133746,29.902778;119.752002,29.682122;119.802594,29.561558"
 * @param color -- 'black'
 * @param weight -- 4
 */
function drawRoadByValue(point_arr,color,weight){
	if(point_arr == null){
		console.log("该路段无数据");
		return;
	}
	var arrPois = [];
	var point_arr_list = point_arr.split(";");
	for(var i=0;i<point_arr_list.length;i++){
		var sp = point_arr_list[i].split(",")[0];
		var ep = point_arr_list[i].split(",")[1];
		var point = new BMap.Point(sp,ep);
		arrPois.push(point);
	}
	var overlay = new BMap.Polyline(arrPois, {
		strokeColor : color,
		strokeWeight : weight,
		strokeOpacity : 1
	});
	map.addOverlay(overlay);
}

function drawRoadByValueID(point_arr,color,weight,setionId){
	if(point_arr == null){
		console.log("该路段无数据："+setionId);
		return;
	}
	var arrPois = [];
	var point_arr_list = point_arr.split(";");
	for(var i=0;i<point_arr_list.length;i++){
		var sp = point_arr_list[i].split(",")[0];
		var ep = point_arr_list[i].split(",")[1];
		var point = new BMap.Point(sp,ep);
		arrPois.push(point);
	}
	var overlay = new BMap.Polyline(arrPois, {
		strokeColor : color,
		strokeWeight : weight,
		strokeOpacity : 0.3
	});
	overlay.addEventListener("click",function(e){
		alert(setionId);
	})
	map.addOverlay(overlay);
}

/**
 * 显示两个点的线路
 * @param startPoint
 * @param endPoint
 * @param color
 * @param weight
 */
function showRoadSetion(sid,s_point, e_point, color, weight) {
	var DrvUtil = new BMap.DrivingRoute('浙江', {
		onSearchComplete : function(res) {
			if (DrvUtil.getStatus() == BMAP_STATUS_SUCCESS) {
				var plan = res.getPlan(0);
				var arrPois = [];
				for (var j = 0; j < plan.getNumRoutes(); j++) {
					var route = plan.getRoute(j);
					arrPois = arrPois.concat(route.getPath());
					//map.setViewport(arrPois);
				}
				
				var overlay = new BMap.Polyline(arrPois, {
					strokeColor : color,
					strokeWeight : weight,
					strokeOpacity : 1
				});
				map.addOverlay(overlay);
				id_overlay_map.put(sid,overlay);
				console.log("路段："+sid);
				bigDataSetionColor(sid);	// 大数据画颜色
			}
		}
	});
	DrvUtil.disableAutoViewport();
	DrvUtil.search(s_point, e_point);
}

// 弧度
function Rad(d){	
    return d * Math.PI / 180.0;
 }

// 两经纬度坐标间距离，单位公里
function get_distance(lat1,lng1,lat2,lng2){	 	
    var radLat1 = Rad(lat1);
    var radLat2 = Rad(lat2);
    var a = radLat1 - radLat2;
    var  b = Rad(lng1) - Rad(lng2);
    var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
    s = s *6378.137 ;// EARTH_RADIUS;
    s = Math.round(s * 10000) / 10000; //输出为公里
    //s=s.toFixed(4);
    return s;
}

// map类
function Map() {  
    this.elements = new Array();  
    //获取MAP元素个数  
    this.size = function() {  
        return this.elements.length;  
    };  
    //判断MAP是否为空  
    this.isEmpty = function() {  
        return (this.elements.length < 1);  
    };  
    //删除MAP所有元素  
    this.clear = function() {  
        this.elements = new Array();  
    };  
    //向MAP中增加元素（key, value)   
    this.put = function(_key, _value) {  
        this.elements.push( {  
            key : _key,  
            value : _value  
        });  
    };  
    //删除指定KEY的元素，成功返回True，失败返回False  
    this.remove = function(_key) {  
        var bln = false;  
        try {  
            for (i = 0; i < this.elements.length; i++) {  
                if (this.elements[i].key == _key) {  
                    this.elements.splice(i, 1);  
                    return true;  
                }  
            }  
        } catch (e) {  
            bln = false;  
        }  
        return bln;  
    };  
    //获取指定KEY的元素值VALUE，失败返回NULL  
    this.get = function(_key) {  
        try {  
            for (i = 0; i < this.elements.length; i++) {  
                if (this.elements[i].key == _key) {  
                    return this.elements[i].value;  
                }  
            }  
        } catch (e) {  
            return null;  
        }  
    };  
    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL  
    this.element = function(_index) {  
        if (_index < 0 || _index >= this.elements.length) {  
            return null;  
        }  
        return this.elements[_index];  
    };  
    //判断MAP中是否含有指定KEY的元素  
    this.containsKey = function(_key) {  
        var bln = false;  
        try {  
            for (i = 0; i < this.elements.length; i++) {  
                if (this.elements[i].key == _key) {  
                    bln = true;  
                }  
            }  
        } catch (e) {  
            bln = false;  
        }  
        return bln;  
    };  
    //判断MAP中是否含有指定VALUE的元素  
    this.containsValue = function(_value) {  
        var bln = false;  
        try {  
            for (i = 0; i < this.elements.length; i++) {  
                if (this.elements[i].value == _value) {  
                    bln = true;  
                }  
            }  
        } catch (e) {  
            bln = false;  
        }  
        return bln;  
    };  
    //获取MAP中所有VALUE的数组（ARRAY）  
    this.values = function() {  
        var arr = new Array();  
        for (i = 0; i < this.elements.length; i++) {  
            arr.push(this.elements[i].value);  
        }  
        return arr;  
    };  
    //获取MAP中所有KEY的数组（ARRAY）  
    this.keys = function() {  
        var arr = new Array();  
        for (i = 0; i < this.elements.length; i++) {  
            arr.push(this.elements[i].key);  
        }  
        return arr;  
    };  
}