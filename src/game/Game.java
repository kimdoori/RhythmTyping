package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;

import musicList.Music;
import rhythm.Main;
import screen.RhythmTyping;

public class Game extends Thread {
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgeLine.png")).getImage();

	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	private Image judgeImage;

	private String titleName;
	private String musicTitle;

	boolean flag = false;

	public static Integer score = new Integer(0);

	public static Music gameMusic;
	ArrayList<Beat> beats = new ArrayList<Beat>();
	
	//static ArrayList<BmsNote> madi = new ArrayList<BmsNote>();
	static boolean datafield=false;


		
		
	
	
	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String musicTitle) {
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		System.out.println(titleName);

	}

	public void screenDraw(Graphics2D g) {
		// if (Game.gameMusic.getTime() % 500 < 3) {
		// if (flag)
		// RhythmTyping.background = new
		// ImageIcon(Main.class.getResource("../images/backGround1.jpg"))
		// .getImage();
		// else
		// RhythmTyping.background = new
		// ImageIcon(Main.class.getResource("../images/backGround2.jpg"))
		// .getImage();
		// flag = !flag;
		// System.out.println(flag);
		// }
		g.drawImage(noteRouteImage, 0, 350, null);
		g.drawImage(judgementLineImage, 1006, 350, null);

		g.drawImage(judgeImage, 460, 250, null);

		// ��Ʈ�׸���
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);

			if (note.getX() > 1120) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
				note.close();
			}
			// ��Ʈ�� �۵��ϰ� �ִ� ���°� �ƴ϶��
			if (!note.isProceeded()) {
				// ��Ʈ����Ʈ���� ����
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("�����ٸ����", Font.BOLD, 30));
		g.drawString(titleName, 100, 60);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString(score.toString(), 565, 60);

	}

	public void press() {
		judge();
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}

	public void release() {
		noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName) {
		// �ϳ��� � ���� ��� ��Ʈ: ���ϴ� ��Ʈ�� ���ϴ� ��Ʈ��
		// ���ϴ� ���� �Ǻ��� ���ؼ� bpm�� ���� : bpm -->1�п� ������ ��Ʈ�� ��
		// 4���� 4������ ��� bpm�� 60�̶�� �� ���ڴ� 1�ʰ� �Ǵ� ���̴�
		// bpm = 132
		//
		// ù��° ���ڰ� �����ϴ� �� �ð��븦 ������.
		// Beat[] beats= {
		// new Beat(1000,"S"),
		// new Beat(2000,"D"),
		// new Beat(3000,"F"),
		// };
		if (titleName.equals("������Ÿ��")) {
			// ��Ʈ�� ���޽ð��� ���ֹ��� �ʰ� �׻� ù��° �Ȱ��� ��Ʈ�� �����ٿ� �����ϴ� Ÿ�̹� ����
			System.out.println(titleName);
			initBeat();
			 AscendingObj ascending = new AscendingObj();
		     Collections.sort(beats, ascending);

		}
		System.out.println("�ʱ�ȭ�Ϸ�");
		int i = 0;
		gameMusic.start();
		// ������¿��� ��Ʈ�� ������������ ��쿡�� �������ݺ��ϴ� ���� �ƴ϶� ���� �θ鼭 ��Ʈ�� ����Ʈ�� �� �ֱ� ������ �ξ� �� ȿ����
		// �ִϸ��̼� �Ų�����
		while (i < beats.size() && !isInterrupted()) {// ������� ����Ǵ� ������ �ǽð����� Ȯ���ؼ� �ش���ġ�� �ɸ´� ��Ʈ�� ����Ʈ��
			boolean dropped = false;

			if (beats.get(i).getTime() <= gameMusic.getTime()) {// ��Ʈ�� �������� �ð��밡 ���ӹ����� getTime���� ���۴ٸ�
				Note note = new Note(beats.get(i).getType(), beats.get(i).getNoteName());// ������ ��Ʈ���� ��Ʈ�̸��� ����
				note.start();// ��Ʈ�� ��������
				noteList.add(note); // ��Ʈ �߰�
				i++; // ������ ��Ʈ�鿡 �ϳ��ϳ� �����ؼ� ��Ʈ�� ����Ʈ�����ְ�
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					// e.printStackTrace();

				}
			}
		}

	}

	public void initBeat() {
		beats=new ArrayList<Beat>();
		int startTime = 1000;// 4460 - Main.REACH_TIME *
		int gap = 125; // �ּ� ������ ���� 8���� 1�̴ϱ� 1000/8 //125
		
//		beats.add(new Beat(startTime, "short", "A"));
//		beats.add(new Beat(startTime + gap * 4, "short", "C"));
//		beats.add(new Beat(startTime + gap * 12, "long", "cherry"));
//		beats.add(new Beat(startTime + gap * 18, "short", "B"));
//		beats.add(new Beat(startTime + gap * 20, "short", "A"));
				
//				new Beat(startTime + gap * 28, "long", "apple"), new Beat(startTime + gap * 34, "short", "A"),
//				new Beat(startTime + gap * 36, "short", "A"), new Beat(startTime + gap * 40, "long", "banana"),
//				new Beat(startTime + gap * 43, "short", "A"), };
		
		
		try {
		      BufferedReader in = new BufferedReader(new FileReader("gangnamstyle.bms"));
		      String s;

		      while ((s = in.readLine()) != null) {
		    	  if(s.contains("*---------------------- MAIN DATA FIELD")) {
		    		  datafield=true;
		    		  continue;
		    	  }
		    	  
		    	  if(datafield) {
		    		  if(s.contains("#")) {
		    			  
		    			  System.out.println(s);

		    			  char ch[]=new char[3];
		    			  ch[0]=s.charAt(1);
		    			  ch[1]=s.charAt(2);
		    			  ch[2]=s.charAt(3);
		    			  //��Ʈ�� �ش��ϴ� ���� 
		    			  int madiOfNote=Integer.parseInt(String.valueOf(ch));
		    			  System.out.println("����:"+madiOfNote);
		    			  char ch2[]=new char[2];
		    			  ch2[0]=s.charAt(4);
		    			  ch2[1]=s.charAt(5);
		    			  //��Ʈ�� �ش��ϴ� ����
		    			  int line=Integer.parseInt(String.valueOf(ch2));
		    			  System.out.println("���ι�ȣ : "+line);

		    			  String noteType = "";
		    			  String noteName = "";
		    			  
		    			  
		    			  switch(line) {
		    			  case 11:
		    				  noteType="short";
		    				  noteName="A";
		    				  break;
		    			  case 12:
		    				  noteType="short";
		    				  noteName="B";
		    				  break;
		    			  case 13:
		    				  noteType="short";
		    				  noteName="C";
		    				  break;
		    			  case 14:
		    				  noteType="long";
		    				  noteName="apple";
		    				  break;
		    			  case 15:
		    				  noteType="long";
		    				  noteName="banana";
		    				  break;
		    			  case 18:
		    				  noteType="long";
		    				  noteName="cherry";
		    				  break;
		    			  
		    			  }

		    			  //���ο� �ִ� ��Ʈ��
		    			  String parseNote=s.substring(7);
		    			  System.out.println(parseNote);
		    			  int index;
		    			  int add=0;
		    			  switch(parseNote.length()) {
		    			  case 2:
		    				  while((index=parseNote.indexOf("1"))!=-1) {
			    					 
		    					  int a = (index+1)/2+add*8;
			    				  
			    				  int timing = madiOfNote*8+a;
			    				  beats.add(new Beat(startTime + gap *timing,noteType, noteName));
			    				  System.out.println(timing);
			    				  parseNote=parseNote.substring(index+1);
			    				  add++;
			    				//(String noteType, String noteName, int madiOrder, int noteOrder
				    			 

		    				  }
		    		
		    				  break;
		    			  
		    			  case 4:
		    				  while((index=parseNote.indexOf("1"))!=-1) {
		    					 
		    					  int a = (index+1)/2+add*4;
			    				  
			    				  int timing = madiOfNote*8+a;
			    				  beats.add(new Beat(startTime + gap *timing,noteType, noteName));
			    				  System.out.println(timing);
			    				  parseNote=parseNote.substring(index+1);
			    				  add++;
			    				//(String noteType, String noteName, int madiOrder, int noteOrder
				    			 

		    				  }
		    		
		    				  break;
		    			  case 8:
		    				  
		    				  while((index=parseNote.indexOf("1"))!=-1) {
		    					 int a=(index+1)/2+add*2;
			    				  int timing = madiOfNote*8+a;
			    				  beats.add(new Beat(startTime + gap *timing,noteType, noteName));
			    				  System.out.println(timing);
			    				  parseNote=parseNote.substring(index+1);
			    				  add++;
			    				  
		    				  }
		    				  break;
		    			  case 16:
		    				  while((index=parseNote.indexOf("1"))!=-1) {
			    				  int a=(index+1+add)/2;
		    					  int timing = madiOfNote*8+a;
			    				  beats.add(new Beat(startTime + gap *timing,noteType, noteName));
			    				  System.out.println(timing);
			    				  parseNote=parseNote.substring(index+1);
			    				  add+=index+1;
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
	}
	
	public void judge() {
		Note note = noteList.get(0);// �ϳ��� ��Ʈ�� ����
		if ((RhythmTyping.input).toLowerCase().equals(note.getNoteType()) && "long".equals(note.getType())) {
			judgeEvent(note.judge());
			RhythmTyping.input = "";
		} else if ((RhythmTyping.input).toUpperCase().equals(note.getNoteType()) && "short".equals(note.getType())) {// �Է���
																									// ��ġ�ϴٸ�
			// �ش��Ʈ�� ������ �����ͼ� �̹��� �����ϴ� �Լ���
			judgeEvent(note.judge());
			RhythmTyping.input = "";
		}
	}
	
	
	


	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {// ���� ������ none�� �ƴѰ��
			// blueFlareImage = new
			// ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
		} else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
		} else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
		} else if (judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
		}
	}

}
class AscendingObj implements Comparator<Beat> {
	 
	@Override
	public int compare(Beat beat1, Beat beat2) {
		// TODO Auto-generated method stub
		return beat1.getTime().compareTo(beat2.getTime());

	}
 
}