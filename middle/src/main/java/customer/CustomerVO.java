package customer;

import lombok.Getter;
import lombok.Setter;

public class CustomerVO {
	@Getter @Setter
	private int id;
	private String name, gender, email, phone;

}
