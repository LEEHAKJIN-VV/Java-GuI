package gui;
import java.awt.event.*;

public class MyKeyListener extends KeyAdapter {
	
	static final int USERMOVDIS = 10; // ����ڰ������̴� �ӷ�
	static final int SIZELIMIT = 20; // ȭ�� ������ ����� �ʰ� �ϴ� ����
	private UserPilot user = GameFrame.user;
	boolean upP = false; boolean downP = false;
	boolean leftP = false; boolean rightP = false;
	
	@Override
	public void keyPressed(KeyEvent e) { // ȭ�� ����� ���� ���� �ʰ���
		int keyCode = e.getKeyCode();
		// Ű���带 ��������
		switch (keyCode) {
		case KeyEvent.VK_UP: // �� ����Ű ��������
			upP = true;
			if (decDir(user.userVec.posx, user.userVec.posy - USERMOVDIS) == true)
				user.userVec.posy -= USERMOVDIS; // ���̸� ������ ����� ����� �ʾҴ�.
			break;
		case KeyEvent.VK_DOWN: // �Ʒ� ����Ű ��������
			downP = true;
			if (decDir(user.userVec.posx, user.userVec.posy + USERMOVDIS) == true)
				user.userVec.posy += USERMOVDIS;
			break;
		case KeyEvent.VK_LEFT: // ���� ����Ű ��������
			leftP = true;
			if (decDir(user.userVec.posx - USERMOVDIS, user.userVec.posy) == true)
				user.userVec.posx -= USERMOVDIS;
			break;
		case KeyEvent.VK_RIGHT: // ������ ����Ű ��������
			rightP = true;
			if (decDir(user.userVec.posx + USERMOVDIS, user.userVec.posy) == true)
				user.userVec.posx += USERMOVDIS;
			break;
		default:
			break;
		}
	}

	@Override
	// Ű���忡�� ���� ������ ��
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			upP = false;
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			downP = false;
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			leftP = false;
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			rightP = false;
	}
	
	private boolean decDir(int x, int y) { // ������ ������ ũ�� ������� �ϴ� �Լ�
		if ((x < MainFrame.WIDTH - SIZELIMIT && x > SIZELIMIT) && (y < MainFrame.HEIGHT - SIZELIMIT && y > SIZELIMIT))
			return true;
		else
			return false;
	}
}


