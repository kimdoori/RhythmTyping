package musicList;

public class Track {

	private String titleName; // �� ����
	private String gameBackgroundImage; // ���� ���ȭ��
	private String gameMusic; // �ش� ���� �������� �� ����

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getGameBackgroundImage() {
		return gameBackgroundImage;
	}

	public void setGameBackgroundImage(String gameBackgroundImage) {
		this.gameBackgroundImage = gameBackgroundImage;
	}

	public String getGameMusic() {
		return gameMusic;
	}

	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}

	public Track(String titleName, String gameBackgroundImage, String gameMusic) {
		super();
		this.titleName = titleName;
		this.gameBackgroundImage = gameBackgroundImage;
		this.gameMusic = gameMusic;
	}

}
