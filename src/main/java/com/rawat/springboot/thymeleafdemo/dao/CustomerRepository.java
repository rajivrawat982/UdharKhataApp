package com.rawat.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rawat.springboot.thymeleafdemo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// that's it ... no need to write any code LOL!
	
	//here declaring our custom method behind the scene JPA do all magic and treat method by taking method name as SQL query.
	public List<Customer> findAllByOrderByLastNameAsc();
	
	public Customer findByUniqueCustomer(String theUnique);
	
}
