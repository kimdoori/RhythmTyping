package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import rhythm.RhythmMain;

public class RecordPanel extends JPanel {//기록화면
	private RhythmTyping frame;

	//뒤로가기 버튼
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			RhythmMain.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(
			RhythmMain.class.getResource("../images/backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);

	public RecordPanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;
		setLayout(null); // 패널의 Layout을 NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT - 30);

		backButton.setVisible(true);
		backButton.setBounds(30, 20, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				backButton.setIcon(backButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				backButton.setIcon(backButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 시작화면으로
				frame.change("startPanel");

			}

		});
		add(backButton);

		int[] x = { 0, 640, 0, 640 };
		int[] y = { 0, 0, 300, 300 };
		String[] songTitle = { "강남스타일 - PSY", "D - 딘", "빨간맛 - 레드벨벳",
				"삐딱하게 - GD" };
		for (int i = 0; i < 4; i++) {
			JScrollPane songScrollPanel = new JScrollPane();
			songScrollPanel
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			songScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
			songScrollPanel.setBorder(new LineBorder(
					new Color(255, 50, 50, 50), 2));

			songScrollPanel.setBackground(new Color(255, 0, 0, 0));
			songScrollPanel.setBounds(0 + x[i], 150 + y[i], 640, 200);

			JPanel songListPanel = new JPanel();
			songListPanel.setPreferredSize(new Dimension(225, 1000));// 스크롤뷰생성
			songListPanel.setLayout(null);
			songListPanel.setBackground(new Color(255, 0, 0, 0)); // 컴포넌트 배경을
																	// 하얀색으로

			songScrollPanel.setViewportView(songListPanel);

			JLabel songInfo = new JLabel(songTitle[i]);
			Font infoFont = new Font("나눔바른고딕", Font.PLAIN, 25);
			songInfo.setBounds(x[i] + 200, y[i] + 100, 400, 50);
			songInfo.setFont(infoFont);
			add(songInfo);

			for (int j = 0; j < RhythmTyping.score[i].length; j++) {
				// 노래 패널
				JPanel songPanel = new JPanel();
				songPanel.setBackground(new Color(255, 10, 10, 10)); // 컴포넌트 배경을
																		// 하얀색으
				songPanel.setLayout(null);
				songPanel.setBounds(0, j * 50, 640, 50);

				JLabel line = new JLabel("");
				line.setOpaque(true);// 이거를 사용해야함
				line.setBackground(new Color(100, 50, 50, 50));
				line.setBounds(0, 0, 640, 2);
				songPanel.add(line);

				Font font = new Font("나눔바른고딕", Font.PLAIN, 15);

				JLabel score = new JLabel("점수     :  "
						+ RhythmTyping.score[i][j]);
				score.setBounds(300, 2, 400, 50);
				score.setFont(font);

				JLabel id = new JLabel("아이디     :  " + RhythmTyping.user[i][j]);
				id.setBounds(100, 2, 500, 50);
				id.setFont(font);

				songPanel.add(score);
				songPanel.add(id);
				songListPanel.add(songPanel);

			}

			add(songScrollPanel);

		}
	}

}
