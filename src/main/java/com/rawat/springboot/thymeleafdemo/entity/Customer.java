package com.rawat.springboot.thymeleafdemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="total")
	private int total;
	
	@Column(name="address")
	private String address;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="unique_customer")
	private String uniqueCustomer;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="customer")
	private List<Order> orders;
	
	public Customer() {
		
	}
	
	
	
	public Customer(String firstName, String lastName, String address, String email, String mobile, int total, String uniqueCustomer) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.total = total;
		this.address = address;
		this.mobile = mobile;
		this.uniqueCustomer = uniqueCustomer;
	}



	public Customer(String firstName, String lastName, String address, String email, String mobile, int total, String uniqueCustomer, List<Order> orders) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.total = total;
		this.address = address;
		this.mobile = mobile;
		this.orders = orders;
		this.uniqueCustomer = uniqueCustomer;
	}
	
	public String getUniqueCustomer() {
		return uniqueCustomer;
	}

	public void setUniqueCustomer(String uniqueCustomer) {
		this.uniqueCustomer = uniqueCustomer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void add(Order tempOrder) {
		if (orders == null) {
			orders = new ArrayList<>();
		}
		
		orders.add(tempOrder);
		tempOrder.setCustomer(this);
	}
	
	

}
