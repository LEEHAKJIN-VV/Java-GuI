package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {
	Thread thread;
	static final int WIDTH = 400; // 게임화면의 가로
	static final int HEIGHT = 400; // 게임화면의 세로

	private LogFrame logFrame; // 로그프레임 변수 생성
	private GameFrame dm;
	static Font font = new Font("SansSerif", Font.BOLD, 10);
	static Image bkImg = new ImageIcon("images/bkImg.jpg").getImage(); // 배경 이미지

	private JButton[] button = new JButton[3]; // GAME START, GAME END, LOG(기록) 을 위한 버튼 생성

	public MainFrame() {
		setTitle("Dodge Game"); // 닷지 게임
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X표시 누르면 화면 꺼짐짐
		Container c = setContainer(getContentPane());
		setButton(c); // 버튼 컨텐트 팬에 추가, 문자열 지정, 위치 set
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void paint(Graphics g) { // 배경이미지 그림
		g.drawImage(MainFrame.bkImg,0,0,this);
	}

	@Override
	public void actionPerformed(ActionEvent e) { // 눌러진 버튼 무슨버튼인지 확인
		switch (((JButton) e.getSource()).getText()) {
		case "START": // 게임 시작 버튼 눌렀을때 난이도 선택 창 만듬
			gameStart();
			break;
		case "RANKING": // 게임 기록 버튼 눌렀을때
			gameLog();
			break;
		case "END": // 게임 끝 버튼 눌렀을때
			gameEnd();
			break;
		}
	}

	private void setButton(Container c) {
		button[0] = new JButton("START"); // 게임시작
		button[1] = new JButton("RANKING"); // 게임 끝
		button[2] = new JButton("END"); // 게임 기록 (1,2,3 등)
		for (int i = 0; i < button.length; i++) {
			c.add(button[i]);
			button[i].setBounds(120, (i + 1) * 60, 150, 30); // 버튼 위치와 크기 지정
			button[i].addActionListener(this); // 버튼 액션리스너 달기
			button[i].setBorderPainted(false); // 버튼 테두리 없앰
		}
	}

	private void gameStart() { // 게임 시작 메소드
		dm = new GameFrame();
		Thread th = new Thread(new TimerThread());
		thread = new Thread(dm);
		thread.start(); // 스레드 시작
		th.start();
	}

	private void gameLog() { // log눌렀을떄
		logFrame = new LogFrame("Log");
	}

	private void gameEnd() { // 게임 end버튼눌렀을떄
		Main.saveLog(); // 게임종료시 게임 기록들 저장
		System.exit(0); // 종료
	}

	static Container setContainer(Container c) { // 배치관리자, 배경 설정
		c.setLayout(null);
		c.setBackground(Color.BLACK);
		return c;
	}

	static void paintLabel(JLabel label) { // 레이블 색 설정 메소드
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		label.setForeground(Color.WHITE);
	}
}
