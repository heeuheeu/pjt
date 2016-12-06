package model.sql;

import java.util.List;

import model.domain.vo.DepartmentVO;
import model.domain.vo.DivisionVO;
import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.domain.vo.FavoriteVO;
import model.domain.vo.LocationlistVO;
import model.domain.vo.MemberVO;
import model.domain.vo.WorkVO;

public interface OracleDao {

	////////////////////////////////////////////////////////////////////////////////////// �뜝�뙂�룞�삕
	public int insertRow(MemberVO member); 
	public int updateRow(MemberVO member);
	public int deleteRow(MemberVO member);	
	public MemberVO 			loginRow(MemberVO member); 
	public List<MemberVO> 		selectRow();
	//////////////////////////////////////////////////////////////////////////////////////
	
	public List<DepartmentVO> 	selectDept();
	public List<DivisionVO> 	selectDiv();
	public List<EmployeeVO> 	selectEmp();
	public EmployeeVO			loginEmp(EmployeeVO employee);
	public List<FavoriteVO> 	selectFav();
	public List<LocationlistVO> selectLoc();
	public List<WorkVO> 		selectWork();
	
	//plusbutton 占쎈듇�몴���늺 鈺곌퀣彛낉옙猷� 占쎌뱽占쎌뒭疫뀐옙
	public List<EmployeeDepartmentVO> listRow();
	//check占쎈릭筌롳옙 favorite 占쎈�믭옙�뵠�뇡遺용퓠 insert
	public int addFavRow(String loginId, String valueArr);
	
	// mylist 
	public int 							selectWorkRow(EmployeeWorkDeptVO user);	
	public EmployeeWorkDeptVO 			mylistRow1(EmployeeWorkDeptVO user); 
	public EmployeeWorkDeptVO 			mylistRow2(EmployeeWorkDeptVO user); 
	public List<EmployeeFavWorkDeptVO> 	selectFavRow(EmployeeVO user);
}

