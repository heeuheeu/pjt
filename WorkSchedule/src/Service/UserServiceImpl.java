package Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import model.domain.vo.MemberVO;
import model.sql.OracleDao;
import model.sql.OracleDaoImpl;

@Service("UserService")
public class UserServiceImpl {

	// ������ ���������̼� �������� ����ϴ� ������ ���� ���� ���. �������� �Ϲ� �ڹ� ����.
	@Resource(name="UserDao")
	private OracleDao dao;
	
	// �α��� �޼ҵ� ����. 
	public MemberVO login(MemberVO member) {
		System.out.println("UserService login");
		return dao.loginRow(member);	
	}
	
	
	public int join(MemberVO member) {
		System.out.println("UserService join");
		return dao.insertRow(member);
	}
	
	public int update(MemberVO member) {
		System.out.println("UserService update");
		return dao.updateRow(member);
	}
	

	public List<MemberVO> list() {
		System.out.println("USER SERVICE LIST");
		return dao.listRow();
	}


	public MemberVO cart(MemberVO member) {
		System.out.println("user service cart");
		return dao.cartRow(member);
	}


	
}
