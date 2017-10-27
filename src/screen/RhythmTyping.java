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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import game.Game;
import musicList.Music;
import mysql.ConnectDB;
import rhythm.Main;

public class RhythmTyping extends JFrame implements KeyListener {
	// ������۸�
	private Image screenImage;
	private Graphics screenGraphic;

	public static String[][] score;
	public static String[][] user;
	
	
	// background �̹���
	public static Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	
	private Image woodStickImage = new ImageIcon(Main.class.getResource("../images/woodStick.png")).getImage();
	private Image woodStickImage2 = new ImageIcon(Main.class.getResource("../images/woodStick2.png")).getImage();
	
	private Image titleImage = new ImageIcon(Main.class.getResource("../images/title.png")).getImage();
	private Image loginImage = new ImageIcon(Main.class.getResource("../images/loginImage.png")).getImage();
	private Image recordImage = new ImageIcon(Main.class.getResource("../images/recordImage.png")).getImage();
	private Image selectImage = new ImageIcon(Main.class.getResource("../images/selectImage.png")).getImage();

	
	private Image woodLoginImage = new ImageIcon(Main.class.getResource("../images/loginBackgroundImage.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();

	private Image note1Image = new ImageIcon(Main.class.getResource("../images/note1Image.png")).getImage();
	private Image note2Image = new ImageIcon(Main.class.getResource("../images/note2Image.png")).getImage();
	
	// menuBar
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	// menuBar�� ��� x��ư(�����ư)
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY;// ���콺�� x��ǥ, y��ǥ

	public static boolean isStartScreen = true;// ����ȭ������
	public static boolean isSelectScreen = false;// �뷡����ȭ������
	public static boolean isGameScreen = false;// ����ȭ������
	public static boolean isLoginScreen = false;// �α���ȭ������
	public static boolean isHowScreen = false;// ���ӹ��ȭ������
	public static boolean isRecordScreen = false;// ���ȭ������
	public static boolean isNoteScreen = false;// ��Ʈ����ȭ������
	public static boolean isResultScreen = false;// ���ȭ������

	
	
	public static String playID;
	public static String playScore[] = new String[4];
	
	private JPanel nowPanel = new StartPanel(this);// ���� �г� -->ó���� ����ȭ�� �гη�

	public SelectMusicPanel selectMusicPanel;// �뷡���� �г�
	public GamePanel gamePanel;// ���� �г�
	public ResultPanel resultPanel;
	Music introMusic = new Music("Mr_Turtle.mp3", true);// ��Ʈ�� ����
	public String gameImage = "";

	public static String input = "";//Ű���� �Է¹���

	public static Game game;// ����

	public static ConnectDB connectDB = new ConnectDB();

	
	public RhythmTyping() {
		introMusic.start();// ��Ʈ�� ���� ����

		setUndecorated(true); // �⺻������ �����ϴ� �޴��ٰ� ������ �ʰ�
		setTitle("RhythmTyping");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);// Frame ������ ���� �Ұ���
		setLocationRelativeTo(null);// Frames�� �� �߾ӿ� �߰�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0)); // ������Ʈ ����� �Ͼ������
		setLayout(null); // ������Ʈ�� �� ��ġ�� ������
		addKeyListener(this);
		setVisible(true);
		setFocusable(true);
		// menubar�� ��� x��ư
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

		// menubar
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

		// �����ӿ� nowPanel��
		add(nowPanel);
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
		// background�� screenImage�� �׸�
		g.drawImage(background, 0, 0, null);
		
		if (isStartScreen) {// ����ȭ���̶��
			background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
			g.drawImage(titleImage, 520, 60, null);
			g.drawImage(woodStickImage, 200, 270, null);
			g.drawImage(woodStickImage2, 490, 400, null);
		}
		else if(isHowScreen) {
			background = new ImageIcon(Main.class.getResource("../images/howtoplay.png")).getImage();

		}
		else if(isLoginScreen) {
			background = new ImageIcon(Main.class.getResource("../images/loginBackground.png")).getImage();
			g.drawImage(woodLoginImage, 340, 150, null);
			g.drawImage(loginImage, 600, 60, null);
			
		}
		else if(isRecordScreen) {

			background = new ImageIcon(Main.class.getResource("../images/loginBackground.png")).getImage();
			g.drawImage(recordImage, 580, 60, null);

		}
		else if (isSelectScreen) {// �뷡����ȭ���̶��
			background = new ImageIcon(Main.class.getResource("../images/selectBackground.jpg")).getImage();
			g.drawImage(selectImage, 600, 50, null);

			selectMusicPanel.screenDraw(g);
			
		}
		else if(isNoteScreen) {
			background = new ImageIcon(Main.class.getResource("../images/selectBackground.jpg")).getImage();
			g.drawImage(selectImage, 600, 50, null);

			g.drawImage(note1Image, 200, 180, null);
			g.drawImage(note2Image, 200, 450, null);
		}
		else if (isGameScreen) {// ����ȭ���̶��
			background = new ImageIcon(Main.class.getResource("../images/"+gameImage)).getImage();
			g.drawImage(gameInfoImage,0,30,null);
			game.screenDraw(g);

		}
		else if (isResultScreen) {// ���ȭ���̶��
			background = new ImageIcon(Main.class.getResource("../images/loginBackground.png")).getImage();
			g.drawImage(woodLoginImage, 340, 150, null);

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

	public void change(String panelName) {//�����ӿ� ������ �г��� ��ȯ�ϴ� �޼ҵ�
		if (panelName.equals("startPanel")) {
			getContentPane().remove(nowPanel);

			nowPanel = new StartPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen=true;
			isLoginScreen=false;
			isHowScreen=false;
			isSelectScreen=false;
			isNoteScreen=false;
			isGameScreen=false;
			isResultScreen=false;

		} 
		else if (panelName.equals("loginPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new LoginPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen=false;
			isLoginScreen=true;
			isHowScreen=false;
			isRecordScreen=false;
			isSelectScreen=false;
			isNoteScreen=false;
			isGameScreen=false;
			isResultScreen=false;

		} 
		
		else if (panelName.equals("howPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new HowPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen=false;
			isLoginScreen=false;
			isHowScreen=true;
			isRecordScreen=false;
			isSelectScreen=false;
			isNoteScreen=false;
			isGameScreen=false;
			isResultScreen=false;

		} else if (panelName.equals("recordPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new RecordPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen=false;
			isLoginScreen=false;
			isHowScreen=false;
			isRecordScreen=true;
			isSelectScreen=false;
			isNoteScreen=false;
			isGameScreen=false;
			isResultScreen=false;

		} else if (panelName.equals("selectMusicPanel")) {
			connectDB.getSongRecord();
			getContentPane().remove(nowPanel);
			selectMusicPanel = new SelectMusicPanel(this);
			nowPanel=selectMusicPanel;
			getContentPane().add(nowPanel);
			isStartScreen=false;
			isLoginScreen=false;
			isHowScreen=false;			
			isRecordScreen=false;
			isSelectScreen=true;
			isNoteScreen=false;
			isGameScreen=false;
			isResultScreen=false;

		} else if (panelName.equals("selectNotePanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new SelectNotePanel(this);// 
			getContentPane().add(nowPanel);
			isStartScreen=false;
			isLoginScreen=false;
			isHowScreen=false;			
			isRecordScreen=false;
			isSelectScreen=false;
			isNoteScreen=true;
			isGameScreen=false;
			isResultScreen=false;

		}
		else if (panelName.equals("gamePanel")) {
			introMusic.close();
			getContentPane().remove(nowPanel);
			gamePanel = new GamePanel(this);
			nowPanel = gamePanel;
			getContentPane().add(nowPanel);
			isStartScreen=false;
			isLoginScreen=false;
			isHowScreen=false;			
			isRecordScreen=false;
			isSelectScreen=false;
			isNoteScreen=false;
			isGameScreen=true;
			isResultScreen=false;

			System.out.println(SelectMusicPanel.songIndex);
			gameStart(SelectMusicPanel.songIndex);//������ �� ��ȣ -->���� ����
		}
		else if (panelName.equals("resultPanel")) {
			getContentPane().remove(nowPanel);
			nowPanel = new ResultPanel(this);
			getContentPane().add(nowPanel);
			isStartScreen=false;
			isLoginScreen=false;
			isHowScreen=false;			
			isRecordScreen=false;
			isSelectScreen=false;
			isNoteScreen=false;
			isGameScreen=false;
			isResultScreen=true;
		}
	}
	
	public void gameStart(int selected) {
		this.requestFocus(true);
		game = new Game(this,SelectMusicPanel.trackList.get(selected).getTitleName(),
				SelectMusicPanel.trackList.get(selected).getGameMusic());
		game.start();
		// Ű�����̺�Ʈ�� �׻� ��Ȯ�� ĳġ�� �� �ְ�
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
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

}
