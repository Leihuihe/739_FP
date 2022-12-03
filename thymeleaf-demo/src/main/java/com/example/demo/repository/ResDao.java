package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.Restaurant;

@Repository
public class ResDao {
	
	private static final Object HASH_KEY = "Restaurant";
	
	@Autowired
	private RedisTemplate template;
	
	@Autowired
	private RedisKeyValueTemplate redisKVTemplate;
	
	public Restaurant save(Restaurant res)
	{
		Long random = (long) (Math.random()*1000000000);
		while(template.opsForHash().get(HASH_KEY, random)!=null)
		{
			random = (long) (Math.random()*1000000000);
		}
		res.setRId(random);
		template.opsForHash().put(HASH_KEY, res.getRId(), res);
		return res;		
	}
	
	public List<Restaurant> findALl()
	{
		return template.opsForHash().values(HASH_KEY);
	}
	
	public String deleteres(String rid)
	{
		template.opsForHash().delete(HASH_KEY, rid);
		return "Deleted";
	}

	
}
