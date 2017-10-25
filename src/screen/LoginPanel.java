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
import rhythm.Main;

public class LoginPanel extends JPanel {
	
	private RhythmTyping frame;
	private ImageIcon loginButtonEnteredImage = new ImageIcon(
				Main.class.getResource("../images/loginButtonEntered.png"));
	private ImageIcon loginButtonBasicImage = new ImageIcon(Main.class.getResource("../images/loginButtonBasic.png"));
		// login��ư
	private JButton loginButton = new JButton(loginButtonBasicImage);
		
	private ImageIcon signupButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/signupButtonEntered.png"));
	private ImageIcon signupButtonBasicImage = new ImageIcon(Main.class.getResource("../images/signupButtonBasic.png"));
	// login��ư
	private JButton signupButton = new JButton(signupButtonBasicImage);
	
	
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private JTextField id;
	private JPasswordField pw = new JPasswordField(20);
	private JTextField name;

	
	private JLabel id_label= new JLabel("���̵� : ");
	private JLabel pw_label= new JLabel("��й�ȣ : ");
	private JLabel name_label= new JLabel("�̸� : ");

	ConnectDB connectDB = new ConnectDB();
	
	private JButton ok;
	public LoginPanel(RhythmTyping rhythmTyping) {
		frame=rhythmTyping;
		setLayout(null); //�г��� Layout�� NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30,Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT-30);
		
		backButton.setVisible(true);
		backButton.setBounds(40, 50, 60, 60);
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
		
		loginButton.setBounds(400, 400, 200, 100);
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				loginButton.setIcon(loginButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				loginButton.setIcon(loginButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				loginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// TODO:ȭ����ȯ
			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				String inputId = id.getText();
				@SuppressWarnings("deprecation")
				String inputPw = pw.getText();
				String inputName = name.getText();

				//frame.change("selectMusicPanel");

				
				if(inputId.length() <3 || inputPw.length()<3) {
					JOptionPane.showMessageDialog(null,"���̵�� ��й�ȣ�� 4�ڸ� �̻��� �Է����ּ���.", "���� �� �Է�",JOptionPane.ERROR_MESSAGE);
					//new ReInputDailog();
				}else {
					if(connectDB.checkInfo(inputId,inputPw,inputName)) {
						id.setFocusable(false);
						pw.setFocusable(false);
						name.setFocusable(false);
						frame.setFocusable(true);
						frame.change("selectMusicPanel");

					}
					else {
						//TODO: ���̵�/��й�ȣ/�̸��� Ʋ�ȴ�.
						JOptionPane.showMessageDialog(null,"�������� �ʴ� ���̵��Դϴ�.", "�α��� ����",JOptionPane.ERROR_MESSAGE);
					}
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
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				signupButton.setIcon(signupButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				signupButton.setIcon(signupButtonBasicImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				signupButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// TODO:ȭ����ȯ
			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				String inputId = id.getText();
				@SuppressWarnings("deprecation")
				String inputPw = pw.getText();
				String inputName = name.getText();
				//frame.change("selectMusicPanel");

				if(!((inputId.length() >3 && inputId.length() <11)|| (inputPw.length()>3 && inputPw.length()<11)||(inputName.length() >2 && inputId.length() <6))) {//10�̻��� �Ƶ�
					JOptionPane.showMessageDialog(null,"���̵�� ��й�ȣ�� 4�ڸ�~10�ڸ��� �Է����ּ���.\n�̸��� 3~5�ڸ��� �Է����ּ���.", "���� �� �Է�",JOptionPane.ERROR_MESSAGE);
					//new ReInputDailog();
				}else {
					if(connectDB.checkJoinInfo(inputId,inputPw,inputName)) {
					
						JOptionPane.showMessageDialog(null,"ȸ�������� ���������� ó���Ǿ����ϴ�.", "ȸ������ ����",JOptionPane.INFORMATION_MESSAGE);
					
					}else {
						JOptionPane.showMessageDialog(null,"�̹� �����ϴ� ���̵��Դϴ�.", "ȸ������ ����",JOptionPane.ERROR_MESSAGE);
					}
				}
				//TODO:
				//�Է��� ������ DB�� ���ٸ� ���Լ��� DIALOG 
			}

		});
		add(signupButton);
		
		id_label.setBounds(500, 140, 200, 50);
		id_label.setFont(new Font("�����ٸ����",Font.PLAIN, 20));
		add(id_label);
		
		id = new JTextField();
		id.setBounds(600, 150, 200, 30);
		add(id);

		pw_label.setBounds(480, 220, 200, 50);
		pw_label.setFont(new Font("�����ٸ����",Font.PLAIN, 20));
		add(pw_label);

		pw.setEchoChar('*');
		pw.setBounds(600, 230, 200, 30);
		add(pw);
		
		name_label.setBounds(500, 300, 200, 50);
		name_label.setFont(new Font("�����ٸ����",Font.PLAIN, 20));
		add(name_label);
		
		name = new JTextField();
		name.setBounds(600, 310, 200, 30);
		name_label.setVisible(true);
		name.setVisible(true);
		add(name);
		
		//TODO: �ѱ۷� ���� �Է� �����ϰ�
	}
	public void backSelect() {
		frame.change("startPanel");
	}
}
