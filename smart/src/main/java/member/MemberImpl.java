package member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//이메소드는 컨드롤러가 호출한다
@Service("member")
public class MemberImpl implements MemberService {

	@Autowired private MemberDAO dao;
	
	
	@Override
	public int member_join(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return dao.member_login(map);
	}

	@Override
	public MemberVO member_myInfo(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int member_idCheck(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int member_myInfo_update(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.member_myInfo_update(vo);
	}

	@Override
	public int member_delete(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> member_list() {
		// TODO Auto-generated method stub
		return dao.member_list();
	}

	@Override
	public String member_salt(String userid) {
		// TODO Auto-generated method stub
		return dao.member_salt(userid);
	}

	@Override
	public String member_userid_email(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.member_userid_email(vo);
	}
}


