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

public class ResultPanel extends JPanel {//게임결과화면

	private RhythmTyping frame;

	//다시시작버튼
	private ImageIcon reButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("reButtonBasic.png"));
	private JButton reButton = new JButton(reButtonBasicImage);

	//확인버튼
	private ImageIcon okayButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("okayButtonBasic.png"));
	private JButton okayButton = new JButton(okayButtonBasicImage);

	private JLabel my_label;
	private JLabel my_score;
	private JLabel my_max_label;
	private JLabel my_max_score;

	public ResultPanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;
		setLayout(null); // 패널의 Layout을 NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT - 30);

		my_label = new JLabel("이번 판 점수");
		my_label.setBounds(570, 150, 200, 50);
		my_label.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		add(my_label);

		my_score = new JLabel(Game.score.toString());
		my_score.setBounds(600, 190, 200, 50);
		my_score.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		add(my_score);

		my_max_label = new JLabel("내 최고 점수");
		my_max_label.setBounds(570, 250, 200, 50);
		my_max_label.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		add(my_max_label);

		my_max_score = new JLabel(
				String.valueOf(RhythmTyping.playScore[SelectMusicPanel.songIndex]));
		my_max_score.setBounds(600, 290, 200, 50);
		my_max_score.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		add(my_max_score);

		reButton.setBounds(400, 400, 200, 100);
		reButton.setBorderPainted(false);
		reButton.setContentAreaFilled(false);
		reButton.setFocusPainted(false);
		reButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				reButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				reButton.setIcon(reButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				reButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// TODO:화면전환
			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 게임 재실행
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
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				// okayButton.setIcon(okayButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				okayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				okayButton.setIcon(okayButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				okayButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}
			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 노래 선택 화면으로
				frame.change("selectMusicPanel");
			}

		});
		add(okayButton);
	}

}
