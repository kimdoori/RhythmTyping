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

		// 노트그리기
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			
			if(note.getY() >620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
				note.close();
			}
			// 노트가 작동하고 있는 상태가 아니라면
			if (!note.isProceeded()) {
				// 노트리스트에서 지움
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
		// 하나의 곡에 대한 모든 비트: 원하는 비트에 원하는 노트를
		// 원하는 곡의 악보를 구해서 bpm을 보자 : bpm -->1분에 나오는 비트의 수
		// 4분의 4박자의 곡에서 bpm이 60이라면 한 박자는 1초가 되는 것이다
		//bpm = 132
		//
		// 첫번째 박자가 시작하는 그 시간대를 구하자.
		// Beat[] beats= {
		// new Beat(1000,"S"),
		// new Beat(2000,"D"),
		// new Beat(3000,"F"),
		// };
		Beat[] beats = null;
		if (titleName.equals("강남스타일") && difficulty.equals("Easy")) {
			// 노트의 도달시간에 구애받지 않고 항상 첫번째 똑같은 노트가 판정바에 적중하는 타이밍 유지
			int startTime = 1000;// 4460 - Main.REACH_TIME * 
			int gap = 125; // 최소 박자의 간격 8분의 1이니까 1000/8

			beats = new Beat[] { new Beat(startTime, "S"), new Beat(startTime + gap * 4, "D"),
					new Beat(startTime + gap * 6, "S"), new Beat(startTime + gap * 8, "D"),
					new Beat(startTime + gap * 14, "S"), new Beat(startTime + gap * 16, "D"),
					new Beat(startTime + gap * 18, "S"), new Beat(startTime + gap * 20, "J"),
					new Beat(startTime + gap * 22, "K"), new Beat(startTime + gap * 26, "J"),
					new Beat(startTime + gap * 30, "K"), new Beat(startTime + gap * 36, "S"),
					new Beat(startTime + gap * 40, "F"), new Beat(startTime + gap * 44, "S"),
					new Beat(startTime + gap * 48, "K"), new Beat(startTime + gap * 54, "J"),

			};

		} else if (titleName.equals("강남스타일") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space"), };
		} else if (titleName.equals("무제") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space"), };
		} else if (titleName.equals("무제") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space"), };
		}
		int i = 0;
		gameMusic.start();

		// 현재상태에서 노트가 떨어지지않은 경우에는 무한정반복하는 것이 아니라 텀을 두면서 노트를 떨어트릴 수 있기 때문에 훨씬 더 효율적
		// 애니메이션 매끄럽게
		while (i < beats.length && !isInterrupted()) {// 현재곡이 재생되는 시점을 실시간으로 확인해서 해당위치에 걸맞는 노트를 떨어트림
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {// 노트가 떨어지는 시간대가 게임뮤직의 getTime보다 더작다면
				Note note = new Note(beats[i].getNoteName());// 현재의 비트에서 노트이름을 얻어옴
				note.start();// 노트가 떨어지게
				noteList.add(note); // 노트 추가
				i++; // 각각의 노트들에 하나하나 접근해서 노트를 떨어트릴수있게
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
			Note note = noteList.get(i);// 하나의 노트씩 얻어내서
			if (input.equals(note.getNoteType())) {// 입력한 input이 현재 노트의 타입과 일치하다면
				//해당노트의 판정을 가져와서 이미지 변경하는 함수에
				judgeEvent(note.judge());
				break;

			}

		}
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {// 현재 판정이 none이 아닌경우
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
