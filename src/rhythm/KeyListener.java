package rhythm;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	
//	APEACH
//	CON
//	FRODO
//	JAY-G
//	NEO
//	MUZI
//	RYAN
//	TUBE
//	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("ㅇ강");

		if (RhythmTyping.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			System.out.println("A");

			RhythmTyping.game.pressA();
			
			//키를 눌렀을 때 효과음
			//new Music(".mp3",false).start();
			
		} else if (e.getKeyCode() == KeyEvent.VK_C) {
			System.out.println("C");
			RhythmTyping.game.pressC();

		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			RhythmTyping.game.pressF();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			RhythmTyping.game.pressJ();
		} else if (e.getKeyCode() == KeyEvent.VK_N) {
			RhythmTyping.game.pressN();
		} else if (e.getKeyCode() == KeyEvent.VK_M) {
			RhythmTyping.game.pressM();
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			RhythmTyping.game.pressR();
		}
		else if (e.getKeyCode() == KeyEvent.VK_T) {
			RhythmTyping.game.pressT();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (RhythmTyping.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			RhythmTyping.game.releaseA();
			//키를 눌렀을 때 효과음
			//new Music(".mp3",false).start();
			
		} else if (e.getKeyCode() == KeyEvent.VK_C) {
			RhythmTyping.game.releaseC();

		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			RhythmTyping.game.releaseF();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			RhythmTyping.game.releaseJ();
		} else if (e.getKeyCode() == KeyEvent.VK_N) {
			RhythmTyping.game.releaseN();
		} else if (e.getKeyCode() == KeyEvent.VK_M) {
			RhythmTyping.game.releaseM();
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			RhythmTyping.game.releaseR();
		}
		else if (e.getKeyCode() == KeyEvent.VK_T) {
			RhythmTyping.game.releaseT();
		}
	}

}
