package model.domain.vo;


public class FavoriteVO { // ���̺� ���Ǿ� �ִ� ��ü�� Ŭ����ȭ 

	private String empid, empidfav;

	public FavoriteVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FavoriteVO(String empid, String empidfav) {
		super();
		this.empid = empid;
		this.empidfav = empidfav;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmpidfav() {
		return empidfav;
	}

	public void setEmpidfav(String empidfav) {
		this.empidfav = empidfav;
	}

	@Override
	public String toString() {
		return "FavoriteVO [empid=" + empid + ", empidfav=" + empidfav + "]";
	}
	
	

}
