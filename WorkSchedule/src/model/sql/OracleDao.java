package model.sql;

import java.util.List;

import model.domain.vo.CalendarVO;
import model.domain.vo.DeptDivisionVO;
import model.domain.vo.EmpIdVO;
import model.domain.vo.EmployeeDeptDivVO;
import model.domain.vo.EmployeeDeptVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;


public interface OracleDao {



	//////////////////////////////////////////////////////////////////////////////////////
	public List<EmployeeVO> 	selectEmp();
	public EmployeeVO			loginEmp(EmployeeVO employee);
	public int insertRow(EmployeeDeptDivVO emp);  // sign up 수정
	public List<DeptDivisionVO> selectDivRow();
	public int insertDefWorkRow(EmployeeDeptVO emp); 	// sign up
	
	public EmployeeDeptVO idCheckRow(String empid);
	 
	// mylist 
	public int 							selectWorkRow(EmployeeWorkDeptVO user);	
	public EmployeeWorkDeptVO 			mylistRow1(EmployeeWorkDeptVO user); 
	public EmployeeWorkDeptVO 			mylistRow2(EmployeeWorkDeptVO user); 
	public List<EmpIdVO> 				selectFavIdRow(String userid);
	public List<EmployeeFavWorkDeptVO> 	selectFavRow(EmployeeWorkDeptVO user);
	
	 //////////////////eunbieunbi///////////////////
	 //plus button 
	 public List<EmployeeDeptDivVO> listRow(EmployeeDeptDivVO user);
	 //orga check
	 public int addFavRow(String loginId, String chkid);
	 public int deleteFavRow(String loginId, String valueArr);
	 //select dept
	 public List<DeptDivisionVO> selectDeptDivRow();

	 ///////////////////////search////////////////////////////
	 public List<EmployeeDeptDivVO> searchEmpRow(EmployeeDeptDivVO member);
	 public List<EmployeeDeptDivVO> searchDeptRow(EmployeeDeptDivVO member);
	 public List<EmployeeDeptDivVO> searchDivRow(EmployeeDeptDivVO member);
	 	 
	 // calendar
	 public List<CalendarVO> myWorkRow(CalendarVO user);
	 // modal update
	 public int updateMyWork(EmployeeWorkDeptVO myinfo);
	 
	 /////////////////// update///////////////
	 public int updateRow(EmployeeDeptVO member);
	 public int updateWorkRow(EmployeeDeptVO member);

	  // calendar modal
	  public EmployeeWorkDeptVO selectCalModal(EmployeeWorkDeptVO myinfo);
	  public List<EmployeeWorkDeptVO> selectDashRow(EmployeeWorkDeptVO myinfo);
}

