package gui;

public class AiAttacker { // AiAttacker �� �����̴� ��ü
	int dir = (int) (Math.random() * 4) + 1; // AiAttacker �� ���� ���� �����ϰ� ����
	Vector vec;

	public AiAttacker() { // AiAttacker ������
		this.vec = new Vector();
	}

	public int getDir() { // dir�� return
		return dir;
	}
}
