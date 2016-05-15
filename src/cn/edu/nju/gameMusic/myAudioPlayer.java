package cn.edu.nju.gameMusic;

import java.io.*;
import javax.sound.sampled.*;

public class myAudioPlayer {
	public static final String BGM = "BGM";
	public static final String chooseMap = "chooseMap";
	public static final String enterSprites = "enterSprites";
	public static final String walkOfBlackWdiow = "walkOfBlackWdiow";
	public static final String walkOfCaptainAmerica = "walkOfCaptainAmerica";
	public static final String walkOfHawkeye = "walkOfHawkeye";
	public static final String walkOfHulk = "walkOfHulk";
	public static final String walkOfIronMan = "walkOfIronMan";
	public static final String walkOfSpiderMan = "walkOfSpiderMan";
	public static final String voiceOfBlackWdiow = "voiceOfBlackWdiow";
	public static final String voiceOfCaptainAmerica = "voiceOfCaptainAmerica";
	public static final String voiceOfHawkeye = "voiceOfHawkeye";
	public static final String voiceOfHulk = "voiceOfHulk";
	public static final String voiceOfIronMan = "voiceOfIronMan";
	public static final String voiceOfSpiderMan = "voiceOfSpiderMan";
	public static final String attackOfBlackWdiow = "attackOfBlackWdiow";
	public static final String attackOfCaptainAmerica = "attackOfCaptainAmerica";
	public static final String attackOfHawkeye = "attackOfHawkeye";
	public static final String attackOfHulk = "attackOfHulk";
	public static final String attackOfIornMan = "attackOfIronMan";
	public static final String attackOfSpiderMan = "attackOfSpiderMan";
	public static final String mapLeft = "mapLeft";
	public static final String mapRight = "mapRight";
	public static final String mapBack = "return";
	public static final String modelLeft = "modelLeft";
	public static final String modelRight = "modelRight";
	public static final String modelBack = "return";
	public static final String mapEnter= "mouseEnter";
	public static final String modelEnter= "mouseEnter";



	public void play(String name){
		SourceDataLine sdline;
		try{
			File file =  new File("src/cn/music/"+name+".wav");
			AudioInputStream aistream = AudioSystem.getAudioInputStream(file);
			AudioFormat afromat = aistream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class,afromat);
			sdline = (SourceDataLine)AudioSystem.getLine(info);
			sdline.open(afromat);
			sdline.start();

			int mbyte=0;
			byte []buffer = new byte[320];
			while(mbyte!=-1){
				mbyte = aistream.read(buffer, 0, 128);
				if(mbyte>=0)
					sdline.write(buffer, 0, mbyte);	
			}
			sdline = null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

