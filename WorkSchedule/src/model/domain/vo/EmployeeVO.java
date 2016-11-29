package model.domain.vo;


public class EmployeeVO { // 테이블에 명세되어 있는 객체를 클래스화 

	private String empid, emppwd, empname, empgrade, empphone, empmail, emploc, deptid;
	
	public EmployeeVO() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	public EmployeeVO(String empid, String emppwd, String empname, String empgrade, String empphone, String empmail,
		String emploc, String deptid) {
		super();
		this.empid = empid;
		this.emppwd = emppwd;
		this.empname = empname;
		this.empgrade = empgrade;
		this.empphone = empphone;
		this.empmail = empmail;
		this.emploc = emploc;
		this.deptid = deptid;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmppwd() {
		return emppwd;
	}

	public void setEmppwd(String emppwd) {
		this.emppwd = emppwd;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpgrade() {
		return empgrade;
	}

	public void setEmpgrade(String empgrade) {
		this.empgrade = empgrade;
	}

	public String getEmpphone() {
		return empphone;
	}

	public void setEmpphone(String empphone) {
		this.empphone = empphone;
	}

	public String getEmpmail() {
		return empmail;
	}

	public void setEmpmail(String empmail) {
		this.empmail = empmail;
	}

	public String getEmploc() {
		return emploc;
	}

	public void setEmploc(String emploc) {
		this.emploc = emploc;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	@Override
	public String toString() {
		return "EmployeeVO [empid=" + empid + ", emppwd=" + emppwd + ", empname=" + empname + ", empgrade=" + empgrade
				+ ", empphone=" + empphone + ", empmail=" + empmail + ", emploc=" + emploc + ", deptid=" + deptid + "]";
	}


}
