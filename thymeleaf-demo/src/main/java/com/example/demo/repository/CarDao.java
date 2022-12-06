package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.PartialUpdate;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Car;

@Repository
public class CarDao {
	
	private static final Object HASH_KEY = "Car";
	
	
	@Autowired
	private RedisTemplate template;
	
	@Autowired
	private RedisKeyValueTemplate redisKVTemplate;
	
	public String updatePass(Car car)
	{
//		PartialUpdate<Car> update = new PartialUpdate<Car>(id, Car.class).set("password", pass);
//		redisKVTemplate.update(update);
		Car newAcc = (Car) template.opsForHash().get(HASH_KEY, car.getuID());
		newAcc.setModel(car.getModel());
		template.opsForHash().put(HASH_KEY, newAcc.getuID(), newAcc);
		return newAcc.getuID()+" New Model: "+newAcc.getModel();
	}
	
	
	public Car save(Car car)
	{
		template.opsForHash().put(HASH_KEY, car.getuID(), car);
		return car;
	}
	
	public List<Car> findALl()
	{
		return template.opsForHash().values(HASH_KEY);
	}
	
	public Car findcarById(String id)
	{
		return (Car) template.opsForHash().get(HASH_KEY, id);
	}
	
	public Car findcarByName(String name)
	{
		return (Car) template.opsForHash().get(HASH_KEY, name);
	}
	
	public String deletecar(String uid)
	{
		template.opsForHash().delete(HASH_KEY, uid);
		return "Deleted";
	}
}