package gui;
import java.awt.event.*;

public class MyKeyListener extends KeyAdapter {
	
	static final int USERMOVDIS = 10; // 사용자가움직이는 속력
	static final int SIZELIMIT = 20; // 화면 프레임 벗어나지 않게 하는 변수
	private UserPilot user = GameFrame.user;
	boolean upP = false; boolean downP = false;
	boolean leftP = false; boolean rightP = false;
	
	@Override
	public void keyPressed(KeyEvent e) { // 화면 사이즈를 벗어 나지 않게함
		int keyCode = e.getKeyCode();
		// 키보드를 눌렀을떄
		switch (keyCode) {
		case KeyEvent.VK_UP: // 위 방향키 눌렀을때
			upP = true;
			if (decDir(user.userVec.posx, user.userVec.posy - USERMOVDIS) == true)
				user.userVec.posy -= USERMOVDIS; // 참이면 윈도우 사이즈를 벗어나지 않았다.
			break;
		case KeyEvent.VK_DOWN: // 아래 방향키 눌렀을때
			downP = true;
			if (decDir(user.userVec.posx, user.userVec.posy + USERMOVDIS) == true)
				user.userVec.posy += USERMOVDIS;
			break;
		case KeyEvent.VK_LEFT: // 왼쪽 방향키 눌렀을때
			leftP = true;
			if (decDir(user.userVec.posx - USERMOVDIS, user.userVec.posy) == true)
				user.userVec.posx -= USERMOVDIS;
			break;
		case KeyEvent.VK_RIGHT: // 오른쪽 방향키 눌렀을때
			rightP = true;
			if (decDir(user.userVec.posx + USERMOVDIS, user.userVec.posy) == true)
				user.userVec.posx += USERMOVDIS;
			break;
		default:
			break;
		}
	}

	@Override
	// 키보드에서 손을 떼었을 때
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
	
	private boolean decDir(int x, int y) { // 윈도우 사이즈 크기 못벗어나게 하는 함수
		if ((x < MainFrame.WIDTH - SIZELIMIT && x > SIZELIMIT) && (y < MainFrame.HEIGHT - SIZELIMIT && y > SIZELIMIT))
			return true;
		else
			return false;
	}
}


