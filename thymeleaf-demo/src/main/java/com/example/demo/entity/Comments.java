package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Comments")
public class Comments extends BasicModel implements Serializable{

	@Id
	private String cId;
	//private Date c_date;
	private String c_Comment;
	private String uId;
	
}
