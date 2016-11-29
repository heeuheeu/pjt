package model.sql;

import java.util.List;

import model.domain.vo.DepartmentVO;
import model.domain.vo.DivisionVO;
import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.FavoriteVO;
import model.domain.vo.LocationlistVO;
import model.domain.vo.MemberVO;
import model.domain.vo.WorkVO;

public interface OracleDao {

	////////////////////////////////////////////////////////////////////////////////////// �ӽ�
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
	
	//plusbutton 누르면 조직도 띄우기
	public List<EmployeeDepartmentVO> listRow();
	//check하면 favorite 테이블에 insert
	public int addFavRow(String loginId, String valueArr);
	
}

