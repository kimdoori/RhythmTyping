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

	// �ڷΰ����ư
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private JButton backButton = new JButton();
	
	JButton goButton = new JButton("go");

	//private Image musicTrack = new ImageIcon(Main.class.getResource("../images/musicTrack.png")).getImage();

	//private Image musicTrackLine = new ImageIcon(Main.class.getResource("../images/musicTrackLine.png")).getImage();

	public static ArrayList<Track> trackList = new ArrayList<Track>();
	public SelectMusicPanel(RhythmTyping rhythmTyping) {
		trackList.add(new Track("���� Title image.png", "������Ÿ��startImage.jpg", "������Ÿ��gameImage.jpg", "PSY-������Ÿ��.mp3",
				"PSY-������Ÿ��.mp3", "������Ÿ��"));
		frame = rhythmTyping;
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(16);
		setBorder(null);
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT - 30);
		frame.add(this);
		
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(450, 2000));//��ũ�Ѻ����
		panel.setLayout(null);
		panel.setBackground(new Color(255, 0, 0, 0)); // ������Ʈ ����� �Ͼ������

		setViewportView(panel);

		
		JPanel jpp=new JPanel();
		jpp.setBackground(new Color(255, 0, 0, 0)); // ������Ʈ ����� �Ͼ����
		jpp.setLayout(null);
		jpp.setBounds(0, 0, 1280, 100);

		JLabel jl=new JLabel("������Ÿ��-PSY");
		Font f1 = new Font("�����ٸ����", Font.PLAIN, 25);
		jl.setBounds(100, 0, 400, 100);
		jl.setFont(f1);
		JLabel jl2=new JLabel("�ְ����� : 123450");
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
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
			//	goButton.setIcon(exitButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				goButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				//goButton.setIcon(exitButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				goButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				
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
