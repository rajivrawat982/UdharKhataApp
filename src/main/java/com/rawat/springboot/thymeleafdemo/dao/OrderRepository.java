package com.rawat.springboot.thymeleafdemo.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rawat.springboot.thymeleafdemo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	
}
