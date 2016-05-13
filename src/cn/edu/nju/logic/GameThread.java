package cn.edu.nju.logic;

import cn.edu.nju.logic.Game;

public class GameThread implements Runnable{

	public Game game;
	
	public GameThread(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			game.execute();
//			try {
//				Thread.sleep(game.snake.speed);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

}
