package com.example.CRUD.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.CRUD.dao.CRUDdao;
import com.example.CRUD.dto.CRUDdto;
import com.example.CRUD.entity.Item;

/**
 * ユーザーからのリクエストを受け付け、Model及びViewに伝達する
 *
 */
@Controller
public class CRUDController {
	
	@Autowired
	private CRUDdao dao;
	
    /**
     * 初期(一覧)画面の表示
     * 
     * listにitemListという名前をつけてModelに格納する(Modelは引数に設定するだけで使える)
     * ModelがView にデータを渡してくれる
     * 
     * @param model モデル
     * @return 初期(一覧)画面
     */
	@GetMapping("/")
    public String index(Model model) {
    	List<Item> list = dao.getAll();
        model.addAttribute("itemList", list);
        return "index";
    }

    /**
     * 新規入力フォーム画面の表示
     * 
     * @param crudForm 入力フォーム
     * @return 新規入力フォーム画面
     */
    @GetMapping("/insert")
    public String form(CRUDdto crudForm) {
        return "insert";
    }
    
    /**
     * 登録処理
     * 
     * ユーザが入力した値をItemに詰めてデータベースに登録
     * redirectで"/"を指定しているため、自動的にリクエストURL "/"(HTTPメソッドはGET)のアクセスが実行される
     * Controllerのindexメソッドが実行され、登録後の一覧ページが表示される
     * 
     * @param crudForm 入力フォーム
     * @return 初期(一覧)画面
     */
    @PostMapping("/insert")
    public String create(CRUDdto crudForm) {
		Item item = new Item();
		item.setName(crudForm.getName());
		item.setCount(crudForm.getCount());
		item.setUnitPrice(crudForm.getUnitPrice());
		item.setIsPr(crudForm.getIsPr());
		item.setRecordDate(LocalDateTime.now());
        dao.insertItem(item);
        return "redirect:/";
    }

    /**
     * 編集画面の表示
     * 
     * データを特定するため、リクエストURLの一部(id)を使用する
     * ＠GetMapping("/edit/{id}") のパスの波括弧で囲んだ{id}の部分は、
     * ＠PathVariableを使用すると変数として受け取ることができる
     * 
     * フォームに表示するデータを受け渡すために、ItemFormを使用する
     * idを抽出条件に対象レコードを取得し、itemFormに値を設定してViewに渡す
     * 
     * @param itemForm 入力フォーム
     * @param id 対象レコードの商品コード
     * @return 編集画面
     */
    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute CRUDdto crudForm, @PathVariable int id) {
    	Item item = dao.selectByCode(id);
    	crudForm.setCode(item.getCode());
    	crudForm.setName(item.getName());
    	crudForm.setUnitPrice(item.getUnitPrice());
    	crudForm.setCount(item.getCount());
    	crudForm.setIsPr(item.getIsPr());
        return "edit";
    }

    /**
     * 更新処理
     * 
     * 編集画面で入力した値で対象レコードを更新する
     * 更新後は初期(一覧)画面にリダイレクトする
     * 
     * @param itemForm 入力フォーム
     * @return 初期(一覧)画面
     */
    @PostMapping("/edit")
    public String update(CRUDdto crudForm) {
        dao.updateItem(crudForm);
        return "redirect:/";
    }

    /**
     * 削除処理
     * 
     * 対象レコードを削除し、初期(一覧)画面にリダイレクトする
     * 
     * @param id 対象レコードの商品コード
     * @return 初期(一覧)画面
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        dao.deleteItem(id);
        return "redirect:/";
    }
}