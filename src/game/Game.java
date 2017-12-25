package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;

import musicList.Music;
import rhythm.RhythmMain;
import screen.GamePanel;
import screen.RhythmTyping;
import screen.SelectMusicPanel;

public class Game extends Thread {
	
	private RhythmTyping frame;

	//��������
	private Image judgementLineImage = new ImageIcon(
			getClass().getClassLoader().getResource("judgeLine.png")).getImage();
	//��Ʈ ��Ʈ �̹���
	private Image noteRouteImage = new ImageIcon(
			getClass().getClassLoader().getResource("noteRoute.png")).getImage();

	//�����̹���
	private Image judgeImage;

	//���̸�
	private String titleName;
	//�뷡�̸�
	private String musicTitle;

	//��������
	public static Integer score = new Integer(0);
	
	//������ ���� ����
	private Music gameMusic;
	
	//bms���� �Ľ��ؿ� ��Ʈ ����
	ArrayList<Beat> beats = new ArrayList<Beat>();
	//����� ��Ʈ ����Ʈ
	ArrayList<Note> noteList = new ArrayList<Note>();

	//��Ʈ�ι���
	public static Music introMusic = null;

	int noteSize = 0;

	public Game(RhythmTyping rhythmTyping, String titleName, String musicTitle) {
		if (introMusic != null)//introMusic�� �������̶�� �����.
			introMusic.close();

		frame = rhythmTyping;
		score = 0;
		beats.clear();
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music("music/"+this.musicTitle, false);

	}

	public void screenDraw(Graphics2D g) {

		g.drawImage(noteRouteImage, 0, 350, null);
		g.drawImage(judgementLineImage, 1006, 350, null);

		g.drawImage(judgeImage, 460, 250, null);

		// ��Ʈ�׸���
		noteSize = noteList.size();
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getX() > 1120) {
				judgeImage = new ImageIcon(
						getClass().getClassLoader().getResource("judgeMiss.png"))
						.getImage();
				note.close();
			}
			// ��Ʈ�� �۵��ϰ� �ִ� ���°� �ƴ϶��
			if (!note.isProceeded()) {
				// ��Ʈ����Ʈ���� ����
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}

		g.setColor(Color.DARK_GRAY);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("�����ٸ����", Font.BOLD, 30));
		g.drawString(titleName, 50, 61);
		g.setFont(new Font("�����ٸ����", Font.BOLD, 25));
		g.drawString("�뷡 �ְ� ����   : "
				+ RhythmTyping.score[SelectMusicPanel.songIndex][0], 700, 62);
		g.drawString("���� ����   : " + score.toString(), 1000, 62);
	}

	public void press() {//Ű���尡 ������ ��
		if (noteList.size() != 0) {
			judge();
		} else {
			RhythmTyping.input = "";
		}
		noteRouteImage = new ImageIcon(
				getClass().getClassLoader().getResource("noteRoutePressed.png"))
				.getImage();
	}

	public void release() {//Ű���尡 ������ ��
		noteRouteImage = new ImageIcon(
				getClass().getClassLoader().getResource("noteRoute.png")).getImage();

	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}

	public void close() {// close
		gameMusic.close();
		this.interrupt();
		introMusic = new Music("music/Mr_Turtle.mp3", true);//intro ���� ����
		introMusic.start();

	}

	public void dropNotes(String titleName) {
		BMS bms = new BMS();//bms ���Ͽ��� ��Ʈ������ �Ľ��ؿ´�.
		beats = bms.initBeat(beats,
				SelectMusicPanel.bmsName[SelectMusicPanel.songIndex]);

		AscendingObj ascending = new AscendingObj();//��Ʈ �������� ����
		Collections.sort(beats, ascending);
		
		int i = 0;
		gameMusic.start();
		// ������¿��� ��Ʈ�� ������������ ��쿡�� �������ݺ��ϴ� ���� �ƴ϶� ���� �θ鼭 ��Ʈ�� ����Ʈ�� �� �ֱ� ������ �ξ� ��
		// ȿ����
		// �ִϸ��̼� �Ų�����
		while (i < beats.size() && !isInterrupted()) {// ������� ����Ǵ� ������ �ǽð�����
														// Ȯ���ؼ� �ش���ġ�� �ɸ´� ��Ʈ��
														// ����Ʈ��
			boolean dropped = false;
			int musicTime = gameMusic.getTime();
			if (beats.get(i).getTime() <= gameMusic.getTime()) {// ��Ʈ�� �������� �ð��밡
																// ���ӹ�����
																// getTime����
																// ���۴ٸ�
				Note note = new Note(beats.get(i).getType(), beats.get(i)
						.getNoteName(),beats.get(i).getAnswer());// ������ ��Ʈ���� ��Ʈ�̸��� ����
				note.start();// ��Ʈ�� ��������
				noteList.add(note); // ��Ʈ �߰�
				i++; // ������ ��Ʈ�鿡 �ϳ��ϳ� �����ؼ� ��Ʈ�� ����Ʈ�����ְ�
				dropped = true;
			}

			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					// e.printStackTrace();

				}
			}
			if (musicTime == 0 && i >= 5 && noteSize == 0) {//������ ������
				RhythmTyping.connectDB.updateScore(score.toString());
				frame.change("resultPanel");
				RhythmTyping.game.close();

				break;
			}

		}
	}

	public void judge() {//��Ʈ�� ���� �´��� �Ǵ�
		if (noteList.size() == 0) {
			RhythmTyping.input = "";
			return;
		}
		Note note = noteList.get(0);// �ϳ��� ��Ʈ�� ����
		if ((RhythmTyping.input).toUpperCase().equals(note.getAnswer())
				&& "long".equals(note.getType())) {
			judgeEvent(note.judge());
			RhythmTyping.input = "";
		} else if ((RhythmTyping.input).toUpperCase()
				.equals(note.getAnswer()) && "short".equals(note.getType())) {// �Է���
			// //								// ��ġ�ϴٸ�
			// �ش��Ʈ�� ������ �����ͼ� �̹��� �����ϴ� �Լ���
			judgeEvent(note.judge());
			RhythmTyping.input = "";
		}
	}

	public void judgeEvent(String judge) {//�Ǵ� �� �̹���
		if (!judge.equals("None")) {// ���� ������ none�� �ƴѰ��

		}
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(
					getClass().getClassLoader().getResource("judgeMiss.png"))
					.getImage();
		} else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(
					getClass().getClassLoader().getResource("judgeLate.png"))
					.getImage();
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(
					getClass().getClassLoader().getResource("judgeGood.png"))
					.getImage();
		} else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(
					getClass().getClassLoader().getResource("judgeGreat.png"))
					.getImage();
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(
					getClass().getClassLoader().getResource("judgePerfect.png"))
					.getImage();
		} else if (judge.equals("Early")) {
			judgeImage = new ImageIcon(
					getClass().getClassLoader().getResource("judgeEarly.png"))
					.getImage();
		}
	}

}

class AscendingObj implements Comparator<Beat> {//��Ʈ �������� ����

	@Override
	public int compare(Beat beat1, Beat beat2) {
		return beat1.getTime().compareTo(beat2.getTime());

	}

}