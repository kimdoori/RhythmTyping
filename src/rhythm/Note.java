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

	private boolean proceeded = true;// �����Ʈ�� ���࿩��

	public String getNoteType() {
		return noteType;

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

	public Note(String noteType) {
		y=400;
		this.noteType = noteType;
	}

	// ȭ�鿡 �׸���
	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);
		} else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);

		}
	}

	// ��Ʈ�� �������� �Լ�
	public void drop() {
		x += Main.NOTE_SPEED;
		// miss�� ���� ����
		if (x > 980) {
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
			System.out.println(e.getMessage());

		}

	}

	public String judge() {
		if (y >= 613) {
			System.out.println("Late");
			close();
			return "Late";
		} else if (y >= 600) {
			System.out.println("Good");
			close();
			return "Good";

		} else if (y >= 587) {
			System.out.println("Great");
			close();
			return "Great";

		} else if (y >= 573) {
			System.out.println("Perfect");
			close();
			return "Perfect";

		} else if (y >= 565) {
			System.out.println("Great");
			close();
			return "Great";

		} else if (y >= 550) {
			System.out.println("Good");
			close();
			return "Good";

		} else if (y >= 535) {
			System.out.println("Early");
			close();
			return "Early";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}

}
