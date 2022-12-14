package hr;

import java.util.List;

import org.springframework.http.codec.cbor.Jackson2CborDecoder;

public interface HrService {
	void employee_insert(EmployeeVO vo); //신규사원등록
	List<EmployeeVO >employee_list();//사원목록
	EmployeeVO employee_info(int employee_id);//사원정보
	void employee_update(EmployeeVO vo);//사원정보수정
	void employee_delete(int employee_id);//사원정보삭제
	
	List<DepartmentVO> hr_department_list();//회사 전체  부서목록
	List<JobVO>hr_job_list();
	

}
