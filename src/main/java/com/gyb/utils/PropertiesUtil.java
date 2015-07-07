package com.gyb.utils;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 加载配置文件
 *
 */
public class PropertiesUtil {

	private static final String BUNDLE_NAME = "conf/config";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    PropertiesUtil() {
    }
  /**
   * 得到属性的字符串值
   * @param key
   * @return String
   */
    public static String getString(final String key) {
        try {
           return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
          return '!' + key + '!';
       }
    }
    
    
}
