package game;

public class Beat {

	private Double time;// ��Ʈ Ÿ�̹�
	private String type;//��Ʈ�� ����(���ĺ�/��þ)
	private String noteName;//  ��Ʈ Ÿ��(��)

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

	public Beat(Double time, String type, String noteName) {
		super();
		this.time = time;
		this.type = type;
		this.noteName = noteName;
	}

}
