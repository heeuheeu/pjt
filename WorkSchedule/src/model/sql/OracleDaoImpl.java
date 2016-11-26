package model.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.domain.vo.MemberVO;


@Repository("UserDao")
public class OracleDaoImpl implements OracleDao { 	

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberVO loginRow(MemberVO member) {
		System.out.println("Dao loginRow");
		return sqlSession.selectOne("member.login", member); // 식별자, 파라미터. 객체 하나만 전달. 
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
	
}
