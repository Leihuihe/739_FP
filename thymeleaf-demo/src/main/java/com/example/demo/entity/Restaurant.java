package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Restaurant")
public class Restaurant extends BasicModel implements Serializable{

	@Id
	private Long rId;
	private String r_Imgurl;
	private String r_Name;
	private String r_Location;
	//private List<Comments> r_Comments;
}
