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
	// ������۸�
	private Image screenImage;
	private Graphics screenGraphic;

	// background �̹���
	public static Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	
	private Image woodStickImage = new ImageIcon(Main.class.getResource("../images/woodStick.png")).getImage();
	private Image woodStickImage2 = new ImageIcon(Main.class.getResource("../images/woodStick2.png")).getImage();
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

	private JPanel nowPanel = new StartPanel(this);// ���� �г� -->ó���� ����ȭ�� �гη�
	private JScrollPane nowScrollPanel;// ���� ��ũ�� �г�

	public SelectMusicPanel selectMusicPanel;// �뷡���� �г�
	public GamePanel gamePanel;// ���� �г�

	Music introMusic = new Music("Mr_Turtle.mp3", true);// ��Ʈ�� ����

	public static String input = "";//Ű���� �Է¹���

	public static Game game;// ����

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
		setFocusable(true);
		setVisible(true);

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
			g.drawImage(woodStickImage, 200, 270, null);
			g.drawImage(woodStickImage2, 490, 400, null);
		}
		else if (isSelectScreen) {// �뷡����ȭ���̶��
			background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
			selectMusicPanel.screenDraw(g);
		}
		else if (isGameScreen) {// ����ȭ���̶��
			if (Game.gameMusic.getTime() < 1)//���� ó�� ���۽� ��׶��� �̹����� 1��
				background = new ImageIcon(Main.class.getResource("../images/gameBackground.jpg")).getImage();
			game.screenDraw(g);

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
			nowPanel = null;//�����г��� null�� 
			nowScrollPanel = selectMusicPanel; //���� ��ũ�� �гο�
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
			gameStart(0);//������ �� ��ȣ -->���� ����
		}

	}
	
	public void gameStart(int selected) {
		game = new Game(SelectMusicPanel.trackList.get(selected).getTitleName(),
				SelectMusicPanel.trackList.get(selected).getGameMusic());
		game.start();
		setFocusable(true);// Ű�����̺�Ʈ�� �׻� ��Ȯ�� ĳġ�� �� �ְ�
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
