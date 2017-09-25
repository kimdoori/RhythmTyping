package rhythm;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME, y; // �����ɶ� y���� ���� //�츮�� x����� ��Ű��
																							// �� (�������ΰ���ؼ�) ��������
	// 580 //1���Ŀ� ����

	private String noteType;
	private String type;
	

	private boolean proceeded = true;// �����Ʈ�� ���࿩��
	


	public String getNoteType() {
		return noteType;

	}
	public String getType() {
		return type;

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

	public Note(String type,String noteType) {
		y=350;
		this.type=type;
		this.noteType = noteType;
		noteBasicImage = new ImageIcon(Main.class.getResource("../images/note"+noteType+".png")).getImage();
	}

	// ȭ�鿡 �׸���
	public void screenDraw(Graphics2D g) {
			g.drawImage(noteBasicImage, x, y, null);
	}

	// ��Ʈ�� �������� �Լ�
	public void drop() {
		x += Main.NOTE_SPEED;
		
		// miss�� ���� ����
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
				
				// 1�ʿ� 700�ȼ���ŭ y��ǥ�� �Ʒ��� ��������.
				drop();
				// ���� ��Ʈ�� ����ؼ� �����̰� �ִ� ��Ȳ�̶��
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
