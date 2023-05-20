package com.example.CRUD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

//ここでdao・repositry　のやり取りの箱にentityを使用したい
@Service
public class CRUDService {
	
	@Autowired
	private CRUDDao dao;
	
	
	
	public void insertOne(ItemDto dto) throws DataAccessException {
		int result = 0;
		
		
		result = dao.insertOne(dto);
		//実行結果をもとにエラー処理　メソッドの戻り値変えて、エラーメッセージセットして返したいな

	}

	public List<ItemDto> searchALL() {

		List<ItemDto> serchList = dao.searchALL();

		return serchList;

	}

	public List<ItemDto> searchOne(int code) throws DataAccessException {

		List<ItemDto> searchOneList = dao.searchOne(code);
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
	
	public void update(ItemDto dto) throws DataAccessException {
		
				//１件更新
				dao.update(dto);
				
				
			}
}
