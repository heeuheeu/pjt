package model.domain.vo;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeDeptDivVO extends SearchVO{ 

	private String empimg, empid, emppwd, empname, empphone, empmail, emploc, deptid;
	private String deptname, divid;
	private String divname;
	
	private MultipartFile file;



	public EmployeeDeptDivVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmployeeDeptDivVO(MultipartFile file, String empimg, String empid, String emppwd, String empname, String empphone,
			String empmail, String emploc, String deptid, String deptname, String divid, String divname) {
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
		this.divname = divname;
		this.file = file;
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
	
	public String getDivname() {
		return divname;
	}


	public void setDivname(String divname) {
		this.divname = divname;
	}
	
	
	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}


	@Override
	public String toString() {
		return "EmployeeDeptDivVO [empimg=" + empimg + ", empid=" + empid + ", emppwd=" + emppwd + ", empname="
				+ empname + ", empphone=" + empphone + ", empmail=" + empmail + ", emploc=" + emploc + ", deptid="
				+ deptid + ", deptname=" + deptname + ", divid=" + divid + ", divname=" + divname + "]";
	}


}
