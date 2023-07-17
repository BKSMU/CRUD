package com.example.CRUD.dto;

/**
 * Viewから受け取るユーザの入力値を保持するJavaオブジェクト
 * Controllerのメソッドの引数として受け取る
 * ユーザが入力するデータを記述する
 * バリデーションのためのアノテーションをつける場合はここでする
 *
 */
public class CRUDdto {
	
	private int code;
	private String name;
	private int unitPrice;
	private int count;
	private int isPr;

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
		return isPr;
	}

	public void setIsPr(int isPr) {
		this.isPr = isPr;
	}
}
