package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import rhythm.RhythmMain;
import screen.GamePanel;
import screen.RhythmTyping;

public class Note extends Thread {

	//��Ʈ �⺻ �̹��� --> ��Ʈ ������ �信 ���� �ٲ�
	private Image noteBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("noteBasic.png")).getImage();
	
	private int x = 580 - (1000 / RhythmMain.SLEEP_TIME * RhythmMain.NOTE_SPEED)
			* RhythmMain.REACH_TIME, y=350; // �����ɶ� x��� y��


	private String answer;//��Ʈ��
	private String type;//��Ʈ ����(���ĺ�/ ��ü)
	private String noteType;//��Ʈ �̹���
	

	private boolean proceeded = true;// �����Ʈ�� ���࿩��

	public String getNoteType() {
		return noteType;

	}

	public String getType() {
		return type;

	}
	

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	// ��Ʈ�� ���������� �Է��ؼ� �ش� ��Ʈ�� ���̻� �̵����� �ʰ�
	public void close() {
		proceeded = false;
	}

	public void setProceeded(boolean proceeded) {
		this.proceeded = proceeded;
	}

	public Note(String type, String noteType,String answer) {
		this.type = type;
		this.noteType = noteType;
		this.answer=answer;
		noteBasicImage = new ImageIcon(getClass().getClassLoader().getResource("note"
				+ noteType + ".png")).getImage();
	}

	// ȭ�鿡 �׸���
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
		g.setColor(new Color (10,50, 50, 50));
		g.fillRect(x+20, y+100,10+answer.length()*10, 30);
		g.setColor(new Color (200,255, 255, 255));
		g.fillRect(1000, 620,200, 50);

		g.setColor(Color.BLACK);
		g.setFont(new Font("�����ٸ����", Font.PLAIN, 22));
		g.drawString(RhythmTyping.input, 1005,650);
		g.drawString(answer, x+20, y+120);
		
	}

	// ��Ʈ�� �������� �Լ�
	public void drop() {
		x += RhythmMain.NOTE_SPEED;

		// miss�� ���� ����
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
				// ���� ��Ʈ�� ����ؼ� �����̰� �ִ� ��Ȳ�̶��
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

	public String judge() {//�� ���ǵ� ����
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
