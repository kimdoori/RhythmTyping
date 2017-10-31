package musicList;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.ImageIcon;

import javazoom.jl.player.Player;
import rhythm.RhythmMain;

public class Music extends Thread {
	// player
	private Player player;
	// 음악이 무한반복인지 한번재생인지
	private boolean isLoop;

	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(RhythmMain.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			// player에 해당파일을 담는다.
			player = new Player(bis);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	// 현재 실행되고 있는 음악이 몇초 재생되고있는지
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	// 곡을 종료할 수 있게
	public void close() {
		isLoop = false;
		player.close();
		// thread를 중지
		this.interrupt();
	}

	@Override
	public void run() {
		try {

			// 곡 실행
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				// player에 해당파일을 담는다.
				player = new Player(bis);

			} while (isLoop);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

}
