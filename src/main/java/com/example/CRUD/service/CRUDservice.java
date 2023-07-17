package com.example.CRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.CRUD.dao.CRUDdao;
import com.example.CRUD.entity.Item;

//ここでdao・repositry　のやり取りの箱にentityを使用したい
@Service
public class CRUDservice {
	
	@Autowired
	private CRUDdao dao;
	
	
	
	public void insertOne(Item dto) throws DataAccessException {
		int result = 0;
		
		
		result = dao.insertOne(dto);
		//実行結果をもとにエラー処理　メソッドの戻り値変えて、エラーメッセージセットして返したいな

	}

	public List<Item> searchALL() {

		List<Item> serchList = dao.searchALL();

		return serchList;

	}

	public List<Item> searchOne(int code) throws DataAccessException {

		List<Item> searchOneList = dao.searchOne(code);
		return searchOneList;
	}

	public int delete(int code) throws DataAccessException {

		//１件削除
		int rowNumber = dao.delete(code);

		return rowNumber;
	}

//	public int update(ItemDto dto) throws DataAccessException {
//
//		//１件更新
//		int rowNumber = dao.update(dto);
//		
//		return rowNumber;
//	}
	
	public void update(Item dto) throws DataAccessException {
		
				//１件更新
				dao.update(dto);
				
				
			}
}
