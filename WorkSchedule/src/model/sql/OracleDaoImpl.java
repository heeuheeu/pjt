package model.sql;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.domain.vo.FavoriteVO;



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
	public EmployeeVO loginEmp(EmployeeVO employee) {
		System.out.println("Dao loginEmp");
		return sqlSession.selectOne("member.loginemp",employee);
	}
	

	@Override
	public List<EmployeeDepartmentVO> listRow() {
		System.out.println("Dao listrow");		
		return sqlSession.selectList("member.pluslist");
	}
	
	@Override
	public int addFavRow(String loginId, String valueArr) {
		System.out.println("dao addFavRow");	
		FavoriteVO fav = new FavoriteVO(loginId, valueArr);
		int flag = sqlSession.insert("member.getCart", fav);
		return flag ; 
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
		//sqlSession.selectList("emp.selectfav", user);
		return sqlSession.selectList("emp.selectfavlist", user);
	}	
}
