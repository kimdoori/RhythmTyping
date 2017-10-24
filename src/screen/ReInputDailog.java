package screen;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ReInputDailog extends JDialog{
	private JLabel info_label = new JLabel("아이디와 비밀 번호는 6자리 이상을 입력해주세요.");
	private JButton ok_button = new JButton("확인");
	public ReInputDailog() {
		setLayout(new BorderLayout());

		info_label.setFont(new Font("맑은고딕",Font.PLAIN, 20));
		add(info_label,BorderLayout.NORTH);


		add(ok_button,BorderLayout.SOUTH);
		setBounds(400,250,400,300);
		setVisible(true);
	}

}
