package model.domain.vo;


public class DivisionVO { // ���̺� ���Ǿ� �ִ� ��ü�� Ŭ����ȭ 

	private String divid, divname, checkyn;



	public DivisionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DivisionVO(String divid, String divname, String checkyn) {
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
	
	@Override
	public String toString() {
		return "DivisionVO [divid=" + divid + ", divname=" + divname + "]";
	}

	
}
