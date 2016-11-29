package model.domain.vo;

import java.sql.Date;

public class WorkVO { // 테이블에 명세되어 있는 객체를 클래스화 

	private Date workdate;
	private String empid, amloc, amlocdetail, pmloc, pmlocdetail ;
	public WorkVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WorkVO(Date workdate, String empid, String amloc, String amlocdetail, String pmloc, String pmlocdetail) {
		super();
		this.workdate = workdate;
		this.empid = empid;
		this.amloc = amloc;
		this.amlocdetail = amlocdetail;
		this.pmloc = pmloc;
		this.pmlocdetail = pmlocdetail;
	}
	public Date getWorkdate() {
		return workdate;
	}
	public void setWorkdate(Date workdate) {
		this.workdate = workdate;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
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
	@Override
	public String toString() {
		return "WorkVO [workdate=" + workdate + ", empid=" + empid + ", amloc=" + amloc + ", amlocdetail=" + amlocdetail
				+ ", pmloc=" + pmloc + ", pmlocdetail=" + pmlocdetail + "]";
	}
	
	

}
