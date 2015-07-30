package com.gyb;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.gyb.utils.MongoDBUtil;


public class TestMongoDB {
	private String firstName;
	private String lastName;
	
	TestMongoDB(String firstName, String lastName ){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static void main(String[] args){
		TestMongoDB test = new TestMongoDB("John", "Connor");
		MongoDBUtil.getMongoOperation().insert(test);
		TestMongoDB stored = MongoDBUtil.getMongoOperation().findOne(new Query(Criteria.where("firstName").is(test.getFirstName())),
				TestMongoDB.class);
		if (stored != null)
		System.out.println(stored.getFirstName() + " " + stored.getLastName());
	}
}
