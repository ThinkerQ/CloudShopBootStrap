<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云购商城->收货地址</title>
<script type="text/javascript" src="/js/jquery/jquery-2.1.3.js"></script>
<script src="/js/system/address.js" type="text/javascript"></script>


</head>
<body>
	<div class="row">
		<span>收货地址:</span> 
		<select style="WIDTH: 80px" id="province" name="provinceName" >
		    <option>选择省份</option>
		    <c:forEach items="${provinceList}" var="province">
				  <option value="${province.provinceId}">${province.province}</option>    
		    </c:forEach>
		</select> 
		
		
		  <select style="WIDTH: 80px" id="city" name="cityName" >
			<c:if test="${not empty businessFpAddress.cityName}">
			     <option value="${businessFpAddress.cityName}">${businessFpAddress.cityName}</option>
			</c:if>
			<c:if test="${empty businessFpAddress.cityName}">
			   <option value="">选择城市</option>
			</c:if>
		</select> 
		
		
		  <select style="WIDTH: 90px" id="area" name="districtName" >
			<c:if test="${not empty businessFpAddress.districtName}">
			   <option value="${businessFpAddress.districtName}">${businessFpAddress.districtName}</option>
			</c:if>
			<c:if test="${empty businessFpAddress.districtName}">
			    <option value="">选择区域</option>
			</c:if>
		</select>
		</div>

	<!-- 方案2 -->
	<br><br><br><br><br>
	<p>方案二-----------------------------------------------------------------------------------------<br>
	<tr>  
          <td>所在地:</td>  
           <td>  
               <select class="select" id="province_code" name="province_code" onchange="getCity()">  
                   <option value="">请选择</option>  
               </select>  

               <select class="select" id="city_code" name="city_code" onchange="getArea()">  
                   <option value="">请选择</option>  
               </select>  

               <select class="select" id="area_code" name="area_code">  
                   <option value="">请选择</option>  
               </select>  
           </td>  
    </tr>  
</body>
</html>