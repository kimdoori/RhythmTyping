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

	// �ڷΰ����ư
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);
	
//	JButton[] goButton = new JButton[4];
	JButton goButton = new JButton();

	//private Image musicTrack = new ImageIcon(Main.class.getResource("../images/musicTrack.png")).getImage();

	//private Image musicTrackLine = new ImageIcon(Main.class.getResource("../images/musicTrackLine.png")).getImage();

	public static ArrayList<Track> trackList = new ArrayList<Track>();
	public SelectMusicPanel(RhythmTyping rhythmTyping) {
		
		trackList.add(new Track("������Ÿ�� - PSY", "gangnamstyle.png","PSY-������Ÿ��.mp3"));
		trackList.add(new Track("���� - GD", "gangnamstyle.jpg","PSY-������Ÿ��.mp3"));

		//		trackList.add(new Track("���� Title image.png", "������Ÿ��startImage.jpg", "������Ÿ��gameImage.jpg", "PSY-������Ÿ��.mp3",
//				"PSY-������Ÿ��.mp3", "������Ÿ��"));
//		trackList.add(new Track("���� Title image.png", "������Ÿ��startImage.jpg", "������Ÿ��gameImage.jpg", "PSY-������Ÿ��.mp3",
//				"PSY-������Ÿ��.mp3", "������Ÿ��"));
//		trackList.add(new Track("���� Title image.png", "������Ÿ��startImage.jpg", "������Ÿ��gameImage.jpg", "PSY-������Ÿ��.mp3",
//				"PSY-������Ÿ��.mp3", "������Ÿ��"));

		
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
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				backButton.setIcon(backButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				backButton.setIcon(backButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				// ����ȭ������ ���ư��� �̺�Ʈ
				backSelect();

			}

		});
		add(backButton);
		
		
		
		JPanel songListPanel = new JPanel();
		songListPanel.setPreferredSize(new Dimension(450, 2000));//��ũ�Ѻ����
		songListPanel.setLayout(null);
		songListPanel.setBackground(new Color(255, 0, 0, 0)); // ������Ʈ ����� �Ͼ������

		setViewportView(songListPanel);

		for(int i =0;i<trackList.size();i++) {
			//�뷡 �г�
			JPanel songPanel=new JPanel();
			songPanel.setBackground(new Color(255, 10, 10, 10)); // ������Ʈ ����� �Ͼ����
			songPanel.setLayout(null);
			songPanel.setBounds(0, 82+i*100, 1280, 100);

			JLabel line=new JLabel("");
			line.setOpaque(true);//�̰Ÿ� ����ؾ���
			line.setBackground(new Color(100, 50, 50, 50));
			line.setBounds(0, 0, 1280, 2);
			songPanel.add(line);

			JLabel title=new JLabel(trackList.get(i).getTitleName());
			Font font = new Font("�����ٸ����", Font.PLAIN, 25);
			title.setBounds(100, 2, 400, 100);
			title.setFont(font);
			JLabel score = new JLabel("�ְ����� : 123450");
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
