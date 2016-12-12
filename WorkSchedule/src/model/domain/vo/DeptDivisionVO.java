package model.domain.vo;


public class DeptDivisionVO { // ���̺� ���Ǿ� �ִ� ��ü�� Ŭ����ȭ 

	private String deptid, deptname, divid;
	private String divname;
	
	public DeptDivisionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public DeptDivisionVO(String deptid, String deptname, String divid, String divname) {
		super();
		this.deptid = deptid;
		this.deptname = deptname;
		this.divid = divid;
		this.divname = divname;
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

	public String getDivname() {
		return divname;
	}

	public void setDivname(String divname) {
		this.divname = divname;
	}

	
	
	
}
