package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import rhythm.RhythmMain;

public class RecordPanel extends JPanel {//���ȭ��
	private RhythmTyping frame;

	//�ڷΰ��� ��ư
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			RhythmMain.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(
			RhythmMain.class.getResource("../images/backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);

	public RecordPanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;
		setLayout(null); // �г��� Layout�� NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT - 30);

		backButton.setVisible(true);
		backButton.setBounds(30, 20, 60, 60);
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
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ �� ����ȭ������
				frame.change("startPanel");

			}

		});
		add(backButton);

		int[] x = { 0, 640, 0, 640 };
		int[] y = { 0, 0, 300, 300 };
		String[] songTitle = { "������Ÿ�� - PSY", "D - ��", "������ - ���座��",
				"�ߵ��ϰ� - GD" };
		for (int i = 0; i < 4; i++) {
			JScrollPane songScrollPanel = new JScrollPane();
			songScrollPanel
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			songScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
			songScrollPanel.setBorder(new LineBorder(
					new Color(255, 50, 50, 50), 2));

			songScrollPanel.setBackground(new Color(255, 0, 0, 0));
			songScrollPanel.setBounds(0 + x[i], 150 + y[i], 640, 200);

			JPanel songListPanel = new JPanel();
			songListPanel.setPreferredSize(new Dimension(225, 1000));// ��ũ�Ѻ����
			songListPanel.setLayout(null);
			songListPanel.setBackground(new Color(255, 0, 0, 0)); // ������Ʈ �����
																	// �Ͼ������

			songScrollPanel.setViewportView(songListPanel);

			JLabel songInfo = new JLabel(songTitle[i]);
			Font infoFont = new Font("�����ٸ����", Font.PLAIN, 25);
			songInfo.setBounds(x[i] + 200, y[i] + 100, 400, 50);
			songInfo.setFont(infoFont);
			add(songInfo);

			for (int j = 0; j < RhythmTyping.score[i].length; j++) {
				// �뷡 �г�
				JPanel songPanel = new JPanel();
				songPanel.setBackground(new Color(255, 10, 10, 10)); // ������Ʈ �����
																		// �Ͼ����
				songPanel.setLayout(null);
				songPanel.setBounds(0, j * 50, 640, 50);

				JLabel line = new JLabel("");
				line.setOpaque(true);// �̰Ÿ� ����ؾ���
				line.setBackground(new Color(100, 50, 50, 50));
				line.setBounds(0, 0, 640, 2);
				songPanel.add(line);

				Font font = new Font("�����ٸ����", Font.PLAIN, 15);

				JLabel score = new JLabel("����     :  "
						+ RhythmTyping.score[i][j]);
				score.setBounds(300, 2, 400, 50);
				score.setFont(font);

				JLabel id = new JLabel("���̵�     :  " + RhythmTyping.user[i][j]);
				id.setBounds(100, 2, 500, 50);
				id.setFont(font);

				songPanel.add(score);
				songPanel.add(id);
				songListPanel.add(songPanel);

			}

			add(songScrollPanel);

		}
	}

}
