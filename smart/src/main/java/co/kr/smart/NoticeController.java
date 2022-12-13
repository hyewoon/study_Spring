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
	@RequestMapping("/delete.no")
	public String delete(int id) {
		//비지니스로직
		//선택한 고객정보를 DB에서 삭제한 후
		service.notice_delete(id);
		//응답화면연결
		return "redirect:list.no";
	}



	//공지글 수정저장요청
	@RequestMapping("/update.no")
	public String update(NoticeVO vo) {
		//비지니스로직
		//화면에서 입력한 정보를 DB에 변경저장한 후
		service.notice_update(vo);
		//응답화면연결
		return "redirect:info.no?id=" + vo.getId();
	}

	

	//공지글 수정화면 요청
	@RequestMapping("/modify.no")
	public String modify(int id, Model model) {
		
		NoticeVO vo = service.notice_info(id);
		model.addAttribute("vo", vo);
		return "notice/modify";
	}
	
	
	//공지글 정보화면 요청
	@RequestMapping("/info.no")
	public String info(int id, Model model) {
		NoticeVO vo = service.notice_info(id);
		model.addAttribute("vo", vo);
		return "notice/info";
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
