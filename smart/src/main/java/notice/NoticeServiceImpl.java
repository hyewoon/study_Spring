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
	
		return dao.notice_list();
	}

	@Override
	public void notice_update(NoticeVO vo) {
		dao.notice_update(vo);
	}

	@Override
	public void notice_delete(int id) {
		dao.notice_delete(id);
	}


	@Override
	public NoticeVO notice_info(int id) {
		return dao.notice_info(id);
	}

}
