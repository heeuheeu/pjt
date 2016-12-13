package model.domain.vo;

public class EmployeeDeptVO extends SearchVO{ 

	private String empimg, empid, emppwd, empname, empphone, empmail, emploc, deptid;
	private String deptname, divid;

	
	
	
	public EmployeeDeptVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmployeeDeptVO(String empimg, String empid, String emppwd, String empname, String empphone,
			String empmail, String emploc, String deptid, String deptname, String divid) {
		super();
		this.empimg = empimg;
		this.empid = empid;
		this.emppwd = emppwd;
		this.empname = empname;
		this.empphone = empphone;
		this.empmail = empmail;
		this.emploc = emploc;
		this.deptid = deptid;
		this.deptname = deptname;
		this.divid = divid;
	}


	public String getEmpimg() {
		return empimg;
	}


	public void setEmpimg(String empimg) {
		this.empimg = empimg;
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
	


}
