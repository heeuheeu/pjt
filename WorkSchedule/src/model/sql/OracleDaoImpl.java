package model.sql;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.domain.vo.CalendarVO;
import model.domain.vo.DeptDivisionVO;
import model.domain.vo.EmpIdVO;
import model.domain.vo.EmployeeDeptDivVO;
import model.domain.vo.EmployeeDeptVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.domain.vo.FavoriteVO;
import model.domain.vo.NfcVO;

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
	public int insertDefWorkRow(EmployeeDeptVO emp) {
		System.out.println("Dao insertRow");
		return sqlSession.insert("member.insertdefaultwork", emp);
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
	public List<EmployeeFavWorkDeptVO> selectFavRow(EmployeeWorkDeptVO user) {
		System.out.println("Dao selectFavRow");
		// sqlSession.selectList("emp.selectfavid", user);
		return sqlSession.selectList("emp.selectfavlist", user);
	}

	@Override
	public List<EmpIdVO> selectFavIdRow(String userid) {
		System.out.println("Dao selectFavidRow");
		return sqlSession.selectList("emp.selectfavid", userid);
	}

	///////////////////////// eunbieunbi/////////////////////////////

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
	///////////////////////// eunbieunbi/////////////////////////////

	/////////////////////////////// search////////////////////////////////////
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

	//////////////////////////////////////////////////////////////////////////

	// calendar
	@Override
	public List<CalendarVO> myWorkRow(CalendarVO user) {
		System.out.println("Dao myWorkRow");
		return sqlSession.selectList("cal.selectmywork", user);
	}

	// modal update
	@Override
	public int updateMyWork(EmployeeWorkDeptVO myinfo) {
		System.out.println("Dao updateWorkRow");
		return sqlSession.update("emp.updatemywork", myinfo);
	}

	/////////////// updateRow////////////////
	/////////////////////////////////////////// 2016-12-09/////////////////////////////////
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



}
