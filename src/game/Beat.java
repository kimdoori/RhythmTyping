package game;

public class Beat {
	
	private Integer time;//타이밍
	private String type;
	private String noteName;//노트의 종류
	
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public Beat(Integer time,String type,String noteName) {
		super();
		this.time = time;
		this.type=type;
		this.noteName = noteName;
	}
	

}
