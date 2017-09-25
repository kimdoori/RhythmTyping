package rhythm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RhythmTyping extends JFrame implements KeyListener {
	// 더블버퍼링
	private Image screenImage;
	private Graphics screenGraphic;

	// background 이미지
	public static Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	
	private Image woodStickImage = new ImageIcon(Main.class.getResource("../images/woodStick.png")).getImage();
	private Image woodStickImage2 = new ImageIcon(Main.class.getResource("../images/woodStick2.png")).getImage();
	// menuBar
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	// menuBar에 띄울 x버튼(종료버튼)
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY;// 마우스의 x좌표, y좌표

	public static boolean isStartScreen = true;// 시작화면인지
	public static boolean isSelectScreen = false;// 노래선택화면인지
	public static boolean isGameScreen = false;// 게임화면인지

	private JPanel nowPanel = new StartPanel(this);// 현재 패널 -->처음은 시작화면 패널로
	private JScrollPane nowScrollPanel;// 현재 스크롤 패널

	public SelectMusicPanel selectMusicPanel;// 노래선택 패널
	public GamePanel gamePanel;// 게임 패널

	Music introMusic = new Music("Mr_Turtle.mp3", true);// 인트로 음악

	public static String input = "";//키보드 입력받을

	public static Game game;// 게임

	public RhythmTyping() {
		introMusic.start();// 인트로 음악 실행

		setUndecorated(true); // 기본적으로 존재하는 메뉴바가 보이지 않게
		setTitle("RhythmTyping");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);// Frame 사이즈 변경 불가능
		setLocationRelativeTo(null);// Frames이 정 중앙에 뜨게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0)); // 컴포넌트 배경을 하얀색으로
		setLayout(null); // 컴포넌트를 그 위치에 꽂힌다
		addKeyListener(this);
		setFocusable(true);
		setVisible(true);

		// menubar에 띄울 x버튼
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				exitButton.setIcon(exitButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				exitButton.setIcon(exitButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
				System.exit(0);
			}

		});
		add(exitButton);

		// menubar
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {// 이벤트가 발생했을 때 실행
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			// 드래그하면 MenuBar를 이동시킬수있게
			@Override
			public void mouseDragged(MouseEvent e) {// 드래그 이벤트가 발생했을 때 실행
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

		// 프레임에 nowPanel를
		add(nowPanel);
	}

	public void paint(Graphics g) {// 화면을 그려주는 함수
		// 사이즈 만큼의 이미지를 만든후 할당
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// screenImage를 이용해 Grapic객체를 얻어옴
		screenGraphic = screenImage.getGraphics();
		// screenGraphic에 그림을 그림
		screenDraw((Graphics2D) screenGraphic);
		// screenImage를 0,0 위치에 그림
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		// background를 screenImage에 그림
		g.drawImage(background, 0, 0, null);
		
		if (isStartScreen) {// 시작화면이라면
			background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
			g.drawImage(woodStickImage, 200, 270, null);
			g.drawImage(woodStickImage2, 490, 400, null);
		}
		else if (isSelectScreen) {// 노래선택화면이라면
			background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
			selectMusicPanel.screenDraw(g);
		}
		else if (isGameScreen) {// 게임화면이라면
			if (Game.gameMusic.getTime() < 1)//게임 처음 시작시 백그라운드 이미지를 1로
				background = new ImageIcon(Main.class.getResource("../images/gameBackground.jpg")).getImage();
			game.screenDraw(g);

		}

		// 추가로 그려줌 --고정 된 컴포넌트 add된것들
		paintComponents(g);

		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public void change(String panelName) {//프레임에 부착할 패널을 전환하는 메소드
		if (panelName.equals("startPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new StartPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen=true;
			isSelectScreen=false;
			isGameScreen=false;
		} else if (panelName.equals("howPanel")) {
//			getContentPane().remove(nowPanel);
//			nowPanel = new HowPanel(this);
//			getContentPane().add(nowPanel);
//			isStartScreen=true;
//			isSelectScreen=false;
//			isGameScreen=false;
		} else if (panelName.equals("recordPanel")) {
//			getContentPane().remove(nowPanel);
//			nowPanel = new RecordPanel(this);
//			getContentPane().add(nowPanel);
//			isStartScreen=true;
//			isSelectScreen=false;
//			isGameScreen=false;
		} else if (panelName.equals("selectMusicPanel")) {
			getContentPane().remove(nowPanel);
			selectMusicPanel = new SelectMusicPanel(this);
			nowPanel = null;//현재패널을 null로 
			nowScrollPanel = selectMusicPanel; //현재 스크롤 패널에
			getContentPane().add(nowScrollPanel);
			isStartScreen=false;
			isSelectScreen=true;
			isGameScreen=false;
		} else if (panelName.equals("gamePanel")) {
			introMusic.close();
			getContentPane().remove(nowScrollPanel);
			gamePanel = new GamePanel(this);
			nowPanel = gamePanel;
			nowScrollPanel=null;
			getContentPane().add(nowPanel);
			isStartScreen=false;
			isSelectScreen=false;
			isGameScreen=true;
			gameStart(0);//선택한 곡 번호 -->게임 시작
		}

	}
	
	public void gameStart(int selected) {
		game = new Game(SelectMusicPanel.trackList.get(selected).getTitleName(),
				SelectMusicPanel.trackList.get(selected).getGameMusic());
		game.start();
		setFocusable(true);// 키보드이벤트가 항상 정확히 캐치할 수 있게
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (game == null) {
			return;
		}
		input += KeyEvent.getKeyText(e.getKeyCode());
		game.press();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (game == null) {
			return;
		}
		game.release();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
