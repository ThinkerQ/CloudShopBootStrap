package com.guangxunet.shop.base.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:19:05 
* 类说明 
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class JsonResult {
	private Boolean success=false;
    private String message; 
    private Object result;
    
    public JsonResult(Boolean success, String msg){
        this.success = success;
        this.message = msg;
    }
    
    //如果使用一个参数的构造器，那么success默认值为false
    public JsonResult(String msg){
        this.message = msg;
    }

	public JsonResult(Boolean success, String message, Object result) {
		super();
		this.success = success;
		this.message = message;
		this.result = result;
	}
}
