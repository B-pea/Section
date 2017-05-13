


























function baidu2gaode(point_str) {
	var gaode_point_str = "";
	var url = "http://restapi.amap.com/v3/assistant/coordinate/convert?key=a008837b3765ede6b03b6087022b0162&locations="
			+ point_str + "&coordsys=baidu";
	$.ajax({
		url : url,
		type : 'post',
		async : false,
		dataType : 'jsonp',
		success : function(data) {
			gaode_point_str = data.locations;
		}
	});
	return gaode_point_str;
}