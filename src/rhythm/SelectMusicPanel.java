package rhythm;

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


public class SelectMusicPanel extends JScrollPane{

	private RhythmTyping frame;

	// 뒤로가기버튼
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private JButton backButton = new JButton();
	
	JButton goButton = new JButton("go");

	//private Image musicTrack = new ImageIcon(Main.class.getResource("../images/musicTrack.png")).getImage();

	//private Image musicTrackLine = new ImageIcon(Main.class.getResource("../images/musicTrackLine.png")).getImage();

	public static ArrayList<Track> trackList = new ArrayList<Track>();
	public SelectMusicPanel(RhythmTyping rhythmTyping) {
		trackList.add(new Track("무제 Title image.png", "강남스타일startImage.jpg", "강남스타일gameImage.jpg", "PSY-강남스타일.mp3",
				"PSY-강남스타일.mp3", "강남스타일"));
		frame = rhythmTyping;
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(16);
		setBorder(null);
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT - 30);
		frame.add(this);
		
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(450, 2000));//스크롤뷰생성
		panel.setLayout(null);
		panel.setBackground(new Color(255, 0, 0, 0)); // 컴포넌트 배경을 하얀색으로

		setViewportView(panel);

		
		JPanel jpp=new JPanel();
		jpp.setBackground(new Color(255, 0, 0, 0)); // 컴포넌트 배경을 하얀색으
		jpp.setLayout(null);
		jpp.setBounds(0, 0, 1280, 100);

		JLabel jl=new JLabel("강남스타일-PSY");
		Font f1 = new Font("나눔바른고딕", Font.PLAIN, 25);
		jl.setBounds(100, 0, 400, 100);
		jl.setFont(f1);
		JLabel jl2=new JLabel("최고점수 : 123450");
		jl2.setBounds(600, 0, 500, 100);
		jl2.setFont(f1);
		
		jpp.add(jl);
		jpp.add(jl2);

		
		goButton.setBounds(1000, 0, 200, 100);
		goButton.setBorderPainted(false);
		goButton.setFont(f1);
		goButton.setContentAreaFilled(false);
		goButton.setFocusPainted(false);
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
			}

		});
		jpp.add(goButton);

		panel.add(jpp);
	}

	public void screenDraw(Graphics2D g) {


		this.repaint();

	}
	
	

}
