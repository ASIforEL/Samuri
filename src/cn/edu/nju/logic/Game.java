package cn.edu.nju.logic;

import javax.swing.JOptionPane;

import cn.edu.nju.panels.GameField;
import cn.edu.nju.panels.MapSelectPanel;
import cn.edu.nju.panels.StartPanel;

public class Game implements Runnable{
	public static final int PLAYER_NUM = 6;
	public static final int TOTAL_TURNS = 1008;
	public static int totalScore;
	public int teamCapScore;
	public int teamIronManScore;

	public Samurai[] samurais = new Samurai[6];
	public GameField gameField;

	public int curSamuraiNum = 0;					//the current controlled samurai
	public int[][] map;								//gameField map
	public int[][] samuraiField;					//samurais and their attackable blocks
	public int[][][] hitRangeField = Configure.hitRangeField;
	public int[][] scoreField = Configure.scoreField;

	public static int[][] rootHitRangeField;

	public Game(GameField gameField) {
		this.gameField = gameField;

		map = Configure.mapField[MapSelectPanel.numOfMap - 1];		
		samuraiField = Configure.samuraiField;
		rootHitRangeField = getHitRangeField(map);
		scoreField = getHitRangeField(map);

		//initialize every samurai's hitRangeField to the root one
		hitRangeField[0] = rootHitRangeField;
		hitRangeField[1] = rootHitRangeField;
		hitRangeField[2] = rootHitRangeField;
		hitRangeField[3] = rootHitRangeField;
		hitRangeField[4] = rootHitRangeField;
		hitRangeField[5] = rootHitRangeField;

		//the even number represents TeamCap, odd number represents TeamIronMan
		//initialize them in their run order
		samurais[0] = new CaptainAmerica(this, gameField);
		samurais[1] = new IronMan(this, gameField);
		samurais[2] = new Hulk(this, gameField);
		samurais[3] = new BlackWidow(this, gameField);
		samurais[4] = new Hawkeye(this, gameField);
		samurais[5] = new SpiderMan(this, gameField);

		samurais[0].mapField = this.map;
		samurais[1].mapField = this.map;
		samurais[2].mapField = this.map;
		samurais[3].mapField = this.map;
		samurais[4].mapField = this.map;
		samurais[5].mapField = this.map;

		samurais[0].samuraiField = this.samuraiField;
		samurais[1].samuraiField = this.samuraiField;
		samurais[2].samuraiField = this.samuraiField;
		samurais[3].samuraiField = this.samuraiField;
		samurais[4].samuraiField = this.samuraiField;
		samurais[5].samuraiField = this.samuraiField;

		System.out.println("Finish Initializing the game");
	}

	public void execute(int curTurn, int action) {
		if (cheakWin() == true && curTurn != TOTAL_TURNS) {
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
		}else if (curTurn == TOTAL_TURNS && cheakWin() == false) {
			JOptionPane.showMessageDialog(null, "No one wins this game!");
			//stop the game & back to the start UI
			StartPanel.getCard().show(StartPanel.getCardPanel(), "mainPane");
		}else {
			//gaming
			this.curSamuraiNum = turn2Samurai(curTurn);
			samurais[curSamuraiNum].setCountroled(true);
			samurais[curSamuraiNum].doAction(action);
			gameField.repaint();

			System.out.println(curTurn);
			System.out.println(samurais[curSamuraiNum].getCurX());
			System.out.println(samurais[curSamuraiNum].getCurY());
			System.out.println(samurais[curSamuraiNum].getLifeSpan());
			System.out.println(samurais[curSamuraiNum].getPower());

			System.out.println();
			//repaint
		}
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
				(numOfDeadIronMan == PLAYER_NUM/2 && numOfDeadCap < PLAYER_NUM/2) || 
				teamCapScore >= totalScore * 0.8 || teamIronManScore >= totalScore * 0.8){
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
				if (samurais[i].getSide() == 0) numOfDeadCap++;
				if (samurais[i].getSide() == 1) numOfDeadIronMan++;
			}
		}
		if ((numOfDeadCap == PLAYER_NUM/2 && numOfDeadIronMan < PLAYER_NUM/2) || 
				teamCapScore >= totalScore * 0.8) return 1;
		if ((numOfDeadIronMan == PLAYER_NUM/2 && numOfDeadCap < PLAYER_NUM/2) || 
				teamIronManScore >= totalScore * 0.8) return 0;
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
			if (samurais[i].getCurX() == x && samurais[i].getCurY() == y && samurais[i].getHidden() == false) {
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
	
	/**
	 * to check whether this block can be reached to
	 * @param x
	 * @param y
	 * @return can /cannot
	 */
	public boolean isReachable(int x, int y) {
		if (x < 0 || x >= 12 || y < 0 || y >= 12) return false;
		else return true;
	}

	/**
	 * get the current Samurai based on the current turn
	 * @param curTurn
	 * @return curSamuraiNum
	 */
	private int turn2Samurai(int curTurn) {
		return curTurn % 6;
	}
	
	/**
	 * get the root one to reset when change happens
	 * @param map
	 * @return the rootHitRangeField
	 */
	private int[][] getHitRangeField(int[][] map) {
		int[][] hitRangeField = new int[12][12];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] != 1) {
					//this block cannot be hitted
					hitRangeField[i][j] = -1;
				}else {
					//initializing it all 0
					hitRangeField[i][j] = 0;
					totalScore++;
				}
			}
		}
		return hitRangeField;
	}
	
	/**
	 * 
	 * @param input
	 * @return actual actions in the way of String, spaced by the " "
	 */
	public String getActions(String input) {
		String inputActions = "";
		if (input.startsWith("# ")) {
			inputActions = input.substring(2, input.length());
		}
		return inputActions;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Start the game");
		
		while (true) {
			
		}
	}
}
