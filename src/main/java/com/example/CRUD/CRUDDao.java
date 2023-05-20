package com.example.CRUD;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CRUDDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertItem(Item item) {
		jdbcTemplate.update("INSERT INTO ITEM(name, unitPrice, count, IsPr, RecordDate) VALUES(?, ?, ?, ?, ?)",
				 item.getName(), item.getUnitPrice(), item.getCount(), item.getIsPr(), item.getRecordDate() );		
	}
	
	public void deleteItem(int code) {
		jdbcTemplate.update("DELETE FROM ITEM WHERE code = ?",
				code );		
	}
	
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
			item.setIsPr((int)result.get("IsPr"));
			item.setRecordDate(((Timestamp)result.get("RecordDate")).toLocalDateTime());
			
			list.add(item);
		}
		
        return list;
	}

	public Item selectByCode(int code) {
		Map<String, Object> result = jdbcTemplate.queryForMap("SELECT code, name, unitPrice, count, IsPr, RecordDate FROM ITEM WHERE code = ?", code);
		
		Item item = new Item();
		item.setCode((int)result.get("code"));
		item.setName((String)result.get("name"));
		item.setUnitPrice((int)result.get("unitPrice"));
		item.setCount((int)result.get("count"));
		item.setIsPr((int)result.get("IsPr"));
		item.setRecordDate(((Timestamp)result.get("RecordDate")).toLocalDateTime());
		
		return item;
	}
	
	public void update(Item item) {
		jdbcTemplate.update("UPDATE ITEM SET name = ?, count = ?, unitPrice = ?, IsPr = ? WHERE code = ?",
				 item.getName(), item.getCount(), item.getUnitPrice(),  item.getIsPr(), item.getCode() );		
	}
}