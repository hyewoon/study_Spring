package notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository // dao객체를생성해오는 처리 new로 생성하는 대신에 스프링이 해주는 처리
public class NoticeDAO implements NoticeService {
	//sql연결:  autowired qualifier
	
	@Qualifier("hanul")@Autowired SqlSession sql;
	
	
	

	@Override
	public void notice_insert(NoticeVO vo) {
		sql.insert("no.insert", vo);

	}

	@Override
	public List<NoticeVO> notice_list() {
		
		return sql.selectList("no.list");
	}

	@Override
	public void notice_update(NoticeVO vo) {
		sql.update("notice.update", vo);

	}

	@Override
	public void notice_delete(int id) {
		sql.delete("notice.delete", id);
	}
	}


	@Override
	public NoticeVO notice_info(int id) {
		return sql.selectOne("notice.info", id);
	}

}
