package screen;

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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Game;
import musicList.Music;
import mysql.ConnectDB;
import rhythm.RhythmMain;

public class RhythmTyping extends JFrame implements KeyListener {//기본프레임
	// 더블버퍼링
	private Image screenImage;
	private Graphics screenGraphic;

	//모든 유저들의 점수와 아이디
	public static int[][] score;
	public static String[][] user;


	
	private Image background = new ImageIcon(
			getClass().getClassLoader().getResource("introBackground.jpg")).getImage();
	
	private Image woodStickImage = new ImageIcon(
			getClass().getClassLoader().getResource("woodStick.png")).getImage();
	private Image woodStickImage2 = new ImageIcon(
			getClass().getClassLoader().getResource("woodStick2.png")).getImage();

	private Image titleImage = new ImageIcon(
			getClass().getClassLoader().getResource("title.png")).getImage();
	private Image loginImage = new ImageIcon(
			getClass().getClassLoader().getResource("loginImage.png")).getImage();
	private Image recordImage = new ImageIcon(
			getClass().getClassLoader().getResource("recordImage.png")).getImage();
	private Image selectImage = new ImageIcon(
			getClass().getClassLoader().getResource("selectImage.png")).getImage();

	private Image woodLoginImage = new ImageIcon(
			getClass().getClassLoader().getResource("loginBackgroundImage.png"))
			.getImage();
	private Image gameInfoImage = new ImageIcon(
			getClass().getClassLoader().getResource("gameInfo.png")).getImage();

