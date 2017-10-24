package bms;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

public class BMS {
	File file;		//파일이름
	int PLAYER;	   // 1인용 2인용
	String GENRE; //장르
	String TITLE;
	String ARTIST;
	double BPM; //Beats Per Minute. default :130
	int PLAYLEVEL;
	int RANK ; 	// 0: very hard, 1: hard, 2: normal, 3: easy . default : 2
	double VOLWAV ; // volume control (percentage)
	double TOTAL; //increments of Groove Gauge ex) TOTAL=120 ->120%
	Image BMP[]= new Image[256]; //FF까지
	GameSound WAV[]= new GameSound[256];//FF까지
	//madi, channel 한세트 정보
	ArrayList[] madi;	//마디 정보 각각 안에는 특정마디에 관한 채널이 다 들어있다.
	ArrayList[] channel;//채널정보 각각 안에는 특정마디에 관한 악보 정보가 들어있다.
	
	int lastmadi;
	
	public BMS(){
		
	}
	

	
}
