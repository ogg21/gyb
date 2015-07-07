package com.gyb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 位置分析工具类
 * @author Shunzhong.Huang
 *
 */
public class PositionAnalysisUtil {
	
	private static String ak = "GeiA0GCcq7GVK8T7PyaZecM2";
	
	private static List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
	
	public static List<HashMap<String, Object>> getList() {
		return list;
	}

	/**
	 * 更具经纬度获取实际地址
	 * @param latitude 纬度
	 * @param longitude 经度
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getActualPosition(String latitude, String longitude)
			throws IOException {
		URL url = new URL("http://api.map.baidu.com/geocoder?ak=" + ak
				+ "&callback=renderReverse&location=" + latitude + "," + longitude
				+ "&output=json");
		URLConnection connection = url.openConnection();
		/**
		 * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
		 * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
		 */
		connection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(
				connection.getOutputStream(), "utf-8");
		// remember to clean up
		out.flush();
		out.close();
		// 一旦发送成功，用以下方法就可以得到服务器的回应：
		String res;
		InputStream l_urlStream;
		l_urlStream = connection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				l_urlStream, "UTF-8"));
		StringBuilder sb = new StringBuilder("");
		while ((res = in.readLine()) != null) {
			sb.append(res.trim());
		}
		String str = sb.toString();
		System.out.println(str);
		Map<String, String> map = null;
		if (!"".equals(str)) {
			map = new HashMap<String, String>();
			int addStart = str.indexOf("formatted_address\":");
			int addEnd = str.indexOf("\",\"business");
			int provinceStart = str.indexOf("province\":");
			int provinceEnd = str.indexOf("\",\"street\":");
			int cityStart = str.indexOf("city\":");
			int cityEnd = str.indexOf("\",\"direction");
			int districtStart = str.indexOf("district\":");
			int districtEnd = str.indexOf("\",\"province\":");
			if(addStart>0 && addEnd>0) {
				String address = str.substring(addStart + 20, addEnd);
				map.put("address", address);
			}
			if(provinceStart>0 && provinceEnd>0){
				String province = str.substring(provinceStart+11, provinceEnd);
				map.put("province", province);
			}
			if(cityStart>0 && cityEnd>0){
				String city = str.substring(cityStart+7, cityEnd);
				map.put("city", city);
			}
			if(districtStart>0 && districtEnd>0){
				String district = str.substring(districtStart+11, districtEnd);
				map.put("district", district);
			}
			if(map.size()>0){
				return map;
			}
		}
		return null;

	}
	
//	public static void main(String[] args) throws IOException {
//		Map<String, String> json = PositionAnalysisUtil.getActualPosition("23.169388", "113.456661");
//		System.out.println("address :" + json.get("address"));
//		System.out.println("province :" + json.get("province"));
//		System.out.println("city :" + json.get("city"));
//		System.out.println("district :" + json.get("district"));
//	}
}
