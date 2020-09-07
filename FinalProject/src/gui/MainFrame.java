package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {
	Thread thread;
	static final int WIDTH = 400; // ����ȭ���� ����
	static final int HEIGHT = 400; // ����ȭ���� ����

	private LogFrame logFrame; // �α������� ���� ����
	private GameFrame dm;
	static Font font = new Font("SansSerif", Font.BOLD, 10);
	static Image bkImg = new ImageIcon("images/bkImg.jpg").getImage(); // ��� �̹���

	private JButton[] button = new JButton[3]; // GAME START, GAME END, LOG(���) �� ���� ��ư ����

	public MainFrame() {
		setTitle("Dodge Game"); // ���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Xǥ�� ������ ȭ�� ������
		Container c = setContainer(getContentPane());
		setButton(c); // ��ư ����Ʈ �ҿ� �߰�, ���ڿ� ����, ��ġ set
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void paint(Graphics g) { // ����̹��� �׸�
		g.drawImage(MainFrame.bkImg,0,0,this);
	}

	@Override
	public void actionPerformed(ActionEvent e) { // ������ ��ư ������ư���� Ȯ��
		switch (((JButton) e.getSource()).getText()) {
		case "START": // ���� ���� ��ư �������� ���̵� ���� â ����
			gameStart();
			break;
		case "RANKING": // ���� ��� ��ư ��������
			gameLog();
			break;
		case "END": // ���� �� ��ư ��������
			gameEnd();
			break;
		}
	}

	private void setButton(Container c) {
		button[0] = new JButton("START"); // ���ӽ���
		button[1] = new JButton("RANKING"); // ���� ��
		button[2] = new JButton("END"); // ���� ��� (1,2,3 ��)
		for (int i = 0; i < button.length; i++) {
			c.add(button[i]);
			button[i].setBounds(120, (i + 1) * 60, 150, 30); // ��ư ��ġ�� ũ�� ����
			button[i].addActionListener(this); // ��ư �׼Ǹ����� �ޱ�
			button[i].setBorderPainted(false); // ��ư �׵θ� ����
		}
	}

	private void gameStart() { // ���� ���� �޼ҵ�
		dm = new GameFrame();
		Thread th = new Thread(new TimerThread());
		thread = new Thread(dm);
		thread.start(); // ������ ����
		th.start();
	}

	private void gameLog() { // log��������
		logFrame = new LogFrame("Log");
	}

	private void gameEnd() { // ���� end��ư��������
		Main.saveLog(); // ��������� ���� ��ϵ� ����
		System.exit(0); // ����
	}

	static Container setContainer(Container c) { // ��ġ������, ��� ����
		c.setLayout(null);
		c.setBackground(Color.BLACK);
		return c;
	}

	static void paintLabel(JLabel label) { // ���̺� �� ���� �޼ҵ�
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		label.setForeground(Color.WHITE);
	}
}
