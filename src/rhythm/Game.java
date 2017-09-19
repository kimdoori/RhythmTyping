package rhythm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Game extends Thread {
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/musicTrackLine.png")).getImage();

	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	private Image blueFlareImage;
	private Image judgeImage;
	
	private String titleName;
	private String difficulty;
	private String musicTitle;

	private Music gameMusic;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		

	}

	public void screenDraw(Graphics2D g) {
		
		g.drawImage(noteRouteImage, 0, 400, null);

		// ��Ʈ�׸���
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			
			if(note.getY() >620) {
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
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		g.setColor(Color.DARK_GRAY);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("00000", 565, 702);

	}

	
//	APEACH
//	CON
//	FRODO
//	JAY-G
//	NEO
//	MUZI
//	RYAN
//	TUBE
//	
	
	public void pressA() {
		System.out.println("a");
		judge("S");
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		// new Music(".mp3",flase).start();
		//APEACH
	}

	public void releaseA() {
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void pressC() {
		judge("C");
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
//		CON

	}

	public void releaseC() {
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void pressF() {
		judge("F");
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
//		FRODO

	}

	public void releaseF() {
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
//		JAY-G

	}

	public void releaseJ() {
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void pressN() {
		judge("N");
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
//		NEO

	}

	public void releaseN() {
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void pressM() {
		judge("M");
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();

	}//	MUZI


	public void releaseM() {
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void pressR() {
		judge("L");
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	
	}//	RYAN


	public void releaseR() {
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressT() {
		judge("T");
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	
	}//	TUBE



	public void releaseT() {
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}


	
	
	@Override
	public void run() {
		dropNotes(this.titleName);
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName) {
		// �ϳ��� � ���� ��� ��Ʈ: ���ϴ� ��Ʈ�� ���ϴ� ��Ʈ��
		// ���ϴ� ���� �Ǻ��� ���ؼ� bpm�� ���� : bpm -->1�п� ������ ��Ʈ�� ��
		// 4���� 4������ ��� bpm�� 60�̶�� �� ���ڴ� 1�ʰ� �Ǵ� ���̴�
		//bpm = 132
		//
		// ù��° ���ڰ� �����ϴ� �� �ð��븦 ������.
		// Beat[] beats= {
		// new Beat(1000,"S"),
		// new Beat(2000,"D"),
		// new Beat(3000,"F"),
		// };
		Beat[] beats = null;
		if (titleName.equals("������Ÿ��") && difficulty.equals("Easy")) {
			// ��Ʈ�� ���޽ð��� ���ֹ��� �ʰ� �׻� ù��° �Ȱ��� ��Ʈ�� �����ٿ� �����ϴ� Ÿ�̹� ����
			int startTime = 1000;// 4460 - Main.REACH_TIME * 
			int gap = 125; // �ּ� ������ ���� 8���� 1�̴ϱ� 1000/8

			beats = new Beat[] { new Beat(startTime, "S"), new Beat(startTime + gap * 4, "D"),
					new Beat(startTime + gap * 6, "S"), new Beat(startTime + gap * 8, "D"),
					new Beat(startTime + gap * 14, "S"), new Beat(startTime + gap * 16, "D"),
					new Beat(startTime + gap * 18, "S"), new Beat(startTime + gap * 20, "J"),
					new Beat(startTime + gap * 22, "K"), new Beat(startTime + gap * 26, "J"),
					new Beat(startTime + gap * 30, "K"), new Beat(startTime + gap * 36, "S"),
					new Beat(startTime + gap * 40, "F"), new Beat(startTime + gap * 44, "S"),
					new Beat(startTime + gap * 48, "K"), new Beat(startTime + gap * 54, "J"),

			};

		} else if (titleName.equals("������Ÿ��") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space"), };
		} else if (titleName.equals("����") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space"), };
		} else if (titleName.equals("����") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space"), };
		}
		int i = 0;
		gameMusic.start();

		// ������¿��� ��Ʈ�� ������������ ��쿡�� �������ݺ��ϴ� ���� �ƴ϶� ���� �θ鼭 ��Ʈ�� ����Ʈ�� �� �ֱ� ������ �ξ� �� ȿ����
		// �ִϸ��̼� �Ų�����
		while (i < beats.length && !isInterrupted()) {// ������� ����Ǵ� ������ �ǽð����� Ȯ���ؼ� �ش���ġ�� �ɸ´� ��Ʈ�� ����Ʈ��
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {// ��Ʈ�� �������� �ð��밡 ���ӹ����� getTime���� ���۴ٸ�
				Note note = new Note(beats[i].getNoteName());// ������ ��Ʈ���� ��Ʈ�̸��� ����
				note.start();// ��Ʈ�� ��������
				noteList.add(note); // ��Ʈ �߰�
				i++; // ������ ��Ʈ�鿡 �ϳ��ϳ� �����ؼ� ��Ʈ�� ����Ʈ�����ְ�
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}

	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);// �ϳ��� ��Ʈ�� ����
			if (input.equals(note.getNoteType())) {// �Է��� input�� ���� ��Ʈ�� Ÿ�԰� ��ġ�ϴٸ�
				//�ش��Ʈ�� ������ �����ͼ� �̹��� �����ϴ� �Լ���
				judgeEvent(note.judge());
				break;

			}

		}
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {// ���� ������ none�� �ƴѰ��
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
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
