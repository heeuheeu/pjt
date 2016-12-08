package model.domain.vo;

import java.util.List;

public class EmpIdVO {
	private List<String> chk ;

	public EmpIdVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpIdVO(List<String> chk) {
		super();
		this.chk = chk;
	}

	public List<String> getChk() {
		return chk;
	}

	public void setChk(List<String> chk) {
		this.chk = chk;
	} 
	
}
