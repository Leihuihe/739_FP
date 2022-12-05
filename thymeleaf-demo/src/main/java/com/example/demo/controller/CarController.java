package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Car;

import com.example.demo.repository.CarDao;


@RestController
@RequestMapping("/car")
public class CarController {

	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

	
	@Autowired
	private CarDao dao;
	
	@PostMapping(value = "/save", consumes= {"*/*"})
	public Car save(@RequestBody Car car)
	{
		System.out.println(car.toString());
		return dao.save(car);
	}
	
	@PostMapping("/changepass")
	//public String changePass(@PathVariable("id") String id, @PathVariable("pass") String pass)
	public String changePass(@RequestBody Car car)
	{
		return dao.updatePass(car);
	}
	
	@GetMapping()
	public List<Car> getAllcars()
	{
		return dao.findALl();
	}
	
	
	
	@GetMapping("/data/{id}")
	//@RequestMapping(value = "/data/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public Car findcar(@PathVariable String id)
	{
		Car car = null;
		System.out.println("get id: "+id);
		try {
			car = dao.findcarById(id);
		} catch (Exception e) {
			System.out.print("Not found");
		}
		if(car == null)
		{
			System.out.print("Not found");
		}
		
		return car;
	}
	
	@DeleteMapping("/{id}")
	public String deletecar(@PathVariable String id)
	{
		return dao.deletecar(id);
	}
}