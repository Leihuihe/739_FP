package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.entity.BasicModel;
import com.example.demo.repository.AccountDao;


@RestController
@RequestMapping("/account")
public class AccountController {

	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

	
	@Autowired
	private AccountDao dao;
	
	@PostMapping(value = "/save", consumes= {"*/*"})
	public BasicModel save(@RequestBody Account account)
	{
		System.out.println(account.toString());
		return dao.save(account);
	}
	
	@PostMapping("/changepass")
	//public String changePass(@PathVariable("id") String id, @PathVariable("pass") String pass)
	public String changePass(@RequestBody Account account)
	{
		return dao.updatePass(account);
	}
	
	@GetMapping()
	public List<Account> getAllaccounts()
	{
		return dao.findALl();
	}
	
	@GetMapping("/login")
	@ResponseBody
	public BasicModel login(@RequestParam String id, @RequestParam String pass, HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Account account = new Account();
		System.out.println("get id: "+id);
		try {
			account = dao.findaccountById(id);
		} catch (Exception e) {
			System.out.print("Not found");
		}
		if(account == null)
		{
			Account acc = new Account();
			System.out.print("Account doesn't exist");
			acc.setMessage("Account doesn't exist");
			return acc;
		}
		else if(!account.getPassword().equals(pass))
		{
			System.out.print("Wrong Password");
			account.setMessage("Wrong Password");
		}
		else
		{
			System.out.print("Login Success");
			account.setMessage("Success");
		}
		
		return account;
	}
	
	@GetMapping("/signup")
	@ResponseBody
	public BasicModel signup(@RequestParam String email,@RequestParam String name, @RequestParam String pass, @RequestParam String check_pass, HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Account account = new Account();
		if(email == null || pass == null)
		{
			Account acc = new Account();
			acc.setMessage("Please enter your ");
			return acc;
		}
		else if(!pass.equals(check_pass))
		{
			Account acc = new Account();
			acc.setMessage("Confirm password doesn't match");
			return acc;
		}
		System.out.println("get id: "+email);
		try {
			account = dao.findaccountById(email);
		} catch (Exception e) {
			System.out.print("Not found");
		}
		if(account != null)
		{
			Account acc = new Account();
			System.out.print("Account already exist");
			acc.setMessage("Account already exist");
			return acc;
		}
		account = new Account();
		account.setuID(email);
		account.setU_Name(name);
		account.setPassword(pass);
		account.setMessage("Created a new account: "+account.getuID());
		dao.save(account);
		System.out.print("Create Success");
		return account;
	}
	
	
	@GetMapping("/data")
	@ResponseBody
	//@RequestMapping(value = "/data/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	//public Account findaccount(@PathVariable String id)
	public BasicModel findaccount(@RequestParam String id, HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Account account = new Account();
		System.out.println("get id: "+id);
		try {
			account = dao.findaccountById(id);
		} catch (Exception e) {
			System.out.print("Not found");
		}
		if(account == null)
		{
			Account acc = new Account();
			System.out.print("Not found");
			acc.setMessage("Not found");
			return acc;
		}
		account.setMessage("Success");
		return account;
	}
	
	@DeleteMapping("/{id}")
	public String deleteaccount(@PathVariable String id)
	{
		return dao.deleteaccount(id);
	}
}
