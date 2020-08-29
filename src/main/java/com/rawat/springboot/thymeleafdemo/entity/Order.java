package com.rawat.springboot.thymeleafdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer_orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="cDate")
	private String date;
	
	@Column(name="cTime")
	private String time;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="details")
	private String detail;
	
	@ManyToOne
	@JoinColumn(name="customer_detail_Id")
	private Customer customer;
	
	public Order() {
		
	}

	public Order(String date, String time, int amount,String detail) {
		this.date = date;
		this.time = time;
		this.amount = amount;
		this.detail = detail;
	}
	public Order(String date, String time, int amount,Customer customer,String detail) {
		this.date = date;
		this.time = time;
		this.amount = amount;
		this.customer = customer;
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
