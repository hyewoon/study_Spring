package hr;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("hr")
public class HrServiceImpl implements HrService {
	//@Autowired private HrDAO dao;
	private HrDAO dao;
	public HrServiceImpl(HrDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void employee_insert(EmployeeVO vo) {
		dao.employee_insert(vo);
	

	}

	

	@Override
	public EmployeeVO employee_info(int employee_id) {
	
		return dao.employee_info(employee_id);
	}

	@Override
	public void employee_update(EmployeeVO vo) {
		dao.employee_update(vo);
		

	}

	@Override
	public void employee_delete(int employee_id) {
		dao.employee_delete(employee_id);
		

	}


	@Override
	public List<EmployeeVO> employee_list() {
		// TODO Auto-generated method stub
		return dao.employee_list();
	}

	@Override
	public List<DepartmentVO> hr_department_list() {
		// TODO Auto-generated method stub
		return dao.hr_department_list();
	}

	@Override
	public List<JobVO> hr_job_list() {
		// TODO Auto-generated method stub
		return dao.hr_job_list();
	}


}