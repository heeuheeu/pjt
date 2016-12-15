package model.domain.vo;


public class LocationVO { // ���̺� ���Ǿ� �ִ� ��ü�� Ŭ����ȭ 

	private String locid, locname, checkyn;

	public LocationVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationVO(String locid, String locname, String checkyn) {
		super();
		this.locid = locid;
		this.locname = locname;
		this.checkyn = checkyn;
	}

	public String getLocid() {
		return locid;
	}

	public void setLocid(String locid) {
		this.locid = locid;
	}

	public String getLocname() {
		return locname;
	}

	public void setLocname(String locname) {
		this.locname = locname;
	}

	public String getCheckyn() {
		return checkyn;
	}

	public void setCheckyn(String checkyn) {
		this.checkyn = checkyn;
	}
	
		
	
}
