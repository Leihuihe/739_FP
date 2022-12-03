package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.entity.BasicModel;
import com.example.demo.entity.Restaurant;
import com.example.demo.repository.ResDao;

@RestController
@RequestMapping("/res")
public class ResController {
	
	@Autowired
	private ResDao dao;
	
	@PostMapping(value = "/save", consumes= {"*/*"})
	public BasicModel save(@RequestBody Restaurant res)
	{
		System.out.println(res.toString());
		return dao.save(res);
	}
	
	@GetMapping()
	public List<Restaurant> getAllaccounts()
	{
		return dao.findALl();
	}
	
	@DeleteMapping("/{id}")
	public String deleteres(@PathVariable String id)
	{
		return dao.deleteres(id);
	}

}
