package model.domain.vo;


public class LocationlistVO { // ���̺� ���Ǿ� �ִ� ��ü�� Ŭ����ȭ 

	private int locnum;
	private String locname;
	
	public LocationlistVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LocationlistVO(int locnum, String locname) {
		super();
		this.locnum = locnum;
		this.locname = locname;
	}
	public int getLocnum() {
		return locnum;
	}
	public void setLocnum(int locnum) {
		this.locnum = locnum;
	}
	public String getLocname() {
		return locname;
	}
	public void setLocname(String locname) {
		this.locname = locname;
	}
	@Override
	public String toString() {
		return "LocationlistVO [locnum=" + locnum + ", locname=" + locname + "]";
	}

	
}
