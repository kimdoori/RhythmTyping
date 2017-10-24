package screen;

import java.awt.Color;

import javax.swing.JPanel;

import rhythm.Main;

public class SignupPanel extends JPanel {
	private RhythmTyping frame;

	public SignupPanel(RhythmTyping rhythmTyping) {
		frame=rhythmTyping;
		setLayout(null); //패널의 Layout을 NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30,Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT-30);
	}

}
