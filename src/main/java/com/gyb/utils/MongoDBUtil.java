package com.gyb.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;

public class MongoDBUtil {
	private volatile static MongoOperations mongoOperation;
	private static final String CONFIG_FILE_NAME = "conf/mongodb";
	private static final String KEY_HOST = "Host";
	private static final String KEY_PORT = "Port";
	private static final String KEY_DATABASE = "Database";
	private static final String KEY_USERNAME = "UserName";
	private static final String KEY_PASSWORD = "Password";

	private MongoDBUtil() {
	}

	public static MongoOperations getMongoOperation() {
		if (mongoOperation == null) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(
					CONFIG_FILE_NAME, Locale.getDefault());//获取配置文件“DataSource.properties”
			String hostAddr = resourceBundle.getString(KEY_HOST) + ":"
					+ resourceBundle.getString(KEY_PORT);
			UserCredentials userCredentials = new UserCredentials(
					resourceBundle.getString(KEY_USERNAME),
					resourceBundle.getString(KEY_PASSWORD));
			try {
				mongoOperation = new MongoTemplate(
						new SimpleMongoDbFactory(new Mongo(hostAddr),
								resourceBundle.getString(KEY_DATABASE),
								userCredentials));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mongoOperation;
	}

}