	private Image note1Image = new ImageIcon(
			getClass().getClassLoader().getResource("note1Image.png")).getImage();
	private Image note2Image = new ImageIcon(
			getClass().getClassLoader().getResource("note2Image.png")).getImage();

	
	private Image typingBackground = new ImageIcon(
		getClass().getClassLoader().getResource("typingBackground.png")).getImage();
	// menuBar
	private JLabel menuBar = new JLabel(new ImageIcon(
			getClass().getClassLoader().getResource("menuBar.png")));
	// menuBar에 띄울 x버튼(종료버튼)
	private ImageIcon exitButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("exitButtonBasic.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY;// 마우스의 x좌표, y좌표

	public static boolean isStartScreen = true;// 시작화면인지
	public static boolean isSelectScreen = false;// 노래선택화면인지
	public static boolean isGameScreen = false;// 게임화면인지
	public static boolean isLoginScreen = false;// 로그인화면인지
	public static boolean isHowScreen = false;// 게임방법화면인지
	public static boolean isRecordScreen = false;// 기록화면인지
	public static boolean isNoteScreen = false;// 노트선택화면인지
	public static boolean isResultScreen = false;// 결과화면인지

	
	//플레이 중인 아이디
	public static String playID;
	//플레이 중인 유저의 점수
	public static int playScore[] = new int[4];

	private JPanel nowPanel = new StartPanel(this);// 현재 패널 -->처음은 시작화면 패널로

	public SelectMusicPanel selectMusicPanel;// 노래선택 패널
	public GamePanel gamePanel;// 게임 패널
	public ResultPanel resultPanel;
	Music introMusic = new Music("music/Mr_Turtle.mp3", true);// 인트로 음악
	
	
	private String gameImage = "";
	
	

	

	public static String input = "";// 키보드로 입력받는 것

	public static Game game;// 게임

	public static ConnectDB connectDB = new ConnectDB();

	public RhythmTyping() {

		introMusic.start();// 인트로 음악 실행

		setUndecorated(true); // 기본적으로 존재하는 메뉴바가 보이지 않게
		setSize(RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT);
		setResizable(false);// Frame 사이즈 변경 불가능
		setLocationRelativeTo(null);// Frames이 정 중앙에 뜨게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0)); // 컴포넌트 배경을 하얀색으로
		setLayout(null); // 컴포넌트를 그 위치에 꽂힌다
		addKeyListener(this);
		setVisible(true);
		setFocusable(true);
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
		screenImage = createImage(RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT);
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
			background = new ImageIcon(
					getClass().getClassLoader().getResource("introBackground.jpg"))
					.getImage();
			g.drawImage(titleImage, 520, 60, null);
			g.drawImage(woodStickImage, 200, 270, null);
			g.drawImage(woodStickImage2, 490, 400, null);
		} else if (isHowScreen) {//게임방법화면이라면
			background = new ImageIcon(
					getClass().getClassLoader().getResource("howToPlay.png"))
					.getImage();

		} else if (isLoginScreen) {//로그인화면이라면
			background = new ImageIcon(
					getClass().getClassLoader().getResource("loginBackground.png"))
					.getImage();
			g.drawImage(woodLoginImage, 340, 150, null);
			g.drawImage(loginImage, 600, 60, null);

		} else if (isRecordScreen) {//기록화면이라면
			background = new ImageIcon(
					getClass().getClassLoader().getResource("loginBackground.png"))
					.getImage();
			g.drawImage(recordImage, 580, 60, null);

		} else if (isSelectScreen) {// 노래선택화면이라면
			background = new ImageIcon(
					getClass().getClassLoader().getResource("selectBackground.jpg"))
					.getImage();
			g.drawImage(selectImage, 600, 50, null);

			selectMusicPanel.screenDraw(g);

		} else if (isNoteScreen) {//노트선택화면이라면
			background = new ImageIcon(
					getClass().getClassLoader().getResource("selectBackground.jpg"))
					.getImage();
			g.drawImage(selectImage, 600, 50, null);

			g.drawImage(note1Image, 200, 180, null);
			g.drawImage(note2Image, 200, 450, null);
		} else if (isGameScreen) {// 게임화면이라면
			background = new ImageIcon(getClass().getClassLoader().getResource(
					gameImage)).getImage();
			g.drawImage(gameInfoImage, 0, 30, null);
			g.drawImage(typingBackground,1000,580,null);

			game.screenDraw(g);

		} else if (isResultScreen) {// 결과화면이라면
			background = new ImageIcon(
					getClass().getClassLoader().getResource("loginBackground.png"))
					.getImage();
			g.drawImage(woodLoginImage, 340, 150, null);

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

	public void change(String panelName) {// 프레임에 부착할 패널을 전환하는 메소드
		if (panelName.equals("startPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new StartPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen = true;
			isLoginScreen = false;
			isHowScreen = false;
			isSelectScreen = false;
			isNoteScreen = false;
			isGameScreen = false;
			isResultScreen = false;

		} else if (panelName.equals("loginPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new LoginPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen = false;
			isLoginScreen = true;
			isHowScreen = false;
			isRecordScreen = false;
			isSelectScreen = false;
			isNoteScreen = false;
			isGameScreen = false;
			isResultScreen = false;

		}

		else if (panelName.equals("howPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new HowPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen = false;
			isLoginScreen = false;
			isHowScreen = true;
			isRecordScreen = false;
			isSelectScreen = false;
			isNoteScreen = false;
			isGameScreen = false;
			isResultScreen = false;

		} else if (panelName.equals("recordPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new RecordPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen = false;
			isLoginScreen = false;
			isHowScreen = false;
			isRecordScreen = true;
			isSelectScreen = false;
			isNoteScreen = false;
			isGameScreen = false;
			isResultScreen = false;

		} else if (panelName.equals("selectMusicPanel")) {
			connectDB.getSongRecord();
			getContentPane().remove(nowPanel);
			selectMusicPanel = new SelectMusicPanel(this);
			nowPanel = selectMusicPanel;
			getContentPane().add(nowPanel);
			isStartScreen = false;
			isLoginScreen = false;
			isHowScreen = false;
			isRecordScreen = false;
			isSelectScreen = true;
			isNoteScreen = false;
			isGameScreen = false;
			isResultScreen = false;

		} else if (panelName.equals("selectNotePanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new SelectNotePanel(this);//
			getContentPane().add(nowPanel);
			isStartScreen = false;
			isLoginScreen = false;
			isHowScreen = false;
			isRecordScreen = false;
			isSelectScreen = false;
			isNoteScreen = true;
			isGameScreen = false;
			isResultScreen = false;

		} else if (panelName.equals("gamePanel")) {
			introMusic.close();
			getContentPane().remove(nowPanel);
			gamePanel = new GamePanel(this);
			nowPanel = gamePanel;
			getContentPane().add(nowPanel);
			isStartScreen = false;
			isLoginScreen = false;
			isHowScreen = false;
			isRecordScreen = false;
			isSelectScreen = false;
			isNoteScreen = false;
			isGameScreen = true;
			isResultScreen = false;

			gameStart(SelectMusicPanel.songIndex);// 선택한 곡 번호 -->게임 시작
		
		} else if (panelName.equals("resultPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new ResultPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen = false;
			isLoginScreen = false;
			isHowScreen = false;
			isRecordScreen = false;
			isSelectScreen = false;
			isNoteScreen = false;
			isGameScreen = false;
			isResultScreen = true;
			connectDB.getSongRecord();

		}
	}

	public void gameStart(int selected) {//게임 시작 메서드
		this.requestFocus(true);// 키보드이벤트가 항상 정확히 캐치할 수 있게

		game = new Game(this, SelectMusicPanel.trackList.get(selected)
				.getTitleName(), SelectMusicPanel.trackList.get(selected)
				.getGameMusic());
		game.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (game == null) {
			return;
		}
		input += KeyEvent.getKeyText(e.getKeyCode());
		System.out.println(input);
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
	public String getGameImage() {
		return gameImage;
	}

	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
}
