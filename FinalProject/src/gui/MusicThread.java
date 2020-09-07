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
				Thread.sleep(255000); // 4�� 15�ʵ��� ���� �ܴ� ( ���� : �뷡�� 4��11���̱� ����)
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
