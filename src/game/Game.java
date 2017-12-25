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

	//판정라인
	private Image judgementLineImage = new ImageIcon(
			getClass().getClassLoader().getResource("judgeLine.png")).getImage();
	//노트 루트 이미지
	private Image noteRouteImage = new ImageIcon(
			getClass().getClassLoader().getResource("noteRoute.png")).getImage();

	//판정이미지
	private Image judgeImage;

	//곡이름
	private String titleName;
	//노래이름
	private String musicTitle;

	//게임점수
	public static Integer score = new Integer(0);
	
	//실행할 게임 음악
	private Music gameMusic;
	
	//bms에서 파싱해온 노트 정보
	ArrayList<Beat> beats = new ArrayList<Beat>();
	//출력할 노트 리스트
	ArrayList<Note> noteList = new ArrayList<Note>();

	//인트로뮤직
	public static Music introMusic = null;

	int noteSize = 0;

	public Game(RhythmTyping rhythmTyping, String titleName, String musicTitle) {
		if (introMusic != null)//introMusic이 실행중이라면 멈춘다.
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

		// 노트그리기
		noteSize = noteList.size();
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getX() > 1120) {
				judgeImage = new ImageIcon(
						getClass().getClassLoader().getResource("judgeMiss.png"))
						.getImage();
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
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		g.drawString(titleName, 50, 61);
		g.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		g.drawString("노래 최고 점수   : "
				+ RhythmTyping.score[SelectMusicPanel.songIndex][0], 700, 62);
		g.drawString("현재 점수   : " + score.toString(), 1000, 62);
	}

	public void press() {//키보드가 눌렸을 때
		if (noteList.size() != 0) {
			judge();
		} else {
			RhythmTyping.input = "";
		}
		noteRouteImage = new ImageIcon(
				getClass().getClassLoader().getResource("noteRoutePressed.png"))
				.getImage();
	}

	public void release() {//키보드가 떼졌을 때
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
		introMusic = new Music("music/Mr_Turtle.mp3", true);//intro 뮤직 실행
		introMusic.start();

	}

	public void dropNotes(String titleName) {
		BMS bms = new BMS();//bms 파일에서 비트정보를 파싱해온다.
		beats = bms.initBeat(beats,
				SelectMusicPanel.bmsName[SelectMusicPanel.songIndex]);

		AscendingObj ascending = new AscendingObj();//비트 오름차순 정렬
		Collections.sort(beats, ascending);
		
		int i = 0;
		gameMusic.start();
		// 현재상태에서 노트가 떨어지지않은 경우에는 무한정반복하는 것이 아니라 텀을 두면서 노트를 떨어트릴 수 있기 때문에 훨씬 더
		// 효율적
		// 애니메이션 매끄럽게
		while (i < beats.size() && !isInterrupted()) {// 현재곡이 재생되는 시점을 실시간으로
														// 확인해서 해당위치에 걸맞는 노트를
														// 떨어트림
			boolean dropped = false;
			int musicTime = gameMusic.getTime();
			if (beats.get(i).getTime() <= gameMusic.getTime()) {// 노트가 떨어지는 시간대가
																// 게임뮤직의
																// getTime보다
																// 더작다면
				Note note = new Note(beats.get(i).getType(), beats.get(i)
						.getNoteName(),beats.get(i).getAnswer());// 현재의 비트에서 노트이름을 얻어옴
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
			if (musicTime == 0 && i >= 5 && noteSize == 0) {//게임이 끝나면
				RhythmTyping.connectDB.updateScore(score.toString());
				frame.change("resultPanel");
				RhythmTyping.game.close();

				break;
			}

		}
	}

	public void judge() {//노트의 답이 맞는지 판단
		if (noteList.size() == 0) {
			RhythmTyping.input = "";
			return;
		}
		Note note = noteList.get(0);// 하나의 노트씩 얻어내서
		if ((RhythmTyping.input).toUpperCase().equals(note.getAnswer())
				&& "long".equals(note.getType())) {
			judgeEvent(note.judge());
			RhythmTyping.input = "";
		} else if ((RhythmTyping.input).toUpperCase()
				.equals(note.getAnswer()) && "short".equals(note.getType())) {// 입력한
			// //								// 일치하다면
			// 해당노트의 판정을 가져와서 이미지 변경하는 함수에
			judgeEvent(note.judge());
			RhythmTyping.input = "";
		}
	}

	public void judgeEvent(String judge) {//판단 후 이미지
		if (!judge.equals("None")) {// 현재 판정이 none이 아닌경우

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

class AscendingObj implements Comparator<Beat> {//노트 오름차순 정렬

	@Override
	public int compare(Beat beat1, Beat beat2) {
		return beat1.getTime().compareTo(beat2.getTime());

	}

}