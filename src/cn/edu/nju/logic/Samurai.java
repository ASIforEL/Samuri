package cn.edu.nju.logic;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import cn.edu.nju.panels.GameField;

public abstract class Samurai {
	private static final int TOTAL_POWER = 7;
	private static String powerInfo;
	private int homeX, homeY;
	public int curX, curY;
	public Direction direction;
	public int lifespan;
	public boolean hidden;
	public int weapon;							//Samurai ID, indicated by the weapon number
	private boolean countrolled;
	private int side;							//Group ID. 0 represents TeamCap，1 represents TeamIronMan
	private int curPower;
	public Game game;
	public GameField gameField;
	
	int[][] mapField = Configure.map;
	int[][] samuraiField = Configure.samuraiField;		//set the samurai's current locatioon
	
	protected int samuraiFieldNum;				//of samuraiField[][]	11 / 22 / 33 / 44 / 55 / 66
	protected int hitRangeFieldNum;				//of hitRangeField[][][] in Game game		1 / 2 / 3 / 4 / 5 / 6
	protected int manor;
	
	protected Image samuraiImg;
	protected Image bloodImg;
	protected Image powerImg;
	
	protected int initBlood_X;
	protected int initBlood_Y;
	protected int initPower_X;
	protected int initPower_Y;
	
