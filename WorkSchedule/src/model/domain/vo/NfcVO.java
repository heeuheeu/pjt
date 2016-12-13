package model.domain.vo;

public class NfcVO {

	private String loc;
	private String empid;
	public NfcVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NfcVO(String loc, String empid) {
		super();
		this.loc = loc;
		this.empid = empid;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	
	
}
