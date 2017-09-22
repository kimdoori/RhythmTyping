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
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgeLine.png")).getImage();

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/musicTrackLine.png")).getImage();

	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	private Image blueFlareImage;
	private Image judgeImage;

	private String titleName;
	private String difficulty;
	private String musicTitle;
	
	boolean flag = false;

	public static Integer score=new Integer(0);

	public static Music gameMusic;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);

	}

	public void screenDraw(Graphics2D g) {
		if (Game.gameMusic.getTime() % 500 < 3) {
			if (flag)
				RhythmTyping.background = new ImageIcon(Main.class.getResource("../images/backGround1.jpg"))
						.getImage();
			else
				RhythmTyping.background = new ImageIcon(Main.class.getResource("../images/backGround2.jpg"))
						.getImage();
			flag = !flag;
			System.out.println(flag);
		}
		
		

		g.drawImage(noteRouteImage, 0, 500, null);
		g.drawImage(judgementLineImage, 1000, 0, null);

		g.drawImage(judgeImage, 460, 310, null);

		// 노트그리기
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);

			if (note.getX() > 1120) {
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
		g.drawString(score.toString(), 565, 702);

	}

	// APEACH
	// CON
	// FRODO
	// JAY-G
	// NEO
	// MUZI
	// RYAN
	// TUBE
	//

	public void press() {
		judge();
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		// new Music(".mp3",flase).start();
		// APEACH
	}

	public void release() {
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
		// bpm = 132
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
			int gap = 250; // 최소 박자의 간격 8분의 1이니까 1000/8 //125
			// APEACH
			// CON
			// FRODO
			// JAY-G
			// NEO
			// MUZI
			// RYAN
			// TUBE

			beats = new Beat[] { new Beat(startTime, "short", "A"), new Beat(startTime + gap * 4, "short", "A"),
					new Beat(startTime + gap * 12, "long", "aenfl"), new Beat(startTime + gap * 18, "short", "A"),
					new Beat(startTime + gap * 24, "short", "A"), new Beat(startTime + gap * 26, "short", "A"),
					new Beat(startTime + gap * 28, "short", "A"), new Beat(startTime + gap * 30, "long", "apeach"),
					new Beat(startTime + gap * 32, "short", "A"), new Beat(startTime + gap * 36, "long", "apeach"),
					new Beat(startTime + gap * 48, "short", "A"), new Beat(startTime + gap * 58, "short", "A"),
					new Beat(startTime + gap * 62, "short", "A"), new Beat(startTime + gap * 66, "short", "A"),
					new Beat(startTime + gap * 72, "short", "A"), new Beat(startTime + gap * 76, "long", "A"),

			};

		} else if (titleName.equals("강남스타일") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "long", "Apeach"), };
		} else if (titleName.equals("무제") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "long", "Apeach"), };
		} else if (titleName.equals("무제") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "long", "Apeach"), };
		}
		int i = 0;
		gameMusic.start();
		

		// 현재상태에서 노트가 떨어지지않은 경우에는 무한정반복하는 것이 아니라 텀을 두면서 노트를 떨어트릴 수 있기 때문에 훨씬 더 효율적
		// 애니메이션 매끄럽게
		while (i < beats.length && !isInterrupted()) {// 현재곡이 재생되는 시점을 실시간으로 확인해서 해당위치에 걸맞는 노트를 떨어트림
			boolean dropped = false;
				
			if (beats[i].getTime() <= gameMusic.getTime()) {// 노트가 떨어지는 시간대가 게임뮤직의 getTime보다 더작다면
				Note note = new Note(beats[i].getType(), beats[i].getNoteName());// 현재의 비트에서 노트이름을 얻어옴
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

	public void judge() {
			Note note = noteList.get(0);// 하나의 노트씩 얻어내서
			System.out.println("RhythmTyping.input : "+RhythmTyping.input+"   note.getNoteType() :"+note.getNoteType()+"   note.getType() : "+note.getType());

			if ((RhythmTyping.input).toLowerCase().equals(note.getNoteType()) && "long".equals(note.getType())) {
				judgeEvent(note.judge());
				RhythmTyping.input="";
			}else if ((RhythmTyping.input).toUpperCase().equals(note.getNoteType()) && "short".equals(note.getType())) {// 입력한 input이 현재 노트의 타입과 일치하다면
				// 해당노트의 판정을 가져와서 이미지 변경하는 함수에
				judgeEvent(note.judge());
				RhythmTyping.input="";
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
