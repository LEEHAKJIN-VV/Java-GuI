package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameOver extends JDialog {
	private JLabel label = new JLabel(); // 기록을 표시할 레이블
	private JTextField text = new JTextField(10); // 이름을 입력받는 텍스트 필드 글자길이는 10
	private JButton button = new JButton("OK"); // 이름을 입력하고 ok 버튼을 누르면 저장 (시간,이름)
	static TreeMap<String, String> logTime = new TreeMap<>(new LogComparator()); // 기록을 위한 Hash map
	// <기록, 이름 > 순으로 저장되어 있음 -> 정렬을 위하여

	public GameOver(JFrame frame, String title, String endTime) { // GrameOver 생성자
		super(frame, title, true); // 모달 다이얼로그 생성
		setLayout(new FlowLayout()); // 레이아웃 배치관리자 null로 만듬
		label.setText("기록: " + endTime);
		add(text); add(label); add(button);

		button.addActionListener(new ActionListener() { // Button ActionListener 작성 
			@Override
			public void actionPerformed(ActionEvent e) { // Button 누를시 기록 삽입
				compareLog(text.getText(), endTime);
				dispose(); // Dialog종료
				setVisible(false);
				Main.saveLog(); // 게임이 비정상적으로 종료될때를 대비해 파일 쓰기
			}
		});
		setSize(300, 100); // 다이얼로그 size
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void compareLog(String name, String endTime) { // Log를 비교해서 10등 이하면 기록을 안한다 10등 안에들면 Update
		/* 10개보다 크면 제일 안좋은 기록 제거 
		 방법: 1개를 넣어서 11개를 만들고 제일 안좋은 기록 제거
		 */
		if(name.length() == 0) name = "No_NAME";
		logTime.put(endTime, name); // 기록 삽입
		if(logTime.size() > LogFrame.LOGSIZE)
			logTime.pollLastEntry();  // 제일 안좋은 기록 제거
	}
}












