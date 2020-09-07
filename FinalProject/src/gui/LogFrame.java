package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogFrame extends JFrame implements ActionListener { // 기록을 표시할 레이블 생성 필요
	static final int LOGSIZE = 10; // 기록할수 있는 LOG 사이즈
	private JLabel[] logLabel = new JLabel[LOGSIZE]; // 1등~10등 표시
	private JButton button = new JButton("Back"); // 뒤로 가는 버튼 표시

	public LogFrame(String title) {
		super(title);
		Container c = MainFrame.setContainer(getContentPane()); // 컨에이너, 배치관리자 설정
		button.addActionListener(this); // 리스너 달기
		button.setBackground(Color.WHITE);
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { // 버튼을 눌렀을때 종료
		dispose();
	}
		
	public void paint(Graphics g) {
		g.drawImage(MainFrame.bkImg,0,0,this);
		setLabel();
	}

	private void setLabel() { // 기록 지정
		int i = 0;
		for (String log : GameOver.logTime.keySet()) { // TreeMap에 있는 모든 key
			logLabel[i] = new JLabel(i + 1 + "등   " + "닉네임: "+ GameOver.logTime.get(log) + "  기록: " + log);
			logLabel[0].setOpaque(false);
			add(logLabel[i]);
			logLabel[i].setBounds(20, (i + 1) * 30, 250, 20);
			drawLogLabel(i);
			i++;
		}
		add(button);
		button.setBounds(300, 330, 80, 20);
		button.setBorderPainted(false); // 버튼 테두리 없앰
	}	
	private void drawLogLabel(int number) { // logLabel을 그리는 메소드
		if(number<3) // 1등,2등,3등 을 그림
			drawRanking(logLabel[number],number);
		else		
			MainFrame.paintLabel(logLabel[number]);
	}
	private void drawRanking(JLabel label, int i) {
		if (i == 0)
			label.setForeground(Color.RED);
		else if (i == 1)
			label.setForeground(Color.BLUE);
		else
			label.setForeground(Color.GREEN);
	}
}
