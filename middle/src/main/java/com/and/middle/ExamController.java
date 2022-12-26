package com.and.middle;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import exam.ExamVO;

//controller : 요청을 받아서 대부분 -> 페이지 전환을 하기 위한 것(Ajax 비동기처리 또는 데이터만 필요할 때)
//ResponseBody : 이거 자체가 응답이다 
//RestController : 데이터만 필요한 경우 사용하는 컨트롤러(@ResponseBody 생략 가능)


@RestController  //컨트롤러 어노테이션
public class ExamController {
	
	
	@RequestMapping( value ="/test1", produces ="text/html;charset=utf-8")
	public String  andExam(String id) {
		
		System.out.println("요청받음");
		id ="min";
		return "test1";
	}
	//"요청"을 받는다 ==>RequestMapping 하위 메소드 실행
	//test 요청받은 맵핑을 하나 만들기 파라미터 3개 이상 입력받을 수 있도록 
	@RequestMapping( value ="/test2", produces ="text/html;charset=utf-8")
	public String  andTest2(String id, String pw, int cnt) {
	
		System.out.println(id);
		System.out.println(pw);
		System.out.println(cnt);
		System.out.println("응답받음");
		return "test2";
	}
	
	//test3
	@RequestMapping( value ="/test3", produces ="text/html;charset=utf-8")
	public String andTest3() {
		
		return "khw";
	}
	//test4 
	@RequestMapping( value ="/test4", produces ="text/html;charset=utf-8")
	public String andTest4() { //Android에서 vo값을 가지고 있지 않다 . Spring에서 값이 나와야 한다
		ExamVO vo = new ExamVO();
		vo.setdVal(3.5);
		vo.setiVal(7);
		vo.setsVal("abc");
		
		return new Gson().toJson(vo);
		
		//{"iVal":7,"sVal":"abc","dVal":3.5}
	}
	//test5  ExamVO 가 10건 묶음 놓은 Array가 크롬창에 써지게 처리하기
	@RequestMapping(value = "/test5", produces = "text/html;charset=utf-8")
	public String test5() {
		ArrayList<ExamVO> list = new ArrayList<ExamVO>();
		for(int i = 0 ; i<10 ; i++) {
			ExamVO vo = new ExamVO();
			vo.setdVal(3.5);
			vo.setiVal(5);
			vo.setsVal("abc");
			list.add(vo);
		}
		return new Gson().toJson(list);//<=
	}
	
	/*
	 *
	 * String a = "a"; 
	 * String[] array= {"a", "b","c"};
	 * 
	 * ArrayList<String> list= new ArrayList();
	 * 
	 */
	 	
	
	
}
	// test5요청시 Vo를 10건 묶어놓은 Array가 크롬창에 써지게 처리해보기.
	
	//------------------- ↑
	// test5 요청시 숫자값을 파라메터로 보낸다. 해당하는 숫자의 크기만큼 Array를 크롬창에 써지게 처리해보게.
	// (※ 단 숫자 외에 값을 입력하면 오류라는 글씨가 크롬창에 써진다. ) <= 파라메터를 String으로 받으면 가능.
	
	
	// test5요청시 Vo를 10건 묶어놓은 Array가 크롬창에 써지게 처리해보기.
	
	//------------------- ↑
	// test5 요청시 숫자값을 파라메터로 보낸다. 해당하는 숫자의 크기만큼 Array를 크롬창에 써지게 처리해보게.
	// (※ 단 숫자 외에 값을 입력하면 오류라는 글씨가 크롬창에 써진다. ) <= 파라메터를 String으로 받으면 가능.
	


	
	
	
