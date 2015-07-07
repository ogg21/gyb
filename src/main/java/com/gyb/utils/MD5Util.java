package com.gyb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *加密工具类
 * 
 */

public class MD5Util {

	public static String encrypt(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			StringBuilder sb = new StringBuilder("");
			String temp = "";
			for (int i = 0; i < b.length; i++) {
				temp = Integer.toHexString(b[i] & 0xFF);
				if (temp.length() == 1) {
					sb.append("0");
				}
				sb.append(temp);
			}
			temp = null;

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
}
