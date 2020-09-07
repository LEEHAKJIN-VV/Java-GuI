package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.util.*;

public class GameFrame extends JFrame implements Runnable {
	static final int ATKSIZE = 5; // 장애물 크기
	static final int USERSIZE = 10; // 사용자 크기
	static String time; // 게임 시간 표시
	static UserPilot user = new UserPilot(); // 움직일 객체 생성
	static ArrayList<AiAttacker> attackers = new ArrayList<>(); // attacker 를 담는 arraylist
	static JLabel timeLabel = new JLabel("00.00"); // 시간표시

	private AttackerThread at = new AttackerThread(); // AttackerThread 생성

	Image userImg = new ImageIcon("images/user.png").getImage(); // 사용자 이미지 가져옴
	Image attackerImg = new ImageIcon("images/attacker.png").getImage(); // attacker 이미지 가져옴

	Graphics gc;
	Image buffimg = null; // 더블버퍼링을 사용하기위한 버퍼이미지를 정의

	public GameFrame() {
		setTitle("Dodge Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X표시 누르면 화면 꺼짐짐
		setOn(getContentPane()); // 스레드 시작,레이블 설정

		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().requestFocus(); // 포커스 강제 지정
	}

	@Override
	public void run() {
		while (collisionDetect()) { // 충돌했을때 while문 탈출
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
		}
		gameOver(time); // 게임 종료 클래스 생성
	}

	public void setOn(Container c) { // 스레드 생성, 기본적인 label 설정
		Thread userThread = new Thread(at);
		userThread.start();
		c = MainFrame.setContainer(getContentPane());
		c.add(timeLabel);
		timeLabel.setFont(MainFrame.font); // 폰트 지정
		MainFrame.paintLabel(timeLabel); // 라벨 색칠
		timeLabel.setBounds(300, 300, 150, 20); // 라벨 위치지정
		c.addKeyListener(new MyKeyListener()); // 키 리스너 등록
		c.requestFocus(); // 포커스 강제 지정
	}
	
	private void gameOver(String endTime) { // 게임이 끝났을때 호출하는 메소드
		new GameOver(this, "이름입력", endTime); // endTime은 끝난 시간
		setVisible(false); // 눈에 안보이게함
		attackers.removeAll(attackers); // 모든 Attackers 객체 삭제
	}

	synchronized public void update(Graphics g) { // 프레임 내의 버퍼이미지를 이용하여 깜빡임 현상을 완전히 없앰
		paint(g);
	}

	public void paint(Graphics g) { // 이미지를 그리기위한메서드를 실행
		buffimg = createImage(MainFrame.WIDTH, MainFrame.HEIGHT); // 버퍼이미지를 그린다 (더블버퍼링을 사용하여 화면의 깜빡임을
		// 없앰
		gc = buffimg.getGraphics(); // 버퍼이미지에 대한 그래픽 객채를 얻어옴
		drawimages(g); // 그래픽을 그림
	}

	public void drawimages(Graphics g) { // 유저와 공격자들을 그리는 메소드
		setBackground(Color.BLACK); // 배경색을 검은색으로 설정
		gc.drawImage(MainFrame.bkImg,0,0,this); // 배경 이미지 설정
		userDrawImg(); // 사용자의 그림을 그림
		atkDrawImg(); // attacker 그림을 그림
		g.drawImage(buffimg, 0, 0, this); // 버퍼이미지를 그림 0,0으로 좌표를 맞춰서프레임크기에 맞춤
	}

	public void userDrawImg() { // 유저를 그리는 메소드
		gc.setClip(user.userVec.posx, user.userVec.posy, USERSIZE, USERSIZE); // 사용자의이미지의 좌표와 크기를 받아온다
		gc.drawImage(userImg, user.userVec.posx, user.userVec.posy, 10, 10, this); // 사용자를 좌표에 따라 장소를 바꾸어 그린다.
	}

	private void moveAtkImg(Image atkImage, int posx, int posy) {
		gc.setClip(posx, posy, ATKSIZE, ATKSIZE); // 장애물의이미지의 좌표와 크기를 받아온다
		gc.drawImage(attackerImg, posx, posy, this); // 장애물을 좌표에 따라 장소를 바꾸어 그린다
	}

	public void atkDrawImg() {
		for (AiAttacker atck : attackers) { // Aiattacker 들의 위치를 그림
			moveAtkImg(attackerImg, atck.vec.posx, atck.vec.posy);
		}
	}

	static boolean collisionDetect() { // 충돌 여부 판단 1개라도 층돌하면 false return
		Iterator<AiAttacker> itr = attackers.iterator();
		while (itr.hasNext()) {
			AiAttacker atk = itr.next();
			if (distance(atk) <= 5)
				return false;
		}
		return true;
	}

	private static double distance(AiAttacker target) { // 두점사이의 거리 return
		return Math.sqrt((user.userVec.posx - target.vec.posx) * (user.userVec.posx - target.vec.posx)
				+ (user.userVec.posy - target.vec.posy) * (user.userVec.posy - target.vec.posy));
	}
}
