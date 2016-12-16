package model.sql;

import java.util.List;

import model.domain.vo.CalendarVO;
import model.domain.vo.DeptDivisionVO;
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


public interface OracleDao {


	//////////////////////////////////////////////////////////////////////////////////////
	public List<EmployeeVO> 			selectEmp();
	public EmployeeVO					loginEmp(EmployeeVO employee);
	public int 							insertRow(EmployeeDeptDivVO emp);  // sign up 수정	
	public List<String> 				selectboxDeptRow(String divname);
	public int 							insertDefWorkRow(WorkVO emp); 	// sign up	
	public EmployeeDeptVO 				idCheckRow(String empid);
	 
	// mylist 
	public int 							selectWorkRow(EmployeeWorkDeptVO user);	
	public EmployeeDeptDivVO			selectEmpRow(EmployeeDeptDivVO user);
	public EmployeeWorkDeptVO 			mylistRow1(EmployeeWorkDeptVO user); 
	public EmployeeWorkDeptVO 			mylistRow2(EmployeeWorkDeptVO user); 
	public List<EmpIdVO> 				selectFavIdRow(String userid);
	public List<EmployeeFavWorkDeptVO> 	selectFavRow(EmployeeWorkDeptVO user);
	

	 // favorite 
	 public List<EmployeeDeptDivVO> 	listRow(EmployeeDeptDivVO user);	
	 public int 						addFavRow(String loginId, String chkid);
	 public int 						deleteFavRow(String loginId, String valueArr);
	 
	 // select dept
	 public List<DeptDivisionVO> 		selectDivRow();
	 public List<DeptDivisionVO> 		selectDeptDivRow();

	 // search
	 public List<EmployeeDeptDivVO> 	searchEmpRow(EmployeeDeptDivVO member);
	 public List<EmployeeDeptDivVO> 	searchDeptRow(EmployeeDeptDivVO member);
	 public List<EmployeeDeptDivVO> 	searchDivRow(EmployeeDeptDivVO member);
	 	 
	 // calendar
	 public List<EmployeeWorkDeptVO> 	myWorkRow(EmployeeWorkDeptVO user);
	 
	 // modal update
	 public int 						updateMyWork(EmployeeWorkDeptVO myinfo);
	 
	 // calendar modal	
	 public EmployeeWorkDeptVO 			selectCalModal(EmployeeWorkDeptVO myinfo);	 
	 
	 // update
	 public int 						updateRow(EmployeeDeptDivVO member);
	 public int 						updateWorkRow(EmployeeDeptDivVO member);

	 // dashboard
	 public List<EmployeeWorkDeptVO> 	selectDashRow(EmployeeWorkDeptVO myinfo);
	 
	 // admin
	 public List<LocationVO> 			selectlocRow();
	 public int 						addDivRow(DivisionVO div);
	 public int 						addDeptRow(DeptDivisionVO div);
	 public int 						addLocRow(LocationVO div);
	  
	// nfc update
	 public int 						updateNfcAm(NfcVO nfc);
	 public int 						updateNfcPm(NfcVO nfc);

	// delete work, emp
	 public int 						deleteWork(EmployeeDeptDivVO member);
	 public int 						deleteEmp(EmployeeDeptDivVO member);



}

