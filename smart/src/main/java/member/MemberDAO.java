package member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDAO implements MemberService {
	//db연결, db어떤거 연결할지는 @Qualifier : 권장사항 생성자 통해서
	@Autowired  @Qualifier("hanul") private SqlSession sql;
	
	
	/*
	 * private SqlSession sql; public MemberDAO(@Qualifier SqlSession sql) {
	 * this.sql=sql; }
	 */

	@Override
	public int member_join(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sql.selectOne("member.login", map);
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
		return sql.update("member.myInfo_update", vo);
	}

	@Override
	public int member_delete(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> member_list() {
		// TODO Auto-generated method stub
		return sql.selectList("member.list");
	}

	@Override
	public String member_salt(String userid) {
		// TODO Auto-generated method stub
		return sql.selectOne("member.salt",userid);
	}

	@Override
	public String member_userid_email(MemberVO vo) {
		// TODO Auto-generated method stub
		return sql.selectOne("member.userid_email", vo);
	}

}
