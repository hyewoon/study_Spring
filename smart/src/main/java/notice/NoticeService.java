package notice;

import java.util.List;

public interface NoticeService {
	//crud
	void notice_insert(NoticeVO vo);//새로운 글등록
	List<NoticeVO>notice_list();//공지글목록
	NoticeVO notice_info(int id);//선택공지글정보
	void notice_update(NoticeVO vo);//공지글 수정
	void notice_delete(int id);//공지글 삭제

}
