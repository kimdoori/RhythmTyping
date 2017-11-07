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

import rhythm.RhythmMain;

public class StartPanel extends JPanel {//����ȭ��

	// ���۹�ư�� ���� �̹���
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("startButtonBasic.png"));
	// ���۹�ư
	private JButton startButton = new JButton(startButtonBasicImage);

	// �����ư�� ���� �̹���
	private ImageIcon howButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("howButtonEntered.png"));
	private ImageIcon howButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("howButtonBasic.png"));
	// �����ư
	private JButton howButton = new JButton(howButtonBasicImage);

	// ��Ϲ�ư�� ���� �̹���
	private ImageIcon recordButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("recordButtonEntered.png"));
	private ImageIcon recordButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("recordButtonBasic.png"));
	// ��Ϲ�ư
	private JButton recordButton = new JButton(recordButtonBasicImage);

	//�����ư
	private ImageIcon quitButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("quitButtonBasic.png"));
	// �����ư
	private JButton quitButton = new JButton(quitButtonBasicImage);

	// �α׾ƿ���ư�� ���� �̹���
	private ImageIcon logoutButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("logoutButtonEntered.png"));
	private ImageIcon logoutButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("logoutButtonBasic.png"));
	// �α׾ƿ���ư
	private JButton logoutButton = new JButton(logoutButtonBasicImage);

	private RhythmTyping frame;

	public StartPanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;
		setLayout(null); // �г��� Layout�� NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT - 30);

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
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ �� �α��� �Ǵ� �뷡 ���� ȭ������
				// ��ư �Ⱥ��̰�
				if (RhythmTyping.playID != null)
					frame.change("selectMusicPanel");
				else
					frame.change("loginPanel");

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
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ �� ���ȭ������
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
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ �� ���ȭ������
				// ��� ȭ������
				RhythmTyping.connectDB.getSongRecord();
				frame.change("recordPanel");
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
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ �� �α׾ƿ�
				if (RhythmTyping.playID == null) {
					JOptionPane.showMessageDialog(null, "�α����� �Ǿ������ʽ��ϴ�.",
							"�α׾ƿ�", JOptionPane.INFORMATION_MESSAGE);

				} else {
					RhythmTyping.playID = null;
					RhythmTyping.playScore = null;
					RhythmTyping.playScore = new int[4];
					JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�.", "�α׾ƿ�",
							JOptionPane.INFORMATION_MESSAGE);
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
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ �� ����
				System.exit(0);

			}

		});
		add(quitButton);

	}

}
