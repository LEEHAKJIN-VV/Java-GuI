package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.util.*;

public class GameFrame extends JFrame implements Runnable {
	static final int ATKSIZE = 5; // ��ֹ� ũ��
	static final int USERSIZE = 10; // ����� ũ��
	static String time; // ���� �ð� ǥ��
	static UserPilot user = new UserPilot(); // ������ ��ü ����
	static ArrayList<AiAttacker> attackers = new ArrayList<>(); // attacker �� ��� arraylist
	static JLabel timeLabel = new JLabel("00.00"); // �ð�ǥ��

	private AttackerThread at = new AttackerThread(); // AttackerThread ����

	Image userImg = new ImageIcon("images/user.png").getImage(); // ����� �̹��� ������
	Image attackerImg = new ImageIcon("images/attacker.png").getImage(); // attacker �̹��� ������

	Graphics gc;
	Image buffimg = null; // ������۸��� ����ϱ����� �����̹����� ����

	public GameFrame() {
		setTitle("Dodge Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Xǥ�� ������ ȭ�� ������
		setOn(getContentPane()); // ������ ����,���̺� ����

		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().requestFocus(); // ��Ŀ�� ���� ����
	}

	@Override
	public void run() {
		while (collisionDetect()) { // �浹������ while�� Ż��
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
		}
		gameOver(time); // ���� ���� Ŭ���� ����
	}

	public void setOn(Container c) { // ������ ����, �⺻���� label ����
		Thread userThread = new Thread(at);
		userThread.start();
		c = MainFrame.setContainer(getContentPane());
		c.add(timeLabel);
		timeLabel.setFont(MainFrame.font); // ��Ʈ ����
		MainFrame.paintLabel(timeLabel); // �� ��ĥ
		timeLabel.setBounds(300, 300, 150, 20); // �� ��ġ����
		c.addKeyListener(new MyKeyListener()); // Ű ������ ���
		c.requestFocus(); // ��Ŀ�� ���� ����
	}
	
	private void gameOver(String endTime) { // ������ �������� ȣ���ϴ� �޼ҵ�
		new GameOver(this, "�̸��Է�", endTime); // endTime�� ���� �ð�
		setVisible(false); // ���� �Ⱥ��̰���
		attackers.removeAll(attackers); // ��� Attackers ��ü ����
	}

	synchronized public void update(Graphics g) { // ������ ���� �����̹����� �̿��Ͽ� ������ ������ ������ ����
		paint(g);
	}

	public void paint(Graphics g) { // �̹����� �׸������Ѹ޼��带 ����
		buffimg = createImage(MainFrame.WIDTH, MainFrame.HEIGHT); // �����̹����� �׸��� (������۸��� ����Ͽ� ȭ���� ��������
		// ����
		gc = buffimg.getGraphics(); // �����̹����� ���� �׷��� ��ä�� ����
		drawimages(g); // �׷����� �׸�
	}

	public void drawimages(Graphics g) { // ������ �����ڵ��� �׸��� �޼ҵ�
		setBackground(Color.BLACK); // ������ ���������� ����
		gc.drawImage(MainFrame.bkImg,0,0,this); // ��� �̹��� ����
		userDrawImg(); // ������� �׸��� �׸�
		atkDrawImg(); // attacker �׸��� �׸�
		g.drawImage(buffimg, 0, 0, this); // �����̹����� �׸� 0,0���� ��ǥ�� ���缭������ũ�⿡ ����
	}

	public void userDrawImg() { // ������ �׸��� �޼ҵ�
		gc.setClip(user.userVec.posx, user.userVec.posy, USERSIZE, USERSIZE); // ��������̹����� ��ǥ�� ũ�⸦ �޾ƿ´�
		gc.drawImage(userImg, user.userVec.posx, user.userVec.posy, 10, 10, this); // ����ڸ� ��ǥ�� ���� ��Ҹ� �ٲپ� �׸���.
	}

	private void moveAtkImg(Image atkImage, int posx, int posy) {
		gc.setClip(posx, posy, ATKSIZE, ATKSIZE); // ��ֹ����̹����� ��ǥ�� ũ�⸦ �޾ƿ´�
		gc.drawImage(attackerImg, posx, posy, this); // ��ֹ��� ��ǥ�� ���� ��Ҹ� �ٲپ� �׸���
	}

	public void atkDrawImg() {
		for (AiAttacker atck : attackers) { // Aiattacker ���� ��ġ�� �׸�
			moveAtkImg(attackerImg, atck.vec.posx, atck.vec.posy);
		}
	}

	static boolean collisionDetect() { // �浹 ���� �Ǵ� 1���� �����ϸ� false return
		Iterator<AiAttacker> itr = attackers.iterator();
		while (itr.hasNext()) {
			AiAttacker atk = itr.next();
			if (distance(atk) <= 5)
				return false;
		}
		return true;
	}

	private static double distance(AiAttacker target) { // ���������� �Ÿ� return
		return Math.sqrt((user.userVec.posx - target.vec.posx) * (user.userVec.posx - target.vec.posx)
				+ (user.userVec.posy - target.vec.posy) * (user.userVec.posy - target.vec.posy));
	}
}
