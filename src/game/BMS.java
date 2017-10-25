package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import screen.SelectNotePanel;

public class BMS {
	// static ArrayList<BmsNote> madi = new ArrayList<BmsNote>();
	static boolean datafield = false;

	public static ArrayList<Beat> initBeat(ArrayList<Beat> beats, String bmsName) {
		beats = new ArrayList<Beat>();
		int startTime = 1000;// 4460 - Main.REACH_TIME *
		int gap = 125; // �ּ� ������ ���� 8���� 1�̴ϱ� 1000/8 //125

		try {
			BufferedReader in = new BufferedReader(new FileReader(bmsName + ".bms"));
			String s;

			while ((s = in.readLine()) != null) {
				if (s.contains("*---------------------- MAIN DATA FIELD")) {
					datafield = true;
					continue;
				}

				if (datafield) {
					if (s.contains("#")) {

						System.out.println(s);

						char ch[] = new char[3];
						ch[0] = s.charAt(1);
						ch[1] = s.charAt(2);
						ch[2] = s.charAt(3);
						// ��Ʈ�� �ش��ϴ� ����
						int madiOfNote = Integer.parseInt(String.valueOf(ch));
						System.out.println("����:" + madiOfNote);
						char ch2[] = new char[2];
						ch2[0] = s.charAt(4);
						ch2[1] = s.charAt(5);
						// ��Ʈ�� �ش��ϴ� ����
						int line = Integer.parseInt(String.valueOf(ch2));
						System.out.println("���ι�ȣ : " + line);

						String noteType = "";
						String noteName = "";
						String[] noteAnswer= {"A","G","B","L","C","O","apple","grape","banana","lemon","cherry","orange"};

							switch (line) {
							case 11:
								noteType = "short";
								noteName = noteAnswer[0+SelectNotePanel.chooseNote];
								break;
							case 12:
								noteType = "short";
								noteName = noteAnswer[2+SelectNotePanel.chooseNote];
								break;
							case 13:
								noteType = "short";
								noteName = noteAnswer[4+SelectNotePanel.chooseNote];
								break;
							case 14:
								noteType = "long";
								noteName = noteAnswer[6+SelectNotePanel.chooseNote];
								break;
							case 15:
								noteType = "long";
								noteName = noteAnswer[8+SelectNotePanel.chooseNote];
								break;
							case 18:
								noteType = "long";
								noteName =noteAnswer[10+SelectNotePanel.chooseNote];
								break;

							}
						
						
						
						// ���ο� �ִ� ��Ʈ��
						String parseNote = s.substring(7);
						System.out.println(parseNote);
						int index;
						int add = 0;
						switch (parseNote.length()) {
						case 2:
							while ((index = parseNote.indexOf("1")) != -1) {

								int a = (index + 1) / 2 + add * 8;

								int timing = madiOfNote * 8 + a;
								beats.add(new Beat(startTime + gap * timing, noteType, noteName));
								System.out.println(timing);
								parseNote = parseNote.substring(index + 1);
								add++;
								// (String noteType, String noteName, int madiOrder, int noteOrder

							}

							break;

						case 4:
							while ((index = parseNote.indexOf("1")) != -1) {

								int a = (index + 1) / 2 + add * 4;

								int timing = madiOfNote * 8 + a;
								beats.add(new Beat(startTime + gap * timing, noteType, noteName));
								System.out.println(timing);
								parseNote = parseNote.substring(index + 1);
								add++;
								// (String noteType, String noteName, int madiOrder, int noteOrder

							}

							break;
						case 8:

							while ((index = parseNote.indexOf("1")) != -1) {
								int a = (index + 1) / 2 + add * 2;
								int timing = madiOfNote * 8 + a;
								beats.add(new Beat(startTime + gap * timing, noteType, noteName));
								System.out.println(timing);
								parseNote = parseNote.substring(index + 1);
								add++;

							}
							break;
						case 16:
							while ((index = parseNote.indexOf("1")) != -1) {
								int a = (index + 1 + add) / 2;
								int timing = madiOfNote * 8 + a;
								beats.add(new Beat(startTime + gap * timing, noteType, noteName));
								System.out.println(timing);
								parseNote = parseNote.substring(index + 1);
								add += index + 1;
							}
							break;

						}

					}
				}
			}
			in.close();
		} catch (IOException e) {
			System.err.println(e); // ������ �ִٸ� �޽��� ���
			System.exit(1);
		}
		return beats;
	}
}
