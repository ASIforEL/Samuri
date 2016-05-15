package cn.edu.nju.panels;
import cn.edu.nju.gameMusic.*;
public class Main {
    public static void main(String[] args)  
    {  
    	musicThread bgm =  new musicThread();
    	bgm.creatMT(myAudioPlayer.BGM, 100);
    	bgm.start();
    	bgm.stop();
        new StartPanel();
        
    }  
}
