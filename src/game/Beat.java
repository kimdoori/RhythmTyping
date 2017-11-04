package game;

public class Beat {

	private Double time;// 노트 타이밍
	private String type;//노트의 종류(알파벳/전첸)
	private String noteName;//  노트 이미지
	private String answer;//노트 답
	
	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
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

	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Beat(Double time, String type, String noteName,String answer) {
		super();
		this.time = time;
		this.type = type;
		this.noteName = noteName;
		this.answer=answer;
	}

}
