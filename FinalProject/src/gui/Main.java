package gui;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		readFile();
		playMusic();
		new MainFrame();
	}

	private static void readFile() { // 파일을 읽어 들이는 메소드
		File file = createFile(); // 읽은 파일 생성
		String str;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while (true) {
				str = br.readLine();
				if (str == null) return;
				addLog(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("게임을 처음 시작하셔서 읽어올 기록이 없습니다. 게임을 시작해 주세요");
		}
	}

	private static File createFile() { // 파일 생성
		return new File(Main.class.getResource("").getPath() + "Log Data.txt");
	}

	private static void addLog(String data) { // 파일 읽고 data 저장
		String[] logData = data.split("\t"); // data[0]에는 기록 data[1]에는 이름 저장
		GameOver.logTime.put(logData[0], logData[1]); // 기록과 이름 TreeMap에 저장
	}

	static void saveLog() {
		File file = createFile(); // 쓸 파일 생성
		try (BufferedWriter wr = new BufferedWriter(new FileWriter(file))){
			for (String data : GameOver.logTime.keySet()) { // 파일에 한줄씩 Tab으로 구분하여 저장
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












