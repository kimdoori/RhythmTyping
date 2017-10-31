package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import rhythm.RhythmMain;

public class GamePanel extends JPanel {//����ȭ��
	private RhythmTyping frame;

	//�ڷΰ��� ��ư
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			RhythmMain.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(
			RhythmMain.class.getResource("../images/backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);

	public GamePanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;

		setLayout(null); // �г��� Layout�� NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT - 30);

		backButton.setVisible(true);
		backButton.setBounds(30, 55, 60, 60);
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
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ �� �뷡����ȭ������
				RhythmTyping.game.close();// �������ǰ� �ִ� ���� ����
				frame.change("selectMusicPanel");
			}

		});
		add(backButton);

	}


}
