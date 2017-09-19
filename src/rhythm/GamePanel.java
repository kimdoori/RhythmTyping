package rhythm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GamePanel extends JPanel {
	private RhythmTyping frame;

	// start��ư�� ���� �̹���
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	// start��ư
	private JButton startButton = new JButton(startButtonBasicImage);

	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);

	private Image background = new ImageIcon(Main.class.getResource("../images/backGround.png")).getImage();

	public GamePanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;

		setLayout(null); // �г��� Layout�� NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT - 30);

		backButton.setVisible(true);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				backButton.setIcon(backButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				backButton.setIcon(backButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				// ����ȭ������ ���ư��� �̺�Ʈ
				// backMain();

			}

		});
		add(backButton);

	}

}
