package com.gyb.utils;

import org.apache.ibatis.session.RowBounds;

/**
 * 页面参数转换成mysql分页参数
 *
 * @author linwh
 */
public class PageUtil {
	
	public static RowBounds getRowBounds(int page, int size){
		
		return new RowBounds((page - 1) * size, size);
	}
	
	public static int getOffset(int page, int size){
		
		return (page-1)*size;
	}

}
