package gui;

public class AiAttacker { // AiAttacker 는 움직이는 객체
	int dir = (int) (Math.random() * 4) + 1; // AiAttacker 의 방향 변수 랜덤하게 설정
	Vector vec;

	public AiAttacker() { // AiAttacker 생성자
		this.vec = new Vector();
	}

	public int getDir() { // dir값 return
		return dir;
	}
}
