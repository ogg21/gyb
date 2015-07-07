package com.gyb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
/**
 * 短信发送工具类
 * @author Shunzhong.Huang
 *
 */
public class SMSUtil {
	
	private static String filePath = "/conf/sms.properties";
	private static String IP = "";
	private static String USERNAME = "";
	private static String USERPASS = "";
	
    static{
    		 Properties p = new Properties();  
    	     try {  
    	            InputStream in = SMSUtil.class.getResourceAsStream(filePath);//这里有人用new FileInputStream(fileName),不过这种方式找不到配置文件。有人说是在classes下，我调过了，不行。  
    	            p.load(in);  
    	            IP = p.getProperty("IP");
    	            USERNAME = p.getProperty("UserName");
    	            USERPASS = p.getProperty("UserPass");
    	            in.close(); 
    	     }catch(IOException e){
    	    	 e.printStackTrace();
    	     }
	
    }
	
	public static boolean send(String content, String mobile) {
		try {
			content = URLEncoder.encode(content, "GBK");
			content = URLEncoder.encode(content, "GBK");
			System.out.println(IP + "?" + "UserName=" + USERNAME + "&UserPass="
					+ USERPASS + "&Content=" + content + "&Mobile=" + mobile);
			String result = httpGetSend(IP + "?" + "UserName=" + USERNAME
					+ "&UserPass=" + USERPASS + "&Content=" + content
					+ "&Mobile=" + mobile, "GBK");
			System.out.print(result);

			result = result.split(",")[0];
			if ("00".equals(result) || "03".equals(result)) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static String httpGetSend(String snedUrl, String encoded)
			throws Exception {
		String urlPath = snedUrl;
		StringBuffer sbf = new StringBuffer("");
		BufferedReader reader = null;
		HttpURLConnection uc = null;

		try {
			URL url = new URL(urlPath);
			uc = (HttpURLConnection) url.openConnection();
			uc.setConnectTimeout(30000);
			uc.setReadTimeout(30000);
			uc.setRequestMethod("GET");
			uc.setDoOutput(true);
			uc.setDoInput(true);

			uc.connect();
			reader = new BufferedReader(new InputStreamReader(
					uc.getInputStream())); // 读取服务器响应信息
			String line;
			while ((line = reader.readLine()) != null) {
				sbf.append(line);
			}
			reader.close();
			uc.disconnect();
			return sbf.toString();
		} finally {
			try {
				reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		SMSUtil.send("你的手机验证码是195624，5分钟内输入有效", "13560327456");
	}
}
