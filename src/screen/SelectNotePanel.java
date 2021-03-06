package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import rhythm.RhythmMain;

public class SelectNotePanel extends JPanel {//노트선택화면
	private RhythmTyping frame;

	// 뒤로가기버튼
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);

	//오케이버튼
	private ImageIcon okayButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("okayButtonEntered.png"));
	private ImageIcon okayButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("okayButtonBasic.png"));
	private JButton okayButton = new JButton(okayButtonBasicImage);

	
	private JLabel message;//한영 입력 메시지

	//선택한 노트 종류
	public static int chooseNote = 1;// 1이면 노트1, 2이면 노트 2

	public SelectNotePanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;

		setLayout(null); // 패널의 Layout을 NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, RhythmMain.SCREEN_WIDTH, RhythmMain.SCREEN_HEIGHT - 30);

		backButton.setVisible(true);
		backButton.setBounds(30, 20, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				backButton.setIcon(backButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				backButton.setIcon(backButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 노래 선택 화면으로
				frame.change("selectMusicPanel");

			}

		});
		add(backButton);

		ButtonGroup noteGroup = new ButtonGroup();
		JRadioButton note1_radio = new JRadioButton("노트 1", true);
		JRadioButton note2_radio = new JRadioButton("노트 2");
		noteGroup.add(note1_radio);
		noteGroup.add(note2_radio);

		note1_radio.setVisible(true);
		note1_radio.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		note1_radio.setBounds(200, 70, 200, 100);
		note1_radio.setBorderPainted(false);
		note1_radio.setContentAreaFilled(false);
		note1_radio.setFocusPainted(false);

		note2_radio.setVisible(true);
		note2_radio.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		note2_radio.setBounds(200, 320, 200, 100);
		note2_radio.setBorderPainted(false);
		note2_radio.setContentAreaFilled(false);
		note2_radio.setFocusPainted(false);

		add(note1_radio);
		add(note2_radio);

		okayButton.setVisible(true);
		okayButton.setBounds(970, 570, 200, 100);
		okayButton.setBorderPainted(false);
		okayButton.setContentAreaFilled(false);
		okayButton.setFocusPainted(false);
		okayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				okayButton.setIcon(okayButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				okayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				okayButton.setIcon(okayButtonEnteredImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				okayButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 게임 화면으로
				if (note1_radio.isSelected()) {// 1이 선택되었을때
					chooseNote = 0;
				} else {
					chooseNote = 1;
				}
				frame.change("gamePanel");

			}

		});
		add(okayButton);

		message = new JLabel("한/영키를 눌러 영어로 맞춰주세요.");
		message.setBounds(500, 590, 400, 50);
		message.setFont(new Font("나눔바른고딕", Font.BOLD, 25));

		add(message);

	}


}
