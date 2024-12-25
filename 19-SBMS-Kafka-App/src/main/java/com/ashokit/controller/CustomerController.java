package com.ashokit.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.model.Customer;
import com.ashokit.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(value = "/saveCustomer")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer)
	{
		return new ResponseEntity<>(customerService.saveCustomer(customer),HttpStatus.OK);
	}
}
