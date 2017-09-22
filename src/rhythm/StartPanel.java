package rhythm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StartPanel extends JPanel {
		// start��ư�� ���� �̹���
		private ImageIcon startButtonEnteredImage = new ImageIcon(
				Main.class.getResource("../images/startButtonEntered.png"));
		private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
		// start��ư
		private JButton startButton = new JButton(startButtonBasicImage);

		// quit��ư�� ���� �̹���
		private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
		private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
		// quit��ư
		private JButton quitButton = new JButton(quitButtonBasicImage);

		// how��ư�� ���� �̹���
		private ImageIcon howButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/howButtonEntered.png"));
		private ImageIcon howButtonBasicImage = new ImageIcon(Main.class.getResource("../images/howButtonBasic.png"));
		// how��ư
		private JButton howButton = new JButton(howButtonBasicImage);

		// record��ư�� ���� �̹���
		private ImageIcon recordButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/recordButtonEntered.png"));
		private ImageIcon recordButtonBasicImage = new ImageIcon(Main.class.getResource("../images/recordButtonBasic.png"));
		// record��ư
		private JButton recordButton = new JButton(recordButtonBasicImage);

		private RhythmTyping frame;
	
	public StartPanel(RhythmTyping rhythmTyping) {
		frame=rhythmTyping;
		setLayout(null); //�г��� Layout�� NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30,Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT-30);
		
		startButton.setBounds(440, 450, 400, 100);
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
		
		


//		quitButton.setBounds(40, 330, 400, 100);
//		quitButton.setBorderPainted(false);
//		quitButton.setContentAreaFilled(false);
//		quitButton.setFocusPainted(false);
//		quitButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
//				quitButton.setIcon(quitButtonEnteredImage);
//				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
//				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
//				quitButton.setIcon(quitButtonBasicImage);
//				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
//				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
//				System.exit(0);
//			}
//
//		});
//		add(quitButton);
		
		howButton.setBounds(440, 310, 260, 100);
		howButton.setBorderPainted(false);
		howButton.setContentAreaFilled(false);
		howButton.setFocusPainted(false);
		howButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				howButton.setIcon(howButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				howButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				howButton.setIcon(howButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				howButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				frame.change("howPanel");		
			}

		});
		add(howButton);
		
		recordButton.setBounds(740, 310, 100, 100);
		recordButton.setBorderPainted(false);
		recordButton.setContentAreaFilled(false);
		recordButton.setFocusPainted(false);
		recordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				recordButton.setIcon(recordButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				recordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				recordButton.setIcon(recordButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				recordButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				//��� ȭ������
				frame.change("recordPanel");

			}

		});
		add(recordButton);
		
	}

}
