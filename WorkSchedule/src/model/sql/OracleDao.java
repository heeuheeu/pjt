package model.sql;

import java.util.List;

import model.domain.vo.DeptDivisionVO;
import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeDeptVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;


public interface OracleDao {

	
	//////////////////////////////////////////////////////////////////////////////////////
	public List<EmployeeVO> 	selectEmp();
	public EmployeeVO			loginEmp(EmployeeVO employee);
	public int insertRow(EmployeeDeptVO emp); 	// sign up
	 
	// mylist 
	public int 							selectWorkRow(EmployeeWorkDeptVO user);	
	public EmployeeWorkDeptVO 			mylistRow1(EmployeeWorkDeptVO user); 
	public EmployeeWorkDeptVO 			mylistRow2(EmployeeWorkDeptVO user); 
	public List<EmployeeFavWorkDeptVO> 	selectFavRow(EmployeeWorkDeptVO user);
	
	 //////////////////eunbieunbi///////////////////
	 //plus button 
	 public List<EmployeeDeptVO> listRow();
	 //orga check
	 public int addFavRow(String loginId, String valueArr);
	 //select dept
	 public List<DeptDivisionVO> selectDeptDivRow();

	 ///////////////////////search////////////////////////////
	 public List<EmployeeDeptVO> searchEmpRow(EmployeeDeptVO member);


}

