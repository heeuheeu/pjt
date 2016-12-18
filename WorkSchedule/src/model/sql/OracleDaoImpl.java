package model.sql;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
import model.domain.vo.FavoriteVO;
import model.domain.vo.LocationVO;
import model.domain.vo.NfcVO;
import model.domain.vo.WorkVO;

@Repository("UserDao")
public class OracleDaoImpl implements OracleDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<EmployeeVO> selectEmp() {
		System.out.println("Dao selectEmp");
		return sqlSession.selectList("member.selectemp");
	}

	@Override
	public int insertRow(EmployeeDeptDivVO emp) {
		System.out.println("Dao insertRow");
		return sqlSession.insert("member.insertemp", emp);
	}

	// 새로 만든거
	@Override
	public List<DeptDivisionVO> selectDivRow() {
		System.out.println("Dao selectDivRow");
		return sqlSession.selectList("member.selectdiv");
	}

	@Override
	public List<String> selectboxDeptRow(String divname) {
		System.out.println("Dao selectboxDeptRow");
		return sqlSession.selectList("member.selectboxdept", divname);
	}

	// INSERT DEFAULT WORKDATE
	@Override
	public int insertDefWorkRow(WorkVO work) {
		System.out.println("Dao insertDefWorkRow");
		return sqlSession.insert("member.insertdefaultwork", work);
	}

	@Override
	public EmployeeVO loginEmp(EmployeeVO employee) {
		System.out.println("Dao loginEmp");
		return sqlSession.selectOne("member.loginemp", employee);
	}

	////////////////////// idcheck////////////////////////
	@Override
	public EmployeeDeptVO idCheckRow(String empid) {
		System.out.println("Dao idCheckRow");
		return sqlSession.selectOne("member.idcheck", empid);
	}

	///////////////////////////////////////////////////////// myDQM > mylist

	@Override
	public int selectWorkRow(EmployeeWorkDeptVO user) {
		System.out.println("Dao selectWorkRow");
		return sqlSession.selectOne("emp.countwork", user);
	}

	@Override
	public EmployeeWorkDeptVO mylistRow1(EmployeeWorkDeptVO user) {
		System.out.println("Dao mylistrow1");
		return sqlSession.selectOne("emp.selectemp", user);
	}

	@Override
	public EmployeeWorkDeptVO mylistRow2(EmployeeWorkDeptVO user) {
		System.out.println("Dao mylistrow2");
		return sqlSession.selectOne("emp.selectwork", user);
	}

	@Override
	public EmployeeDeptDivVO selectEmpRow(EmployeeDeptDivVO user) {
		System.out.println("Dao mylistrow2");
		return sqlSession.selectOne("emp.selectempinfo", user);
	}

	@Override
	public List<EmployeeFavWorkDeptVO> selectFavRow(EmployeeWorkDeptVO user) {
		System.out.println("Dao selectFavRow");
		return sqlSession.selectList("emp.selectfavlist", user);
	}

	@Override
	public List<EmpIdVO> selectFavIdRow(String userid) {
		System.out.println("Dao selectFavidRow");
		return sqlSession.selectList("emp.selectfavid", userid);
	}

	@Override
	public List<EmployeeDeptDivVO> listRow(EmployeeDeptDivVO user) {
		System.out.println("Dao listrow");
		return sqlSession.selectList("member.selectsearchview", user);
	}

	@Override
	public int addFavRow(String loginId, String chkid) {
		System.out.println("dao addFavRow");
		FavoriteVO fav = new FavoriteVO(loginId, chkid);
		int flag = sqlSession.insert("member.addfavorite", fav);
		return flag;
	}

	@Override
	public int deleteFavRow(String loginId, String valueArr) {
		System.out.println("dao deleteFavRow");
		FavoriteVO fav = new FavoriteVO(loginId, valueArr);
		int flag = sqlSession.delete("member.deletefavorite", fav);
		return flag;
	}

	@Override
	public List<DeptDivisionVO> selectDeptDivRow() {
		System.out.println("Dao selectDeptDivRow");
		return sqlSession.selectList("member.selectdeptdiv");
	}

	////////////////////////////////////////////////////////////////////////// search
	@Override
	public List<EmployeeDeptDivVO> searchEmpRow(EmployeeDeptDivVO member) {
		System.out.println("Dao searchEmpRow");
		return sqlSession.selectList("member.searchemp", member);
	}

	@Override
	public List<EmployeeDeptDivVO> searchDeptRow(EmployeeDeptDivVO member) {
		System.out.println("Dao searchDeptRow");
		return sqlSession.selectList("member.searchdept", member);
	}

	@Override
	public List<EmployeeDeptDivVO> searchDivRow(EmployeeDeptDivVO member) {
		System.out.println("Dao searchDivRow");
		return sqlSession.selectList("member.searchdiv", member);
	}

	////////////////////////////////////////////////////////////////////////// calendar

	// calendar
	@Override
	public List<EmployeeWorkDeptVO> myWorkRow(EmployeeWorkDeptVO user) {
		System.out.println("Dao myWorkRow");
		return sqlSession.selectList("cal.selectmywork", user);
	}

	// modal update
	@Override
	public int updateMyWork(EmployeeWorkDeptVO myinfo) {
		System.out.println("Dao updateWorkRow");
		return sqlSession.update("emp.updatemywork", myinfo);
	}

	//////////////////////////////////////////////////////////////////////////// updateRow
	@Override
	public int updateRow(EmployeeDeptDivVO member) {
		System.out.println("Dao updateRow");
		return sqlSession.update("member.update", member);
	}

	@Override
	public int updateWorkRow(EmployeeDeptDivVO member) {
		System.out.println("Dao updateRow");
		return sqlSession.update("member.updateWork", member);
	}

	// calendar modal
	@Override
	public EmployeeWorkDeptVO selectCalModal(EmployeeWorkDeptVO myinfo) {
		System.out.println("Dao selectCalModal");
		return sqlSession.selectOne("emp.selectcalmodal", myinfo);
	}

	@Override
	public List<EmployeeWorkDeptVO> selectDashRow(EmployeeWorkDeptVO myinfo) {
		System.out.println("Dao selectDashRow");
		return sqlSession.selectList("emp.selectdashemp", myinfo);
	}

	// nfc update
	@Override
	public int updateNfcAm(NfcVO nfc) {
		System.out.println("Dao nfcUpdate");
		return sqlSession.update("member.nfcupdateam", nfc);
	}

	@Override
	public int updateNfcPm(NfcVO nfc) {
		System.out.println("Dao nfcUpdate");
		return sqlSession.update("member.nfcupdatepm", nfc);
	}

	//////////////////////////////////////////////////////////////////////////// updateRow

	@Override
	public List<LocationVO> selectlocRow() {
		System.out.println("Dao selectDashRow");
		return sqlSession.selectList("member.selectloc");
	}

	@Override
	public int addDivRow(DivisionVO div) {
		System.out.println("Dao addDivRow");
		return sqlSession.insert("adm.addDiv", div);
	}

	@Override
	public int addLocRow(LocationVO div) {
		System.out.println("Dao addLocRow");
		return sqlSession.insert("adm.addLoc", div);
	}

	@Override
	public int addDeptRow(DeptDivisionVO div) {
		System.out.println("Dao addDivRow");
		return sqlSession.insert("adm.addDept", div);
	}

	@Override
	public int deleteWork(EmployeeDeptDivVO member) {
		System.out.println("Dao deleteWork");
		return sqlSession.delete("member.deletework", member);
	}

	@Override
	public int deleteEmp(EmployeeDeptDivVO member) {
		System.out.println("Dao deleteEmp");
		return sqlSession.delete("member.deleteemp", member);
	}

	@Override
	public List<DeptDivisionVO> selectedDept(EmployeeDeptDivVO mylist) {
		System.out.println("Dao selectedDept");
		return sqlSession.selectList("member.selecteddept", mylist);
	}

	@Override
	public List<String> selectEmpList() {
		System.out.println("Dao selectedEmpList");
		return sqlSession.selectList("insertwork.selectemplist");
	}

	@Override
	public int insertWork(EmployeeWorkDeptVO mylist) {
		System.out.println("Dao insertWork");
		return sqlSession.insert("insertwork.insertworktable", mylist);
	}

	/////////////////// admin list up//////////////////
	@Override
	public List<DivisionCheckYnVO> admSelectDivRow() {
		System.out.println("Dao admSelectDivRow");
		return sqlSession.selectList("adm.admSelectDiv");
	}

	@Override
	public List<DivDeptCheckVO> admSelectDeptDivRow() {
		System.out.println("Dao admSelectDeptDivRow");
		return sqlSession.selectList("adm.admSelectDeptDiv");
	}

	@Override
	public List<LocationVO> admSelectLocRow() {
		System.out.println("Dao admSelectLocRow");
		return sqlSession.selectList("adm.admSelectLoc");
	}

	///////////////////// admin modify DIV////////////////////

	@Override
	public String admDivCheckYNRow(String divid) {
		System.out.println("Dao admDivCheckYNRow");
		return sqlSession.selectOne("adm.admDivCheckYN", divid);
	}

	@Override
	public int adminDivAddRow(String divid) {
		System.out.println("Dao adminDivAddRow");
		System.out.println("Y로 바꾸는거  " + divid);
		return sqlSession.update("adm.adminDivAdd", divid);
	}

	@Override
	public int adminDivDelRow(String divid) {
		System.out.println("Dao adminDivDelRow");
		System.out.println("N로 바꾸는거  " + divid);
		return sqlSession.update("adm.adminDivDelete", divid);
	}

	///////////////////// admin modify LOC////////////////////

	@Override
	public String admLocCheckYNRow(String locid) {
		System.out.println("Dao admLocCheckYNRow");
		return sqlSession.selectOne("adm.admLocCheckYN", locid);
	}

	@Override
	public int adminLocAddRow(String locid) {
		System.out.println("Dao adminLocAddRow");
		System.out.println("Y로 바꾸는거  " + locid);
		return sqlSession.update("adm.adminLocAdd", locid);
	}

	@Override
	public int adminLocDelRow(String locid) {
		System.out.println("Dao adminLocDelRow");
		System.out.println("N로 바꾸는거  " + locid);
		return sqlSession.update("adm.adminLocDelete", locid);
	}

	///////////////////// admin modify DEPT////////////////////

	@Override
	public String admDeptCheckYNRow(String deptid) {
		System.out.println("Dao admDeptCheckYNRow");
		return sqlSession.selectOne("adm.admDeptCheckYN", deptid);
	}

	@Override
	public int adminDeptAddRow(String deptid) {
		System.out.println("Dao adminDeptAddRow");
		System.out.println("Y로 바꾸는거  " + deptid);
		return sqlSession.update("adm.adminDeptAdd", deptid);
	}

	@Override
	public int adminDeptDelRow(String deptid) {
		System.out.println("Dao adminDeptDelRow");
		System.out.println("N로 바꾸는거  " + deptid);
		return sqlSession.update("adm.adminDeptDelete", deptid);
	}

	////////////// oracledaoimp//////////////
	@Override
	public List<DivisionCheckYnVO> admSelectDivYRow() {
		System.out.println("Dao admSelectDivYRow");
		return sqlSession.selectList("adm.admSelectDivY");
	}
}
