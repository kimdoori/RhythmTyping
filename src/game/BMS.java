package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import rhythm.RhythmMain;
import screen.SelectMusicPanel;
import screen.SelectNotePanel;

public class BMS {
	boolean datafield = false;//파싱부분이 데이터필드인지

	//bms 파싱해서 beats(노트) 초기화
	public ArrayList<Beat> initBeat(ArrayList<Beat> beats, String bmsName) {
		
		beats = new ArrayList<Beat>();
		int startTime = -3300;

		double gap = 0;
		switch (SelectMusicPanel.songIndex) {// 각 노래에 해당하는 박자간격
		case 0:
			gap = 224;
			break;
		case 1:
			gap = 250;
			break;
		case 2:
			gap = 256;
			break;
		case 3:
			startTime = -5300;
			gap = 250;
			break;
		}

		try {
			BufferedReader in = new BufferedReader(new FileReader(bmsName
					+ ".bms"));
			String s;

			while ((s = in.readLine()) != null) {
				if (s.contains("*---------------------- MAIN DATA FIELD")) {
					datafield = true;
					continue;
				}

				if (datafield) {
					if (s.contains("#")) {
						char ch[] = new char[3];
						ch[0] = s.charAt(1);
						ch[1] = s.charAt(2);
						ch[2] = s.charAt(3);
						// 노트에 해당하는 마디
						int madiOfNote = Integer.parseInt(String.valueOf(ch));
						char ch2[] = new char[2];
						ch2[0] = s.charAt(4);
						ch2[1] = s.charAt(5);
						// 노트에 해당하는 라인
						int line = Integer.parseInt(String.valueOf(ch2));

						String noteType = "";
						String noteName = "";
						String[] noteAnswer = { "A", "G", "B", "L", "C", "O",
								"apple", "grape", "banana", "lemon", "cherry",
								"orange" };

						switch (line) {
						case 11:
							noteType = "short";
							noteName = noteAnswer[0 + SelectNotePanel.chooseNote];
							break;
						case 12:
							noteType = "short";
							noteName = noteAnswer[2 + SelectNotePanel.chooseNote];
							break;
						case 13:
							noteType = "short";
							noteName = noteAnswer[4 + SelectNotePanel.chooseNote];
							break;
						case 14:
							noteType = "long";
							noteName = noteAnswer[6 + SelectNotePanel.chooseNote];
							break;
						case 15:
							noteType = "long";
							noteName = noteAnswer[8 + SelectNotePanel.chooseNote];
							break;
						case 18:
							noteType = "long";
							noteName = noteAnswer[10 + SelectNotePanel.chooseNote];
							break;

						}

						// 라인에 있는 노트들
						String parseNote = s.substring(7);
						int index;
						int add = 0;
						switch (parseNote.length()) {
						case 2:
							while ((index = parseNote.indexOf("1")) != -1) {

								int a = (index + 1) / 2 + add * 8;

								int timing = madiOfNote * 8 + a;
								beats.add(new Beat(startTime + gap * timing,
										noteType, noteName));
								parseNote = parseNote.substring(index + 1);
								add++;
						

							}

							break;

						case 4:
							while ((index = parseNote.indexOf("1")) != -1) {

								int a = (index + 1) / 2 + add * 4;

								int timing = madiOfNote * 8 + a;
								beats.add(new Beat(startTime + gap * timing,
										noteType, noteName));
								parseNote = parseNote.substring(index + 1);
								add++;
							}

							break;
						case 8:

							while ((index = parseNote.indexOf("1")) != -1) {
								int a = (index + 1) / 2 + add * 2;
								int timing = madiOfNote * 8 + a;
								beats.add(new Beat(startTime + gap * timing,
										noteType, noteName));
								parseNote = parseNote.substring(index + 1);
								add++;
							}
							break;
						case 16:
							while ((index = parseNote.indexOf("1")) != -1) {
								int a = (index + 1 + add) / 2;
								int timing = madiOfNote * 8 + a;
								beats.add(new Beat(startTime + gap * timing,
										noteType, noteName));
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
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}
		return beats;
	}
}
