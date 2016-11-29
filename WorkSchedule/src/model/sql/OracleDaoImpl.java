package model.sql;

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
		return sqlSession.selectOne("member.login", member); // �ĺ���, �Ķ����. ��ü �ϳ��� ����. 
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
	public List<MemberVO> listRow() {
		System.out.println("Dao listrow");		
		return sqlSession.selectList("member.list");
	}


	@Override
	public MemberVO cartRow(MemberVO book) {
		System.out.println("dao cartrow");		
		return sqlSession.selectOne("member.getCart",book);
	}
	
	
	
}
