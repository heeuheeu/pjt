package model.domain.vo;


public class DepartmentVO { // 테이블에 명세되어 있는 객체를 클래스화 

	private String deptid, deptname, divid;

	public DepartmentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartmentVO(String deptid, String deptname, String divid) {
		super();
		this.deptid = deptid;
		this.deptname = deptname;
		this.divid = divid;
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

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	@Override
	public String toString() {
		return "DepartmentVO [deptid=" + deptid + ", deptname=" + deptname + ", divid=" + divid + "]";
	}
	
	
}
