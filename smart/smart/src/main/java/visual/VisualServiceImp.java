package visual;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    //생성된 객체 
public class VisualServiceImp implements VisualService {
	
@Autowired	private VisualDAO dao;

	@Override
	public List<HashMap<String, Object>> department() {
		// TODO Auto-generated method stub
		return dao.department();
	}

	@Override
	public List<HashMap<String, Object>> hirement_year() {
		// TODO Auto-generated method stub
		return dao.hirement_year();
	}

	@Override
	public List<HashMap<String, Object>> hirement_month() {
		// TODO Auto-generated method stub
		return dao.hirement_month();
	}

	@Override
	public List<HashMap<String, Object>> hirement_top3_year() {
		// TODO Auto-generated method stub
		return dao.hirement_top3_year();
	}

	@Override
	public List<HashMap<String, Object>> hirement_top3_month() {
		// TODO Auto-generated method stub
		return dao.hirement_top3_month();
	}

}
