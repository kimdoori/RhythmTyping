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

public class StartPanel extends JPanel {//시작화면

	// 시작버튼에 넣을 이미지
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("startButtonBasic.png"));
	// 시작버튼
	private JButton startButton = new JButton(startButtonBasicImage);

	// 방법버튼에 넣을 이미지
	private ImageIcon howButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("howButtonEntered.png"));
	private ImageIcon howButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("howButtonBasic.png"));
	// 방법버튼
	private JButton howButton = new JButton(howButtonBasicImage);

	// 기록버튼에 넣을 이미지
	private ImageIcon recordButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("recordButtonEntered.png"));
	private ImageIcon recordButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("recordButtonBasic.png"));
	// 기록버튼
	private JButton recordButton = new JButton(recordButtonBasicImage);

	//종료버튼
	private ImageIcon quitButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("quitButtonBasic.png"));
	// 종료버튼
	private JButton quitButton = new JButton(quitButtonBasicImage);

	// 로그아웃버튼에 넣을 이미지
	private ImageIcon logoutButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("logoutButtonEntered.png"));
	private ImageIcon logoutButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("logoutButtonBasic.png"));
	// 로그아웃버튼
	private JButton logoutButton = new JButton(logoutButtonBasicImage);

	private RhythmTyping frame;

	public StartPanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;
		setLayout(null); // 패널의 Layout을 NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT - 30);

		startButton.setBounds(118, 280, 200, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				startButton.setIcon(startButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				startButton.setIcon(startButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// TODO:화면전환
			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 로그인 또는 노래 선택 화면으로
				// 버튼 안보이게
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
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				howButton.setIcon(howButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				howButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				howButton.setIcon(howButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				howButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 방법화면으로
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
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				recordButton.setIcon(recordButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				recordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				recordButton.setIcon(recordButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				recordButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 기록화면으로
				// 기록 화면으로
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
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				logoutButton.setIcon(logoutButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				logoutButton.setIcon(logoutButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				logoutButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 로그아웃
				if (RhythmTyping.playID == null) {
					JOptionPane.showMessageDialog(null, "로그인이 되어있지않습니다.",
							"로그아웃", JOptionPane.INFORMATION_MESSAGE);

				} else {
					RhythmTyping.playID = null;
					RhythmTyping.playScore = null;
					RhythmTyping.playScore = new int[4];
					JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "로그아웃",
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
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				quitButton.setIcon(quitButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				quitButton.setIcon(quitButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 종료
				System.exit(0);

			}

		});
		add(quitButton);

	}

}
