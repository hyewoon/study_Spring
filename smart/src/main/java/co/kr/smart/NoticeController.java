package co.kr.smart;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import notice.NoticeService;
import notice.NoticeVO;

@Controller
public class NoticeController {
	//service와 연결
	
	private NoticeService service;
	
	public NoticeController(NoticeService notice) {
		this.service = notice;
		
	}
	
	//공지글 삭제
	
	//
	
	
	//공지글 정보화면 요청
	@RequestMapping("/info.no")
	public String info(String writer, Model model) {
		
		NoticeVO vo = service.notice_info(writer);
		model.addAttribute("vo", vo);
		return "notice/info";
	}
	
	//공지글 수정
	@RequestMapping("/modify.no")
	public String modify(String writer, Model model) {
		
		NoticeVO vo = service.notice_info(writer);
		model.addAttribute("vo", vo);
		return "notice/modify";
	}
	
	//새 공지글 등록하고 DB에 저장하는 처리
	@RequestMapping("/insert.no")
	public String insert(NoticeVO vo) {
		//새 공지글 등록하고 DB에 저장하는 처리
		service.notice_insert(vo);
		return "redirect:list.no";
	}
	
	//새로운 공지글 입력처리
	@RequestMapping("/new.no")
	public String notice() {
		
		return "notice/new";
	}
	
	
	//1. 공지글목록화면 요청
	@RequestMapping("/list.no")
	public String list(HttpSession session, Model model) {
		//공지글관리 정보를 session에 담는다 화면공지글이 활성화될 수있도록
		session.setAttribute("category", "no");
		//비지니스로직-DB에서 공지글목록을 조회한다. -> 목록화면에 출력하도록 Model에 attribute로 담는다
		
		// List<NoticeVO> list =service.notice_list(); 
		// model.addAttribute("list",list);
		 
		model.addAttribute("list",service.notice_list() );
		//응답화면연결
		return "notice/list"; 
	}
}
