package game;

public class Beat {
	
	private int time;//Ÿ�̹�
	private String type;
	private String noteName;//��Ʈ�� ����
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
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
	public Beat(int time,String type,String noteName) {
		super();
		this.time = time;
		this.type=type;
		this.noteName = noteName;
	}
	

}
