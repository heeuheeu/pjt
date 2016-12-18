package model.domain.vo;

public class DivDeptCheckVO {
	
	//checkyn : department checkyn !!
	
	private String divid, divname, checkyn;

	private String deptid, deptname;

	public DivDeptCheckVO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public DivDeptCheckVO(String divid, String divname, String checkyn, String deptid, String deptname) {
		super();
		this.divid = divid;
		this.divname = divname;
		this.checkyn = checkyn;
		this.deptid = deptid;
		this.deptname = deptname;
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
	
	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	
}
