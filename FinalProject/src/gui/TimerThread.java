package gui;

public class TimerThread implements Runnable{

	@Override
	synchronized public void run() {
		int n = 0; // Ÿ�̸� ī��Ʈ ��
		while (GameFrame.collisionDetect()) { // �浹������ while�� Ż��
			n++; GameFrame.timeLabel.setText(toTime(n));
			if(n%1500 == 0) allChangeDir(); // 15�ʸ��� ���� �����ϰ� �ٲ�
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
		}
		GameFrame.time=toTime(n); // gameOver ������ ���ڰ� �Ѱ���
	}
	private void allChangeDir() { // ��� ATK�� ���Ⱚ�� �������� �ٲ�
		for (AiAttacker atk : GameFrame.attackers)
			atk.dir = (int) (Math.random() * 4) + 1;
	}
	
	private String toTime(int time) {
		int mSec = time % 100;
		int sec = (time / 100) % 60;
		int min = (time / 100) / 60;
		return String.format("%02d : %02d : %02d", min, sec, mSec);
	}
}
