package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import rhythm.Main;

public class SelectNotePanel extends JPanel{
	private RhythmTyping frame;

	// �ڷΰ����ư
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);

	private ImageIcon okayButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/okayButtonEntered.png"));
	private ImageIcon okayButtonBasicImage = new ImageIcon(Main.class.getResource("../images/okayButtonBasic.png"));
	private JButton okayButton = new JButton(okayButtonBasicImage);
	
	public static int chooseNote=1;//1�̸� ��Ʈ1, 2�̸� ��Ʈ 2
	public SelectNotePanel(RhythmTyping rhythmTyping){
		frame = rhythmTyping;

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
		
		ButtonGroup noteGroup=new ButtonGroup();
		JRadioButton note1_radio=new JRadioButton("��Ʈ 1",true);
		JRadioButton note2_radio=new JRadioButton("��Ʈ 2");
		noteGroup.add(note1_radio);
		noteGroup.add(note2_radio);
		
		note1_radio.setVisible(true);
		note1_radio.setFont(new Font("�����ٸ����",Font.PLAIN, 30));
		note1_radio.setBounds(300, 100, 200, 100);
		note1_radio.setBorderPainted(false);
		note1_radio.setContentAreaFilled(false);
		note1_radio.setFocusPainted(false);
		
		note2_radio.setVisible(true);
		note2_radio.setFont(new Font("�����ٸ����",Font.PLAIN, 30));
		note2_radio.setBounds(300, 350, 200, 100);
		note2_radio.setBorderPainted(false);
		note2_radio.setContentAreaFilled(false);
		note2_radio.setFocusPainted(false);
		
		add(note1_radio);
		add(note2_radio);
		
		okayButton.setVisible(true);
		okayButton.setBounds(1000, 600, 200, 100);
		okayButton.setBorderPainted(false);
		okayButton.setContentAreaFilled(false);
		okayButton.setFocusPainted(false);
		okayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ö���� �� ����
				okayButton.setIcon(okayButtonEnteredImage);
				// ��ư���� ���콺�� �ö󰡸� �� ������� �ٲ�
				okayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// �ش� ��ư���� ���콺�� ������ ��
				okayButton.setIcon(okayButtonEnteredImage);
				// ���콺�� ��ư���� ����� ���� ������� �ٲ�
				okayButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// ��ư�� Ŭ������ ��
				if(note1_radio.isSelected()) {//1�� ���õǾ�����
					chooseNote=0;
				}else {
					chooseNote=1;
				}
				frame.change("gamePanel");

			}

		});
		add(okayButton);
		
	}
	public void backSelect() {
		frame.change("selectMusicPanel");
	}
}
