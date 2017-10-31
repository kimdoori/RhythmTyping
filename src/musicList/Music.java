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
	// ������ ���ѹݺ����� �ѹ��������
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
			// player�� �ش������� ��´�.
			player = new Player(bis);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	// ���� ����ǰ� �ִ� ������ ���� ����ǰ��ִ���
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	// ���� ������ �� �ְ�
	public void close() {
		isLoop = false;
		player.close();
		// thread�� ����
		this.interrupt();
	}

	@Override
	public void run() {
		try {

			// �� ����
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				// player�� �ش������� ��´�.
				player = new Player(bis);

			} while (isLoop);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

}
