package rhythm;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FrameExam extends JFrame {
	public FrameExam() {
		setTitle("Control wheel speed of JScrollPane");
		setSize(1280, 720);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane jsp = new JScrollPane();
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(16);	//스크롤 속도
		jsp.setBorder(null);
		jsp.setBounds(0, 0, 1280, 720);
		add(jsp);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(450, 2000));//스크롤뷰생성
		panel.setLayout(null);
		jsp.setViewportView(panel);

		
		JPanel jpp=new JPanel();
		jpp.setLayout(null);
		jpp.setBounds(0, 0, 1280, 100);

		JLabel jl=new JLabel("강남스타일-PSY");
		Font f1 = new Font("나눔바른고딕", Font.PLAIN, 25);
		jl.setBounds(100, 0, 400, 100);
		jl.setFont(f1);
		JLabel jl2=new JLabel("최고점수 : 123450");
		jl2.setBounds(600, 0, 500, 100);
		jl2.setFont(f1);
		JButton textbox = new JButton("1");
		textbox.setBounds(1000, 0, 200, 100);
		jpp.add(jl);
		jpp.add(jl2);

		jpp.add(textbox);
		panel.add(jpp);
		
		JButton textbox2 = new JButton("2");
		textbox2.setBounds(0, 100, 1280, 100);
		panel.add(textbox2);

		JButton textbox3 = new JButton("3");
		textbox3.setBounds(0, 200, 1280, 100);
		panel.add(textbox3);

		JButton textbox4 = new JButton("4");
		textbox4.setBounds(0, 300, 1280, 100);
		panel.add(textbox4);

		JButton textbox5 = new JButton("5");
		textbox5.setBounds(0, 400, 1280, 100);
		panel.add(textbox5);

		JButton textbox6 = new JButton("6");
		textbox6.setBounds(0, 500, 1280, 100);
		panel.add(textbox6);

		JButton textbox7= new JButton("7");
		textbox7.setBounds(0, 600, 1280, 100);
		panel.add(textbox7);
		
		JButton textbox8 = new JButton("8");
		textbox8.setBounds(0, 700, 1280, 100);
		
		panel.add(textbox8);
		
		JButton textbox9 = new JButton("9");
		textbox9.setBounds(0, 800, 1280, 100);
		panel.add(textbox9);

		JButton textbox10 = new JButton("10");
		textbox10.setBounds(0, 900, 1280, 100);
		panel.add(textbox10);

		JButton textbox11 = new JButton("11");
		textbox11.setBounds(0, 1000, 1280, 100);
		panel.add(textbox11);

		JButton textbox12 = new JButton("12");
		textbox12.setBounds(0, 1100, 1280, 100);
		panel.add(textbox12);

		JButton textbox13 = new JButton("13");
		textbox13.setBounds(0, 1200, 1280, 100);
		panel.add(textbox13);

		JButton textbox14= new JButton("14");
		textbox14.setBounds(0, 1300, 1280, 100);
		panel.add(textbox14);

		
		setVisible(true);
	}
	public static void main(String arg[]) {
		new FrameExam();
		
	}
}