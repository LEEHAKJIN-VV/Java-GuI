package gui;

public class TimerThread implements Runnable{

	@Override
	synchronized public void run() {
		int n = 0; // 타이머 카운트 값
		while (GameFrame.collisionDetect()) { // 충돌했을때 while문 탈출
			n++; GameFrame.timeLabel.setText(toTime(n));
			if(n%1500 == 0) allChangeDir(); // 15초마다 방향 랜덤하게 바꿈
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
		}
		GameFrame.time=toTime(n); // gameOver 했을때 인자값 넘겨줌
	}
	private void allChangeDir() { // 모든 ATK의 방향값을 랜덤으로 바꿈
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
