package rhythm;

import screen.RhythmTyping;

public class RhythmMain {
	public static final int SCREEN_WIDTH=1280;
	public static final int SCREEN_HEIGHT=720;
	public static final int NOTE_SPEED=3;
	public static final int SLEEP_TIME=10;
	public static final int REACH_TIME=2;//노트가 생성되고 나서 판정바에 도달하기 까지의 시간
	

	public static void main(String[] args) {
		new RhythmTyping();

	}

}
