package com.hanul.web01;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.MemberVO;

@Controller
public class TestController {
	
	//로그인처리 요청
	@RequestMapping("/login_result")
		public String login(String id, String pw) {
			//id: admin, pw:1234 이면 성공
			//로그인 성공하는 경우
			if(id.equals("admin")&& pw.equals("1234")) {
				//웰컴페이지로(index)
				return "redirect:/";
			}else {
				//로그인 실패- 로그인 페이지로
				return "redirect:login";
			
			}
			
		}
		
	
		//로그인 화면요청
		@RequestMapping("/login")
		public String login() {
			return "member/login";
			
		}
	
	
		//경로의 형태로 데이터를 접근
		@RequestMapping("/joinPath/{name}/{g}/{email}")
		public String join(@PathVariable  String name, Model model, @PathVariable("g") String gender , @PathVariable String email) {
			//비즈니스로직 - 경로를통해 받은 정보를 화면에 출력할 수 있도록 담는다
			model.addAttribute("name", name);
			model.addAttribute("gender", gender);
			model.addAttribute("email", email);
			model.addAttribute("method", "@PathVariable 방식");
			
			
			//응답화면연결
			return "member/info";
		}
	
	
	
		//DatObject방식으로 파라미터 접근
		@RequestMapping("/joinDataObject")
		public String join(Model model, MemberVO vo) {
		//비즈니스로직 - 파라미터 정보를 화면에 출력할 수 있도록 담는다
		model.addAttribute("method", "데이터객체 방식");
		model.addAttribute("vo", vo);
			
		
			//응답화면연결
			return "member/info";
			}
	
	
	//RequestParam방식으로 파라미터 접근
		@RequestMapping("/joinParam")
		public String join(String name, @RequestParam("email")  String mail, @RequestParam("gender") String code, Model model) {
		//비즈니스로직 - 파라미터 정보를 화면에 출력할 수 있도록 담는다
		model.addAttribute("name", name);
		model.addAttribute("gender", code);
		model.addAttribute("email", mail);
		model.addAttribute("method", "@RequestParam");
			//응답화면연결
			return "member/info";
			}
			
		//HttpServletRequest방식으로 파라미터 접근
		@RequestMapping("/joinRequest")
		public String join(HttpServletRequest request, Model model) {
			//비즈니스로직 - 파라미터 정보를 화면에 출력할 수 있도록 담는다
			model.addAttribute("name", request.getParameter("name"));
			model.addAttribute("gender", request.getParameter("gender"));
			String email = request.getParameter("email");
			model.addAttribute("email", email);
			model.addAttribute("method", "HttpServletRequest 방식");
			
			
			//응답화면연결
			return "member/info";
		}
	
	
		@RequestMapping("/member")
		public String member() {
			return "member/join";
			
		}
	
	
		@RequestMapping("/third")
		public ModelAndView thrid() {
		 //비즈니스 로직
			//현재 날짜, 시각을 화면에 출력할 수 있도록 한다 
			ModelAndView model = new ModelAndView();
			String today = 
			new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
			model.addObject("todayNow", today); //데이터 저장
			model.setViewName("index"); //화면연결
			return model;
	}
	
	
		@RequestMapping("/second")
		public String second(Model model) {
			//비즈니스로직 
			//현재시각을 화면에 출력할 수 있도록 \Model 타입 변수에 attribute로 담는다
			String now = 
			new SimpleDateFormat("HH:mm:ss").format(new Date());
		
			model.addAttribute("now",  now);	
			return "index";
		}
	

	//웹브라우저 주소에 /first 라고 요청할 때 이 메소드를 실행하고자 한다.
		//요청에 대해 이 메소드를 연결: @RequestMapping 
		
		@RequestMapping("/first")
		public String first(Model model) {
			
			//오늘 날짜를 화면에 출력할 수 있도록 저장한다(비즈니스로직) : request에 atrribute로 저장
			//----> Model 타입에 attribute로 저장
			 String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			 model.addAttribute("today", today);
			 
			 //응답화면 연결
			 return "index";
			


			
			
		}

	

		
	}
	
	

