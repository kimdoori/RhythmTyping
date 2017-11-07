package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Game;
import rhythm.RhythmMain;

public class ResultPanel extends JPanel {//���Ӱ��ȭ��

	private RhythmTyping frame;

	//�ٽý��۹�ư
	private ImageIcon reButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("reButtonBasic.png"));
	private JButton reButton = new JButton(reButtonBasicImage);

	//Ȯ�ι�ư
	private ImageIcon okayButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("okayButtonBasic.png"));
	private JButton okayButton = new JButton(okayButtonBasicImage);

	private JLabel my_label;
	private JLabel my_score;
	private JLabel my_max_label;
	private JLabel my_max_score;

	public ResultPanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;
		setLayout(null); // �г��� Layout�� NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT - 30);

		my_label = new JLabel("�̹� �� ����");
		my_label.setBounds(570, 150, 200, 50);
		my_label.setFont(new Font("�����ٸ����", Font.BOLD, 30));
		add(my_label);

		my_score = new JLabel(Game.score.toString());
		my_score.setBounds(600, 190, 200, 50);
		my_score.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		add(my_score);

		my_max_label = new JLabel("�� �ְ� ����");
		my_max_label.setBounds(570, 250, 200, 50);
		my_max_label.setFont(new Font("�����ٸ����", Font.BOLD, 30));
		add(my_max_label);

		my_max_score = new JLabel(
				String.valueOf(RhythmTyping.playScore[SelectMusicPanel.songIndex]));
		my_max_score.setBounds(600, 290, 200, 50);
		my_max_score.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		add(my_max_score);

		reButton.setBounds(400, 400, 200, 100);
		reButton.setBorderPainted(false);
		reButton.setContentAreaFilled(false);
		reButton.setFocusPainted(false);
		reButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				reButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				reButton.setIcon(reButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				reButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// TODO:ȭ����ȯ
			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ �� ���� �����
				frame.change("gamePanel");
			}

		});
		add(reButton);

		okayButton.setBounds(690, 400, 200, 100);
		okayButton.setBorderPainted(false);
		okayButton.setContentAreaFilled(false);
		okayButton.setFocusPainted(false);
		okayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				// okayButton.setIcon(okayButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				okayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				okayButton.setIcon(okayButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				okayButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}
			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ �� �뷡 ���� ȭ������
				frame.change("selectMusicPanel");
			}

		});
		add(okayButton);
	}

}
