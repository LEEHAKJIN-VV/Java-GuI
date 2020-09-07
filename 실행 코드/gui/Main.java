package gui;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		readFile();
		playMusic();
		new MainFrame();
	}

	private static void readFile() { // ������ �о� ���̴� �޼ҵ�
		File file = createFile(); // ���� ���� ����
		String str;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while (true) {
				str = br.readLine();
				if (str == null) return;
				addLog(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("������ ó�� �����ϼż� �о�� ����� �����ϴ�. ������ ������ �ּ���");
		}
	}

	private static File createFile() { // ���� ����
		return new File(Main.class.getResource("").getPath() + "Log Data.txt");
	}

	private static void addLog(String data) { // ���� �а� data ����
		String[] logData = data.split("\t"); // data[0]���� ��� data[1]���� �̸� ����
		GameOver.logTime.put(logData[0], logData[1]); // ��ϰ� �̸� TreeMap�� ����
	}

	static void saveLog() {
		File file = createFile(); // �� ���� ����
		try (BufferedWriter wr = new BufferedWriter(new FileWriter(file))){
			for (String data : GameOver.logTime.keySet()) { // ���Ͽ� ���پ� Tab���� �����Ͽ� ����
				wr.write(data + "\t" + GameOver.logTime.get(data)+"\n");
			}
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void playMusic() {
		Thread thread = new Thread(new MusicThread());
		thread.start();
	}
}












