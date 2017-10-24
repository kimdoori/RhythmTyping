package bms;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;


import javax.imageio.ImageIO;

public class BMSCheck {
	BMSfilter bmsfilter = new BMSfilter();
	 String flist[] ;
	 File bmslist[]= new File[25];
	 int changer=0,size=0;
	 Image titleimg;
	 String path=null;//파일주소
	 String folderpath=null;//폴더주소
	 String titlename="";
	BMSCheck(){
		
		File dir = new File("bms");//bms폴더내에서
		if( dir.isDirectory()){
			flist = dir.list();
		  for (int i=0; i<flist.length; i++)
		  {
			  
			  System.out.println(i + " " + flist[i]);
			  if(i<=24){
				  bmslist[i] = new File("bms/"+flist[i]);
			  }
		  }
		}
		size=flist.length;
		
		
	}
	public void search(){
		
		File list[] = bmslist[changer].listFiles(bmsfilter);
		path = ""+list[0];
		
	
		titleimg=smallBeatReader(path);
	}
	public Image smallBeatReader(String fileName){
		try{
			titlename = fileName;
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String[] splitline;
			String path="";
			BufferedImage img, bufimg;
			Image noimg=Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/menu/noimage.png"));
			if (fileName.indexOf("\\") != -1) {
				path = fileName.substring(0, fileName.lastIndexOf("\\")+1);;
			}
			for (int i = 1; (line = br.readLine()) != null; i++) {
				if (line.indexOf("#") != -1) {// "#"인 들었있는지 확인
					if (line.charAt(0) == '#') {// 만약 배열없으면 오류나므로 이중 처리
						//System.out.println(i + "줄 " + line);
						splitline = line.split(" ");

						if (splitline[0].indexOf("#STAGEFILE") != -1) {
							line.substring(11);
							//System.out.println(path+line.substring(11));
							if(splitline[1].indexOf(".bmp") != -1){

								img = ImageIO.read(new File(path+line.substring(11)));
								bufimg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
								bufimg.getGraphics().drawImage(img, 0, 0,	null);
								br.close();
								fr.close();
								return bufimg;
								
							}else{
							//jpg 경우
								return Toolkit.getDefaultToolkit()
									.getImage(path+line.substring(11));
								
							}
						}
					}
				}
			}
			return noimg;
		} catch (IOException e) {
			System.out.println("입력오류");
		}
		return null;
	}
	
	//데이터 불러오기
	//@SuppressWarnings("deprecation")
	public  int GameDataLoad() {
		
		int HighScore=0;
		String[] splitline;
		try{
			String line = "";
			FileReader input = new FileReader("bms\\"+flist[changer]+"\\savedata.dat");
			BufferedReader br = new BufferedReader(input);
			for (int i = 1; (line = br.readLine()) != null; i++) {
				if (line.indexOf("#") != -1)
				{
					splitline = line.split(" ");
					if (splitline[0].indexOf("#score") != -1) {
						return HighScore=Integer.parseInt(splitline[1]);
						
					}
				}
			}
			br.close();
			input.close();
		}catch(IOException e){
			
	   		System.out.println("데이터로드 실패");
	   	}
		return HighScore;
	}
	//데이터저장
	public void GameDataSave(int HighScore, int MaxCombo) {
		try{
		FileWriter output = new FileWriter("bms\\"+flist[changer]+"\\savedata.dat");
		BufferedWriter bw = new BufferedWriter(output);
		bw.write("#score "+HighScore);
		bw.newLine();
		bw.write("#maxcombo "+MaxCombo);
	    bw.close();
		output.close();
		}catch(IOException e){
			
		}
	}
	//파일의 존재여부확인
	public boolean fileexists() {
		File FileExist = new File("bms\\"+flist[changer]+"\\savedata.dat"); 
        return FileExist.exists(); 
    }
	public static void main(String[] args) {
		BMSCheck test = new BMSCheck();
	}
}

class BMSfilter implements FilenameFilter{
	public boolean accept(File dir, String name) {
		return name.endsWith(".bms");
	}
}
