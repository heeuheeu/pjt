package Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.sql.OracleDao;

@Service("UserService")
public class UserServiceImpl {

	@Resource(name="UserDao")
	private OracleDao dao;

	/////////////////////////////////////////////////////// �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
	public EmployeeVO loginEmp(EmployeeVO employee) {
		System.out.println("UserService login");
		return dao.loginEmp(employee);	
	}
	
	//plus甕곌쑵�뱣 占쎈듇�몴���늺 鈺곌퀣彛낉옙猷� 占쎌뱽占쎌뒭疫뀐옙
	public List<EmployeeDepartmentVO> list() {
		System.out.println("USER SERVICE LIST");
		return dao.listRow();
	}

	public int addFav(String loginId, String valueArr) {
		System.out.println("user service addFav");
		return dao.addFavRow(loginId, valueArr);
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
	
}
