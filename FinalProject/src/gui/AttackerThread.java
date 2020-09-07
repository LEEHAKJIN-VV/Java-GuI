package gui;

import java.util.ArrayList;

public class AttackerThread implements Runnable{
	private static final int MAX_SIZE = 80; // 최대 Attacker 갯수
	private static final int ATKMOVDIS = 5; // 장애물의 속력

	@Override
	synchronized public void run() {
		while (GameFrame.collisionDetect()) { // check가 false 이면 게임이 종료 된거임 그래서 바로 종료
			if (GameFrame.attackers.size() < MAX_SIZE) { // 최대 50개 생성
				GameFrame.attackers.add(new AiAttacker());
			}
			decDirection(GameFrame.attackers); // 계속 방향 바꿈 동시에 움직이기도 함
			try {
				Thread.sleep(100); // 0.1초 동안 잠을 잔다

			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	private void decDirection(ArrayList<AiAttacker> list) { // 방향을 결정해주는 메소드
		for (AiAttacker atk : list) { // 모든 AiAttacker객체에 대해 방향값과, 움직일 거리 정함
			changeDirection(atk.getDir(), atk); // 방향 변경
			move(atk.getDir(), atk); // AiAttacker객체 움직임
		}
	}

	private void changeDirection(int dir, AiAttacker target) { // 방향을 바꿔주는 메소드
		switch (dir) {
		case Direction.DL: // 왼쪽아래로 이동할떄
			if (target.vec.posx <= 0 && target.vec.posy >= 0) // x값 음수 y 값 양수
				target.dir = Direction.DR;
			else if (target.vec.posy >= MainFrame.HEIGHT) // y값이 세로 보다 커짐
				target.dir = Direction.UL;
			break;
		case Direction.DR: // 오른쪽 아래로 이동할때
			if (target.vec.posx >= MainFrame.WIDTH) // x값이 가로 사이즈를 벗어나면 (커짐)
				target.dir = Direction.DL;
			else if (target.vec.posy >= MainFrame.HEIGHT) // y값이 세로보다 커짐
				target.dir = Direction.UR;
			break;
		case Direction.UL: // 왼쪽위로 이동할떄
			if (target.vec.posx <= 0) // x 값이 0보다 작을떄
				target.dir = Direction.UR;
			else if (target.vec.posy <= 0) // x값도 음수이고 y값이 음수
				target.dir = Direction.DL;
			break;
		case Direction.UR: // 오른쪽 위로 이동할때
			if (target.vec.posx >= MainFrame.WIDTH) // x값이 가로 사이즈를 벗어나면 (커짐)
				target.dir = Direction.UL;
			else if (target.vec.posy <= 0) // y값이 0보다 작아짐
				target.dir = Direction.DR;
			break;
		default:
			break;
		}
	}

	public void move(int op, AiAttacker target) { // AiAttacker객체가 움직이는 메소드
		switch (op) {
		case Direction.DL: // 왼쪽 아래로 이동할때
			target.vec.posx -= ATKMOVDIS;
			target.vec.posy += ATKMOVDIS;
			break;
		case Direction.DR: // 오른쪽 아래로 이동할떄
			target.vec.posx += ATKMOVDIS;
			target.vec.posy += ATKMOVDIS;
			break;
		case Direction.UL: // 왼쪽 위로 이동할때
			target.vec.posx -= ATKMOVDIS;
			target.vec.posy -= ATKMOVDIS;
			break;
		case Direction.UR: // 오른쪽 위로 이동할떄
			target.vec.posx += ATKMOVDIS;
			target.vec.posy -= ATKMOVDIS;
			break;
		default:
			break;
		}
	}
}
