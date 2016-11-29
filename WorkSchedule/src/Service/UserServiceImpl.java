package Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.MemberVO;
import model.sql.OracleDao;
import model.sql.OracleDaoImpl;

@Service("UserService")
public class UserServiceImpl {

	// ������ ���������̼� �������� ����ϴ� ������ ���� ���� ���. �������� �Ϲ� �ڹ� ����.
	@Resource(name="UserDao")
	private OracleDao dao;
	
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

	/////////////////////////////////////////////////////// ������
	public EmployeeVO loginEmp(EmployeeVO employee) {
		System.out.println("UserService login");
		return dao.loginEmp(employee);	
	}
	
	//plus버튼 누르면 조직도 띄우기
	public List<EmployeeDepartmentVO> list() {
		System.out.println("USER SERVICE LIST");
		return dao.listRow();
	}

	public int addFav(String loginId, String valueArr) {
		System.out.println("user service addFav");
		return dao.addFavRow(loginId,valueArr);
	}

	
	
	
}
