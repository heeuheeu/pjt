package Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import model.domain.vo.DeptDivisionVO;
import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeDeptVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.sql.OracleDao;

@Service("UserService")
public class UserServiceImpl {

	@Resource(name="UserDao")
	private OracleDao dao;

	/////////////////////////////////////////////////////// main.jsp
	public EmployeeVO loginEmp(EmployeeVO employee) {
		System.out.println("UserService login");
		return dao.loginEmp(employee);	
	}

	// sign up
	public int join(EmployeeDeptVO emp) {
		System.out.println("UserService join");
		return dao.insertRow(emp);
	}
	
	/////////////////////////////////////////////////////// About user
	
	public int selectwork(EmployeeWorkDeptVO user) { // work table�뿉 媛� �엳�뒗吏� 李얘린
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
	
	
	/////////////////////////////////////////////////////// About favorite

	public List<EmployeeFavWorkDeptVO> selectempfav(EmployeeWorkDeptVO user) {
		System.out.println("UserService selectempfav");
		return dao.selectFavRow(user);
	}

	//////////// eunbieunbi//////////////////////////////
	///////////////////////// plus button - orga list /////////////////
	public List<EmployeeDeptVO> list() {
		System.out.println("USER SERVICE LIST");
		return dao.listRow();
	}

	public int addFav(String loginId, String valueArr) {
		System.out.println("user service addFav");
		return dao.addFavRow(loginId, valueArr);
	}

	public List<DeptDivisionVO> selectdeptdiv() {
		System.out.println("USER SERVICE LIST");
		return dao.selectDeptDivRow();
	}

	
	 ////////////////////////search//////////////////
	 public List<EmployeeDeptVO> searchEmp(EmployeeDeptVO member) {
	  System.out.println("UserService searchEmp");
	  return dao.searchEmpRow(member);
	 }


}
