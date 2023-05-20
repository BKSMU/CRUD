package com.example.CRUD;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CRUDController {
	
	private final CRUDService CRUDService;
	
	public CRUDController(CRUDService CRUDService) {
		this.CRUDService = CRUDService;
	}
	
	@RequestMapping("/")
	public String index(CRUDForm CRUDForm) {
		return "entry";
	}

	@PostMapping("/insert")
    public String insert(Model model,
    		@Validated CRUDForm CRUDForm,
    		BindingResult result) {
		
        if (result.hasErrors()) {
			return "entry";
		}
		
		Item item = new Item();
		item.setName(CRUDForm.getName());
		item.setCount(CRUDForm.getCount());
		item.setUnitPrice(CRUDForm.getUnitPrice());
		item.setIsPr(CRUDForm.getIsPr());
		item.setRecordDate(LocalDateTime.now());
		
		CRUDService.insert(item);
		
		List<Item> list = CRUDService.getAll();
		model.addAttribute("resultList", list);
		
		return "insert";
    }
	
	@GetMapping("/read")
	public String read(Model model) {
		List<Item> list = CRUDService.getAll();

		model.addAttribute("resultList", list);

		return "read";
	}

	/** 
	 * name = "code"に紐づく値を引数codeに設定している
	 */
	@PostMapping("/delete")
    public String delete(Model model, 
    		             @RequestParam(name = "code") int code) {

		CRUDService.delete(code);
		
		List<Item> list = CRUDService.getAll();
		model.addAttribute("resultList", list);
		
		return "read";
    }
	
	/** 
	 * URLマッピングで指定するURLに「{」と「}」で囲まれた部分がパラメータ名になり、
	 * @PathVariableアノテーションのvalue属性にパラメータ名を指定することで
	 * URLの部分文字列を取得することができる
	 */
	@GetMapping("/edit/{id:.+}")
	String edit(@PathVariable("id") int code,
			    Model model) {
		
        System.out.println("userId = " + code);
		
		Item item = CRUDService.selectByCode(code);
		
    	// 画面返却ようのFormに値を設定する
    	CRUDForm CRUDForm = new CRUDForm();
    	CRUDForm.setCode(item.getCode());
    	CRUDForm.setName(item.getName());
    	CRUDForm.setUnitPrice(item.getUnitPrice());
    	CRUDForm.setCount(item.getCount());
    	CRUDForm.setIsPr(item.getIsPr());
    	
		model.addAttribute("CRUDForm", CRUDForm);
		
		return "edit";
	}
	
	@PostMapping("edit")
	public String update(@ModelAttribute @Validated CRUDForm CRUDForm,
			             BindingResult result,
			             Model model) {
		
		// バリデーションエラーの場合
		if (result.hasErrors()) {
			// 編集画面に遷移
			return "edit";
		}
		
		CRUDService.update(CRUDForm);
		
		// 一覧画面にリダイレクト
		return "redirect:/read";
	}
}