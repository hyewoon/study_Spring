package co.kr.smart;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.EmployeeVO;
import hr.HrService;

@Controller
public class HrController {
	
	
	private HrService service;
	
	public HrController(HrService hr) {
		this.service = hr;
	}
	
	
	
	//신규사원등록처리요청
	@RequestMapping("/insert.hr")
	public String insert(EmployeeVO vo) {
		service.employee_insert(vo);
		//비즈니스로직 -화면에서 입력한 정보를 db에 신규저장한다
	
		return "redirect:list.hr";
		
	}
	
	//신규사원등록화면 요청
	//사원정보수정해서 저장하는 처리
		@RequestMapping("/new.hr")
	public String employee( Model model) {
			
			
			//부서목록, 업무목록을 조회한다
			model.addAttribute("departments", service.hr_department_list());
			model.addAttribute("jobs", service.hr_job_list());
		
			
			return "employee/new";
		
	}
	
	
	//사원정보수정해서 저장하는 처리
	@RequestMapping("/update.hr")
	public String update(EmployeeVO vo) {
		//비즈니스로직화면에서 수정입력한 정보들을 db에 변경저장한 후, 
		//응답화면연결 
		service.employee_update(vo);
		return "redirect:info.hr?id=" + vo.getEmployee_id();
	}
	
	
	
	//사원정보수정하는 처리
	@RequestMapping("/modify.hr")
	public String modify(int id, Model model) {
		//비즈니스 로직 : 선택한 사원정보를 수정화면에 출력한다
		
		//선택한 사원정보를 db에 조회한 후 출력할 수있도록 model에 담는다
		model.addAttribute("vo",service.employee_info(id) );
		//부서목록, 업무목록을 조회한다
		model.addAttribute("departments", service.hr_department_list());
		model.addAttribute("jobs", service.hr_job_list());
		return "employee/modify";
		
	}
	
	//사원정보삭제 처리 요청
	@RequestMapping("/delete.hr")
	public String delete(int id) {
		//비즈니스로직- 선택한 사원정보를 db에서 삭제한다
		service.employee_delete(id);
		//응답화면 연결
		return "redirect:list.hr";
		
	}
	
	
	
	
	//사원정보화면 요청
	@RequestMapping("/info.hr")
	public String info(int id, Model model) {
		
		model.addAttribute("vo",service.employee_info(id) );
		return "employee/info";
		
	}
	
	//사원목록화면 요청
	@RequestMapping("/list.hr")
	public String list(HttpSession session, Model model) {
		session.setAttribute("category", "hr");
		//비즈니스로직_DB에서 사원목록을 조회한다-->화면에 출력할 수 있도록 Model에 담는다
//		 List<EmployeeVO> list = service.employee_list(); 
//		model.addAttribute("list",list);
		model.addAttribute("list",service.employee_list());
		//응답화면연결
		return "employee/list";
		
	}
	
	
	
}
