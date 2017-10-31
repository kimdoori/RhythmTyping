package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import musicList.Track;
import rhythm.RhythmMain;

public class SelectMusicPanel extends JPanel {//�뷡����ȭ��

	private RhythmTyping frame;

	// �ڷΰ����ư
	private ImageIcon homeButtonEnteredImage = new ImageIcon(
			RhythmMain.class.getResource("../images/homeButtonEntered.png"));
	private ImageIcon homeButtonBasicImage = new ImageIcon(
			RhythmMain.class.getResource("../images/homeButtonBasic.png"));
	private JButton homeButton = new JButton(homeButtonBasicImage);

	//�뷡�÷��̹�ư
	JButton goButton = new JButton();

	//�� �뷡
	public static int songIndex = 0;
	
	//�Ľ��� bms ���� �̸�
	public static final String[] bmsName = { "gangnamstyle", "D", "redflavor",
			"cracked" };

	//�뷡���
	public static ArrayList<Track> trackList = new ArrayList<Track>();

	public SelectMusicPanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;

		trackList.clear();
		trackList.add(new Track("������Ÿ�� - PSY", "gangnamstyle.png",
				"PSY-������Ÿ��.mp3"));
		trackList.add(new Track("D - ��", "D.png", "��-D.mp3"));
		trackList.add(new Track("������ - ���座��", "������.png", "���座��-������.mp3"));
		trackList.add(new Track("�ߵ��ϰ� - GD", "�ߵ��ϰ�.png", "GD-�ߵ��ϰ�.mp3"));

		setLayout(null); // �г��� Layout�� NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT - 30);

		homeButton.setVisible(true);
		homeButton.setBounds(30, 20, 60, 60);
		homeButton.setBorderPainted(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusPainted(false);
		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				homeButton.setIcon(homeButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				homeButton.setIcon(homeButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				homeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ �� ����ȭ������
				// ����ȭ������ ���ư��� �̺�Ʈ
				frame.change("startPanel");

			}

		});
		add(homeButton);

		JScrollPane musicListPanel = new JScrollPane();
		musicListPanel
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		musicListPanel.getVerticalScrollBar().setUnitIncrement(16);
		musicListPanel.setBorder(new LineBorder(new Color(255, 50, 50, 50), 2));
		musicListPanel.setBackground(new Color(255, 0, 0, 0));
		musicListPanel.setBounds(0, 100, RhythmMain.SCREEN_WIDTH, 570);// Main.SCREEN_WIDTH

		// frame.add(this);

		JPanel songListPanel = new JPanel();
		songListPanel.setPreferredSize(new Dimension(450, 2000));// ��ũ�Ѻ����
		songListPanel.setLayout(null);
		songListPanel.setBackground(new Color(255, 0, 0, 0)); // ������Ʈ ����� �Ͼ������

		musicListPanel.setViewportView(songListPanel);

		for (int i = 0; i < trackList.size(); i++) {
			// �뷡 �г�
			JPanel songPanel = new JPanel();
			songPanel.setBackground(new Color(255, 10, 10, 10)); // ������Ʈ �����
																	// �Ͼ����
			songPanel.setLayout(null);
			songPanel.setBounds(0, i * 100, 1280, 100);

			JLabel line = new JLabel("");
			line.setOpaque(true);// �̰Ÿ� ����ؾ���
			line.setBackground(new Color(100, 50, 50, 50));
			line.setBounds(0, 0, 1280, 2);
			songPanel.add(line);

			JLabel title = new JLabel(trackList.get(i).getTitleName());
			Font font = new Font("�����ٸ����", Font.PLAIN, 22);
			title.setBounds(100, 2, 400, 100);
			title.setFont(font);

			JLabel score = new JLabel("�ְ�����   : " + RhythmTyping.score[i][0]
					+ "      ���̵�   : " + RhythmTyping.user[i][0]);
			score.setBounds(500, 2, 500, 100);
			score.setFont(font);

			songPanel.add(title);
			songPanel.add(score);

			goButton = new JButton("play");
			goButton.setBounds(1100, 25, 100, 50);
			goButton.setBorderPainted(false);
			goButton.setFont(font);
			goButton.setBackground(new Color(20, 30, 30, 30));
			goButton.setFocusPainted(false);
			int index = i;
			goButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����

				}

				@Override
				public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��

				}

				@Override
				public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
					songIndex = index;
					frame.setGameImage(trackList.get(index)
							.getGameBackgroundImage());
					frame.change("selectNotePanel");
				}

			});
			songPanel.add(goButton);

			songListPanel.add(songPanel);

		}
		add(musicListPanel);

	}

	public void screenDraw(Graphics2D g) {
		this.repaint();

	}

}
