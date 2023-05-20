package com.example.CRUD;

import java.time.LocalDateTime;

public class Item {

	// 商品コード
	private int code;
	// 商品名
	private String name;
	// 金額
	private int unitPrice;
	// 数量
	private int count;
	// おすすめ商品フラグ
	private int IsPr;
	// 更新時間
	private LocalDateTime RecordDate;

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
		return IsPr;
	}

	public void setIsPr(int isPr) {
		IsPr = isPr;
	}

	public LocalDateTime getRecordDate() {
		return RecordDate;
	}

	public void setRecordDate(LocalDateTime recordDate) {
		RecordDate = recordDate;
	}
}
