package model.domain.vo;

import java.sql.Date;

public class MemberVO { // 테이블에 명세되어 있는 객체를 클래스화 

	private String 	id, pwd, name; 
	private int 	salary;
	private String	dept;
	private Date  	hiredate;

	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberVO(String id, String pwd, String name, Date hiredate, int salary, String dept) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.hiredate = hiredate;
		this.salary = salary;
		this.dept = dept;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", hiredate=" + hiredate + ", salary="
				+ salary + ", dept=" + dept + "]";
	}
	
	
	
}
