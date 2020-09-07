package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameOver extends JDialog {
	private JLabel label = new JLabel(); // ����� ǥ���� ���̺�
	private JTextField text = new JTextField(10); // �̸��� �Է¹޴� �ؽ�Ʈ �ʵ� ���ڱ��̴� 10
	private JButton button = new JButton("OK"); // �̸��� �Է��ϰ� ok ��ư�� ������ ���� (�ð�,�̸�)
	static TreeMap<String, String> logTime = new TreeMap<>(new LogComparator()); // ����� ���� Hash map
	// <���, �̸� > ������ ����Ǿ� ���� -> ������ ���Ͽ�

	public GameOver(JFrame frame, String title, String endTime) { // GrameOver ������
		super(frame, title, true); // ��� ���̾�α� ����
		setLayout(new FlowLayout()); // ���̾ƿ� ��ġ������ null�� ����
		label.setText("���: " + endTime);
		add(text); add(label); add(button);

		button.addActionListener(new ActionListener() { // Button ActionListener �ۼ� 
			@Override
			public void actionPerformed(ActionEvent e) { // Button ������ ��� ����
				compareLog(text.getText(), endTime);
				dispose(); // Dialog����
				setVisible(false);
				Main.saveLog(); // ������ ������������ ����ɶ��� ����� ���� ����
			}
		});
		setSize(300, 100); // ���̾�α� size
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void compareLog(String name, String endTime) { // Log�� ���ؼ� 10�� ���ϸ� ����� ���Ѵ� 10�� �ȿ���� Update
		/* 10������ ũ�� ���� ������ ��� ���� 
		 ���: 1���� �־ 11���� ����� ���� ������ ��� ����
		 */
		if(name.length() == 0) name = "No_NAME";
		logTime.put(endTime, name); // ��� ����
		if(logTime.size() > LogFrame.LOGSIZE)
			logTime.pollLastEntry();  // ���� ������ ��� ����
	}
}












