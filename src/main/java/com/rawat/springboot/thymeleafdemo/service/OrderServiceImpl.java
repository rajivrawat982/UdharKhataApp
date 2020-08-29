package com.rawat.springboot.thymeleafdemo.service;

import org.springframework.stereotype.Service;

import com.rawat.springboot.thymeleafdemo.dao.OrderRepository;
import com.rawat.springboot.thymeleafdemo.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository orderRepository;
	
	public OrderServiceImpl(OrderRepository theOrderRepository) {
		orderRepository = theOrderRepository;
	}

	@Override
	public void saveOrder(Order theOrder) {
		orderRepository.save(theOrder);	
	}



}
