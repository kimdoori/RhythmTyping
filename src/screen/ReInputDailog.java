package screen;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ReInputDailog extends JDialog{
	private JLabel info_label = new JLabel("���̵�� ��� ��ȣ�� 6�ڸ� �̻��� �Է����ּ���.");
	private JButton ok_button = new JButton("Ȯ��");
	public ReInputDailog() {
		setLayout(new BorderLayout());

		info_label.setFont(new Font("�������",Font.PLAIN, 20));
		add(info_label,BorderLayout.NORTH);


		add(ok_button,BorderLayout.SOUTH);
		setBounds(400,250,400,300);
		setVisible(true);
	}

}
