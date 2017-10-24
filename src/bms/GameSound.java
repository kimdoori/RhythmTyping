package bms;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class GameSound {
	 private Clip clip;
	public  GameSound(String SoundFileName) {
		try {
			File soundFile = new File(SoundFileName);
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public  GameSound(URL SoundFileName) {
		try {
			
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(SoundFileName);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public void play() {

		if (clip.isRunning()){

			clip.stop(); //만약 다른게 clip이 실행중이면 중단
		}
		clip.setFramePosition(0); 

		clip.start(); 

	}
	public void stop() {
		clip.stop();
	}
}

