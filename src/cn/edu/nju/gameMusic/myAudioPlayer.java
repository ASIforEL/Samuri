package cn.edu.nju.gameMusic;
import java.io.*;
import javax.sound.sampled.*;
public class myAudioPlayer {
	public static final String BGM = "src/cn/music/BGM";
	public static final String chooseMap = "src/cn/music/chooseMap";
	public static final String enterSprites = "src/cn/music/enterSprites";
	public static final String walkOfBlackWdiow = "src/cn/music/walkOfBlackWdiow";
	public static final String walkOfCaptainAmerica = "src/cn/music/walkOfCaptainAmerica";
	public static final String walkOfHawkeye = "src/cn/music/walkOfHawkeye";
	public static final String walkOfHulk = "src/cn/music/walkOfHulk";
	public static final String walkOfIronMan = "src/cn/music/walkOfIronMan";
	public static final String walkOfSpiderMan = "src/cn/music/walkOfSpiderMan";
	public static final String voiceOfBlackWdiow = "src/cn/music/voiceOfBlackWdiow";
	public static final String voiceOfCaptainAmerica = "src/cn/music/voiceOfCaptainAmerica";
	public static final String voiceOfHawkeye = "src/cn/music/voiceOfHawkeye";
	public static final String voiceOfHulk = "src/cn/music/voiceOfHulk";
	public static final String voiceOfIronMan = "src/cn/music/voiceOfIronMan";
	public static final String voiceOfSpiderMan = "src/cn/music/voiceOfSpiderMan";
	public static final String attackOfBlackWdiow = "src/cn/music/attackOfBlackWdiow";
	public static final String attackOfCaptainAmerica = "src/cn/music/attackOfCaptainAmerica";
	public static final String attackOfHawkeye = "src/cn/music/attackOfHawkeye";
	public static final String attackOfHulk = "src/cn/music/attackOfHulk";
	public static final String attackOfIornMan = "src/cn/music/attackOfIronMan";
	public static final String attackOfSpiderMan = "src/cn/music/attackOfSpiderMan";
	public static final String mapLeft = "src/cn/music/mapLeft";
	public static final String mapRight = "src/cn/music/mapRight";
	public static final String mapBack = "src/cn/music/return";
	public static final String modelLeft = "src/cn/music/modelLeft";
	public static final String modelRight = "src/cn/music/modelRight";
	public static final String modelBack = "src/cn/music/return";
	public static final String mapEnter= "src/cn/music/mouseEnter";
	public static final String modelEnter= "src/cn/music/mouseEnter";
	public static final String hideMusic= "src/cn/music/hideMusic";
	public static final String spaceMusic= "src/cn/music/spaceMusic";
	public static final String Enter= "src/cn/music/mouseEnter";
	
	
	
	public void play(String name){
		SourceDataLine sdline;
		try{
			File file =  new File(name+".wav");
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

