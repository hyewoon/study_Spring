package member;

import lombok.Getter;
import lombok.Setter;

public class MemberVO {
	
	@Getter @Setter
	private String userid, userpw, name, gender, email, post,phone,
				address, admin, social, profile, birth, salt;
	

}
