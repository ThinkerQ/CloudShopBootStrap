/***
 * 省市区三级联动：
 * 三个下拉列表id分别是：province、city、area
 * @returns
 */
$(function(){
	//获取城市
	    $("#province").change(function () {//当点击省份的下拉选的时候，选中之后，城市进行联动
	        $.ajax({
	        　　url: "/Address/getCityByProvinceId.screen?id="+$("#province").val(),
	        　　type: "POST",
	        　　dataType:'json',
	        　　success:function(data){
	            $("#city").empty(); //清空下拉列表
	             $.each(data,function(i,item){
	                 $("#city").append(" <option value='" + item.cityId + "'>" + item.city + "</option>");
	             });
	        　　}
	        });
	    });
	
	    //获取区县
	    $("#city").change(function () {//当选择城市的下拉选的时候，区域进行联动
	        $.ajax({
	            　　url: "/Address/getAreaByCityId.screen?id="+$("#city").val(),
	            　　type: "POST",
	            　　dataType:'json',
	            　　success:function(data){
	                $("#area").empty(); //清空下拉列表
	                 $.each(data,function(i,item){
	                     $("#area").append(" <option value='" + item.id + "'>" + item.area + "</option>");
	                 });
	            　　}
	            });
	    });         
});


/*加载省下拉选*/  
$(function () {  
    $.ajax({  
    	url: "/Address/getAllProvinces.screen", 
        type: "post", 
        dataType:'json',
        success: function (data) {  
        	$.each(data,function(i,item){
                $('#province_code').append("<option value='" + item.provinceId + "' >" + item.province + "</option>");  
            });
        	
        },  
        error: function () {  
            alert("加载省失败");  
        }  
    });  
});  
/*加载市下拉选*/  
function getCity() {  
    var id = $("#province_code").val();  
    $("#city_code").empty();  
    $("#area_code").empty();  
    $.ajax({  
    	url: "/Address/getCityByProvinceId.screen",  
        type: "post",  
        data: {"id": id},  
        dataType:'json',
        success: function (data) {  
            $('#city_code').append("<option value='' selected='selected' >" + '请选择' + "</option>");  
            $('#area_code').append("<option value='' selected='selected' >" + '请选择' + "</option>");  
            for (var i = 0; i < data.length; i++) {  
                $('#city_code').append("<option value='" + data[i].cityId + "' >" + data[i].city + "</option>");  
            }  
        },  
        error: function () {  
            alert("加载市失败");  
        }  
    });  
}  
;  
/*加载地区下拉选*/  
function getArea() {  
    var id = $("#city_code").val();  
    $("#area_code").empty();  
    $.ajax({  
        type: "post",  
        url: "/Address/getAreaByCityId.screen",  
        data: {"id": id},  
        dataType:'json',
        success: function (data) {  
            $('#area_code').append("<option value='' selected='selected' >" + '请选择' + "</option>");  
            for (var i = 0; i < data.length; i++) {  
                $('#area_code').append("<option value='" + data[i].id + "' >" + data[i].area + "</option>");  
            }  
        },  
        error: function () {  
            alert("加载区失败");  
        }  
    });  
}  