package model.domain.vo;

public class DivisionCheckYnVO {
	private String divid, divname, checkyn;

	public DivisionCheckYnVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DivisionCheckYnVO(String divid, String divname, String checkyn) {
		super();
		this.divid = divid;
		this.divname = divname;
		this.checkyn = checkyn;
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

	public String getCheckyn() {
		return checkyn;
	}

	public void setCheckyn(String checkyn) {
		this.checkyn = checkyn;
	}
	
	
}
