package cn.edu.nju.gameMusic;

public class musicThread {
	public int loopTimes=1;
	public myAudioPlayer threadPlayer;
	public myRunnable mRun = new myRunnable();
	public Thread thread;
	public boolean isRunning=false;
	
	
	public void changeLoopTimes(int loopTimes){
		this.loopTimes=loopTimes;
	}
	
	public void creatMT(String musicName,int loopTimes){
		mRun.musicName=musicName;
		changeLoopTimes(loopTimes);
		mRun.loopTimes=this.loopTimes;
		
	}
	public void start(){
		isRunning = true;
		thread =  new Thread(mRun);
		thread.start();
	}
	
    public void stop(){
    	isRunning = false;
    }
    }

