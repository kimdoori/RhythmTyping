package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rhythm.Main;

public class StartPanel extends JPanel {

	// start��ư�� ���� �̹���
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	// start��ư
	private JButton startButton = new JButton(startButtonBasicImage);


	// how��ư�� ���� �̹���
	private ImageIcon howButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/howButtonEntered.png"));
	private ImageIcon howButtonBasicImage = new ImageIcon(Main.class.getResource("../images/howButtonBasic.png"));
	// how��ư
	private JButton howButton = new JButton(howButtonBasicImage);

	// record��ư�� ���� �̹���
	private ImageIcon recordButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/recordButtonEntered.png"));
	private ImageIcon recordButtonBasicImage = new ImageIcon(Main.class.getResource("../images/recordButtonBasic.png"));
	// record��ư
	private JButton recordButton = new JButton(recordButtonBasicImage);
	
		private ImageIcon quitButtonEnteredImage = new ImageIcon(
				Main.class.getResource("../images/quitButtonEntered.png"));
		private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
		// record��ư
		private JButton quitButton = new JButton(quitButtonBasicImage);
	
		// record��ư�� ���� �̹���
		private ImageIcon logoutButtonEnteredImage = new ImageIcon(
				Main.class.getResource("../images/logoutButtonEntered.png"));
		private ImageIcon logoutButtonBasicImage = new ImageIcon(Main.class.getResource("../images/logoutButtonBasic.png"));
		// record��ư
		private JButton logoutButton = new JButton(logoutButtonBasicImage);
		
	private RhythmTyping frame;

	public StartPanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;
		setLayout(null); // �г��� Layout�� NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT - 30);

		startButton.setBounds(118, 280, 200, 100);
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
				if(RhythmTyping.playID!=null)
					frame.change("selectMusicPanel");
				else
					frame.change("loginPanel");
				//frame.change("resultPanel");
				
				
			}

		});
		add(startButton);


		howButton.setBounds(118, 390, 200, 130);
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

		recordButton.setBounds(400, 380, 200, 100);
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
				// ��� ȭ������
				RhythmTyping.connectDB.getSongRecord();
				
				frame.change("recordPanel");
				//frame.change("resultPanel");
			}

		});
		add(recordButton);

		
		logoutButton.setBounds(1100, 600, 60, 60);
		logoutButton.setBorderPainted(false);
		logoutButton.setContentAreaFilled(false);
		logoutButton.setFocusPainted(false);
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				logoutButton.setIcon(logoutButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				logoutButton.setIcon(logoutButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				logoutButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				if(RhythmTyping.playID==null) {
					JOptionPane.showMessageDialog(null,"�α����� �Ǿ������ʽ��ϴ�.", "�α׾ƿ�",JOptionPane.INFORMATION_MESSAGE);

				}
				else {
				RhythmTyping.playID=null;
				RhythmTyping.playScore=null;
				RhythmTyping.playScore= new String[4];
				JOptionPane.showMessageDialog(null,"�α׾ƿ� �Ǿ����ϴ�.", "�α׾ƿ�",JOptionPane.INFORMATION_MESSAGE);
				}

			}

		});
		add(logoutButton);
		
		
		quitButton.setBounds(1200, 600, 60, 60);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				quitButton.setIcon(quitButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				quitButton.setIcon(quitButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				System.exit(0);

			}

		});
		add(quitButton);
		
		
	}

}
