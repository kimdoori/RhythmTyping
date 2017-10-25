package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;

import musicList.Music;
import rhythm.Main;
import screen.RhythmTyping;

public class Game extends Thread {
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgeLine.png")).getImage();

	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	private Image judgeImage;

	private String titleName;
	private String musicTitle;

	boolean flag = false;

	public static Integer score = new Integer(0);

	public static Music gameMusic;
	ArrayList<Beat> beats = new ArrayList<Beat>();
	

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String musicTitle) {
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		System.out.println(titleName);

	}

	public void screenDraw(Graphics2D g) {

		g.drawImage(noteRouteImage, 0, 350, null);
		g.drawImage(judgementLineImage, 1006, 350, null);

		g.drawImage(judgeImage, 460, 250, null);

		// ��Ʈ�׸���
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);

			if (note.getX() > 1120) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
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

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("�����ٸ����", Font.BOLD, 30));
		g.drawString(titleName, 100, 60);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString(score.toString(), 565, 60);

	}

	public void press() {
		judge();
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}

	public void release() {
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}

	public void close() {
		gameMusic.close();//close
		this.interrupt();
	}

	public void dropNotes(String titleName) {
		if (titleName.equals("������Ÿ�� - PSY")) {
			// ��Ʈ�� ���޽ð��� ���ֹ��� �ʰ� �׻� ù��° �Ȱ��� ��Ʈ�� �����ٿ� �����ϴ� Ÿ�̹� ����
			System.out.println(titleName);
			beats=BMS.initBeat(beats,"gangnamstyle");
			
		}
		
		 AscendingObj ascending = new AscendingObj();
	     Collections.sort(beats, ascending);
		int i = 0;
		gameMusic.start();
		// ������¿��� ��Ʈ�� ������������ ��쿡�� �������ݺ��ϴ� ���� �ƴ϶� ���� �θ鼭 ��Ʈ�� ����Ʈ�� �� �ֱ� ������ �ξ� �� ȿ����
		// �ִϸ��̼� �Ų�����
		while (i < beats.size() && !isInterrupted()) {// ������� ����Ǵ� ������ �ǽð����� Ȯ���ؼ� �ش���ġ�� �ɸ´� ��Ʈ�� ����Ʈ��
			boolean dropped = false;

			if (beats.get(i).getTime() <= gameMusic.getTime()) {// ��Ʈ�� �������� �ð��밡 ���ӹ����� getTime���� ���۴ٸ�
				Note note = new Note(beats.get(i).getType(), beats.get(i).getNoteName());// ������ ��Ʈ���� ��Ʈ�̸��� ����
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
		}

	}

	
	
	public void judge() {
		Note note = noteList.get(0);// �ϳ��� ��Ʈ�� ����
		if ((RhythmTyping.input).toLowerCase().equals(note.getNoteType()) && "long".equals(note.getType())) {
			judgeEvent(note.judge());
			RhythmTyping.input = "";
		} else if ((RhythmTyping.input).toUpperCase().equals(note.getNoteType()) && "short".equals(note.getType())) {// �Է���
																									// ��ġ�ϴٸ�
			// �ش��Ʈ�� ������ �����ͼ� �̹��� �����ϴ� �Լ���
			judgeEvent(note.judge());
			RhythmTyping.input = "";
		}
	}
	
	
	


	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {// ���� ������ none�� �ƴѰ��
			// blueFlareImage = new
			// ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
		} else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
		} else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
		} else if (judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
		}
	}

}
class AscendingObj implements Comparator<Beat> {
	 
	@Override
	public int compare(Beat beat1, Beat beat2) {
		// TODO Auto-generated method stub
		return beat1.getTime().compareTo(beat2.getTime());

	}
 
}