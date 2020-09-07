package gui;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			playMusic();
			try {
				Thread.sleep(255000); // 4분 15초동안 잠을 잔다 ( 이유 : 노래가 4분11초이기 떄문)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void playMusic() {
		File file = new File(Main.class.getResource("").getPath() + "BGM.wav");
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
