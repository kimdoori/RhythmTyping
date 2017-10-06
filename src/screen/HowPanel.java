package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import rhythm.Main;

public class HowPanel extends JPanel {
	private RhythmTyping frame;
	//TODO: ȭ�� ������
	// start��ư�� ���� �̹���
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/howToPlay.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/howToPlay.png"));
	// start��ư
	private JButton startButton = new JButton(startButtonBasicImage);
	
	public HowPanel(RhythmTyping rhythmTyping) {
		frame=rhythmTyping;
		setLayout(null); //�г��� Layout�� NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30,Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT-30);
		
		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				startButton.setIcon(startButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				startButton.setIcon(startButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// TODO:ȭ����ȯ
			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				// ��ư �Ⱥ��̰�
				// introMusic �ȵ鸮��
				// enterMain();
				// TODO:selectMusicView�� �̵�
				frame.change("selectMusicPanel");


			}

		});
		add(startButton);
	}

}
