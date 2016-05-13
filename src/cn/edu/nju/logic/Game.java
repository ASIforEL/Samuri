package cn.edu.nju.logic;

import javax.swing.JOptionPane;

import cn.edu.nju.panels.GameField;
import cn.edu.nju.panels.MapSelectPanel;
import cn.edu.nju.panels.StartPanel;

public class Game {
	public GameField gameField;

	public static final int PLAYER_NUM = 6;
	public static final int TOTAL_TURNS = 1008;
	public static Samurai[] samurais;
	public int curTurn = 0;							//the current turn of the game
	public int curSamuraiNum = 0;					//the current controlled samurai
	public int side;								//group ID（0 represent TeamCap，1 represent TeamIronMan）
	public int weapon;								//Samurai ID，indicated by the weapon number
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
		if (cheakWin() == true || curTurn == TOTAL_TURNS) {
			//one side has won the game, output the promot & !!!stop the game!!!
			int winSide = winSide();
			String winner = "";
			switch (winSide) {
			case 0:winner = "TeamCap";break;
			case 1:winner = "TeampIronMan";break;
			}
			JOptionPane.showMessageDialog(null, winner + "has won this game!");
			//stop the game & back to the start UI
			StartPanel.getCard().show(StartPanel.getCardPanel(), "mainPane");
		}else {
			//gaming
			this.curSamuraiNum = turn2Samurai(curTurn);
			samurais[curSamuraiNum].doAction(this.action);
			curTurn++;
			//repaint
		}

	}

	public void startGame() {
		curSamuraiNum = 0;
		gameField.requestFocus();
		Thread t = new Thread(new GameThread(this));
		t.start();
		System.out.println("Initializing the game");
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
		}else {
			return false;
		}
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

	private int turn2Samurai(int curTurn) {
		return curTurn % 6;
	}
}
