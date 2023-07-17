package com.example.CRUD.entity;

import java.time.LocalDateTime;

public class Item {
	
	private int code;
	private String name;
	private int unitPrice;
	private int count;
	private int isPr;
	private LocalDateTime recordDate;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getIsPr() {
		return isPr;
	}
	public void setIsPr(int isPr) {
		this.isPr = isPr;
	}
	public LocalDateTime getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(LocalDateTime recordDate) {
		this.recordDate = recordDate;
	}

	

}
