package musicList;

public class Track {

	private String titleName; // 곡 제목
	private String gameBackgroundImage; // 게임 배경화면
	private String gameMusic; // 해당 곡을 실행했을 때 음악

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
