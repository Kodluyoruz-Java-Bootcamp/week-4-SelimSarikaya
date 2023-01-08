package com.emlakcepte.model;



public class Realty {
	
	private RealtyType status;
	private Integer no;
	public Realty() {
		super();
	}
	
	public Realty(RealtyType status, Integer no) {
		super();
		this.status = status;
		this.no = no;
	}

	public RealtyType getStatus() {
		return status;
	}
	public void setStatus(RealtyType status) {
		this.status = status;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "Realty [status=" + status + ", no=" + no + "]";
	}



}
