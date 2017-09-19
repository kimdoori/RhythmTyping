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
	 

	// ������۸�
	private Image screenImage;
	private Graphics screenGraphic;

	// background �̹���
	private Image background = new ImageIcon(Main.class.getResource("../images/backGround.png")).getImage();

	// menuBar
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	// �����ư
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
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				exitButton.setIcon(exitButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				exitButton.setIcon(exitButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				System.exit(0);
			}

		});
		add(exitButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {// �̺�Ʈ�� �߻����� �� ����
				mouseX = e.getX();
				mouseY = e.getY();
			}

		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {

			// �巡���ϸ� MenuBar�� �̵���ų���ְ�
			@Override
			public void mouseDragged(MouseEvent e) {// �巡�� �̺�Ʈ�� �߻����� �� ����
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);

			}

		});
		add(menuBar);

		nowPanel = new StartPanel(this);
		add(nowPanel);

		setUndecorated(true); // �⺻������ �����ϴ� �޴��ٰ� ������ �ʰ�
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);// Frame ������ ���� �Ұ���
		setLocationRelativeTo(null);// Frames�� �� �߾ӿ� �߰�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); // ������Ʈ ����� �Ͼ������
		setLayout(null); // ������Ʈ�� �� ��ġ�� ������
		addKeyListener(this);
		setFocusable(true);

	}

	public void paint(Graphics g) {// ȭ���� �׷��ִ� �Լ�
		// ������ ��ŭ�� �̹����� ������ �Ҵ�
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// screenImage�� �̿��� Grapic��ü�� ����
		screenGraphic = screenImage.getGraphics();

		// screenGraphic�� �׸��� �׸�
		screenDraw((Graphics2D) screenGraphic);

		// screenImage�� 0,0 ��ġ�� �׸�
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		// IntroBackground�� screenImage�� �׸�
		// background �׸���
		g.drawImage(background, 0, 0, null);

		if (isSelectScreen) {
			selectMusicPanel.screenDraw(g);

		} else if (isGameScreen) {
			game.screenDraw(g);

		} else if (isMainScreen) {

		}

		// �߰��� �׷��� --���� �� ������Ʈ add�Ȱ͵�
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
		game = new Game("������Ÿ��", "Easy", "PSY-������Ÿ��.mp3");
		game.start();
		// Ű�����̺�Ʈ�� �׻� ��Ȯ�� ĳġ�� �� �ְ�
		setFocusable(true);


	}

}
