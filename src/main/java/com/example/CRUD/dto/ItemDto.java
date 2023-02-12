/**
 * 
 */
package com.example.CRUD.dto;

import java.util.Date;

/**
 * @author vtr03
 *
 */

//アイテムクラス
public class ItemDto {
	
	// コード
	long code;
	
	//商品名
	String name;
	
	//価格
	int unitPrice;
	
	//数量
	int count;

	//おススメフラグ
	int IsPr;
	
	//登録日時
	Date RecordDate;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
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

	public Date getRecordDate() {
		return RecordDate;
	}

	public void setRecordDate(Date recordDate) {
		RecordDate = recordDate;
	}
	
	

}
