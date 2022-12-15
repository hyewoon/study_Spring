package member;

import java.util.HashMap;
import java.util.List;

public interface MemberService {
	
	int member_join(MemberVO vo);                              //회원가입 회원정보 저장
	MemberVO member_login(HashMap<String, String>map);          //로그인처리
	MemberVO member_myInfo(String userid);                   //내정보보기 : 마이페이지r
	int member_idCheck(String userid);                      //회원가입시 아이디중복확인r
	int member_myInfo_update(MemberVO vo);                  //마이페이지에서 회원정보변경저장
	int member_delete(String userid);    				//회원탈퇴시 회원정보 삭제
	String member_salt(String userid);										//회원의 비번 암호화에 사용하는 salt 조회
	String member_userid_email(MemberVO vo);         		//아이디와 이메일이 일치하는 회원의 이름 조회
	
	

	//관리자 모드에서 전체 회원목록조회
	
	List<MemberVO> member_list();
	

}
