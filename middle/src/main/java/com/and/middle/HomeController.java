package com.and.middle;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;


@Controller
public class HomeController {
	

	
	@ResponseBody //res.getWriter().println()
	@RequestMapping(value = "/")
	public String home(HttpServletResponse res) {
		//android에서 직접 db에 접속할수 없다(예외 sql)
		//android -->spring  미들웨어 --<오라클
		//android에서 필요한 것은 데이터(페이지 x)
		
		//request = 요청
		//response = 응답
		login("admin", "admin1234");
		
		
		//1. forward 2.redirect 
		
		return "middle"; //응답을 바로 하지 않고, forward페이지로 
		
	//코드가 의미하는 것 
			
		
	}
	
	@ResponseBody
	@RequestMapping("/andTest")//post, get둘다 가능
	public String andTest(String id){ //android가 post방식으로 파라미터로 보내준 것
		System.out.println(id);
		return "kk";
		
	}
	//id와 pw를 입력받은 다음
	//idrk admin pw admin1234 페이지 나오게 처리
	//그 외 비밀번호 틀림 메세지  나오게
	
	//get: url 뒤에 파라미터 이름과 값 담아서 보인다 
	//post
	@ResponseBody//응답내가함
	@RequestMapping("/login")
	public String login(String id, String pw) {
		
			
		if(id.equals("admin")&& pw.equals("admin1234")) {
//			System.out.println("로그인 되었습니다");
			return"로그인";
			
		}else {
//		System.out.println("비밀번호가 틀림");
		return"실패";
	}
		
		//json <==스트링으로 값을 리턴하면  구분하기 위해 사용 
		//[] :  배열을 표현하는 식: 값이 여러개
		//{} :	객체(object)를  표현하는 식: 값이 하나일때 
		
		
		
	
}
	
	//REST API : 
	
	
	@ResponseBody
	@RequestMapping("/jsontest")	
	public String jsonTest(){
		TempDTO dto= new TempDTO("data123");
		ArrayList<TempDTO> list = new ArrayList<TempDTO>();
		list.add(new TempDTO("a"));
		list.add(dto);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	public class TempDTO{
		
		
		String data;
		public TempDTO(String data) {
			this.data= data;
		}
	}

	
}
