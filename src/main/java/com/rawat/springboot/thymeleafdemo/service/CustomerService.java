package com.rawat.springboot.thymeleafdemo.service;

import java.util.List;

import com.rawat.springboot.thymeleafdemo.entity.Customer;
import com.rawat.springboot.thymeleafdemo.entity.Order;

public interface CustomerService {

	public List<Customer> findAll();
	
	public Customer findById(int theId);
	
	public void save(Customer theCustomer);
	
	public void deleteById(int theId);
	
	public List<Order> findCustomerOrders(int theId);
	
	public Customer findByUniqueCustomer(String theUnique);
	
	
}
