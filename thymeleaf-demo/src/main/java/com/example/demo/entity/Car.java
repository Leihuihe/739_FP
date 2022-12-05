package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Car")
public class Car implements Serializable{

	@Id
	private String uID;
	@Indexed
	private String u_CarName;
	private String Model;
	private String u_Make;
	private String u_Rental;
	private String u_Price;
	private String u_Customer;
	private String u_Rate;
	private String u_Review;
	
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setModel(Object model2) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}