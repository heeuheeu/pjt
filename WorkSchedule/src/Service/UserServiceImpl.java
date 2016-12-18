package Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import model.domain.vo.CalendarVO;
import model.domain.vo.DeptDivisionVO;
import model.domain.vo.DivDeptCheckVO;
import model.domain.vo.DivisionCheckYnVO;
import model.domain.vo.DivisionVO;
import model.domain.vo.EmpIdVO;
import model.domain.vo.EmployeeDeptDivVO;
import model.domain.vo.EmployeeDeptVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.domain.vo.LocationVO;
import model.domain.vo.NfcVO;
import model.domain.vo.WorkVO;
import model.sql.OracleDao;

@Service("UserService")
public class UserServiceImpl {

	@Resource(name = "UserDao")
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

	public int insertDefWorkRow(WorkVO work) {
		System.out.println("UserService updateDiv");
		return dao.insertDefWorkRow(work);
	}

	//////////////// idcheck///////////////////////////
	public EmployeeDeptVO idCheck(String empid) {
		System.out.println("UserService idcheck");
		return dao.idCheckRow(empid);
	}

	/////////////////////////////////////////////////////// About user

	public int selectwork(EmployeeWorkDeptVO user) { //
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

	public EmployeeDeptDivVO selectEmpInfo(EmployeeDeptDivVO user) {
		System.out.println("UserService mylist2");
		return dao.selectEmpRow(user);
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

	public List<EmployeeWorkDeptVO> selectMyWork(EmployeeWorkDeptVO user) {
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
	// nfc
	public int nfcUpdateAm(NfcVO nfc) {
		System.out.println("UserService nfcUpdateAm");
		return dao.updateNfcAm(nfc);
	}

	public int nfcUpdatePm(NfcVO nfc) {
		System.out.println("UserService nfcUpdatePm");
		return dao.updateNfcPm(nfc);

	}

	public List<LocationVO> selectloc() {
		System.out.println("UserService selectloc");
		return dao.selectlocRow();
	}

	public int addDiv(DivisionVO div) {
		System.out.println("UserService addDiv");
		return dao.addDivRow(div);
	}

	public int addDept(DeptDivisionVO div) {
		System.out.println("UserService addDept");
		return dao.addDeptRow(div);
	}

	public int addLoc(LocationVO div) {
		System.out.println("UserService addLoc");
		return dao.addLocRow(div);
	}

	////////////////////////////////// delete work
	public int deletework(EmployeeDeptDivVO member) {
		System.out.println("UserService deleteWork");
		return dao.deleteWork(member);
	}

	///////////////////////////////// delete emp
	public int deleteemp(EmployeeDeptDivVO member) {
		System.out.println("UserService deleteEmp");
		return dao.deleteEmp(member);
	}

	////////////////////////////////// selected dept --update.jsp
	public List<DeptDivisionVO> selecteddept(EmployeeDeptDivVO mylist) {
		System.out.println("UserService selectedDept");
		return dao.selectedDept(mylist);
	}

	////////////////////////////////////////////////////// dashboard에서 새로고침시
	////////////////////////////////////////////////////// worktable 검사
	public List<String> selectEmpList() {
		System.out.println("UserService select emp list");
		return dao.selectEmpList();
	}

	////////////////////////////////////////////////////// dashboard에서 30일 이후 항목
	////////////////////////////////////////////////////// 없으면 insert
	public int insertWork(EmployeeWorkDeptVO mylist) {
		System.out.println("UserService insert work");
		return dao.insertWork(mylist);
	}

	////////////// admin list up//////////////////
	public List<DivisionCheckYnVO> admSelectDiv() {
		System.out.println("USER SERVICE admSelectDiv");
		return dao.admSelectDivRow();
	}

	public List<DivDeptCheckVO> admSelectDeptDiv() {
		System.out.println("USER SERVICE admSelectDeptDiv");
		return dao.admSelectDeptDivRow();
	}

	public List<LocationVO> admSelectLoc() {
		System.out.println("UserService admSelectLoc");
		return dao.admSelectLocRow();
	}

	///////////////// admin update: DIV/////////////
	public String admDivCheckYN(String divid) {
		System.out.println("UserService select div");
		return dao.admDivCheckYNRow(divid);
	}

	public int adminDivAdd(String divid) {
		System.out.println("UserService updateDiv");
		return dao.adminDivAddRow(divid);
	}

	public int adminDivDel(String divid) {
		System.out.println("UserService updateDiv");
		return dao.adminDivDelRow(divid);
	}

	////////////////// admin update: LOC//////////////////
	public String admLocCheckYN(String locid) {
		System.out.println("UserService admLocCheckYN");
		return dao.admLocCheckYNRow(locid);
	}

	public int adminLocAdd(String locid) {
		System.out.println("UserService adminLocAdd");
		return dao.adminLocAddRow(locid);
	}

	public int adminLocDel(String locid) {
		System.out.println("UserService adminLocDel");
		return dao.adminLocDelRow(locid);
	}

	////////////////// admin update: DEPT//////////////////
	public String admDeptCheckYN(String deptid) {
		System.out.println("UserService admDeptCheckYN");
		return dao.admDeptCheckYNRow(deptid);
	}

	public int adminDeptAdd(String deptid) {
		System.out.println("UserService adminDeptAdd");
		return dao.adminDeptAddRow(deptid);
	}

	public int adminDeptDel(String deptid) {
		System.out.println("UserService adminDeptDel");
		return dao.adminDeptDelRow(deptid);
	}

	public List<DivisionCheckYnVO> admSelectDivY() {
		System.out.println("USER SERVICE admSelectDivY");
		return dao.admSelectDivYRow();
	}

}
