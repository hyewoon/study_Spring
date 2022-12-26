package com.and.middle;

import java.util.List;

import javax.print.attribute.SetOfIntegerSyntax;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import customer.CustomerVO;

@RestController 
public class CustomerController {
	//규칙 : Customer 모듈에 관련된 맵핑은 모두 .cu로 끝낼 것
	
	@Autowired @Qualifier("hanul") SqlSession sql;
	@RequestMapping( value ="/select.cu", produces ="text/html;charset=utf-8")
	public String select() {
		
		System.out.println(sql.selectList("cu.select").size());
		List<CustomerVO> list = sql.selectList("cu.select");
		return new Gson().toJson(list);
	}
		
	/*
	 * //
	 * 
	 * @Autowired @Qualifier("hanul") SqlSession sql;
	 * 
	 * @RequestMapping( value ="/custom", produces ="text/html;charset=utf-8")
	 * public List<String> Custom() { List<String> list= sql.selectList(null);
	 * 
	 * return list; }
	 */
	
	
	//삭제하기 : 삭제할때 정보 필요 id값
	@RequestMapping( value ="/delete.cu", produces ="text/html;charset=utf-8")
	public String delete(int id) {
		System.out.println(id);
		 int result= sql.delete("cu.delete", id);
		 
		 System.out.println(result);
		
		return "";
	}
}
