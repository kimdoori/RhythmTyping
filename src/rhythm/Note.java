package rhythm;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME, y; // 생성될때 y축은 고정 //우리는 x축고정 시키면
																							// 됨 (판정라인고려해서) 판정라인
	// 580 //1초후에 닿음

	private String noteType;
	private String type;
	

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

	public Note(String type,String noteType) {
		y=350;
		this.type=type;
		this.noteType = noteType;
		noteBasicImage = new ImageIcon(Main.class.getResource("../images/note"+noteType+".png")).getImage();
	}

	// 화면에 그리기
	public void screenDraw(Graphics2D g) {
			g.drawImage(noteBasicImage, x, y, null);
	}

	// 노트가 떨어지는 함수
	public void drop() {
		x += Main.NOTE_SPEED;
		
		// miss에 대한 판정
		if (x > 1120) {
			RhythmTyping.input="";
			System.out.println("Miss");
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				
				// 1초에 700픽셀만큼 y좌표가 아래로 내려간다.
				drop();
				// 현재 노트가 계속해서 움직이고 있는 상황이라면
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);

				} else {
					interrupt();
					break;
				}
			}

		} catch (Exception e) {
		//	System.out.println(e.getMessage());

		}

	}

	public String judge() {
		if (x > 1106) {
			System.out.println("Late");
			close();
			return "Late";
		} else if (x >= 1070) {
			System.out.println("Good");
			close();
			Game.score+=100;
			return "Good";

		} else if (x >= 1050) {
			System.out.println("Great");
			close();
			Game.score+=150;
			return "Great";

		} else if (x >= 1035) {
			System.out.println("Perfect");
			close();
			Game.score+=200;
			return "Perfect";

		} else if (x >= 1020) {
			System.out.println("Great");
			close();
			Game.score+=150;
			return "Great";

		} else if (x >= 1000) {
			System.out.println("Good");
			close();
			Game.score+=100;
			return "Good";

		} else if (x >= 975) {
			System.out.println("Early");
			close();
			Game.score+=50;
			return "Early";
		}
		return "None";
	}
	
	public int getX() {
		return x;
	}

}
