package cn.edu.nju.logic;

import cn.edu.nju.panels.MapSelectPanel;
import cn.edu.nju.panels.StartPanel;

public class Game {
	public StartPanel startPanel;
	
	public static final int PLAYER_NUM = 6;
	public static final int turns = 1008;
	public static final int maxCure = 24;			//the turns a samurai need to do actions again
	public int side;								//group ID（0 represent TeamCap，1 represent TeamIronMan）
	public int weapon;								//Samurai ID，indicated by the weapon number
	public static Samurai[] samurais;
	public int action;
	int[][] map;
	
	public Game() {
		map = Configure.mapField[MapSelectPanel.numOfMap];
		
		//the even number represents TeamCap, odd number represents TeamIronMan
		samurais[0] = new CaptainAmerica();
		samurais[1] = new IronMan();
		samurais[2] = new Hulk();
		samurais[3] = new BlackWidow();
		samurais[4] = new Hawkeye();
		samurais[5] = new SpiderMan();
	}

	public void execute() {		
		samurais[weapon].doAction(this.action);
		
	}
	
	public void startGame() {
		
	}
	
	/**
	 * check whether there is a side having been the winner
	 * @return
	 */
	public boolean cheakWin() {
		int numOfDeadCap = 0;
		int numOfDeadIronMan = 0;
		for (int i = 0; i < samurais.length; i++) {
			if (samurais[i].isKilled()) {
				if (samurais[i].getSide() == 0) {
					numOfDeadCap++;
				}
				if (samurais[i].getSide() == 1) {
					numOfDeadIronMan++;
				}
			}
		}
		if ((numOfDeadCap == PLAYER_NUM/2 && numOfDeadIronMan < PLAYER_NUM/2) ||
				(numOfDeadIronMan == PLAYER_NUM/2 && numOfDeadCap < PLAYER_NUM/2)){
			return true;
		}
		return false;
	}
	
	/**
	 * @return the winner group
	 */
	public int winSide() {
		int numOfDeadCap = 0;
		int numOfDeadIronMan = 0;
		for (int i = 0; i < samurais.length; i++) {
			if (samurais[i].isKilled()) {
				if (samurais[i].getSide() == 0) {
					numOfDeadCap++;
				}
				if (samurais[i].getSide() == 1) {
					numOfDeadIronMan++;
				}
			}
		}
		if ((numOfDeadCap == PLAYER_NUM/2 && numOfDeadIronMan < PLAYER_NUM/2)){
			return 1;
		}
		if ((numOfDeadIronMan == PLAYER_NUM/2 && numOfDeadCap < PLAYER_NUM/2)) {
			return 0;
		}
		return -1;
	}
	
	/**
	 * to check whether there is a samurai existed or not
	 * @param x
	 * @param y
	 * @return the reference of samurai
	 */
	public Samurai isSamurai(int x, int y) {
		for (int i = 0; i < samurais.length; i++) {
			if (samurais[i].getCurX() == x && samurais[i].getCurY() == y && samurais[i].getHidden() != true) {
				return samurais[i];
			}
		}
		return null;
	}
	
	/**
	 * to check whether there is a barrier or a transfer gateway
	 * @param x
	 * @param y
	 * @return can / cannot
	 */
	public boolean isSpecial(int x, int y) {
		if (map[x][y] == Configure.MOUN || map[x][y] == Configure.TREE || 
				map[x][y] == Configure.TRANS1 || map[x][y] == Configure.TRANS2 || 
				map[x][y] == Configure.TRANS3 || map[x][y] == Configure.TRANS4 || 
				map[x][y] == Configure.TRANS5 || map[x][y] == Configure.TRANS6 || 
				map[x][y] == Configure.TRANS7 || map[x][y] == Configure.TRANS8) {
			return true;
		}else {
			return false;
		}
	}
}