	public Samurai(Game game, GameField gameField){
		try {
			bloodImg = ImageIO.read(this.getClass().getResource("/cn/picture/blood.png"));
			powerImg = ImageIO.read(this.getClass().getResource("/cn/picture/power.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Samurai.powerInfo = "";
		
		this.game = game;
		this.gameField = gameField;
		this.hidden = false;
		this.countrolled = false;
		this.curPower = TOTAL_POWER;
	}


	//getters & setters
	public int getHomeX() {
		return this.homeX;
	}

	public int getHomeY() {
		return this.homeY;
	}

	public int getPower() {
		return this.curPower;
	}

	public int getCurX() {
		return this.curX;
	}

	public int getCurY() {
		return this.curY;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public int getLifeSpan() {
		return this.lifespan;
	}

	public boolean getHidden() {
		return this.hidden;
	}

	public int getSide() {
		return this.side;
	}
	
	public int getWeapon() {
		return this.weapon;
	}

	public boolean getControled() {
		return this.countrolled;
	}
	
	public String getPowerInfo() {
		return Samurai.powerInfo;
	}

	public void setHomeX(int info) {
		this.homeX = info;
	}

	public void setHomeY(int info) {
		this.homeY = info;
	}
	
	public void setPower() {
		this.curPower = TOTAL_POWER;
	}

	public void setCurX(int info) {
		this.curX = info;
	}

	public void setCurY(int info) {
		this.curY = info;
	}

	public Direction setDirection(Direction info) {
		return this.direction = info;
	}

	public void setLifeSpan(int info){
		this.lifespan = info;
	}

	public void setHidden(boolean info) {
		this.hidden = info;
	}

	public void setSide(int info) {
		this.side = info;
	}

	public void setWeapon(int info) {
		this.weapon = info;
	}

	public void setCountroled(boolean info) {
		this.countrolled = info;
	}
	
	public void setPowerInfo(String info) {
		Samurai.powerInfo = info;
	}




	/**
	 * @return whether the samurai is killed or not
	 */
	public boolean isKilled() {
		return this.lifespan == 0;
	}

	/**
	 * do the action actually
	 */
	public void doAction(int action) {
			if (this.isValid(action) == true) {
				if (isKilled() == true) {
					//this samurai has dead
					this.setCountroled(false);
					GameField.curTurn++;
					//this action actually need to be done by the next samurai
					game.execute(GameField.curTurn, action);
					return;
				}
				//it can do this action
				if (action == 0) {
					this.setCountroled(false);
					updatePower();
					GameField.curTurn++;
				}

				if (action >= 1 && action <= 4){
					int preX = this.getCurX();
					int preY = this.getCurY();

					this.rotate(action);
					moveForward();
					this.curPower -= 2;

					int value = mapField[this.getCurY()][this.getCurX()];
					if (value == Configure.TRANS1 || value == Configure.TRANS2 || value == Configure.TRANS3 || 
							value == Configure.TRANS4 || value == Configure.TRANS5 || value == Configure.TRANS6) {
						transfer();
					}

					samuraiField[preY][preX] = 0;
					samuraiField[this.getCurY()][this.getCurX()] = this.samuraiFieldNum;
				}

				if (action == 6){
					this.hidden = true;
					this.curPower -= 1;
				}

				if (action == 7){
					this.hidden = false;
					this.curPower -= 1;
				}

				if (action == 10) {
					occupy();
					
					switch (this.weapon) {
					case 0:
						for (int i = 11; i <= 13; i++) {
							if (canHit(getHittenSamurai(i))) {
								hit(getHittenSamurai(i));
							}
						}break;

					case 1:
						for (int i = 11; i <= 15; i++) {
							if (canHit(getHittenSamurai(i))) {
								hit(getHittenSamurai(i));
							}
						}break;

					case 2:
						for (int i = 11; i <= 17; i++) {
							if (canHit(getHittenSamurai(i))) {
								hit(getHittenSamurai(i));
							}
						}break;
					}
					this.curPower -= 3;
				}
			}else {
				//it can not do this action
				if (this.getPowerInfo() != "") {
					JOptionPane.showMessageDialog(null, this.getPowerInfo());
					this.setPowerInfo("");
				}else {
					switch (action) {
					case 1:JOptionPane.showMessageDialog(null, "You cannot move to this block!");break;
					case 2:JOptionPane.showMessageDialog(null, "You cannot move to this block!");break;
					case 3:JOptionPane.showMessageDialog(null, "You cannot move to this block!");break;
					case 4:JOptionPane.showMessageDialog(null, "You cannot move to this block!");break;
					case 6:JOptionPane.showMessageDialog(null, "You cannot hide!");break;
					case 7:JOptionPane.showMessageDialog(null, "You cannot show!");break;
					case 10:JOptionPane.showMessageDialog(null, "You cannot hit this region!");break;
					}

				}
			}
//		}else {
//			//this one has dead
//			this.setCountroled(false);
//			GameField.curTurn++;
//		}
	}


	/**
	 * change the direction
	 * @param direction
	 */
	private void rotate(int action){
		switch (action) {
		case 1:this.direction = Direction.WEST;break;
		case 2:this.direction = Direction.EAST;break;
		case 3:this.direction = Direction.NORTH;break;
		case 4:this.direction = Direction.SOUTH;break;
		}
		
//		/*
//		 * the following code runs when the player plays the game as if he was a samurai,
//		 * brought into the Samurai's perspective 
//		 */
//		switch (Samurai.direction) {
//		case WEST:
//			switch (action) {
//			case 1:Samurai.direction = Direction.SOUTH;break;
//			case 2:Samurai.direction = Direction.NORTH;break;
//			case 3:Samurai.direction = Direction.WEST;break;
//			case 4:Samurai.direction = Direction.EAST;break;
//			}
//
//		case EAST:
//			switch (action) {
//			case 1:Samurai.direction = Direction.NORTH;break;
//			case 2:Samurai.direction = Direction.SOUTH;break;
//			case 3:Samurai.direction = Direction.EAST;break;
//			case 4:Samurai.direction = Direction.WEST;break;
//			}
//		case NORTH:
//			switch (action) {
//			case 1:Samurai.direction = Direction.WEST;break;
//			case 2:Samurai.direction = Direction.EAST;break;
//			case 3:Samurai.direction = Direction.NORTH;break;
//			case 4:Samurai.direction = Direction.SOUTH;break;
//			}
//		case SOUTH:
//			switch (action) {
//			case 1:Samurai.direction = Direction.EAST;break;
//			case 2:Samurai.direction = Direction.WEST;break;
//			case 3:Samurai.direction = Direction.SOUTH;break;
//			case 4:Samurai.direction = Direction.NORTH;break;
//			}
//		}
	}

	/**
	 * move to the direction it faces
	 */
	private void moveForward() {
		switch (this.getDirection()) {
		case WEST:this.setCurX(this.getCurX() - 1);break;
		case EAST:this.setCurX(this.getCurX() + 1);break;
		case NORTH:this.setCurY(this.getCurY() - 1);break;
		case SOUTH:this.setCurY(this.getCurY() + 1);break;
		}
	}

	/**
	 * deliver it to another gateway accordingly
	 */
	private void transfer() {
		int value = mapField[this.getCurY()][this.getCurX()];
		
		switch (value) {
		case Configure.TRANS1:setCurX(8);setCurY(11);break;
		case Configure.TRANS2:setCurX(4);setCurY(1);break;
		case Configure.TRANS3:setCurX(7);setCurY(1);break;
		case Configure.TRANS4:setCurX(3);setCurY(11);break;
		case Configure.TRANS5:setCurX(7);setCurY(8);setDirection(Direction.EAST);break;
		case Configure.TRANS6:setCurX(5);setCurY(8);setDirection(Direction.SOUTH);break;
		case Configure.TRANS7:setCurX(8);setCurY(6);setDirection(Direction.WEST);break;
		case Configure.TRANS8:setCurX(4);setCurY(7);setDirection(Direction.NORTH);break;
		}
	}

	/**
	 * if hitted, lifespan - 1 & go back to its home
	 */
	private void hit(Samurai hittenSamurai) {
		if (hittenSamurai != null) {
			hittenSamurai.setLifeSpan(hittenSamurai.getLifeSpan() - 1);
			hittenSamurai.setCurX(hittenSamurai.getHomeX());
			hittenSamurai.setCurY(hittenSamurai.getHomeY());
		}
	}
	
	/**
	 * to check whether the action is valid or not
	 * @param action
	 * @return 
	 */
	private boolean isValid(int action) {
		if (this.isKilled() == true) {
			//this samurai has dead and it cannot do actions actually
			//but we return true and check it in the following steps
			return true;
		}else {
			//this samurai is alive
			//save the previous data to resume the situation
			int preX = this.getCurX();
			int preY = this.getCurY();
			Direction preDirection = this.getDirection();
			boolean preHidden = this.getHidden();

			if (action == 0) {
				return true;
			}

			if (action >= 1 && action <= 4){
				//check it has enough power to do the action
				if (this.getPower() < NeededPower.MOVE) {
					this.setPowerInfo("You have not enough power to execute this action!");
					return false;
				}else{
					if (canMove(action) == true) {
						resume(preX, preY, preDirection, preHidden);
						return true;
					}else {
						resume(preX, preY, preDirection, preHidden);
						return false;
					}
				}
			}

			if (action == 6){
				//check it has enough power to do the action
				if (this.getPower() < NeededPower.HIDE) {
					this.setPowerInfo("You have not enough power to execute this action!");
					return false;
				}else {
					if (this.getHidden() == true){
						resume(preX, preY, preDirection, preHidden);
						return false;
					}else  {
						resume(preX, preY, preDirection, preHidden);
						return true;
					}
				}
			}

			if (action == 7){
				//check it has enough power to do the action
				if (this.getPower() < NeededPower.SHOW) {
					this.setPowerInfo("You have not enough power to execute this action!");
					return false;
				}else{
					if (this.getHidden() == false){
						//it is not hidden
						resume(preX, preY, preDirection, preHidden);
						return false;
					}else {
						//it is hidden, but it cannot show if there is a samurai, which is not hidden, in the block
						for (int i = 0; i < Game.PLAYER_NUM; ++i){
							Samurai other = game.samurais[i];
							if (other.getHidden() == false && 
									(other.getCurX() == curX && other.getCurY() == curY)){
								resume(preX, preY, preDirection, preHidden);
								return false;
							}
						}
					}

					resume(preX, preY, preDirection, preHidden);
					return true;
				}

			}

			if (action == 10) {
				//check it has enough power to do the action
				if (this.getPower() < NeededPower.ATTACK) {
					this.setPowerInfo("You have not enough power to execute this action!");
					return false;
				}else {
					switch (this.weapon) {
					case 0:
						if (canHit(getHittenSamurai(11)) || canHit(getHittenSamurai(12)) || canHit(getHittenSamurai(13))) {
							resume(preX, preY, preDirection, preHidden);
							return true;
						}else {
							resume(preX, preY, preDirection, preHidden);
							return false;
						}
					case 1:
						if (canHit(getHittenSamurai(11)) || canHit(getHittenSamurai(12)) || canHit(getHittenSamurai(13)) || 
								canHit(getHittenSamurai(14)) || canHit(getHittenSamurai(15))) {
							resume(preX, preY, preDirection, preHidden);
							return true;
						}else {
							resume(preX, preY, preDirection, preHidden);
							return false;
						}
					case 2:
						if (canHit(getHittenSamurai(11)) || canHit(getHittenSamurai(12)) || canHit(getHittenSamurai(13)) || 
								canHit(getHittenSamurai(14)) || canHit(getHittenSamurai(15)) || 
								canHit(getHittenSamurai(16)) || canHit(getHittenSamurai(17))) {
							resume(preX, preY, preDirection, preHidden);
							return true;
						}else {
							resume(preX, preY, preDirection, preHidden);
							return false;
						}
					}
				}
			}
			return false;	
		}
	}

	/**
	 * resume the situation in the method isValid() 
	 * then do the action actually in the method doAction() accordingly
	 * @param preX
	 * @param preY
	 * @param preDirection
	 */
	private void resume(int preX, int preY, Direction preDirection, boolean preHidden) {
		setCurX(preX);
		setCurY(preY);
		setDirection(preDirection);
		setHidden(preHidden);
	}

	/**
	 * to check whether the samurai can rotate and then move
	 * @param action
	 * @return
	 */
	private boolean canMove(int action) {
		//assume it can move to that block firstly, then check whether it is legal
		rotate(action);
		moveForward();

		if (this.getCurX() < 0 || this.getCurX() >= 12 || this.getCurY() < 0 || this.getCurY() >= 12){	
			return false;		
		}

		//samurai can move to a block which has already a samurai existed if it's hidden
		for (int i = 0; i < Game.PLAYER_NUM; i++){
			if ((this.getCurX() == game.samurais[i].getCurX() &&
					this.getCurY() == game.samurais[i].getCurY())){
				if (this.getWeapon() == game.samurais[i].getWeapon() && this.getSide() == game.samurais[i].getSide()) {
					//this one is the caller 
					continue;
				}else {
					//there is another one existing
					if (this.getHidden() == true) {
						return true;
					}
					if (this.getHidden() == false) {
						return false;
					}
				}
				
			}
		}

		//can't move to other samurais' home
		for (int i = 0; i < Game.PLAYER_NUM; i++) {
			if ((this.getCurX() == game.samurais[i].getHomeX() &&
					this.getCurY() == game.samurais[i].getHomeY())){
				if (this.getWeapon() == game.samurais[i].getWeapon() && this.getSide() == game.samurais[i].getSide()) {
					//this one is the caller 
					return true;
				}else {
					//this isn't its home
					return false;
				}
			}
		}

		int value = mapField[this.getCurY()][this.getCurX()];
		//cannot to the block existing mount or tree
		if (value == Configure.MOUN || value == Configure.TREE) {
			return false;
		}
		//can go into the transfer gateway in an exact direction and then be transferred to the other gateway accordingly
		if (value == Configure.TRANS1 || value == Configure.TRANS2 || value == Configure.TRANS3 || 
				value == Configure.TRANS4 || value == Configure.TRANS5 || value == Configure.TRANS6 || 
				value == Configure.TRANS7 || value == Configure.TRANS8) {
			boolean result = false;
			if (value == Configure.TRANS1 && this.direction == Direction.WEST) {
				setCurX(9);
				setCurY(11);
				if (isValid(1)) {
					result = true;
				}else{
					result = false;
				}	
			}
			if (value == Configure.TRANS2 && this.direction == Direction.EAST) {
				setCurX(3);
				setCurY(1);
				if (isValid(2)) {
					result = true;
				}else {
					result = false;
				}
			}
			if (value == Configure.TRANS3 && this.direction == Direction.WEST) {
				setCurX(8);
				setCurY(1);
				if (isValid(1)) {
					result = true;
				}else {
					result = false;
				}
			}
			if (value == Configure.TRANS4 && this.direction == Direction.EAST) {
				setCurX(2);
				setCurY(11);
				if (isValid(2)) {
					result = true;
				}else {
					result = false;
				}
			}
			if (value == Configure.TRANS5 && this.direction == Direction.NORTH) {
				setCurX(6);
				setCurY(8);
				setDirection(Direction.EAST);
				if (isValid(2)) {
					result = true;
				}else {
					result = false;
				}
			}
			if (value == Configure.TRANS6 && this.direction == Direction.WEST) {
				setCurX(5);
				setCurY(7);
				setDirection(Direction.SOUTH);
				if (isValid(4)) {
					result = true;
				}else {
					result = false;
				}
			}
			if (value == Configure.TRANS7 && this.direction == Direction.SOUTH) {
				setCurX(9);
				setCurY(6);
				setDirection(Direction.WEST);
				if (isValid(1)) {
					result = true;
				}else {
					result = false;
				}
			}
			if (value == Configure.TRANS8 && this.direction == Direction.EAST) {
				setCurX(4);
				setCurY(8);
				setDirection(Direction.NORTH);
				if (isValid(2)) {
					result = true;
				}else {
					result = false;
				}
			}	
			return result;
		}
		return true;
	}
	
	/**
	 * set this samurai's attack range
	 * before you do this method setHitRange()
	 * you need to reset this samurai's preHitRange (in game.hitRangeField[2weapon + side]) to rootHitRangeField
	 */
	public void setHitRange() {
		switch (this.getWeapon()) {
		case 0:setHitRange0();break;
		case 1:setHitRange1();break;
		case 2:setHitRange2();break;
		}
	}
	
	private void setHitRange0() {
		//reset it first
		game.hitRangeField[2 * this.weapon + this.side] = Game.rootHitRangeField;
		
		switch (this.getDirection()) {
		case WEST:
			for (int i = 1; i <= 3; i++) {
				if (!game.isSpecial(this.getWest(i).getX(), this.getWest(i).getY()) && 
						game.isReachable(this.getWest(i).getX(), this.getWest(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getWest(i).getY()][this.getWest(i).getX()] 
							= this.hitRangeFieldNum;
				}
			}break;
		case EAST:
			for (int i = 1; i <= 3; i++) {
				if (!game.isSpecial(this.getEast(i).getX(), this.getEast(i).getY()) && 
						game.isReachable(this.getEast(i).getX(), this.getEast(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getEast(i).getY()][this.getEast(i).getX()] 
							= this.hitRangeFieldNum;
				}
			}break;
		case NORTH:
			for (int i = 1; i <= 3; i++) {
				if (!game.isSpecial(this.getNorth(i).getX(), this.getNorth(i).getY()) && 
						game.isReachable(this.getNorth(i).getX(), this.getNorth(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getNorth(i).getY()][this.getNorth(i).getX()] 
							= this.hitRangeFieldNum;
				}
			}break;
		case SOUTH:
			for (int i = 1; i <= 3; i++) {
				if (!game.isSpecial(this.getSouth(i).getX(), this.getSouth(i).getY()) && 
						game.isReachable(this.getSouth(1).getX(), this.getSouth(1).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getSouth(i).getY()][this.getSouth(i).getX()] 
							= this.hitRangeFieldNum;
				}
			}break;
		}
	}
	
	private void setHitRange1() {
		//reset it first
		game.hitRangeField[2 * this.weapon + this.side] = Game.rootHitRangeField;
		
		switch (this.getDirection()) {
		case WEST:
			for (int i = 1; i <= 2; i++) {
				if (!game.isSpecial(this.getSouth(i).getX(), this.getSouth(i).getY()) && 
						game.isReachable(this.getSouth(i).getX(), this.getSouth(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getSouth(i).getY()][this.getSouth(i).getX()] 
							= this.hitRangeFieldNum;
				}
				if (!game.isSpecial(this.getWest(i).getX(), this.getWest(i).getY()) && 
						game.isReachable(this.getWest(i).getX(), this.getWest(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getWest(i).getY()][this.getWest(i).getX()] 
							= this.hitRangeFieldNum;
				}
			}
			if (!game.isSpecial(this.getSouthWest(1).getX(), this.getSouthWest(1).getY()) && 
					game.isReachable(this.getSouthWest(1).getX(), this.getSouthWest(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getSouthWest(1).getY()][this.getSouthWest(1).getX()] 
						= this.hitRangeFieldNum;
			}
			break;
		case EAST:
			for (int i = 1; i <= 2; i++) {
				if (!game.isSpecial(this.getNorth(i).getX(), this.getNorth(i).getY()) && 
						game.isReachable(this.getNorth(i).getX(), this.getNorth(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getNorth(i).getY()][this.getNorth(i).getX()] 
							= this.hitRangeFieldNum;
				}
				if (!game.isSpecial(this.getEast(i).getX(), this.getEast(i).getY()) && 
						game.isReachable(this.getEast(i).getX(), this.getEast(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getEast(i).getY()][this.getEast(i).getX()] 
							= this.hitRangeFieldNum;
				}
			}
			if (!game.isSpecial(this.getNorthEast(1).getX(), this.getNorthEast(1).getY()) && 
					game.isReachable(this.getNorthEast(1).getX(), this.getNorthEast(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getNorthEast(1).getY()][this.getNorthEast(1).getX()] 
						= this.hitRangeFieldNum;
			}
			break;
		case NORTH:
			for (int i = 1; i <= 2; i++) {
				if (!game.isSpecial(this.getNorth(i).getX(), this.getNorth(i).getY()) && 
						game.isReachable(this.getNorth(i).getX(), this.getNorth(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getNorth(i).getY()][this.getNorth(i).getX()] 
							= this.hitRangeFieldNum;
				}
				if (!game.isSpecial(this.getWest(i).getX(), this.getWest(i).getY()) && 
						game.isReachable(this.getWest(i).getX(), this.getWest(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getWest(i).getY()][this.getWest(i).getX()] 
							= this.hitRangeFieldNum;
				}
			}
			if (!game.isSpecial(this.getNorthWest(1).getX(), this.getNorthWest(1).getY()) && 
					game.isReachable(this.getNorthWest(1).getX(), this.getNorthWest(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getNorthWest(1).getY()][this.getNorthWest(1).getX()] 
						= this.hitRangeFieldNum;
			}
			break;
		case SOUTH:
			for (int i = 1; i <= 2; i++) {
				if (!game.isSpecial(this.getSouth(i).getX(), this.getSouth(i).getY()) && 
						game.isReachable(this.getSouth(i).getX(), this.getSouth(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getSouth(i).getY()][this.getSouth(i).getX()] 
							= this.hitRangeFieldNum;
				}
				if (!game.isSpecial(this.getEast(i).getX(), this.getEast(i).getY()) && 
						game.isReachable(this.getEast(i).getX(), this.getEast(i).getY())) {
					game.hitRangeField[2 * this.weapon + this.side][this.getEast(i).getY()][this.getEast(i).getX()] 
							= this.hitRangeFieldNum;
				}
			}
			if (!game.isSpecial(this.getSouthEast(1).getX(), this.getSouthEast(1).getY()) && 
					game.isReachable(this.getSouthEast(1).getX(), this.getSouthEast(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getSouthEast(1).getY()][this.getSouthEast(1).getX()] 
						= this.hitRangeFieldNum;
			}
			break;
		}
	}
	
	private void setHitRange2() {
		//reset it first
		game.hitRangeField[2 * this.weapon + this.side] = Game.rootHitRangeField;
		
		if (!game.isSpecial(this.getSouthEast(1).getX(), this.getSouthEast(1).getY()) && 
				game.isReachable(this.getSouthEast(1).getX(), this.getSouthEast(1).getY())) {
			game.hitRangeField[2 * this.weapon + this.side][this.getSouthEast(1).getY()][this.getSouthEast(1).getX()] 
					= this.hitRangeFieldNum;
		}
		if (!game.isSpecial(this.getSouthWest(1).getX(), this.getSouthWest(1).getY()) && 
				game.isReachable(this.getSouthWest(1).getX(), this.getSouthWest(1).getY())) {
			game.hitRangeField[2 * this.weapon + this.side][this.getSouthWest(1).getY()][this.getSouthWest(1).getX()] 
					= this.hitRangeFieldNum;
		}
		if (!game.isSpecial(this.getNorthWest(1).getX(), this.getNorthWest(1).getY()) && 
				game.isReachable(this.getNorthWest(1).getX(), this.getNorthWest(1).getY())) {
			game.hitRangeField[2 * this.weapon + this.side][this.getNorthWest(1).getY()][this.getNorthWest(1).getX()] 
					= this.hitRangeFieldNum;
		}
		if (!game.isSpecial(this.getNorthEast(1).getX(), this.getNorthEast(1).getY()) && 
				game.isReachable(this.getNorthEast(1).getX(), this.getNorthEast(1).getY())) {
			game.hitRangeField[2 * this.weapon + this.side][this.getNorthEast(1).getY()][this.getNorthEast(1).getX()] 
					= this.hitRangeFieldNum;
		}
		switch (this.getDirection()) {
		case WEST:
			if (!game.isSpecial(this.getWest(1).getX(), this.getWest(1).getY()) && 
					game.isReachable(this.getWest(1).getX(), this.getWest(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getWest(1).getY()][this.getWest(1).getX()] 
						= this.hitRangeFieldNum;
			}
			if (!game.isSpecial(this.getNorth(1).getX(), this.getNorth(1).getY()) && 
					game.isReachable(this.getNorth(1).getX(), this.getNorth(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getNorth(1).getY()][this.getNorth(1).getX()] 
						= this.hitRangeFieldNum;
			}if (!game.isSpecial(this.getSouth(1).getX(), this.getSouth(1).getY()) && 
					game.isReachable(this.getSouth(1).getX(), this.getSouth(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getSouth(1).getY()][this.getSouth(1).getX()] 
						= this.hitRangeFieldNum;
			}break;
		case EAST:
			if (!game.isSpecial(this.getEast(1).getX(), this.getEast(1).getY()) && 
					game.isReachable(this.getEast(1).getX(), this.getEast(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getEast(1).getY()][this.getEast(1).getX()] 
						= this.hitRangeFieldNum;
			}
			if (!game.isSpecial(this.getNorth(1).getX(), this.getNorth(1).getY()) && 
					game.isReachable(this.getNorth(1).getX(), this.getNorth(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getNorth(1).getY()][this.getNorth(1).getX()] 
						= this.hitRangeFieldNum;
			}if (!game.isSpecial(this.getSouth(1).getX(), this.getSouth(1).getY()) && 
					game.isReachable(this.getSouth(1).getX(), this.getSouth(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getSouth(1).getY()][this.getSouth(1).getX()]
						= this.hitRangeFieldNum;
			}break;
		case NORTH:
			if (!game.isSpecial(this.getWest(1).getX(), this.getWest(1).getY()) && 
					game.isReachable(this.getWest(1).getX(), this.getWest(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getWest(1).getY()][this.getWest(1).getX()] 
						= this.hitRangeFieldNum;
			}
			if (!game.isSpecial(this.getEast(1).getX(), this.getEast(1).getY()) && 
					game.isReachable(this.getEast(1).getX(), this.getEast(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getEast(1).getY()][this.getEast(1).getX()] 
						= this.hitRangeFieldNum;
			}
			if (!game.isSpecial(this.getNorth(1).getX(), this.getNorth(1).getY()) && 
					game.isReachable(this.getNorth(1).getX(), this.getNorth(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getNorth(1).getY()][this.getNorth(1).getX()] 
						= this.hitRangeFieldNum;
			}break;
		case SOUTH:
			if (!game.isSpecial(this.getWest(1).getX(), this.getWest(1).getY()) && 
					game.isReachable(this.getWest(1).getX(), this.getWest(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getWest(1).getX()][this.getWest(1).getY()] 
						= this.hitRangeFieldNum;
			}
			if (!game.isSpecial(this.getEast(1).getX(), this.getEast(1).getY()) && 
					game.isReachable(this.getEast(1).getX(), this.getEast(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getEast(1).getX()][this.getEast(1).getY()] 
						= this.hitRangeFieldNum;
			}
			if (!game.isSpecial(this.getSouth(1).getX(), this.getSouth(1).getY()) && 
					game.isReachable(this.getSouth(1).getX(), this.getSouth(1).getY())) {
				game.hitRangeField[2 * this.weapon + this.side][this.getSouth(1).getX()][this.getSouth(1).getY()] 
						= this.hitRangeFieldNum;
			}break;
		}
	}
	
	/**
	 * to check whether the samurai can hit the hittenSamurai
	 * @param hittenSamurai
	 * @return
	 */
	private boolean canHit(Samurai hittenSamurai) {
		if (hittenSamurai != null) {
			//there is a samurai can be hitten
			int myWeapon = this.weapon;
			int x1 = this.getCurX();
			int y1 = this.getCurY();
			int x2 = hittenSamurai.getCurX();
			int y2 = hittenSamurai.getCurY();

			if (myWeapon == 0) {
				return checkLineHit(x1, y1, x2, y2);
			}else {
				if (x1 == x2 || y1 == y2) {
					//the two samurai is in line
					return checkLineHit(x1, y1, x2, y2);
				}else {
					//the two samurai is in diagonal
					return checkCornerHit(x1, y1, x2, y2);
				}
			}
		}else {
			return false;
		}
		
	}
	
	/**
	 * to check whether a can hit b if a & b are in the same line
	 * and you can hit another across one samurai
	 * @param a Samurai this
	 * @param b Samurai hittenSamurai
	 * @return can / cannot
	 */
	private boolean checkLineHit(int x1, int y1, int x2, int y2) {
		if (x1 ==x2) {
			//ensure y1 < y2
			if (y1 > y2) {
				int temp = y1;
				y1 = y2;
				y2 = temp;
			}
			for (int i = y1 + 1; i <= y2; i++) {
				if (i == y2) {
					return true;
				}
				if (game.isSpecial(x1, i) == true) {
					break;
				}
			}
			return false;
		}
		if (y1 == y2) {
			//ensure x1 < x2
			if (x1 > x2) {
				int temp = x1;
				x1 = x2;
				x2 = temp;
			}
			for (int i = x1 + 1; i <= x2; i++) {
				if (i == x2) {
					return true;
				}
				if (game.isSpecial(i, y1) == true) {
					break;
				}
			}
			return false;
		}
		return false;
	}

	/**
	 * to check whether a can hit b if a & b are in diagonal, 
	 * and most importantly the offset between is 2
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean checkCornerHit(int x1, int y1, int x2, int y2) {
		if (game.isSpecial(x1, y2) && game.isSpecial(x2, y1)) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * occupy all blocks that are able to be attacked by this samurai
	 */
	private void occupy() {
		int[][] thisHitRangeField = game.hitRangeField[2 * weapon + side];
		
		for (int i = 0; i < thisHitRangeField.length; i++) {
			for (int j = 0; j < thisHitRangeField[0].length; j++) {
				if (thisHitRangeField[i][j] == this.hitRangeFieldNum) {
					game.scoreField[i][j] = this.manor;
				}
			}
		}
	}
	
	/**
	 * @param action
	 * @return the hittenSamurai reference
	 */
	private Samurai getHittenSamurai(int action) {
		int myWeapon = this.weapon;
		if (action == 11) {
			switch (myWeapon) {
			case 0:return this.hittenSamuraiOf0(action);
			case 1:return this.hittenSamurai11Of1();
			case 2:return this.hittenSamurai11Of2();
			}
		}
		if (action == 12) {
			switch (myWeapon) {
			case 0:return this.hittenSamuraiOf0(action);
			case 1:return this.hittenSamurai12Of1();
			case 2:return this.hittenSamurai12Of2();
			}
		}
		if (action == 13) {
			switch (myWeapon) {
			case 0:return this.hittenSamuraiOf0(action);
			case 1:return this.hittenSamurai13Of1();
			case 2:return this.hittenSamurai13Of2();
			}
		}
		if (action == 14) {
			switch (myWeapon) {
			case 1:return this.hittenSamurai14Of1();
			case 2:return this.hittenSamurai14Of2();
			}
		}
		if (action == 15) {
			switch (myWeapon) {
			case 1:return this.hittenSamurai15Of1();
			case 2:return this.hittenSamurai15Of2();
			}
		}
		if (action == 16) {
			return this.hittenSamurai16Of2();
		}
		if (action == 17) {
			return this.hittenSamurai17Of2();
		}
		return null;
	}

	/**
	 * @param action
	 * @return
	 */
	private Samurai hittenSamuraiOf0(int action) {
		Samurai hittenSamurai = null;
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:hittenSamurai = game.isSamurai(getWest(action -10).getX(), getWest(action - 10).getY());break;
		case EAST:hittenSamurai = game.isSamurai(getEast(action -10).getX(), getEast(action - 10).getY());break;
		case NORTH:hittenSamurai = game.isSamurai(getNorth(action -10).getX(), getNorth(action - 10).getY());break;
		case SOUTH:hittenSamurai = game.isSamurai(getSouth(action -10).getX(), getSouth(action - 10).getY());break;
		}
		return hittenSamurai;
	}

	private Samurai hittenSamurai11Of1() {
		return hittenSamuraiOf0(11);
	}

	private Samurai hittenSamurai11Of2() {
		Samurai hittenSamurai = null;
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:hittenSamurai = game.isSamurai(getNorthEast(1).getX(), getNorthEast(1).getY());break;
		case EAST:hittenSamurai = game.isSamurai(getSouthWest(1).getX(), getSouthWest(1).getY());break;
		case NORTH:hittenSamurai = game.isSamurai(getSouthEast(1).getX(),getSouthEast(1).getY());break;
		case SOUTH:hittenSamurai = game.isSamurai(getNorthWest(1).getX(), getNorthWest(1).getY());break;
		}
		return hittenSamurai;
	}

	private Samurai hittenSamurai12Of1() {
		return hittenSamuraiOf0(12);
	}

	private Samurai hittenSamurai12Of2() {
		Samurai hittenSamurai = null;
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:hittenSamurai = game.isSamurai(getNorth(1).getX(), getNorth(1).getY());break;
		case EAST:hittenSamurai = game.isSamurai(getSouth(1).getX(), getSouth(1).getY());break;
		case NORTH:hittenSamurai = game.isSamurai(getEast(1).getX(), getEast(1).getY());break;
		case SOUTH:hittenSamurai = game.isSamurai(getWest(1).getX(), getWest(1).getY());break;
		}
		return hittenSamurai;
	}

	private Samurai hittenSamurai13Of1() {
		Samurai hittenSamurai = null;
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:hittenSamurai = game.isSamurai(getSouthWest(1).getX(),getSouthWest(1).getY());break;
		case EAST:hittenSamurai = game.isSamurai(getNorthEast(1).getX(), getNorthEast(1).getY());break;
		case NORTH:hittenSamurai = game.isSamurai(getNorthWest(1).getX(),getNorthWest(1).getY());break;
		case SOUTH:hittenSamurai = game.isSamurai(getSouthEast(1).getX(),getSouthEast(1).getY());break;
		}
		return hittenSamurai;
	}


	private Samurai hittenSamurai13Of2() {
		Samurai hittenSamurai = null;
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:hittenSamurai = game.isSamurai(getNorthWest(1).getX(),getNorthWest(1).getY());break;
		case EAST:hittenSamurai = game.isSamurai(getSouthEast(1).getX(),getSouthEast(1).getY());break;
		case NORTH:hittenSamurai = game.isSamurai(getNorthEast(1).getX(), getNorthEast(1).getY());break;
		case SOUTH:hittenSamurai = game.isSamurai(getSouthWest(1).getX(),getSouthWest(1).getY());break;
		}
		return hittenSamurai;
	}

	private Samurai hittenSamurai14Of1() {
		Samurai hittenSamurai = null;
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:hittenSamurai = game.isSamurai(getSouth(1).getX(), getSouth(1).getY());break;
		case EAST:hittenSamurai = game.isSamurai(getNorth(1).getX(), getNorth(1).getY());break;
		case NORTH:hittenSamurai = game.isSamurai(getWest(1).getX(), getWest(1).getY());break;
		case SOUTH:hittenSamurai = game.isSamurai(getEast(1).getX(), getEast(1).getY());break;
		}
		return hittenSamurai;
	}

	private Samurai hittenSamurai14Of2() {
		return hittenSamurai11Of1();
	}

	private Samurai hittenSamurai15Of1() {
		Samurai hittenSamurai = null;
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:hittenSamurai = game.isSamurai(getSouth(2).getX(), getSouth(2).getY());break;
		case EAST:hittenSamurai = game.isSamurai(getNorth(2).getX(), getNorth(2).getY());break;
		case NORTH:hittenSamurai = game.isSamurai(getWest(2).getX(), getWest(2).getY());break;
		case SOUTH:hittenSamurai = game.isSamurai(getEast(2).getX(), getEast(2).getY());break;
		}
		return hittenSamurai;
	}

	private Samurai hittenSamurai15Of2() {
		return hittenSamurai13Of1();
	}

	private Samurai hittenSamurai16Of2() {
		return hittenSamurai14Of1();
	}

	private Samurai hittenSamurai17Of2() {
		Samurai hittenSamurai = null;
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:hittenSamurai = game.isSamurai(getSouthEast(1).getX(), getSouthEast(1).getY());break;
		case EAST:hittenSamurai = game.isSamurai(getNorthWest(1).getX(), getNorthWest(1).getY());break;
		case NORTH:hittenSamurai = game.isSamurai(getSouthEast(1).getX(), getSouthEast(1).getY());break;
		case SOUTH:hittenSamurai = game.isSamurai(getNorthEast(1).getX(), getNorthEast(1).getY());break;
		}
		return hittenSamurai;
	}
	
	/**
	 * get the Point of different direction
	 * to help the method hittenSamuraiXXOfX()
	 * @return
	 */
	private Point getNorth(int offset) {
		return new Point(this.getCurX(), this.getCurY() - offset);
	}

	private Point getSouth(int offset) {
		return new Point(this.getCurX(), this.getCurY() + offset);
	}

	private Point getWest(int offset) {
		return new Point(this.getCurX() - offset, this.getCurY());
	}

	private Point getEast(int offset) {
		return new Point(this.getCurX() + offset, this.getCurY());
	}

	private Point getNorthEast(int offset) {
		return new Point(this.getCurX() + offset, this.getCurY() - offset);
	}

	private Point getNorthWest(int offset) {
		return new Point(this.getCurX() - offset, this.getCurY() - offset);
	}

	private Point getSouthEast(int offset) {
		return new Point(this.getCurX() + offset, this.getCurY() + offset);
	}

	private Point getSouthWest(int offset) {
		return new Point(this.getCurX() - offset, this.getCurY() + offset);
	}

	public abstract void drawBlood(Graphics g);
	public abstract void drawPower(Graphics g);

	/**
	 * update the informations of this samurai
	 */
	public void updateSamurai(){

	}

	public void updateBlood(){

	}

	public void updatePower(){
		setPower();
	}
}
