package model.sql;

import java.util.List;

import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;


public interface OracleDao {

	
	//////////////////////////////////////////////////////////////////////////////////////
	public List<EmployeeVO> 	selectEmp();
	public EmployeeVO			loginEmp(EmployeeVO employee);
	
	//plusbutton 占쎈듇�몴���늺 鈺곌퀣彛낉옙猷� 占쎌뱽占쎌뒭疫뀐옙
	public List<EmployeeDepartmentVO> listRow();
	//check占쎈릭筌롳옙 favorite 占쎈�믭옙�뵠�뇡遺용퓠 insert
	public int addFavRow(String loginId, String valueArr);
	
	// mylist 
	public int 							selectWorkRow(EmployeeWorkDeptVO user);	
	public EmployeeWorkDeptVO 			mylistRow1(EmployeeWorkDeptVO user); 
	public EmployeeWorkDeptVO 			mylistRow2(EmployeeWorkDeptVO user); 
	public List<EmployeeFavWorkDeptVO> 	selectFavRow(EmployeeWorkDeptVO user);
}

