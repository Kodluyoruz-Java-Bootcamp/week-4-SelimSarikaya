package com.emlakcepte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	private Integer id;
	private Integer amount;
	private boolean didPay = false;
	
	public Payment() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public boolean isDidPay() {
		return didPay;
	}

	public void setDidPay(boolean didPay) {
		this.didPay = didPay;
	}
	

}
