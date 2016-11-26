package model.sql;

import java.util.List;
import model.domain.vo.MemberVO;

public interface OracleDao {
	// DML은 리턴으로 result set이 필요 없으므로 리턴 타입이 int
	public int insertRow(MemberVO member); 
	public int updateRow(MemberVO member);
	public int deleteRow(MemberVO member);
	
	public MemberVO loginRow(MemberVO member);
	// select는 return 타입이 list
	public List<MemberVO> selectRow();

	
}

