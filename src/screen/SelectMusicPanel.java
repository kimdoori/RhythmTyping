package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import musicList.Track;
import rhythm.RhythmMain;

public class SelectMusicPanel extends JPanel {//노래선택화면

	private RhythmTyping frame;

	// 뒤로가기버튼
	private ImageIcon homeButtonEnteredImage = new ImageIcon(
			RhythmMain.class.getResource("../images/homeButtonEntered.png"));
	private ImageIcon homeButtonBasicImage = new ImageIcon(
			RhythmMain.class.getResource("../images/homeButtonBasic.png"));
	private JButton homeButton = new JButton(homeButtonBasicImage);

	//노래플레이버튼
	JButton goButton = new JButton();

	//고른 노래
	public static int songIndex = 0;
	
	//파싱할 bms 파일 이름
	public static final String[] bmsName = { "gangnamstyle", "D", "redflavor",
			"cracked" };

	//노래목록
	public static ArrayList<Track> trackList = new ArrayList<Track>();

	public SelectMusicPanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;

		trackList.clear();
		trackList.add(new Track("강남스타일 - PSY", "gangnamstyle.png",
				"PSY-강남스타일.mp3"));
		trackList.add(new Track("D - 딘", "D.png", "딘-D.mp3"));
		trackList.add(new Track("빨간맛 - 레드벨벳", "빨간맛.png", "레드벨벳-빨간맛.mp3"));
		trackList.add(new Track("삐딱하게 - GD", "삐딱하게.png", "GD-삐딱하게.mp3"));

		setLayout(null); // 패널의 Layout을 NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT - 30);

		homeButton.setVisible(true);
		homeButton.setBounds(30, 20, 60, 60);
		homeButton.setBorderPainted(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusPainted(false);
		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				homeButton.setIcon(homeButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				homeButton.setIcon(homeButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				homeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 시작화면으로
				// 메인화면으로 돌아가는 이벤트
				frame.change("startPanel");

			}

		});
		add(homeButton);

		JScrollPane musicListPanel = new JScrollPane();
		musicListPanel
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		musicListPanel.getVerticalScrollBar().setUnitIncrement(16);
		musicListPanel.setBorder(new LineBorder(new Color(255, 50, 50, 50), 2));
		musicListPanel.setBackground(new Color(255, 0, 0, 0));
		musicListPanel.setBounds(0, 100, RhythmMain.SCREEN_WIDTH, 570);// Main.SCREEN_WIDTH

		// frame.add(this);

		JPanel songListPanel = new JPanel();
		songListPanel.setPreferredSize(new Dimension(450, 2000));// 스크롤뷰생성
		songListPanel.setLayout(null);
		songListPanel.setBackground(new Color(255, 0, 0, 0)); // 컴포넌트 배경을 하얀색으로

		musicListPanel.setViewportView(songListPanel);

		for (int i = 0; i < trackList.size(); i++) {
			// 노래 패널
			JPanel songPanel = new JPanel();
			songPanel.setBackground(new Color(255, 10, 10, 10)); // 컴포넌트 배경을
																	// 하얀색으
			songPanel.setLayout(null);
			songPanel.setBounds(0, i * 100, 1280, 100);

			JLabel line = new JLabel("");
			line.setOpaque(true);// 이거를 사용해야함
			line.setBackground(new Color(100, 50, 50, 50));
			line.setBounds(0, 0, 1280, 2);
			songPanel.add(line);

			JLabel title = new JLabel(trackList.get(i).getTitleName());
			Font font = new Font("나눔바른고딕", Font.PLAIN, 22);
			title.setBounds(100, 2, 400, 100);
			title.setFont(font);

			JLabel score = new JLabel("최고점수   : " + RhythmTyping.score[i][0]
					+ "      아이디   : " + RhythmTyping.user[i][0]);
			score.setBounds(500, 2, 500, 100);
			score.setFont(font);

			songPanel.add(title);
			songPanel.add(score);

			goButton = new JButton("play");
			goButton.setBounds(1100, 25, 100, 50);
			goButton.setBorderPainted(false);
			goButton.setFont(font);
			goButton.setBackground(new Color(20, 30, 30, 30));
			goButton.setFocusPainted(false);
			int index = i;
			goButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행

				}

				@Override
				public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때

				}

				@Override
				public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
					songIndex = index;
					frame.setGameImage(trackList.get(index)
							.getGameBackgroundImage());
					frame.change("selectNotePanel");
				}

			});
			songPanel.add(goButton);

			songListPanel.add(songPanel);

		}
		add(musicListPanel);

	}

	public void screenDraw(Graphics2D g) {
		this.repaint();

	}

}
