package com.gyb.utils;

import com.gyb.message.Response;
import com.gyb.message.Status;


/**
 * 用于生成接口调用响应消息体
 *
 * @author linwh
 */
public class ResponseUtil {
	
	/**
	 * 请求成功，返回数据消息
	 * 
	 * @param object 数据
	 * @return
	 */
	public static String data(Object object){
		
		Response resp = new Response(Status.SUCCESS, null, object);
		
		return JacksonUtil.toJson(resp);
	}
	

	public static String data(String msg,Object object){
		
		Response resp = new Response(Status.SUCCESS, msg, object);
		
		return JacksonUtil.toJson(resp);
	}
	
	
	/**
	 * 请求成功
	 * 
	 */
	public static String success(){
		
		Response resp = new Response(Status.SUCCESS, null, null);
		
		return JacksonUtil.toJson(resp);
	}
	
	
	/**
	 * 请求出错
	 * 
	 * @return
	 */
	public static String error(){
		
		Response resp = new Response(Status.ERROR, null);
		
		return JacksonUtil.toJson(resp);
	}
	
	/**
	 * 请求出错，返回错误消息
	 * 
	 * @param msg 消息内容
	 * @return
	 */
	public static String error(String msg){
		
		Response resp = new Response(Status.ERROR, msg);
		
		return JacksonUtil.toJson(resp);
	}
	
	private static final String unkownVersion = "未知版本号";
	
	/**
	 * 版本号错误
	 * 
	 * @return
	 */
	public static String unknownVersion(){
		
		Response resp = new Response(Status.ERROR, unkownVersion);
		
		return JacksonUtil.toJson(resp);
	}

}
