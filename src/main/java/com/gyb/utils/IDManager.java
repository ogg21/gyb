package com.gyb.utils;

import java.util.UUID;

/**
 * ID生成工具类，生成32位随机字符串
 * @author Shunzhong.Huang
 *
 */
public class IDManager {

	public static String getUUID(){
		
		return UUID.randomUUID().toString().replace("-", "");
	}
}
