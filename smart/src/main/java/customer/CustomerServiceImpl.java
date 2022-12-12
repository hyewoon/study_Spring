package customer;

import java.util.List;
import org.springframework.stereotype.Service;


@Service("customer")
public class CustomerServiceImpl implements CustomerService {
	//@Autowired 
	private CustomerDAO dao;
	public CustomerServiceImpl( CustomerDAO dao) {
		this.dao = dao;
	}
	

	@Override
	public void customer_insert(CustomerVO vo) {
		dao.customer_insert(vo);

	}

	@Override
	public List<CustomerVO> customer_list() {
		// TODO Auto-generated method stub
	 return dao.customer_list();
	}

	@Override
	public CustomerVO customer_info(int id) {
		// TODO Auto-generated method stub
		return dao.customer_info(id);
	}

	@Override
	public void customer_update(CustomerVO vo) {
		/* sql.update("customer.update", vo); */
		dao.customer_update(vo);
	}

	

	@Override
	public void customer_delete(int id) {
		dao.customer_delete(id);

	}

}
