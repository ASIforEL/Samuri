package cn.edu.nju.logic;

import javax.swing.JOptionPane;

import cn.edu.nju.panels.GameField;
import cn.edu.nju.panels.MapSelectPanel;
import cn.edu.nju.panels.StartPanel;

public class Game implements Runnable{
	public static final int PLAYER_NUM = 6;
	public static final int TOTAL_TURNS = 1008;
	public int totalScore;
	public int teamCapScore;
	public int teamIronManScore;

	public Samurai[] samurais = new Samurai[6];
	public GameField gameField;

	public int curSamuraiNum = 0;										//the current controlled samurai
	public int[][] map;													//gameField map
	public int[][][] samuraiField = new int[7][12][12];					//samurais and their attackable blocks
	public int[][][] hitRangeField = Configure.hitRangeField;
	public int[][] scoreField = Configure.scoreField;

	public static int[][] rootHitRangeField;

	public Game(GameField gameField) {
		this.gameField = gameField;

		map = Configure.mapField[MapSelectPanel.numOfMap - 1];	
		
		//to ensure the initializing one is all showing out
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				samuraiField[0][i][j] = Configure.samuraiField[i][j];	
			}
		}
		//to empty the latter hidden ones
		for (int k = 1; k < 7; k++) {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 12; j++) {
					samuraiField[k][i][j] = 0;
				}
			}
		}
		
		
		rootHitRangeField = getHitRangeField(map);
		scoreField = getHitRangeField(map);
		
		Configure.scoreField = scoreField;
		totalScore = getTotalScore(rootHitRangeField);

		//initialize every samurai's hitRangeField to the root one
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 12; j++) {
				for (int j2 = 0; j2 < 12; j2++) {
					hitRangeField[i][j][j2] = rootHitRangeField[j][j2];
				}
			}
		}

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

		samurais[0].samuraiField = this.samuraiField[0];
		samurais[1].samuraiField = this.samuraiField[0];
		samurais[2].samuraiField = this.samuraiField[0];
		samurais[3].samuraiField = this.samuraiField[0];
		samurais[4].samuraiField = this.samuraiField[0];
		samurais[5].samuraiField = this.samuraiField[0];
		
		samurais[0].scoreField = this.scoreField;
		samurais[1].scoreField = this.scoreField;
		samurais[2].scoreField = this.scoreField;
		samurais[3].scoreField = this.scoreField;
		samurais[4].scoreField = this.scoreField;
		samurais[5].scoreField = this.scoreField;

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
			JOptionPane.showMessageDialog(null, winner + " has won this game!");
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

			System.out.print(curTurn + " ");
			System.out.print(totalScore + " ");
			System.out.println();
			System.out.print(samurais[curSamuraiNum].getCurX() + " ");
			System.out.print(samurais[curSamuraiNum].getCurY() + " ");
			System.out.print(samurais[curSamuraiNum].getLifeSpan() + " ");
			System.out.print(samurais[curSamuraiNum].getPower());

			System.out.println();
