package gui;

public class Vector { // ��ǥ���� ���� ǥ���ϴ� Ŭ����
	int posx, posy; // Vector��ǥ�� x,y��ǥ

	public Vector() { // Atk�� Vector���� ������
		if (GameFrame.attackers.size() % 2 == 0) { // ¦���̸� ���� �����ӿ� ����
			this.posx = (int) (Math.random() * MainFrame.WIDTH);
			this.posy = (int) (Math.random() * 30);
		} else { 									// Ȧ���̸� �Ʒ��� ������ ����
			this.posx = (int) (Math.random() * MainFrame.WIDTH);
			this.posy = (int) (Math.random() * 30) + 360;
		}
	}

	public Vector(int posx, int posy) { // UserPiolt�� Vector ������
		this.posx = posx;
		this.posy = posy;
	}
}
