package com.example.CRUD;


import java.time.LocalDateTime;//localtimestamp型に変更しよう
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CRUDController {
	
	
	@Autowired
	private CRUDService service;
	
	
	@GetMapping("/")
	 public String home(Model model){
		model.addAttribute("bean",new Bean());
	 return "home";
	 }
	
	@PostMapping("/insert")
	 public String insert(@ModelAttribute Bean bean, Model model){
		
		ItemDto dto = new ItemDto();
		dto.setName(bean.getName());
		dto.setCount(bean.getCount());
		dto.setUnitPrice(bean.getUnitPrice());
		dto.setIsPr(bean.getIsPr());
		dto.setRecordDate(LocalDateTime.now());
		
		 service.insertOne(dto);
		
		model.addAttribute("bean",dto);
		
	 return "insert";
	 }
	
	@RequestMapping("/read")
	 public String read(Model model){
		List<ItemDto> searchAllList = service.searchALL();
		model.addAttribute("searchAllList", searchAllList);
		
	 return "read";
	 }
	
	@RequestMapping(value = "/delete", params = "delete", method = RequestMethod.POST)
	 public String delete(@RequestParam("code") int code){
		service.delete(code);
	 return "delete";
	 }
	
	@RequestMapping("/searchOne")
	 public String searchOne(int code, Model model){
		List<ItemDto> search = service.searchOne(code);
		
		Bean bean = new Bean();
		for (int i = 0; i < search.size(); i++) {
			bean.setCode(search.get(i).getCode());
			bean.setName(search.get(i).getName());
			bean.setUnitPrice(search.get(i).getUnitPrice());
			bean.setCount(search.get(i).getCount());
			bean.setIsPr(search.get(i).getIsPr());
			bean.setRecordDate(search.get(i).getRecordDate());
			
		}
		
		model.addAttribute("bean", bean);
		
	 return "searchOne";
	 }
	
	@PostMapping(value = "/update", params = "update")
	 public String update(@ModelAttribute Bean bean, Model model){
		
		ItemDto dto = new ItemDto();
		dto.setName(bean.getName());
		dto.setCount(bean.getCount());
		dto.setUnitPrice(bean.getUnitPrice());
		dto.setIsPr(bean.getIsPr());
		dto.setRecordDate(LocalDateTime.now());
		
		service.update(dto);
		
		List<ItemDto> search = service.searchOne(bean.getCode());
		model.addAttribute("searchAllList", search);
		
	 return "update";
	 }
}
