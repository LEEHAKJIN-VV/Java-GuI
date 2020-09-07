package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogFrame extends JFrame implements ActionListener { // ����� ǥ���� ���̺� ���� �ʿ�
	static final int LOGSIZE = 10; // ����Ҽ� �ִ� LOG ������
	private JLabel[] logLabel = new JLabel[LOGSIZE]; // 1��~10�� ǥ��
	private JButton button = new JButton("Back"); // �ڷ� ���� ��ư ǥ��

	public LogFrame(String title) {
		super(title);
		Container c = MainFrame.setContainer(getContentPane()); // �����̳�, ��ġ������ ����
		button.addActionListener(this); // ������ �ޱ�
		button.setBackground(Color.WHITE);
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { // ��ư�� �������� ����
		dispose();
	}
		
	public void paint(Graphics g) {
		g.drawImage(MainFrame.bkImg,0,0,this);
		setLabel();
	}

	private void setLabel() { // ��� ����
		int i = 0;
		for (String log : GameOver.logTime.keySet()) { // TreeMap�� �ִ� ��� key
			logLabel[i] = new JLabel(i + 1 + "��   " + "�г���: "+ GameOver.logTime.get(log) + "  ���: " + log);
			logLabel[0].setOpaque(false);
			add(logLabel[i]);
			logLabel[i].setBounds(20, (i + 1) * 30, 250, 20);
			drawLogLabel(i);
			i++;
		}
		add(button);
		button.setBounds(300, 330, 80, 20);
		button.setBorderPainted(false); // ��ư �׵θ� ����
	}	
	private void drawLogLabel(int number) { // logLabel�� �׸��� �޼ҵ�
		if(number<3) // 1��,2��,3�� �� �׸�
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
