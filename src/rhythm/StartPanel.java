package rhythm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StartPanel extends JPanel {
		// start버튼에 넣을 이미지
		private ImageIcon startButtonEnteredImage = new ImageIcon(
				Main.class.getResource("../images/startButtonEntered.png"));
		private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
		// start버튼
		private JButton startButton = new JButton(startButtonBasicImage);

		// quit버튼에 넣을 이미지
		private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
		private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
		// quit버튼
		private JButton quitButton = new JButton(quitButtonBasicImage);

		// how버튼에 넣을 이미지
		private ImageIcon howButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/howButtonEntered.png"));
		private ImageIcon howButtonBasicImage = new ImageIcon(Main.class.getResource("../images/howButtonBasic.png"));
		// how버튼
		private JButton howButton = new JButton(howButtonBasicImage);

		// record버튼에 넣을 이미지
		private ImageIcon recordButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/recordButtonEntered.png"));
		private ImageIcon recordButtonBasicImage = new ImageIcon(Main.class.getResource("../images/recordButtonBasic.png"));
		// record버튼
		private JButton recordButton = new JButton(recordButtonBasicImage);

		private RhythmTyping frame;
	
	public StartPanel(RhythmTyping rhythmTyping) {
		frame=rhythmTyping;
		setLayout(null); //패널의 Layout을 NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30,Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT-30);
		
		startButton.setBounds(440, 450, 400, 100);
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
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
				// 버튼 안보이게
				// introMusic 안들리게
				// enterMain();
				// TODO:selectMusicView로 이동
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
//			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
//				quitButton.setIcon(quitButtonEnteredImage);
//				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
//				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
//				quitButton.setIcon(quitButtonBasicImage);
//				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
//				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
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
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
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
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
				//기록 화면으로
				frame.change("recordPanel");

			}

		});
		add(recordButton);
		
	}

}
