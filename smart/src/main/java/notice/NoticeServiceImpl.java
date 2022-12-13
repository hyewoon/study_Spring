package notice;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("notice") //서비스를 가져오는처리
public class NoticeServiceImpl implements NoticeService {
	//dao연결
	
	private NoticeDAO dao;
	public NoticeServiceImpl(NoticeDAO dao) {
		this.dao = dao;
	}
	

	@Override
	public void notice_insert(NoticeVO vo) {
		dao.notice_insert(vo);

	}

	@Override
	public List<NoticeVO> notice_list() {
		// TODO Auto-generated method stub
		return dao.notice_list();
	}

	@Override
	public void notice_update(NoticeVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notice_delete(int id) {
		// TODO Auto-generated method stub

	}


	@Override
	public NoticeVO notice_info(String writer ) {
		
		return dao.notice_info(writer);
	}

}
