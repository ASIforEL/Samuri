package cn.edu.nju.gameMusic;

public class myRunnable implements Runnable{
	public myAudioPlayer Player ;
	public String musicName;
	public int loopTimes =1;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Player = new myAudioPlayer();
		if(this.musicName.equals("BGM")){
			for(int i=0;i<this.loopTimes;i++){
			     Player.play(musicName);
			}
		}
		else{
	      for(int i=0;i<this.loopTimes;i++){
		     Player.play(musicName);
	   }
		}
	}
	
	public myAudioPlayer getPlayer(){
		return this.Player;
	}

}
