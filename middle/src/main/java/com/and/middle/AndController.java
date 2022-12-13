package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import customer.CustomerVO;


@RestController
public class AndController {
	
	@Autowired @Qualifier("hanul") SqlSession sql;
	//@ResponseBody <=RestAPI방식이 
	@RequestMapping(value = "/and" , produces ="text/html;charset=utf-8")

	public String and() {
		
//		System.out.println(sql.selectOne("and.test"+""));
		
		List<CustomerVO> list =sql.selectList("and.customer");
		System.out.println(list.size());
		/*
		 * for(int i =0;i<list.size();i++) { System.out.println(list.get(i));
		 * 
		 * }
		 */
		
	
		return new Gson().toJson(list);
	}
	
	

	@RequestMapping(value = "/andVo" , produces ="text/html;charset=utf-8")

	public String andVo() {
		
//		System.out.println(sql.selectOne("and.test"+""));
		
		List<CustomerVO> list =sql.selectList("and.customer");
		System.out.println(list.size());
		/*
		 * for(int i =0;i<list.size();i++) { System.out.println(list.get(i));
		 * 
		 * }
		 */
		
	
		return new Gson().toJson(list.get(0));
	}
	

}
