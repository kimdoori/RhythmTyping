package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import musicList.Track;
import rhythm.Main;


public class SelectMusicPanel extends JScrollPane{

	private RhythmTyping frame;

	// 뒤로가기버튼
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);
	
//	JButton[] goButton = new JButton[4];
	JButton goButton = new JButton();

	//private Image musicTrack = new ImageIcon(Main.class.getResource("../images/musicTrack.png")).getImage();

	//private Image musicTrackLine = new ImageIcon(Main.class.getResource("../images/musicTrackLine.png")).getImage();

	public static ArrayList<Track> trackList = new ArrayList<Track>();
	public SelectMusicPanel(RhythmTyping rhythmTyping) {
		
		trackList.add(new Track("강남스타일 - PSY", "gangnamstyle.png","PSY-강남스타일.mp3"));
		trackList.add(new Track("무제 - GD", "gangnamstyle.jpg","PSY-강남스타일.mp3"));

		//		trackList.add(new Track("무제 Title image.png", "강남스타일startImage.jpg", "강남스타일gameImage.jpg", "PSY-강남스타일.mp3",
//				"PSY-강남스타일.mp3", "강남스타일"));
//		trackList.add(new Track("무제 Title image.png", "강남스타일startImage.jpg", "강남스타일gameImage.jpg", "PSY-강남스타일.mp3",
//				"PSY-강남스타일.mp3", "강남스타일"));
//		trackList.add(new Track("무제 Title image.png", "강남스타일startImage.jpg", "강남스타일gameImage.jpg", "PSY-강남스타일.mp3",
//				"PSY-강남스타일.mp3", "강남스타일"));

		
		frame = rhythmTyping;
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(16);
		setBorder(null);
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT - 30);
		frame.add(this);
		
		backButton.setVisible(true);
		backButton.setBounds(40, 20, 60, 60);
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
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
				// 메인화면으로 돌아가는 이벤트
				backSelect();

			}

		});
		add(backButton);
		
		
		
		JPanel songListPanel = new JPanel();
		songListPanel.setPreferredSize(new Dimension(450, 2000));//스크롤뷰생성
		songListPanel.setLayout(null);
		songListPanel.setBackground(new Color(255, 0, 0, 0)); // 컴포넌트 배경을 하얀색으로

		setViewportView(songListPanel);

		for(int i =0;i<trackList.size();i++) {
			//노래 패널
			JPanel songPanel=new JPanel();
			songPanel.setBackground(new Color(255, 10, 10, 10)); // 컴포넌트 배경을 하얀색으
			songPanel.setLayout(null);
			songPanel.setBounds(0, 82+i*100, 1280, 100);

			JLabel line=new JLabel("");
			line.setOpaque(true);//이거를 사용해야함
			line.setBackground(new Color(100, 50, 50, 50));
			line.setBounds(0, 0, 1280, 2);
			songPanel.add(line);

			JLabel title=new JLabel(trackList.get(i).getTitleName());
			Font font = new Font("나눔바른고딕", Font.PLAIN, 25);
			title.setBounds(100, 2, 400, 100);
			title.setFont(font);
			JLabel score = new JLabel("최고점수 : 123450");
			score.setBounds(600, 2, 500, 100);
			score.setFont(font);
			
			songPanel.add(title);
			songPanel.add(score);

			goButton = new JButton("play");
			goButton.setBounds(1000, 2, 200, 100);
			goButton.setBorderPainted(false);
			goButton.setFont(font);
			goButton.setContentAreaFilled(false);
			goButton.setFocusPainted(false);
			int index=i;
			goButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				//	goButton.setIcon(exitButtonEnteredImage);
					// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
					goButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
					//goButton.setIcon(exitButtonBasicImage);
					// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
					goButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
					frame.change("gamePanel");
					frame.gameImage=trackList.get(index).getGameBackgroundImage();
				}

			});
			songPanel.add(goButton);
			
			songListPanel.add(songPanel);

		}

	}

	public void screenDraw(Graphics2D g) {
		this.repaint();

	}
	
	public void backSelect() {
		frame.change("startPanel");
	}	

}
