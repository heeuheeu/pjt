package model.domain.vo;

public class CalendarNoteVO {
	private String date, note;

	public CalendarNoteVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CalendarNoteVO(String date, String note) {
		super();
		this.date = date;
		this.note = note;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	
}
