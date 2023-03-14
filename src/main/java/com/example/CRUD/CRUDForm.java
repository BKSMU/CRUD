package com.example.CRUD;

import jakarta.validation.constraints.NotNull;

public class CRUDForm {
	
	private int code;

	// 商品名
	private String name;
	
	// 金額
	@NotNull
	private int unitPrice;

	// 数量
	@NotNull
	private int count;
	
	// おすすめ商品フラグ
	@NotNull
	private int IsPr;

	public String getName() {
		return name;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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
		return IsPr;
	}

	public void setIsPr(int isPr) {
		IsPr = isPr;
	}
}
