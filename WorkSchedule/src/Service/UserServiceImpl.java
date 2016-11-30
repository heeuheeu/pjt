package Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.domain.vo.MemberVO;
import model.sql.OracleDao;
import model.sql.OracleDaoImpl;

@Service("UserService")
public class UserServiceImpl {

	// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占싱쇽옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙求占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占�. 占쏙옙占쏙옙占쏙옙占쏙옙 占싹뱄옙 占쌘뱄옙 占쏙옙占쏙옙.
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

	/////////////////////////////////////////////////////// 占쏙옙占쏙옙占쏙옙
	public EmployeeVO loginEmp(EmployeeVO employee) {
		System.out.println("UserService login");
		return dao.loginEmp(employee);	
	}
	
	//plus踰꾪듉 �늻瑜대㈃ 議곗쭅�룄 �쓣�슦湲�
	public List<EmployeeDepartmentVO> list() {
		System.out.println("USER SERVICE LIST");
		return dao.listRow();
	}

	public int addFav(String loginId, String valueArr) {
		System.out.println("user service addFav");
		return dao.addFavRow(loginId, valueArr);
	}

	
	
	/////////////////////////////////////////////////////// About user
	
	public int selectwork(EmployeeWorkDeptVO user) { // work table에 값 있는지 찾기
		System.out.println("UserService mylist");
		return dao.selectWorkRow(user); 
	}
	
	
	public EmployeeWorkDeptVO mylist1(EmployeeWorkDeptVO user) {
		System.out.println("UserService mylist1");
		return dao.mylistRow1(user); 
	}
	
	public EmployeeWorkDeptVO mylist2(EmployeeWorkDeptVO user) { 
		System.out.println("UserService mylist2");
		return dao.mylistRow2(user); 
	}
		
	
}
