package model.sql;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


@Repository("UserDao")
public class OracleDaoImpl implements OracleDao { 	

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberVO loginRow(MemberVO member) {
		System.out.println("Dao loginRow");
		return sqlSession.selectOne("member.login", member); 
	}

	@Override
	public List<MemberVO> selectRow() {
		System.out.println("Dao selectRow");
		return sqlSession.selectList("member.selectlist");
	} 
	
	@Override
	public int insertRow(MemberVO member) {
		System.out.println("Dao insertRow");
		return sqlSession.insert("member.insert", member);
	}

	@Override
	public int updateRow(MemberVO member) {
		System.out.println("Dao updateRow");
		return sqlSession.update("member.update", member);
	}

	@Override
	public int deleteRow(MemberVO member) {
		System.out.println("Dao deleteRow");
		return sqlSession.delete("member.delete", member);
	}

	@Override
	public List<DepartmentVO> selectDept() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DivisionVO> selectDiv() {
		// TODO Auto-generated method stub
		return null;
	}

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
	public List<FavoriteVO> selectFav() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocationlistVO> selectLoc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkVO> selectWork() {
		// TODO Auto-generated method stub
		return null;
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
	public List<EmployeeFavWorkDeptVO> selectFavRow(EmployeeVO user) {
		System.out.println("Dao selectFavRow");		
		sqlSession.selectList("emp.selectfav", user);
		return sqlSession.selectList("emp.selectfavlist", user);
	}	
}