//			printScoreField();
//			System.out.println();
//			printScoreFieldOfCon();
//			printRootOne();
//			System.out.println();
//			printHitRange();
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
		if (rootHitRangeField[y][x] == -1) {
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
	public int turn2Samurai(int curTurn) {
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
	 * reset the hitRangeField to the root one
	 */
	public void resetHitRange() {
		for (int j = 0; j < 12; j++) {
			for (int j2 = 0; j2 < 12; j2++) {
				hitRangeField[curSamuraiNum][j][j2] = rootHitRangeField[j][j2];
			}
		}
	}
	
	/**
	 * set this samurai's attack range
	 * before you do this method setHitRange()
	 * you need to reset this samurai's preHitRange (in hitRangeField[2weapon + side]) to rootHitRangeField
	 */
	public void setHitRange(Samurai samurai) {
		if (samurai.getHidden() == true) {
			//this one is hidden and it cannot hit others
			resetHitRange();
		}else {
			switch (samurai.getWeapon()) {
			case 0:setHitRange0();break;
			case 1:setHitRange1();break;
			case 2:setHitRange2();break;
			}
		}
	}

	private void setHitRange0() {
		resetHitRange();
		switch (samurais[curSamuraiNum].getDirection()) {
		case WEST:
			for (int i = 1; i <= 3; i++) {
				if (isReachable(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getWest(i).getY()][samurais[curSamuraiNum].getWest(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
			}break;
		case EAST:
			for (int i = 1; i <= 3; i++) {
				if (isReachable(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getEast(i).getY()][samurais[curSamuraiNum].getEast(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
			}break;
		case NORTH:
			for (int i = 1; i <= 3; i++) {
				if (isReachable(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getNorth(i).getY()][samurais[curSamuraiNum].getNorth(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
			}break;
		case SOUTH:
			for (int i = 1; i <= 3; i++) {
				if (isReachable(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getSouth(i).getX(), samurais[curSamuraiNum].getSouth(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getSouth(i).getY()][samurais[curSamuraiNum].getSouth(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
			}break;
		}
	}
	
	private void setHitRange1() {
		resetHitRange();		
		switch (samurais[curSamuraiNum].getDirection()) {
		case WEST:
			for (int i = 1; i <= 2; i++) {
				if (isReachable(samurais[curSamuraiNum].getSouth(i).getX(), samurais[curSamuraiNum].getSouth(i).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getSouth(i).getX(), samurais[curSamuraiNum].getSouth(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getSouth(i).getY()][samurais[curSamuraiNum].getSouth(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
				if (isReachable(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getWest(i).getY()][samurais[curSamuraiNum].getWest(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
			}
			if (isReachable(samurais[curSamuraiNum].getSouthWest(1).getX(), samurais[curSamuraiNum].getSouthWest(1).getY()) &&
					!isSpecial(samurais[curSamuraiNum].getSouthWest(1).getX(), samurais[curSamuraiNum].getSouthWest(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getSouthWest(1).getY()][samurais[curSamuraiNum].getSouthWest(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}
			break;
		case EAST:
			for (int i = 1; i <= 2; i++) {
				if (isReachable(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getNorth(i).getY()][samurais[curSamuraiNum].getNorth(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
				if (isReachable(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY()) &&
						!isSpecial(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getEast(i).getY()][samurais[curSamuraiNum].getEast(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
			}
			if (isReachable(samurais[curSamuraiNum].getNorthEast(1).getX(), samurais[curSamuraiNum].getNorthEast(1).getY()) &&
					!isSpecial(samurais[curSamuraiNum].getNorthEast(1).getX(), samurais[curSamuraiNum].getNorthEast(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getNorthEast(1).getY()][samurais[curSamuraiNum].getNorthEast(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}
			break;
		case NORTH:
			for (int i = 1; i <= 2; i++) {
				if (isReachable(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY()) &&
						!isSpecial(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getNorth(i).getY()][samurais[curSamuraiNum].getNorth(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
				if (isReachable(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getWest(i).getY()][samurais[curSamuraiNum].getWest(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
			}
			if (isReachable(samurais[curSamuraiNum].getNorthWest(1).getX(), samurais[curSamuraiNum].getNorthWest(1).getY()) &&
					!isSpecial(samurais[curSamuraiNum].getNorthWest(1).getX(), samurais[curSamuraiNum].getNorthWest(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getNorthWest(1).getY()][samurais[curSamuraiNum].getNorthWest(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}
			break;
		case SOUTH:
			for (int i = 1; i <= 2; i++) {
				if (isReachable(samurais[curSamuraiNum].getSouth(i).getX(), samurais[curSamuraiNum].getSouth(i).getY()) &&
						!isSpecial(samurais[curSamuraiNum].getSouth(i).getX(), samurais[curSamuraiNum].getSouth(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getSouth(i).getY()][samurais[curSamuraiNum].getSouth(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
				if (isReachable(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY()) &&
						!isSpecial(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY())) {
					hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getEast(i).getY()][samurais[curSamuraiNum].getEast(i).getX()] 
							= samurais[curSamuraiNum].hitRangeFieldNum;
				}
			}
			if (isReachable(samurais[curSamuraiNum].getSouthEast(1).getX(), samurais[curSamuraiNum].getSouthEast(1).getY()) &&
					!isSpecial(samurais[curSamuraiNum].getSouthEast(1).getX(), samurais[curSamuraiNum].getSouthEast(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getSouthEast(1).getY()][samurais[curSamuraiNum].getSouthEast(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}
			break;
		}
	}
	
	private void setHitRange2() {
		resetHitRange();		
		if (isReachable(samurais[curSamuraiNum].getSouthEast(1).getX(), samurais[curSamuraiNum].getSouthEast(1).getY()) && 
				!isSpecial(samurais[curSamuraiNum].getSouthEast(1).getX(), samurais[curSamuraiNum].getSouthEast(1).getY())) {
			hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getSouthEast(1).getY()][samurais[curSamuraiNum].getSouthEast(1).getX()] 
					= samurais[curSamuraiNum].hitRangeFieldNum;
		}
		if (isReachable(samurais[curSamuraiNum].getSouthWest(1).getX(), samurais[curSamuraiNum].getSouthWest(1).getY()) &&
				!isSpecial(samurais[curSamuraiNum].getSouthWest(1).getX(), samurais[curSamuraiNum].getSouthWest(1).getY())) {
			hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getSouthWest(1).getY()][samurais[curSamuraiNum].getSouthWest(1).getX()] 
					= samurais[curSamuraiNum].hitRangeFieldNum;
		}
		if (isReachable(samurais[curSamuraiNum].getNorthWest(1).getX(), samurais[curSamuraiNum].getNorthWest(1).getY()) && 
				!isSpecial(samurais[curSamuraiNum].getNorthWest(1).getX(), samurais[curSamuraiNum].getNorthWest(1).getY())) {
			hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getNorthWest(1).getY()][samurais[curSamuraiNum].getNorthWest(1).getX()] 
					= samurais[curSamuraiNum].hitRangeFieldNum;
		}
		if (isReachable(samurais[curSamuraiNum].getNorthEast(1).getX(), samurais[curSamuraiNum].getNorthEast(1).getY()) && 
				!isSpecial(samurais[curSamuraiNum].getNorthEast(1).getX(), samurais[curSamuraiNum].getNorthEast(1).getY())) {
			hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getNorthEast(1).getY()][samurais[curSamuraiNum].getNorthEast(1).getX()] 
					= samurais[curSamuraiNum].hitRangeFieldNum;
		}
		switch (samurais[curSamuraiNum].getDirection()) {
		case WEST:
			if (isReachable(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getWest(1).getY()][samurais[curSamuraiNum].getWest(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}
			if (isReachable(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getNorth(1).getY()][samurais[curSamuraiNum].getNorth(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}if (isReachable(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getSouth(1).getY()][samurais[curSamuraiNum].getSouth(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}break;
		case EAST:
			if (isReachable(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getEast(1).getY()][samurais[curSamuraiNum].getEast(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}
			if (isReachable(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getNorth(1).getY()][samurais[curSamuraiNum].getNorth(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}if (isReachable(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getSouth(1).getY()][samurais[curSamuraiNum].getSouth(1).getX()]
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}break;
		case NORTH:
			if (isReachable(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY())&&
					!isSpecial(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getWest(1).getY()][samurais[curSamuraiNum].getWest(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}
			if (isReachable(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getEast(1).getY()][samurais[curSamuraiNum].getEast(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}
			if (isReachable(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getNorth(1).getY()][samurais[curSamuraiNum].getNorth(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}break;
		case SOUTH:
			if (isReachable(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY())&&
					!isSpecial(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getWest(1).getY()][samurais[curSamuraiNum].getWest(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}
			if (isReachable(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getEast(1).getY()][samurais[curSamuraiNum].getEast(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}
			if (isReachable(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY())) {
				hitRangeField[curSamuraiNum][samurais[curSamuraiNum].getSouth(1).getY()][samurais[curSamuraiNum].getSouth(1).getX()] 
						= samurais[curSamuraiNum].hitRangeFieldNum;
			}break;
		}
	}
	
	/**
	 * set this samurai's occuiped blocks
	 */
	public void setScoreField(Samurai samurai) {
		switch (samurai.getWeapon()) {
		case 0:setScoreField0();break;
		case 1:setScoreField1();break;
		case 2:setScoreField2();break;
		}
	}
	
	private void setScoreField0() {
		switch (samurais[curSamuraiNum].getDirection()) {
		case WEST:
			for (int i = 1; i <= 3; i++) {
				if (isReachable(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY())) {
					scoreField[samurais[curSamuraiNum].getWest(i).getY()][samurais[curSamuraiNum].getWest(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
			}break;
		case EAST:
			for (int i = 1; i <= 3; i++) {
				if (isReachable(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY())) {
					scoreField[samurais[curSamuraiNum].getEast(i).getY()][samurais[curSamuraiNum].getEast(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
			}break;
		case NORTH:
			for (int i = 1; i <= 3; i++) {
				if (isReachable(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY())) {
					scoreField[samurais[curSamuraiNum].getNorth(i).getY()][samurais[curSamuraiNum].getNorth(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
			}break;
		case SOUTH:
			for (int i = 1; i <= 3; i++) {
				if (isReachable(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY()) && 
						!isSpecial(samurais[curSamuraiNum].getSouth(i).getX(), samurais[curSamuraiNum].getSouth(i).getY())) {
					scoreField[samurais[curSamuraiNum].getSouth(i).getY()][samurais[curSamuraiNum].getSouth(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
			}break;
		}
	}
	
	private void setScoreField1() {		
		switch (samurais[curSamuraiNum].getDirection()) {
		case WEST:
			for (int i = 1; i <= 2; i++) {
				if (!isSpecial(samurais[curSamuraiNum].getSouth(i).getX(), samurais[curSamuraiNum].getSouth(i).getY()) && 
						isReachable(samurais[curSamuraiNum].getSouth(i).getX(), samurais[curSamuraiNum].getSouth(i).getY())) {
					scoreField[samurais[curSamuraiNum].getSouth(i).getY()][samurais[curSamuraiNum].getSouth(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
				if (!isSpecial(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY()) && 
						isReachable(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY())) {
					scoreField[samurais[curSamuraiNum].getWest(i).getY()][samurais[curSamuraiNum].getWest(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
			}
			if (!isSpecial(samurais[curSamuraiNum].getSouthWest(1).getX(), samurais[curSamuraiNum].getSouthWest(1).getY()) && 
					isReachable(samurais[curSamuraiNum].getSouthWest(1).getX(), samurais[curSamuraiNum].getSouthWest(1).getY())) {
				scoreField[samurais[curSamuraiNum].getSouthWest(1).getY()][samurais[curSamuraiNum].getSouthWest(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}
			break;
		case EAST:
			for (int i = 1; i <= 2; i++) {
				if (!isSpecial(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY()) && 
						isReachable(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY())) {
					scoreField[samurais[curSamuraiNum].getNorth(i).getY()][samurais[curSamuraiNum].getNorth(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
				if (!isSpecial(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY()) && 
						isReachable(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY())) {
					scoreField[samurais[curSamuraiNum].getEast(i).getY()][samurais[curSamuraiNum].getEast(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
			}
			if (!isSpecial(samurais[curSamuraiNum].getNorthEast(1).getX(), samurais[curSamuraiNum].getNorthEast(1).getY()) && 
					isReachable(samurais[curSamuraiNum].getNorthEast(1).getX(), samurais[curSamuraiNum].getNorthEast(1).getY())) {
				scoreField[samurais[curSamuraiNum].getNorthEast(1).getY()][samurais[curSamuraiNum].getNorthEast(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}
			break;
		case NORTH:
			for (int i = 1; i <= 2; i++) {
				if (!isSpecial(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY()) && 
						isReachable(samurais[curSamuraiNum].getNorth(i).getX(), samurais[curSamuraiNum].getNorth(i).getY())) {
					scoreField[samurais[curSamuraiNum].getNorth(i).getY()][samurais[curSamuraiNum].getNorth(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
				if (!isSpecial(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY()) && 
						isReachable(samurais[curSamuraiNum].getWest(i).getX(), samurais[curSamuraiNum].getWest(i).getY())) {
					scoreField[samurais[curSamuraiNum].getWest(i).getY()][samurais[curSamuraiNum].getWest(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
			}
			if (!isSpecial(samurais[curSamuraiNum].getNorthWest(1).getX(), samurais[curSamuraiNum].getNorthWest(1).getY()) && 
					isReachable(samurais[curSamuraiNum].getNorthWest(1).getX(), samurais[curSamuraiNum].getNorthWest(1).getY())) {
				scoreField[samurais[curSamuraiNum].getNorthWest(1).getY()][samurais[curSamuraiNum].getNorthWest(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}
			break;
		case SOUTH:
			for (int i = 1; i <= 2; i++) {
				if (!isSpecial(samurais[curSamuraiNum].getSouth(i).getX(), samurais[curSamuraiNum].getSouth(i).getY()) && 
						isReachable(samurais[curSamuraiNum].getSouth(i).getX(), samurais[curSamuraiNum].getSouth(i).getY())) {
					scoreField[samurais[curSamuraiNum].getSouth(i).getY()][samurais[curSamuraiNum].getSouth(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
				if (!isSpecial(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY()) && 
						isReachable(samurais[curSamuraiNum].getEast(i).getX(), samurais[curSamuraiNum].getEast(i).getY())) {
					scoreField[samurais[curSamuraiNum].getEast(i).getY()][samurais[curSamuraiNum].getEast(i).getX()] 
							= samurais[curSamuraiNum].manor;
				}
			}
			if (!isSpecial(samurais[curSamuraiNum].getSouthEast(1).getX(), samurais[curSamuraiNum].getSouthEast(1).getY()) && 
					isReachable(samurais[curSamuraiNum].getSouthEast(1).getX(), samurais[curSamuraiNum].getSouthEast(1).getY())) {
				scoreField[samurais[curSamuraiNum].getSouthEast(1).getY()][samurais[curSamuraiNum].getSouthEast(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}
			break;
		}
	}
	
	private void setScoreField2() {
		if (isReachable(samurais[curSamuraiNum].getSouthEast(1).getX(), samurais[curSamuraiNum].getSouthEast(1).getY()) && 
				!isSpecial(samurais[curSamuraiNum].getSouthEast(1).getX(), samurais[curSamuraiNum].getSouthEast(1).getY())) {
			scoreField[samurais[curSamuraiNum].getSouthEast(1).getY()][samurais[curSamuraiNum].getSouthEast(1).getX()] 
					= samurais[curSamuraiNum].manor;
		}
		if (isReachable(samurais[curSamuraiNum].getSouthWest(1).getX(), samurais[curSamuraiNum].getSouthWest(1).getY()) &&
				!isSpecial(samurais[curSamuraiNum].getSouthWest(1).getX(), samurais[curSamuraiNum].getSouthWest(1).getY())) {
			scoreField[samurais[curSamuraiNum].getSouthWest(1).getY()][samurais[curSamuraiNum].getSouthWest(1).getX()] 
					= samurais[curSamuraiNum].manor;
		}
		if (isReachable(samurais[curSamuraiNum].getNorthWest(1).getX(), samurais[curSamuraiNum].getNorthWest(1).getY()) && 
				!isSpecial(samurais[curSamuraiNum].getNorthWest(1).getX(), samurais[curSamuraiNum].getNorthWest(1).getY())) {
			scoreField[samurais[curSamuraiNum].getNorthWest(1).getY()][samurais[curSamuraiNum].getNorthWest(1).getX()] 
					= samurais[curSamuraiNum].manor;
		}
		if (isReachable(samurais[curSamuraiNum].getNorthEast(1).getX(), samurais[curSamuraiNum].getNorthEast(1).getY()) && 
				!isSpecial(samurais[curSamuraiNum].getNorthEast(1).getX(), samurais[curSamuraiNum].getNorthEast(1).getY())) {
			scoreField[samurais[curSamuraiNum].getNorthEast(1).getY()][samurais[curSamuraiNum].getNorthEast(1).getX()] 
					= samurais[curSamuraiNum].manor;
		}
		switch (samurais[curSamuraiNum].getDirection()) {
		case WEST:
			if (isReachable(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY())) {
				scoreField[samurais[curSamuraiNum].getWest(1).getY()][samurais[curSamuraiNum].getWest(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}
			if (isReachable(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY())) {
				scoreField[samurais[curSamuraiNum].getNorth(1).getY()][samurais[curSamuraiNum].getNorth(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}if (isReachable(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY())) {
				scoreField[samurais[curSamuraiNum].getSouth(1).getY()][samurais[curSamuraiNum].getSouth(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}break;
		case EAST:
			if (isReachable(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY())) {
				scoreField[samurais[curSamuraiNum].getEast(1).getY()][samurais[curSamuraiNum].getEast(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}
			if (isReachable(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY())) {
				scoreField[samurais[curSamuraiNum].getNorth(1).getY()][samurais[curSamuraiNum].getNorth(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}if (isReachable(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY())) {
				scoreField[samurais[curSamuraiNum].getSouth(1).getY()][samurais[curSamuraiNum].getSouth(1).getX()]
						= samurais[curSamuraiNum].manor;
			}break;
		case NORTH:
			if (isReachable(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY())&&
					!isSpecial(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY())) {
				scoreField[samurais[curSamuraiNum].getWest(1).getY()][samurais[curSamuraiNum].getWest(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}
			if (isReachable(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY())) {
				scoreField[samurais[curSamuraiNum].getEast(1).getY()][samurais[curSamuraiNum].getEast(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}
			if (isReachable(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getNorth(1).getX(), samurais[curSamuraiNum].getNorth(1).getY())) {
				scoreField[samurais[curSamuraiNum].getNorth(1).getY()][samurais[curSamuraiNum].getNorth(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}break;
		case SOUTH:
			if (isReachable(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY())&&
					!isSpecial(samurais[curSamuraiNum].getWest(1).getX(), samurais[curSamuraiNum].getWest(1).getY())) {
				scoreField[samurais[curSamuraiNum].getWest(1).getY()][samurais[curSamuraiNum].getWest(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}
			if (isReachable(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getEast(1).getX(), samurais[curSamuraiNum].getEast(1).getY())) {
				scoreField[samurais[curSamuraiNum].getEast(1).getY()][samurais[curSamuraiNum].getEast(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}
			if (isReachable(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY()) && 
					!isSpecial(samurais[curSamuraiNum].getSouth(1).getX(), samurais[curSamuraiNum].getSouth(1).getY())) {
				scoreField[samurais[curSamuraiNum].getSouth(1).getY()][samurais[curSamuraiNum].getSouth(1).getX()] 
						= samurais[curSamuraiNum].manor;
			}break;
		}
	}
	
	/**
	 * @param rootHitRangeField
	 * @return the total blocks can be occupied
	 */
	private int getTotalScore(int[][] rootHitRangeField) {
		for (int i = 0; i < rootHitRangeField.length; i++) {
			for (int j = 0; j < rootHitRangeField[0].length; j++) {
				if (rootHitRangeField[i][j] != -1) {
					totalScore++;
				}
			}
		}
		//due to the reality
		return totalScore / 3;
	}
	
	/**
	 * @return the blocks occupied by the teamCap
	 */
	public int getScoreA() {
		int result = 0;
		for (int i = 0; i < scoreField.length; i++) {
			for (int j = 0; j < scoreField[0].length; j++) {
				if (scoreField[i][j] == 1) {
					result++;
				}
			}
		}
		return result;
	}
	
	/**
	 * @return the blocks occupied by the teamIronMan
	 */
	public int getScoreB() {
		int result = 0;
		for (int i = 0; i < scoreField.length; i++) {
			for (int j = 0; j < scoreField[0].length; j++) {
				if (scoreField[i][j] == 2) {
					result++;
				}
			}
		}
		return result;
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
	
//	private void printHitRange() {
//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 12; j++) {
//				System.out.print(hitRangeField[curSamuraiNum][i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
//	
//	private void printRootOne() {
//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 12; j++) {
//				System.out.print(rootHitRangeField[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}

//	private void printScoreField() {
//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 12; j++) {
//				System.out.print(scoreField[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
//	
//	private void printScoreFieldOfCon() {
//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 12; j++) {
//				System.out.print(Configure.scoreField[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Start the game");
		
		while (true) {
			
		}
	}
}
