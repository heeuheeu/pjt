package model.domain.vo;

import java.util.List;

public class EmpIdVO {
	private String empidfav ;

	public EmpIdVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpIdVO(String empidfav) {
		super();
		this.empidfav = empidfav;
	}

	public String getEmpidfav() {
		return empidfav;
	}

	public void setEmpidfav(String empidfav) {
		this.empidfav = empidfav;
	}

	@Override
	public String toString() {
		return "EmpIdVO [empidfav=" + empidfav + "]";
	}

	
	
	
}
