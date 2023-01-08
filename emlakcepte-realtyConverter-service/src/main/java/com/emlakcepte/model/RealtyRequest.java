package com.emlakcepte.model;

public class RealtyRequest {
	
	private RealtyType status;
	private Integer no;
	
	public RealtyRequest() {
		super();
	}
	
	public RealtyRequest(RealtyType status, Integer no) {
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
		return "RealtyRequest [status=" + status + ", no=" + no + "]";
	}

}
