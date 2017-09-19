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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class RhythmTyping extends JFrame implements KeyListener {
	 @Override
	    public void keyPressed(KeyEvent e) 
	    {
//			APEACH
//			CON
//			FRODO
//			JAY-G
//			NEO
//			MUZI
//			RYAN
//			TUBE

	        if (RhythmTyping.game == null) {
				return;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				RhythmTyping.game.pressA();
			} else if (e.getKeyCode() == KeyEvent.VK_C) {
				RhythmTyping.game.pressC();
			} else if (e.getKeyCode() == KeyEvent.VK_F) {
				RhythmTyping.game.pressF();
			} else if (e.getKeyCode() == KeyEvent.VK_J) {
				RhythmTyping.game.pressJ();
			} else if (e.getKeyCode() == KeyEvent.VK_N) {
				RhythmTyping.game.pressN();
			} else if (e.getKeyCode() == KeyEvent.VK_M) {
				RhythmTyping.game.pressM();
			} else if (e.getKeyCode() == KeyEvent.VK_R) {
				RhythmTyping.game.pressR();
			}else if (e.getKeyCode() == KeyEvent.VK_T) {
				RhythmTyping.game.pressT();
			}
	    }
	    @Override
	    public void keyReleased(KeyEvent e) 
	    {
	    	if (RhythmTyping.game == null) {
				return;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				RhythmTyping.game.releaseA();	
			} else if (e.getKeyCode() == KeyEvent.VK_C) {
				RhythmTyping.game.releaseC();
			} else if (e.getKeyCode() == KeyEvent.VK_F) {
				RhythmTyping.game.releaseF();
			} else if (e.getKeyCode() == KeyEvent.VK_J) {
				RhythmTyping.game.releaseJ();
			} else if (e.getKeyCode() == KeyEvent.VK_N) {
				RhythmTyping.game.releaseN();
			} else if (e.getKeyCode() == KeyEvent.VK_M) {
				RhythmTyping.game.releaseM();
			} else if (e.getKeyCode() == KeyEvent.VK_R) {
				RhythmTyping.game.releaseR();
			}else if (e.getKeyCode() == KeyEvent.VK_T) {
				RhythmTyping.game.releaseT();
			}
	    }
	    @Override
	    public void keyTyped(KeyEvent e) 
	    {
	    	
	    }
	 

	// 더블버퍼링
	private Image screenImage;
	private Graphics screenGraphic;

	// background 이미지
	private Image background = new ImageIcon(Main.class.getResource("../images/backGround.png")).getImage();

	// menuBar
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	// 종료버튼
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY;

	private JPanel nowPanel;

	public static boolean isMainScreen;

	public static boolean isSelectScreen;

	public static boolean isGameScreen;

	public SelectMusicPanel selectMusicPanel;

	public GamePanel gamePanel;

	public static Game game;

	public RhythmTyping() {

		//addKeyListener(new KeyListener());
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

		nowPanel = new StartPanel(this);
		add(nowPanel);

		setUndecorated(true); // 기본적으로 존재하는 메뉴바가 보이지 않게
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);// Frame 사이즈 변경 불가능
		setLocationRelativeTo(null);// Frames이 정 중앙에 뜨게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); // 컴포넌트 배경을 하얀색으로
		setLayout(null); // 컴포넌트를 그 위치에 꽂힌다
		addKeyListener(this);
		setFocusable(true);

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
		// IntroBackground를 screenImage에 그림
		// background 그리기
		g.drawImage(background, 0, 0, null);

		if (isSelectScreen) {
			selectMusicPanel.screenDraw(g);

		} else if (isGameScreen) {
			game.screenDraw(g);

		} else if (isMainScreen) {

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

	public void change(String panelName) {
		if (panelName.equals("startPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new StartPanel(this);
			getContentPane().add(nowPanel);
		} else if (panelName.equals("howPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new HowPanel(this);
			getContentPane().add(nowPanel);
		} else if (panelName.equals("recordPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new RecordPanel(this);
			getContentPane().add(nowPanel);
		} else if (panelName.equals("selectMusicPanel")) {
			getContentPane().remove(nowPanel);
			selectMusicPanel = new SelectMusicPanel(this);
			nowPanel = selectMusicPanel;
			isSelectScreen = true;
			isGameScreen = false;
			isMainScreen = false;
			getContentPane().add(nowPanel);

		} else if (panelName.equals("gamePanel")) {
			getContentPane().remove(nowPanel);
			gamePanel = new GamePanel(this);
			nowPanel = gamePanel;
			isGameScreen = true;
			isMainScreen = false;
			isSelectScreen = false;
			getContentPane().add(nowPanel);
			gameStart(0, "Easy");

		}

	}

	public void gameStart(int nowSelected, String difficulty) {
		isMainScreen = false;
		isGameScreen = true;
		game = new Game("강남스타일", "Easy", "PSY-강남스타일.mp3");
		game.start();
		// 키보드이벤트가 항상 정확히 캐치할 수 있게
		setFocusable(true);


	}

}
