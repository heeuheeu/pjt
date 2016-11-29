package model.domain.vo;


public class DivisionVO { // 테이블에 명세되어 있는 객체를 클래스화 

	private String divid, divname;

	public DivisionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DivisionVO(String divid, String divname) {
		super();
		this.divid = divid;
		this.divname = divname;
	}

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public String getDivname() {
		return divname;
	}

	public void setDivname(String divname) {
		this.divname = divname;
	}

	@Override
	public String toString() {
		return "DivisionVO [divid=" + divid + ", divname=" + divname + "]";
	}

	
}
