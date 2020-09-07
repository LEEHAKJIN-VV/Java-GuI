package gui;

import java.util.ArrayList;

public class AttackerThread implements Runnable{
	private static final int MAX_SIZE = 80; // �ִ� Attacker ����
	private static final int ATKMOVDIS = 5; // ��ֹ��� �ӷ�

	@Override
	synchronized public void run() {
		while (GameFrame.collisionDetect()) { // check�� false �̸� ������ ���� �Ȱ��� �׷��� �ٷ� ����
			if (GameFrame.attackers.size() < MAX_SIZE) { // �ִ� 50�� ����
				GameFrame.attackers.add(new AiAttacker());
			}
			decDirection(GameFrame.attackers); // ��� ���� �ٲ� ���ÿ� �����̱⵵ ��
			try {
				Thread.sleep(100); // 0.1�� ���� ���� �ܴ�

			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	private void decDirection(ArrayList<AiAttacker> list) { // ������ �������ִ� �޼ҵ�
		for (AiAttacker atk : list) { // ��� AiAttacker��ü�� ���� ���Ⱚ��, ������ �Ÿ� ����
			changeDirection(atk.getDir(), atk); // ���� ����
			move(atk.getDir(), atk); // AiAttacker��ü ������
		}
	}

	private void changeDirection(int dir, AiAttacker target) { // ������ �ٲ��ִ� �޼ҵ�
		switch (dir) {
		case Direction.DL: // ���ʾƷ��� �̵��ҋ�
			if (target.vec.posx <= 0 && target.vec.posy >= 0) // x�� ���� y �� ���
				target.dir = Direction.DR;
			else if (target.vec.posy >= MainFrame.HEIGHT) // y���� ���� ���� Ŀ��
				target.dir = Direction.UL;
			break;
		case Direction.DR: // ������ �Ʒ��� �̵��Ҷ�
			if (target.vec.posx >= MainFrame.WIDTH) // x���� ���� ����� ����� (Ŀ��)
				target.dir = Direction.DL;
			else if (target.vec.posy >= MainFrame.HEIGHT) // y���� ���κ��� Ŀ��
				target.dir = Direction.UR;
			break;
		case Direction.UL: // �������� �̵��ҋ�
			if (target.vec.posx <= 0) // x ���� 0���� ������
				target.dir = Direction.UR;
			else if (target.vec.posy <= 0) // x���� �����̰� y���� ����
				target.dir = Direction.DL;
			break;
		case Direction.UR: // ������ ���� �̵��Ҷ�
			if (target.vec.posx >= MainFrame.WIDTH) // x���� ���� ����� ����� (Ŀ��)
				target.dir = Direction.UL;
			else if (target.vec.posy <= 0) // y���� 0���� �۾���
				target.dir = Direction.DR;
			break;
		default:
			break;
		}
	}

	public void move(int op, AiAttacker target) { // AiAttacker��ü�� �����̴� �޼ҵ�
		switch (op) {
		case Direction.DL: // ���� �Ʒ��� �̵��Ҷ�
			target.vec.posx -= ATKMOVDIS;
			target.vec.posy += ATKMOVDIS;
			break;
		case Direction.DR: // ������ �Ʒ��� �̵��ҋ�
			target.vec.posx += ATKMOVDIS;
			target.vec.posy += ATKMOVDIS;
			break;
		case Direction.UL: // ���� ���� �̵��Ҷ�
			target.vec.posx -= ATKMOVDIS;
			target.vec.posy -= ATKMOVDIS;
			break;
		case Direction.UR: // ������ ���� �̵��ҋ�
			target.vec.posx += ATKMOVDIS;
			target.vec.posy -= ATKMOVDIS;
			break;
		default:
			break;
		}
	}
}
