/**
 * 
 */
package com.example.CRUD.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CRUD.dao.ItemDao;
import com.example.CRUD.dto.ItemDto;

/**
 * @author vtr03
 *
 */

@Controller
public class CRUDController {	

	@Autowired
	ItemDao itemDao;

	// 初期画面
	@RequestMapping("/init")
	public String init(){
		return "init";
	}

	// 登録画面
	@RequestMapping("/entry")
	public String entry(){
		return "entry";
	}

	// 登録処理
	@RequestMapping("/insert")
	public String insert(@RequestParam String name,
			int unitPrice,
			int count,
			int IsPr,
			Model model) {

		ItemDto itemDto = new ItemDto();

		itemDto.setName(name);
		itemDto.setUnitPrice(unitPrice);
		itemDto.setCount(count);
		itemDto.setIsPr(IsPr);
		itemDto.setRecordDate(new Date());

		itemDao.insert(itemDto);

		return "finish";
	}

	// 一覧画面
	@RequestMapping("/search")
	public String search(Model model){
		// DBに登録されている一覧を取得
		List<ItemDto> itemList = itemDao.selectAll();

		// 画面に取得した値を渡して表示
		model.addAttribute("itemList", itemList);
		return "search";
	}

	// 編集画面
	@RequestMapping(value="/edit/{code}")
	public String edit(@PathVariable int code, Model model){
		// 指定のデータを取得
		ItemDto itemDto = itemDao.selectOne(code);
		
		System.out.println(itemDto);

		// 画面に取得した値を渡して表示
		model.addAttribute("itemDto", itemDto);


		return "edit";
	}

	// 更新処理
	@RequestMapping("/update")
	public String upadate(@RequestParam int code,
			String name,
			int unitPrice,
			int count,
			int IsPr,
			Model model) {

		ItemDto itemDto = new ItemDto();
		
		itemDto.setCode(code);
		itemDto.setName(name);
		itemDto.setUnitPrice(unitPrice);
		itemDto.setCount(count);
		itemDto.setIsPr(IsPr);
		itemDto.setRecordDate(new Date());

		itemDao.upadate(itemDto);

		return "finish";
	}

	// 削除
	@RequestMapping(value="/delete/{code}")
	public String delete(@PathVariable int code){

		itemDao.deleteOne(code);

		return "finish";
	}

}






