package com.example.CRUD.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.CRUD.entity.Item;

@Repository("CRUDDao")
public class CRUDdao {

	@Autowired
	JdbcTemplate jdbc;

	public int count() throws DataAccessException {

		// 全件取得してカウント（カラムを一つだけ取得→queryForObject）
		int count = jdbc.queryForObject("SELECT * FROM ITEM", Integer.class);

		return count;
	}

	//@Override
	public int insertOne(Item dto) throws DataAccessException {
		int result = 0;
		//１件登録。登録、更新、削除はupdateを使う。第一引数はSQL、第二はPreparedStatement。
		String sql = "INSERT INTO ITEM(name, unitPrice, count, IsPr, RecordDate)"
				+ "VALUES(?, ?, ?, ?, ?)";
		result = jdbc.update(sql,
				dto.getName(),
				dto.getUnitPrice(),
				dto.getCount(),
				dto.getIsPr(),
				dto.getRecordDate());

		return result;

	}

	public List<Item> searchALL() {
		String sql = "SELECT * FROM ITEM";
		List<Map<String, Object>> searcheALL = jdbc.queryForList(sql);

		List<Item> serchList = new ArrayList<>();
		for (Map<String, Object> search : searcheALL) {
			Item dto = new Item();
			dto.setCode((int) search.get("CODE"));
			dto.setName((String) search.get("NAME"));
			dto.setUnitPrice((int) search.get("UNITPRICE"));
			dto.setCount((int) search.get("COUNT"));
			dto.setIsPr((int) search.get("ISPR"));
			dto.setRecordDate(((Timestamp) search.get("RECORDDATE")).toLocalDateTime());
			
			serchList.add(dto);

		}

		return serchList;

	}

	public List<Item> searchOne(int code) throws DataAccessException {

		List<Item> serchList = new ArrayList<>();
		Item dto = new Item();

		String sql = "SELECT code, name, unitPrice, count, IsPr, RecordDate " + "FROM ITEM " + "WHERE code = " + code;

		//		queryForMap 結果マップのクエリを実行
		Map<String, Object> searcheOne = jdbc.queryForMap(sql);
		dto.setCode((int) searcheOne.get("CODE"));
		dto.setName((String) searcheOne.get("NAME"));
		dto.setUnitPrice((int) searcheOne.get("UNITPRICE"));
		dto.setCount((int) searcheOne.get("COUNT"));
		dto.setIsPr((int) searcheOne.get("ISPR"));
		dto.setRecordDate(((Timestamp) searcheOne.get("RECORDDATE")).toLocalDateTime());

		serchList.add(dto);
		return serchList;
	}

	public int delete(int code) throws DataAccessException {

		String sql = "DELETE FROM ITEM WHERE code = ?";
		//１件削除
		int rowNumber = jdbc.update(sql, code);

		return rowNumber;
	}

	public int update(Item dto) throws DataAccessException {

		String sql = "UPDATE ITEM SET name = ?, unitPrice = ?, count = ?, isPr = ?, recordDate = ? WHERE code = ?;";
		//１件更新
		int rowNumber = jdbc.update(sql,
				dto.getName(),
				dto.getUnitPrice(),
				dto.getCount(),
				dto.getIsPr(),
				dto.getRecordDate(),
				dto.getCode());
		
		return rowNumber;
	}
	
//	public void update(ItemDto dto) {
//		jdbc.update("UPDATE ITEM SET name = ?, count = ?, unitPrice = ?, IsPr = ? WHERE code = ?",
//				dto.getName(), dto.getCount(), dto.getUnitPrice(),  dto.getIsPr(), dto.getCode() );		
	
	
	

}
