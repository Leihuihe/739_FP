package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.PartialUpdate;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;

@Repository
public class AccountDao {
	
	private static final Object HASH_KEY = "Account";
	
	
	@Autowired
	private RedisTemplate template;
	
	@Autowired
	private RedisKeyValueTemplate redisKVTemplate;
	
	public String updatePass(Account account)
	{
//		PartialUpdate<Account> update = new PartialUpdate<Account>(id, Account.class).set("password", pass);
//		redisKVTemplate.update(update);
		Account newAcc = (Account) template.opsForHash().get(HASH_KEY, account.getuID());
		newAcc.setPassword(account.getPassword());
		template.opsForHash().put(HASH_KEY, newAcc.getuID(), newAcc);
		return newAcc.getuID()+" New Password: "+newAcc.getPassword();
	}
	
	
	public Account save(Account account)
	{
		if(findaccountById(account.getuID()) != null)
		{
			return new Account();
		}
		else 
		{
			template.opsForHash().put(HASH_KEY, account.getuID(), account);
			return account;
		}
		
	}
	
	public Account login(Account account)
	{
		template.opsForHash().put(HASH_KEY, account.getuID(), account);
		return account;
	}
	
	public List<Account> findALl()
	{
		return template.opsForHash().values(HASH_KEY);
	}
	
	public Account findaccountById(String id)
	{
		return (Account) template.opsForHash().get(HASH_KEY, id);
	}
	
	public Account findaccountByName(String name)
	{
		return (Account) template.opsForHash().get(HASH_KEY, name);
	}
	
	public String deleteaccount(String uid)
	{
		template.opsForHash().delete(HASH_KEY, uid);
		return "Deleted";
	}
}
