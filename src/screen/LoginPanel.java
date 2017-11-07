package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mysql.ConnectDB;
import rhythm.RhythmMain;

public class LoginPanel extends JPanel {//로그인화면

	private RhythmTyping frame;
	
	// 로그인 버튼
	private ImageIcon loginButtonEnteredImage = new ImageIcon(getClass().getClassLoader().getResource("loginButtonEntered.png"));
	private ImageIcon loginButtonBasicImage = new ImageIcon(getClass().getClassLoader().getResource("loginButtonBasic.png"));
	private JButton loginButton = new JButton(loginButtonBasicImage);

	// 회원가입 버튼
	private ImageIcon signupButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("signupButtonEntered.png"));
	private ImageIcon signupButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("signupButtonBasic.png"));
	private JButton signupButton = new JButton(signupButtonBasicImage);

	//뒤로가기 버튼
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(
			getClass().getClassLoader().getResource("backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);

	private JTextField id;
	private JPasswordField pw = new JPasswordField(20);
	private JTextField name;

	private JLabel id_label = new JLabel("아이디 : ");
	private JLabel pw_label = new JLabel("비밀번호 : ");
	private JLabel name_label = new JLabel("이름 : ");

	public LoginPanel(RhythmTyping rhythmTyping) {
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
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 시작화면으로
				frame.change("startPanel");

			}

		});
		add(backButton);

		loginButton.setBounds(400, 400, 200, 100);
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				loginButton.setIcon(loginButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				loginButton.setIcon(loginButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				loginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// TODO:화면전환
			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 로그인
				String inputId = id.getText();
				@SuppressWarnings("deprecation")
				String inputPw = pw.getText();
				String inputName = name.getText();

				String message = null;
				if ((message = RhythmTyping.connectDB.checkInfo(inputId,
						inputPw, inputName)) == "true") {//입력한 정보의 플레이어가 존재한다면
					id.setFocusable(false);
					pw.setFocusable(false);
					name.setFocusable(false);
					frame.setFocusable(true);
					frame.change("selectMusicPanel");

				} else {
					JOptionPane.showMessageDialog(null, message, "로그인 실패",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		add(loginButton);

		signupButton.setBounds(690, 400, 200, 100);
		signupButton.setBorderPainted(false);
		signupButton.setContentAreaFilled(false);
		signupButton.setFocusPainted(false);
		signupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				signupButton.setIcon(signupButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				signupButton.setIcon(signupButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				signupButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// TODO:화면전환
			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때 회원가입
				String inputId = id.getText();
				@SuppressWarnings("deprecation")
				String inputPw = pw.getText();
				String inputName = name.getText();
				if ((inputId.length() > 3 && inputId.length() < 9)
						&& (inputPw.length() > 3 && inputPw.length() < 9)
						&& (inputName.length() > 1 && inputName.length() < 5)) {// 10이상은
																				// 안됨
					if (RhythmTyping.connectDB.checkJoinInfo(inputId, inputPw,
							inputName)) {//입력한 정보가 플레이어에 없다면

						JOptionPane.showMessageDialog(null,
								"회원가입이 정상적으로 처리되었습니다.", "회원가입 성공",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.",
								"회원가입 실패", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"아이디와 비밀번호는 4자리~8자리를 입력해주세요.\n이름은 2~4자리를 입력해주세요.",
							"정보 재 입력", JOptionPane.ERROR_MESSAGE);

				}
			}

		});
		add(signupButton);

		id_label.setBounds(500, 140, 200, 50);
		id_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		add(id_label);

		id = new JTextField();
		id.setBounds(600, 150, 200, 30);
		add(id);

		pw_label.setBounds(480, 220, 200, 50);
		pw_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		add(pw_label);

		pw.setEchoChar('*');
		pw.setBounds(600, 230, 200, 30);
		add(pw);

		name_label.setBounds(500, 300, 200, 50);
		name_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		add(name_label);

		name = new JTextField();
		name.setBounds(600, 310, 200, 30);
		name_label.setVisible(true);
		name.setVisible(true);
		add(name);

	}

}
