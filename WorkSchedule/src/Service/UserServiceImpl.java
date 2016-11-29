package Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import model.domain.vo.EmployeeVO;
import model.domain.vo.MemberVO;
import model.sql.OracleDao;
import model.sql.OracleDaoImpl;

@Service("UserService")
public class UserServiceImpl {

	// 서블릿은 프리젠테이션 영역에서 담당하는 서블릿만 서블릿 파일 사용. 나머지는 일반 자바 파일.
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

	/////////////////////////////////////////////////////// 수정중
	public EmployeeVO loginEmp(EmployeeVO employee) {
		System.out.println("UserService login");
		return dao.loginEmp(employee);	
	}
	
	
}
