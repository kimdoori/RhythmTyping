package rhythm;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Test extends JFrame {

	public Test() {
		// TODO Auto-generated method stub

		setTitle("Control wheel speed of JScrollPane");
		setSize(500, 500);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane jsp = new JScrollPane();
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도
		jsp.setBorder(null);
		jsp.setBounds(10, 10, 470, 470);
		add(jsp);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(450, 1000));
		panel.setLayout(null);
		
		jsp.setViewportView(panel);
		
		JTextArea textbox = new JTextArea();
		textbox.setBounds(0, 0, 450, 1000);
		panel.add(textbox);

		
		
	

		setVisible(true);

	}

	public static void main(String[] args) {
		new Test();
	}

}
