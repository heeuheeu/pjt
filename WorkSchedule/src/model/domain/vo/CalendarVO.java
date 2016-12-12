package model.domain.vo;

public class CalendarVO {

	private String empid, emppwd, empname, empphone, empmail, emploc, deptid;
	private String workdate;
	private String amloc, amlocdetail, pmloc, pmlocdetail, deptname, divid;
	public CalendarVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CalendarVO(String empid, String emppwd, String empname, String empphone, String empmail,
			String emploc, String deptid, String workdate, String amloc, String amlocdetail, String pmloc,
			String pmlocdetail, String deptname, String divid) {
		super();
		this.empid = empid;
		this.emppwd = emppwd;
		this.empname = empname;
		this.empphone = empphone;
		this.empmail = empmail;
		this.emploc = emploc;
		this.deptid = deptid;
		this.workdate = workdate;
		this.amloc = amloc;
		this.amlocdetail = amlocdetail;
		this.pmloc = pmloc;
		this.pmlocdetail = pmlocdetail;
		this.deptname = deptname;
		this.divid = divid;
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
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	public String getAmloc() {
		return amloc;
	}
	public void setAmloc(String amloc) {
		this.amloc = amloc;
	}
	public String getAmlocdetail() {
		return amlocdetail;
	}
	public void setAmlocdetail(String amlocdetail) {
		this.amlocdetail = amlocdetail;
	}
	public String getPmloc() {
		return pmloc;
	}
	public void setPmloc(String pmloc) {
		this.pmloc = pmloc;
	}
	public String getPmlocdetail() {
		return pmlocdetail;
	}
	public void setPmlocdetail(String pmlocdetail) {
		this.pmlocdetail = pmlocdetail;
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
