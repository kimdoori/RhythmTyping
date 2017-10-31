package game;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import rhythm.RhythmMain;
import screen.RhythmTyping;

public class Note extends Thread {

	//노트 기본 이미지 --> 노트 종류와 답에 따라 바꿈
	private Image noteBasicImage = new ImageIcon(
			RhythmMain.class.getResource("../images/noteBasic.png")).getImage();
	
	private int x = 580 - (1000 / RhythmMain.SLEEP_TIME * RhythmMain.NOTE_SPEED)
			* RhythmMain.REACH_TIME, y=350; // 생성될때 x축과 y축


	private String type;//노트 종류(알파벳/ 전체)
	private String noteType;//노트 답

	private boolean proceeded = true;// 현재노트의 진행여부

	public String getNoteType() {
		return noteType;

	}

	public String getType() {
		return type;

	}

	public boolean isProceeded() {
		return proceeded;
	}

	// 노트를 성공적으로 입력해서 해당 노트를 더이상 이동하지 않게
	public void close() {
		proceeded = false;
	}

	public void setProceeded(boolean proceeded) {
		this.proceeded = proceeded;
	}

	public Note(String type, String noteType) {
		this.type = type;
		this.noteType = noteType;
		noteBasicImage = new ImageIcon(RhythmMain.class.getResource("../images/note"
				+ noteType + ".png")).getImage();
	}

	// 화면에 그리기
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
	}

	// 노트가 떨어지는 함수
	public void drop() {
		x += RhythmMain.NOTE_SPEED;

		// miss에 대한 판정
		if (x > 1120) {
			RhythmTyping.input = "";
			System.out.println("Miss");
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {

				drop();
				// 현재 노트가 계속해서 움직이고 있는 상황이라면
				if (proceeded) {
					Thread.sleep(RhythmMain.SLEEP_TIME);

				} else {
					interrupt();
					break;
				}
			}

		} catch (Exception e) {
			// System.out.println(e.getMessage());

		}

	}

	public String judge() {//답 스피드 판정
		if (x > 1106) {
			System.out.println("Late");
			close();
			Game.score += 20;
			return "Late";
		} else if (x >= 1070) {
			System.out.println("Good");
			close();
			Game.score += 150;
			return "Good";

		} else if (x >= 1050) {
			System.out.println("Great");
			close();
			Game.score += 220;
			return "Great";

		} else if (x >= 1035) {
			System.out.println("Perfect");
			close();
			Game.score += 300;
			return "Perfect";

		} else if (x >= 1020) {
			System.out.println("Great");
			close();
			Game.score += 220;
			return "Great";

		} else if (x >= 1000) {
			System.out.println("Good");
			close();
			Game.score += 150;
			return "Good";

		} else if (x >= 975) {
			System.out.println("Early");
			close();
			Game.score += 20;
			return "Early";
		}
		return "None";
	}

	public int getX() {
		return x;
	}

}
