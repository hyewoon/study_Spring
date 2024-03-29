package visual;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository // db저장관련
public class VisualDAO implements VisualService {
	
	@Autowired @Qualifier("hr") private SqlSession sql;

	@Override
	public List<HashMap<String, Object>> department() {
		// TODO Auto-generated method stub
		return sql.selectList("visual.department");
	}

	@Override
	public List<HashMap<String, Object>> hirement_year() {
		// TODO Auto-generated method stub
		return sql.selectList("visual.hirement_year");
	}

	@Override
	public List<HashMap<String, Object>> hirement_month() {
		// TODO Auto-generated method stub
		return sql.selectList("visual.hirement_month");
	}

	@Override
	public List<HashMap<String, Object>> hirement_top3_year() {
		// TODO Auto-generated method stub
		return sql.selectList("visual.hirement_top3_year");
	}

	@Override
	public List<HashMap<String, Object>> hirement_top3_month() {
		// TODO Auto-generated method stub
		return sql.selectList("visual.hirement_top3_month");

	}
	
}
