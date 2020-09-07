package gui;

public class Vector { // 좌표값을 쉽게 표현하는 클래스
	int posx, posy; // Vector좌표의 x,y좌표

	public Vector() { // Atk의 Vector변수 생성자
		if (GameFrame.attackers.size() % 2 == 0) { // 짝수이면 위쪽 프레임에 생성
			this.posx = (int) (Math.random() * MainFrame.WIDTH);
			this.posy = (int) (Math.random() * 30);
		} else { 									// 홀수이면 아래쪽 프레임 생성
			this.posx = (int) (Math.random() * MainFrame.WIDTH);
			this.posy = (int) (Math.random() * 30) + 360;
		}
	}

	public Vector(int posx, int posy) { // UserPiolt의 Vector 생성자
		this.posx = posx;
		this.posy = posy;
	}
}
