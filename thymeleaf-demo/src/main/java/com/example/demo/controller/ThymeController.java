package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.entity.Restaurant;
import com.example.demo.repository.AccountDao;
import com.example.demo.repository.ResDao;

@Controller
public class ThymeController {

	@Autowired
	private AccountDao accdao;
	
	@Autowired
	private ResDao resdao;
	
	@GetMapping("/all")
	public String getAllacc(Model model)
	{
		List<Account> list = accdao.findALl();
		model.addAttribute("accounts", list);
		return "account_show";
	}
	
	@GetMapping("/input")
	public String inputUserData(Model model)
	{
		return "inputdata.html";
	}
	
	@GetMapping("/map")
	public String getMap(Model model)
	{
		return "mapindex";
	}
	
	@GetMapping("/rest")
	public String getRestaurant(Model model)
	{
		List<Restaurant> list = resdao.findALl();
		model.addAttribute("rests", list);
		return "restaurant";
	}
	
	@GetMapping("/rest/input")
	public String inputRes(Model model)
	{
		return "inputres.html";
	}
	
	@GetMapping("/acc/login")
	public String login(Model model)
	{
		return "login";
	}
	
	@GetMapping("/index")
	public String gotoIndex(Model model)
	{
		return "index";
	}
	
	@GetMapping("/acc")
	public String gotoAcc(Model model)
	{
		return "account";
	}
	
	
}
