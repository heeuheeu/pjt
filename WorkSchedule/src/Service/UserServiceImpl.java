package Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import model.domain.vo.CalendarVO;
import model.domain.vo.DeptDivisionVO;
import model.domain.vo.EmpIdVO;
import model.domain.vo.EmployeeDeptDivVO;
import model.domain.vo.EmployeeDeptVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.domain.vo.NfcVO;
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
	public int join(EmployeeDeptDivVO emp) {
		  System.out.println("UserService join");
		  return dao.insertRow(emp);
	}
	 public List<DeptDivisionVO> selectdiv() {
		  System.out.println("USER SERVICE selectdiv");
		  return dao.selectDivRow();
	 }

	 public List<String> selectboxDept(String divname) {
		  System.out.println("UserService updateDiv");
		  return dao.selectboxDeptRow(divname);
		 }
	 
	 //////////////// idcheck///////////////////////////
	 public EmployeeDeptVO idCheck(String empid) {
	  System.out.println("UserService idcheck");
	  return dao.idCheckRow(empid);
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
	public List<EmpIdVO> selectFavId(String userid) {
		System.out.println("UserService selectempfav");
		return dao.selectFavIdRow(userid);	
	}
	
	//////////// eunbieunbi//////////////////////////////
	///////////////////////// plus button - orga list /////////////////
	public List<EmployeeDeptDivVO> list(EmployeeDeptDivVO user) {
		System.out.println("USER SERVICE LIST");
		return dao.listRow(user);
	}

	public int addFav(String loginId, String chkid) {
		System.out.println("user service addFav");
		return dao.addFavRow(loginId, chkid);
	}
	
	public int deleteFav(String loginId, String valueArr) {
		System.out.println("user service addFav");
		return dao.deleteFavRow(loginId, valueArr);
	}
	
	public List<DeptDivisionVO> selectdeptdiv() {
		System.out.println("USER SERVICE LIST");
		return dao.selectDeptDivRow();
	}

	//////////////////////// search ////////////////////////
	public List<EmployeeDeptDivVO> searchEmp(EmployeeDeptDivVO member) {
		System.out.println("UserService searchEmp");
		return dao.searchEmpRow(member);
	}
	public List<EmployeeDeptDivVO> searchDept(EmployeeDeptDivVO member) {
		System.out.println("UserService searchDept");
		return dao.searchDeptRow(member);
	}
	public List<EmployeeDeptDivVO> searchDiv(EmployeeDeptDivVO member) {
		System.out.println("UserService searchDept");
		return dao.searchDivRow(member);
	}
	
	/////////////////////////////////////////////////////// About calendar

	public List<CalendarVO> selectMyWork(CalendarVO user) {
		System.out.println("UserService selectMyWork");
		return dao.myWorkRow(user);
	}

	///////////////// update///////////////////
	public int update(EmployeeDeptDivVO member) {
		System.out.println("user service update");
		return dao.updateRow(member);
	}

	public int updateWork(EmployeeDeptDivVO member) {
		System.out.println("user service updateWork");
		return dao.updateWorkRow(member);
	}
	
	/////////////////////////////////////////////////////// Modal info update
	/////////////////////////////////////////////////////// --sukjin

	public int update(EmployeeWorkDeptVO myinfo) {
		System.out.println("UserService updateMyWork");
		return dao.updateMyWork(myinfo);
	}

	//////////////////////////////////////////////////////// Calendar.jsp modal
	public EmployeeWorkDeptVO selectCalModal(EmployeeWorkDeptVO myinfo) {
		System.out.println("UserService calendarModal");
		return dao.selectCalModal(myinfo);
	}
	
	public List<EmployeeWorkDeptVO> selectDashEmp(EmployeeWorkDeptVO myinfo) {
		System.out.println("UserService calendarModal");
		return dao.selectDashRow(myinfo);
	}
	
	//////////////////////////////////////////////////////// nfc
	// nfc 태그로 업데이트
	 public int nfcUpdateAm(NfcVO nfc) {
	  System.out.println("UserService nfcUpdateAm");
	  return dao.updateNfcAm(nfc);
	 }
	 public int nfcUpdatePm(NfcVO nfc) {
	  System.out.println("UserService nfcUpdatePm");
	  return dao.updateNfcPm(nfc);
	  
	 }
}
