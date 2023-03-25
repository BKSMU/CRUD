package com.example.CRUD.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.CRUD.dto.ItemDto;

@Repository
public class ItemDao {

	@Autowired
	private JdbcTemplate jdbc;

	// 新規登録
	public int insert(ItemDto itemDto) throws DataAccessException {

		//１件登録。登録、更新、削除はupdateを使う。第一引数はSQL、第二はPreparedStatement。
		int rowNumber = jdbc.update("INSERT INTO ITEM(name,"
				+ " unitPrice,"
				+ " count,"
				+ " IsPr,"
				+ " RecordDate)"
				+ " VALUES(?, ?, ?, ?, ?)",
				itemDto.getName(),
				itemDto.getUnitPrice(),
				itemDto.getCount(),
				itemDto.getIsPr(),
				itemDto.getRecordDate());

		return rowNumber; //正常終了なら０を返す。異常は０以外を返す。
	}

	// 一覧取得
	public List<ItemDto> selectAll() throws DataAccessException {

		// ITEMテーブルのデータを全件取得（queryForList）
		// map→keyとvalueの組み合わせ。それをListで受け取る。
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM ITEM");

		System.out.println(getList);


		// 結果返却用の変数
		List<ItemDto> itemList = new ArrayList<>();

		// 取得したデータを結果返却用のListに格納していく
		for (Map<String, Object> map : getList) {

			//Itemインスタンスの生成
			ItemDto itemDto = new ItemDto();

			// Itemインスタンスに取得したデータをセットする
			itemDto.setCode((int) map.get("code")); //コード
			itemDto.setName((String) map.get("name")); //名称
			itemDto.setUnitPrice((int) map.get("unitPrice")); //価格
			itemDto.setCount((int) map.get("count")); //値段
			itemDto.setIsPr((int) map.get("IsPr")); //おススメフラグ
			itemDto.setRecordDate((Date) map.get("RecordDate")); //登録日

			//結果返却用のListに追加
			itemList.add(itemDto);
		}

		return itemList;
	}

	//一件取得
	public ItemDto selectOne(int code) throws DataAccessException {


		// SQL文を作成
		String sql = ""
				+ "SELECT"
				+ " *"
				+ " FROM"
				+ " ITEM"
				+ " WHERE"
				+ " code = ?";

		// queryForObjectメソッドを実行
		// SQLの実行結果は、自動的にUserクラスにマッピングされて取得できる。
		RowMapper<ItemDto> rowMapper = new BeanPropertyRowMapper<ItemDto>(ItemDto.class);
		ItemDto itemDto = jdbc.queryForObject(sql, rowMapper, code);

		return itemDto;


	}

	//更新
	public int upadate(ItemDto itemDto) throws DataAccessException {
		int rowNumber = jdbc.update("UPDATE ITEM SET"
				+ " name = ?,"
				+ " unitPrice = ?,"
				+ " count = ?,"
				+ " IsPr = ?,"
				+ " RecordDate = ?"
				+ " WHERE"
				+ " code = ?",
				itemDto.getName(),
				itemDto.getUnitPrice(),
				itemDto.getCount(),
				itemDto.getIsPr(),
				itemDto.getRecordDate(),
				itemDto.getCode());

		return rowNumber; //正常終了なら０を返す。異常は０以外を返す。
	}


	// 削除
	public int deleteOne(int code) throws DataAccessException {

		//１件登録。登録、更新、削除はupdateを使う。第一引数はSQL、第二はPreparedStatement。
		int rowNumber = jdbc.update("DELETE FROM ITEM WHERE code = ?",
				code);

		return rowNumber; //正常終了なら０を返す。異常は０以外を返す。
	}
}
