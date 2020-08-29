package com.rawat.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rawat.springboot.thymeleafdemo.entity.Customer;
import com.rawat.springboot.thymeleafdemo.entity.Order;
import com.rawat.springboot.thymeleafdemo.service.CustomerService;
import com.rawat.springboot.thymeleafdemo.service.OrderService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	private CustomerService customerService;
	private OrderService orderService;
	
	
	@Autowired
	public CustomerController(CustomerService theCustomerService, OrderService theOrderService) {
		customerService = theCustomerService;
		orderService = theOrderService;

	}
	
	
	@RequestMapping("/")
	public String showHomePage() {
		return "customer/customer-home";
	}
	
	//request mapping to get all customer list
	@GetMapping("/list")
	public String getAllCustomers(Model theModel) {
		List<Customer> theCustomers = customerService.findAll();
		
		theModel.addAttribute("customers",theCustomers);
		
		return "customer/list-customer";
	}
	
	//request mapping to show form for adding new customer
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer/customer-form";
	}
	
	//post mapping to save the customer info in database
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.save(theCustomer);
		
		return "redirect:/customers/list";
	}
	
	//
	//this field to manage customer id while creating new orders. 
	private int customerfieldId;
	
	//request mapping to show form for new order
	@GetMapping("/showFormForNewOrder")
	public String showFormForNewOrder(@RequestParam("customerId") int theId, Model theModel) {
		
		Order theOrder = new Order();
		
		customerfieldId = theId;
		
		theModel.addAttribute("order", theOrder);
				
		return "customer/new-orderform";
	}
	
	@PostMapping("/saveOrder")
	public String saveNewOrder(@ModelAttribute("order") Order theOrder) {
		Customer customerUpdate = customerService.findById(customerfieldId);
		
		int newTotal = customerUpdate.getTotal() + theOrder.getAmount();
		customerUpdate.setTotal(newTotal);
		
		customerService.save(customerUpdate);
		
		theOrder.setCustomer(customerUpdate);
		orderService.saveOrder(theOrder);
		
		return "redirect:/customers/list";
	}
	
	//request mapping to see orders/customer full info of particular customer just click on [MORE]. 
	@GetMapping("/customerPage")
	public String showCustomerInfo(@RequestParam("customerId") int theId, Model theModel) {
		Customer theCustomer = customerService.findById(theId);
		
		List<Order> theOrders = customerService.findCustomerOrders(theId);
		
		theModel.addAttribute("orders", theOrders);
		theModel.addAttribute("customer", theCustomer);
		
		return "customer/orders";
	} 
	
	
	//request mapping to delete the customer account 
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerService.deleteById(theId);
		
		return "redirect:/customers/list";
	}
	
	//handle payment scene
	
	private int payId;
	
	@GetMapping("/pay")
	public String payAmount(@RequestParam("customerId") int theId, Model theModel) {
		//Customer theCustomer = customerService.findById(theId);
		Customer theCustomer = new Customer();
		payId = theId;
		theModel.addAttribute("customer", theCustomer);
		
		return "customer/payForm";
	}
	
	@PostMapping("/paySave")
	public String savePayment(@ModelAttribute("customer") Customer thePaymentCustomer) {
		Customer customerAmount = customerService.findById(payId);
		
		customerAmount.setTotal(customerAmount.getTotal()-thePaymentCustomer.getTotal());
		
		customerService.save(customerAmount);
		
		return "redirect:/customers/list";
	}
	
	//----------------------------------------------------------------------------------------------------------------//
	@GetMapping("/uniqueForm")
	public String showFormForCustomerAccount(Model theModel) {
		
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer/unique-form";
	}
	
	//trial mapping
	@PostMapping("/customerAccessPage")
	public String showCustomerInfoToCustomer(@ModelAttribute("customer") Customer theCust , Model theModel) {
		String uniqueCustomer = theCust.getUniqueCustomer();
		Customer theCustomer = customerService.findByUniqueCustomer(uniqueCustomer);
			
		List<Order> theOrders = customerService.findCustomerOrders(theCustomer.getId());
			
		theModel.addAttribute("orders", theOrders);
		theModel.addAttribute("customer", theCustomer);
			
		return "customer/orders";
	}	
	
	/*
	@GetMapping(path="/list/{customerId}")
	public String getOrdersByCustomer(@PathVariable("customerId") int customerId ,Model theModel) {
		List<Order> theOrders = customerService.findCustomerOrders(customerId);
		
		theModel.addAttribute("orders", theOrders);
		
		return "customer/orders";
	}  */
	
	
}
