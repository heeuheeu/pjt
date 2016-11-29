package model.sql;

import java.util.List;


import model.domain.vo.MemberVO;

public interface OracleDao {
	// DML�� �������� result set�� �ʿ� �����Ƿ� ���� Ÿ���� int
	public int insertRow(MemberVO member); 
	public int updateRow(MemberVO member);
	public int deleteRow(MemberVO member);
	
	public MemberVO loginRow(MemberVO member);
	// select�� return Ÿ���� list
	public List<MemberVO> selectRow();
	
	
	//조직도 띄우기
	public List<MemberVO> listRow();

	public MemberVO cartRow(MemberVO book);
	
}

