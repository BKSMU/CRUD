package com.example.CRUD;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * データベースへのアクセスを行う
 *
 */
@Repository
public class CRUDDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 全件取得
	 * 
	 * @return 取得結果List
	 */
	public List<Item> getAll(){
		String sql = "SELECT code, name, unitPrice, count, IsPr, RecordDate FROM ITEM";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		List<Item> list = new ArrayList<Item>();
		
		for(Map<String, Object> result : resultList) {
			Item item = new Item();
			item.setCode((int)result.get("code"));
			item.setName((String)result.get("name"));
			item.setUnitPrice((int)result.get("unitPrice"));
			item.setCount((int)result.get("count"));
			item.setIsPr((int)result.get("isPr"));
			item.setRecordDate(((Timestamp)result.get("recordDate")).toLocalDateTime());
			
			list.add(item);
		}		
        return list;
	}

	/**
	 * 1件取得
	 * 
	 * @param code 商品コード
	 * @return 取得結果
	 */
	public Item selectByCode(int code) {
		Map<String, Object> result = jdbcTemplate
				.queryForMap("SELECT code, name, unitPrice, count, isPr, recordDate FROM ITEM WHERE code = ?", code);

		Item item = new Item();
		item.setCode((int)result.get("code"));
		item.setName((String)result.get("name"));
		item.setUnitPrice((int)result.get("unitPrice"));
		item.setCount((int)result.get("count"));
		item.setIsPr((int)result.get("isPr"));
		item.setRecordDate(((Timestamp)result.get("recordDate")).toLocalDateTime());
		
		return item;
	}

    /**
	 * 登録
	 * 
	 * @param item ITEMテーブルのEntity
	 */
    void insertItem(Item item) {
		jdbcTemplate.update("INSERT INTO ITEM(name, unitPrice, count, IsPr, RecordDate) VALUES(?, ?, ?, ?, ?)",
				 item.getName(), item.getUnitPrice(), item.getCount(), item.getIsPr(), item.getRecordDate() );		
	}
	
	/**
	 * 削除
	 * 
	 * @param code 商品コード
	 */
	public void deleteItem(int code) {
		jdbcTemplate.update("DELETE FROM ITEM WHERE code = ?",
				code );		
	}

	/**
	 * 更新
	 * 
	 * @param itemForm 入力フォーム
	 */
	public void updateItem(CRUDForm crudForm) {
		jdbcTemplate.update(
				"UPDATE ITEM SET name = ?, count = ?, unitPrice = ?, isPr = ?, recordDate = ? WHERE code = ?",
				crudForm.getName(), crudForm.getCount(), crudForm.getUnitPrice(), crudForm.getIsPr(),
				LocalDateTime.now(), crudForm.getCode());
	}
}