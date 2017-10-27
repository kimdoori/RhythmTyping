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
import rhythm.Main;
import screen.RhythmTyping;
import screen.SelectMusicPanel;

public class Game extends Thread {
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgeLine.png")).getImage();

	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	private Image judgeImage;

	private String titleName;
	private String musicTitle;

	boolean flag = false;

	public static Integer score = new Integer(0);
	RhythmTyping frame;
	public Music gameMusic;
	ArrayList<Beat> beats = new ArrayList<Beat>();
	

	ArrayList<Note> noteList = new ArrayList<Note>();
	public static Music introMusic=null;

	
	int noteSize=0;
	
	public Game(RhythmTyping rhythmTyping,String titleName, String musicTitle) {
		if(introMusic!=null)
			introMusic.close();
		
		frame=rhythmTyping;
		score=0;
		beats.clear();
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		System.out.println(titleName);

	}

	public void screenDraw(Graphics2D g) {

		g.drawImage(noteRouteImage, 0, 350, null);
		g.drawImage(judgementLineImage, 1006, 350, null);

		g.drawImage(judgeImage, 460, 250, null);

		// 노트그리기
		noteSize= noteList.size();
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

		g.setColor(Color.DARK_GRAY);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		g.drawString(titleName, 50, 61);
		g.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		g.drawString("노래 최고 점수   : " + RhythmTyping.score[SelectMusicPanel.songIndex][RhythmTyping.connectDB.rowcount-1], 700, 62);
		g.drawString("현재 점수   : " + score.toString(), 1000, 62);
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
		introMusic= new Music("Mr_Turtle.mp3", true);
		introMusic.start();
		
	}

	public void dropNotes(String titleName) {
		BMS bms=new BMS();
		beats=bms.initBeat(beats,SelectMusicPanel.bmsName[SelectMusicPanel.songIndex]);

		 AscendingObj ascending = new AscendingObj();
	     Collections.sort(beats, ascending);
		int i = 0;
		gameMusic.start();
		// 현재상태에서 노트가 떨어지지않은 경우에는 무한정반복하는 것이 아니라 텀을 두면서 노트를 떨어트릴 수 있기 때문에 훨씬 더 효율적
		// 애니메이션 매끄럽게
		int zeroCount=0;
		while (i < beats.size() && !isInterrupted()) {// 현재곡이 재생되는 시점을 실시간으로 확인해서 해당위치에 걸맞는 노트를 떨어트림
			boolean dropped = false;
			int musicTime=gameMusic.getTime();
			if (beats.get(i).getTime() <= gameMusic.getTime()) {// 노트가 떨어지는 시간대가 게임뮤직의 getTime보다 더작다면
				Note note = new Note(beats.get(i).getType(), beats.get(i).getNoteName());// 현재의 비트에서 노트이름을 얻어옴
				note.start();// 노트가 떨어지게
				noteList.add(note); // 노트 추가
				i++; // 각각의 노트들에 하나하나 접근해서 노트를 떨어트릴수있게
				dropped = true;
			}
			
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					// e.printStackTrace();

				}
			}
			
			if(musicTime == 0 && i>=5 && noteSize==0) {

				RhythmTyping.connectDB.updateScore(score.toString());
				frame.change("resultPanel");
				RhythmTyping.game.close();

				break;
			}

		}
	//	// 현재실행되고 있는 게임 종료
	//	


	}

	
	
	public void judge() {
		if(noteList==null)
			return;
		Note note = noteList.get(0);// 하나의 노트씩 얻어내서
		if ((RhythmTyping.input).toLowerCase().equals(note.getNoteType()) && "long".equals(note.getType())) {
			judgeEvent(note.judge());
			RhythmTyping.input = "";
		} else if ((RhythmTyping.input).toUpperCase().equals(note.getNoteType()) && "short".equals(note.getType())) {// 입력한
																									// 일치하다면
			// 해당노트의 판정을 가져와서 이미지 변경하는 함수에
			judgeEvent(note.judge());
			RhythmTyping.input = "";
		}
	}
	
	
	


	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {// 현재 판정이 none이 아닌경우
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