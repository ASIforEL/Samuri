package logic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class Samurai {
	private int homeX, homeY;			//å¤§æœ¬è?
	private int curX, curY;					//çŽ°åœ¨çš„ä½ç½®ï¼Œè¡¨ç¤ºä¸ºåœ°å›¾ä¸Šçš„åŒºå—xè¡Œyåˆ?
	private int power;						//ä½“åŠ›
	private Direction direction;			//æ–¹å‘
	private int lifespan;					//å¯¿å‘½
	private int score;						//åˆ†æ•°
	private boolean hidden;					//éšèº«çŠ¶æ??
	private int side;						//é˜Ÿä¼IDï¼?0ä¸ºTeamCapï¼?1ä¸ºTeamIronManï¼?
	private int weapon;						//æ­¦å£«IDï¼Œç”¨æ­¦å™¨æ ‡è¯†
	private boolean isHit;
	public Game game;
	protected Image samuraiImg;
	protected Image bloodImg;
	private Image powerImg;
	
	protected int initBlood_X;
	protected int initBlood_Y;
	protected int initPower_X;
	protected int initPower_Y;
	
	
	public Samurai(){
//		this.homeX = 0;
//		this.homeY = 0;
		//³õÊ¼»¯Ê±½«bloodºÍpowerµÄÍ¼Æ¬¼ÓÔØ½øÈ¥
		try {
			bloodImg = ImageIO.read(this.getClass().getResource("/cn/picture/A.png"));
		} catch (IOException e) {
			// TODO ×Ô¶¯Éú³ÉµÄ catch ¿é
			e.printStackTrace();
		}
		try {
			powerImg = ImageIO.read(this.getClass().getResource("/cn/picture/A.png"));
		} catch (IOException e) {
			// TODO ×Ô¶¯Éú³ÉµÄ catch ¿é
			e.printStackTrace();
		}
		
		this.curX = this.homeX;
		this.curY = this.homeY;
		this.power = 7;
		this.score = 0;
		this.hidden = false;
		this.isHit = false;
	}


	//getters & setters
	public int getHomeX() {
		return this.homeX;
	}

	public int getHomeY() {
		return this.homeY;
	}

	public int getCurX() {
		return this.curX;
	}

	public int getCurY() {
		return this.curY;
	}

	public int getPower() {
		return this.power;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public int getLifeSpan() {
		return this.lifespan;
	}

	public int getScore() {
		return this.score;
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

	public void setHomeX(int info) {
		this.homeX = info;
	}

	public void setHomeY(int info) {
		this.homeY = info;
	}

	public void setCurX(int info) {
		this.curX = info;
	}

	public void setCurY(int info) {
		this.curY = info;
	}

	public void setPower(int info) {
		this.power = info;
	}

	public void setDirection(Direction info) {
		this.direction = info;
	}

	public void setLifeSpan(int info){
		this.lifespan = info;
	}

	public void setScore(int info) {
		this.score = info;
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



	

	/**
	 * è¿”å›žæ­¤æ­¦å£«æ˜¯å¦å·²ç»æ­»äº?
	 */
	public boolean isKilled() {
		return this.getLifeSpan() == 0;
	}

	/**
	 * åšè¡Œä¸?
	 * @param action
	 */
	public void doAction(int action) {
		assert this.isValid(action);

		if (action >= 1 && action <= 4){
			this.rotate(action);
		}
		if (action == 5){
			moveForward();
			this.power -= 2;
		}
		if (action == 6){
			this.setHidden(true);
			this.power -= 1;
		}
		if (action == 7){
			this.setHidden(false);
			this.power -= 1;
		}
		//ä»¥ä¸‹æ˜¯å‡ ç§æ”»å‡»æ–¹å¼?
		if (action == 11) {
			int myWeapon = this.weapon;
			switch (myWeapon) {
			case 0:actionOf0(action);break;
			case 1:action11Of1();break;
			case 2:action11Of2();break;
			}
		}
		if (action == 12) {
			int myWeapon = this.weapon;
			switch (myWeapon) {
			case 0:actionOf0(action);break;
			case 1:action12Of1();break;
			case 2:action12Of2();break;
			}
		}
		if (action == 13) {
			int myWeapon = this.weapon;
			switch (myWeapon) {
			case 0:actionOf0(action);break;
			case 1:action13Of1();break;
			case 2:action13Of2();break;
			}
		}
		if (action == 14) {
			int myWeapon = this.weapon;
			switch (myWeapon) {
			case 0:actionOf0(action);break;
			case 1:action14Of1();break;
			case 2:action14Of2();break;
			}
		}
		if (action == 15) {
			int myWeapon = this.weapon;
			switch (myWeapon) {
			case 1:action15Of1();break;
			case 2:action15Of2();break;
			}
		}
		if (action == 16) {
			int myWeapon = this.weapon;
			switch (myWeapon) {
			case 2:action16Of2();break;
			}
		}
		if (action == 17) {
			int myWeapon = this.weapon;
			switch (myWeapon) {
			case 2:action16Of2();break;
			}
		}
	}
	
	/**
	 * æ”¹å˜æ–¹å‘ï¼Œæ—‹è½?
	 * @param direction
	 * @return
	 */
	public void rotate(int direction){
		switch (direction) {
		case 1:this.direction = Direction.WEST;break;
		case 2:this.direction = Direction.EAST;break;
		case 3:this.direction = Direction.NORTH;break;
		case 4:this.direction = Direction.SOUTH;break;
		}
	}

	/**
	 * å‘é¢å‰æ–¹å‘ç§»åŠ?
	 */
	public void moveForward() {
		switch (this.direction) {
		case WEST:this.setCurY(this.getCurY() - 1);break;
		case EAST:this.setCurY(this.getCurY() + 1);break;
		case NORTH:this.setCurX(this.getCurX() - 1);break;
		case SOUTH:this.setCurX(this.getCurX() + 1);break;
		}
	}
	
	private void actionOf0(int action) {
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:
			Samurai hittenSamuraiW = isSamurai(this.getCurX(), this.getCurY() - action -10);
			if (hittenSamuraiW != null) {
				hittenSamuraiW.hit();
			}
			break;
		case EAST:
			Samurai hittenSamuraiE = isSamurai(this.getCurX(), this.getCurY() + action -10);
			if (hittenSamuraiE != null) {
				hittenSamuraiE.hit();
			}
			break;
		case NORTH:
			Samurai hittenSamuraiN = isSamurai(this.getCurX() - action -10, this.getCurY());
			if (hittenSamuraiN != null) {
				hittenSamuraiN.hit();
			}
			break;
		case SOUTH:
			Samurai hittenSamuraiS = isSamurai(this.getCurX() + action -10, this.getCurY());
			if (hittenSamuraiS != null) {
				hittenSamuraiS.hit();
			}
			break;
		}
	}


	private void action11Of1() {
		actionOf0(11);
	}
	
	private void action11Of2() {
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:
			Samurai hittenSamuraiW = isSamurai(this.getCurX() - 1, this.getCurY() + 1);
			if (hittenSamuraiW != null) {
				hittenSamuraiW.hit();
			}
			break;
		case EAST:
			Samurai hittenSamuraiE = isSamurai(this.getCurX() + 1, this.getCurY() - 1);
			if (hittenSamuraiE != null) {
				hittenSamuraiE.hit();
			}
			break;
		case NORTH:
			Samurai hittenSamuraiN = isSamurai(this.getCurX() + 1, this.getCurY() + 1);
			if (hittenSamuraiN != null) {
				hittenSamuraiN.hit();
			}
			break;
		case SOUTH:
			Samurai hittenSamuraiS = isSamurai(this.getCurX() - 1, this.getCurY() - 1);
			if (hittenSamuraiS != null) {
				hittenSamuraiS.hit();
			}
			break;
		}
	}
	
	private void action12Of1() {
		actionOf0(12);
	}
	
	private void action12Of2() {
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:
			Samurai hittenSamuraiW = isSamurai(this.getCurX() - 1, this.getCurY());
			if (hittenSamuraiW != null) {
				hittenSamuraiW.hit();
			}
			break;
		case EAST:
			Samurai hittenSamuraiE = isSamurai(this.getCurX() + 1, this.getCurY());
			if (hittenSamuraiE != null) {
				hittenSamuraiE.hit();
			}
			break;
		case NORTH:
			Samurai hittenSamuraiN = isSamurai(this.getCurX(), this.getCurY() + 1);
			if (hittenSamuraiN != null) {
				hittenSamuraiN.hit();
			}
			break;
		case SOUTH:
			Samurai hittenSamuraiS = isSamurai(this.getCurX(), this.getCurY() - 1);
			if (hittenSamuraiS != null) {
				hittenSamuraiS.hit();
			}
			break;
		}
	}
	
	private void action13Of1() {
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:
			Samurai hittenSamuraiW = isSamurai(this.getCurX() + 1, this.getCurY() - 1);
			if (hittenSamuraiW != null) {
				hittenSamuraiW.hit();
			}
			break;
		case EAST:
			Samurai hittenSamuraiE = isSamurai(this.getCurX() - 1, this.getCurY() + 1);
			if (hittenSamuraiE != null) {
				hittenSamuraiE.hit();
			}
			break;
		case NORTH:
			Samurai hittenSamuraiN = isSamurai(this.getCurX() - 1, this.getCurY() - 1);
			if (hittenSamuraiN != null) {
				hittenSamuraiN.hit();
			}
			break;
		case SOUTH:
			Samurai hittenSamuraiS = isSamurai(this.getCurX() + 1, this.getCurY() + 1);
			if (hittenSamuraiS != null) {
				hittenSamuraiS.hit();
			}
			break;
		}
	}
	
	
	private void action13Of2() {
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:
			Samurai hittenSamuraiW = isSamurai(this.getCurX() - 1, this.getCurY() - 1);
			if (hittenSamuraiW != null) {
				hittenSamuraiW.hit();
			}
			break;
		case EAST:
			Samurai hittenSamuraiE = isSamurai(this.getCurX() + 1, this.getCurY() + 1);
			if (hittenSamuraiE != null) {
				hittenSamuraiE.hit();
			}
			break;
		case NORTH:
			Samurai hittenSamuraiN = isSamurai(this.getCurX() - 1, this.getCurY() + 1);
			if (hittenSamuraiN != null) {
				hittenSamuraiN.hit();
			}
			break;
		case SOUTH:
			Samurai hittenSamuraiS = isSamurai(this.getCurX() + 1, this.getCurY() - 1);
			if (hittenSamuraiS != null) {
				hittenSamuraiS.hit();
			}
			break;
		}
	}
	
	private void action14Of1() {
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:
			Samurai hittenSamuraiW = isSamurai(this.getCurX() - 1, this.getCurY());
			if (hittenSamuraiW != null) {
				hittenSamuraiW.hit();
			}
			break;
		case EAST:
			Samurai hittenSamuraiE = isSamurai(this.getCurX() + 1, this.getCurY());
			if (hittenSamuraiE != null) {
				hittenSamuraiE.hit();
			}
			break;
		case NORTH:
			Samurai hittenSamuraiN = isSamurai(this.getCurX(), this.getCurY() - 1);
			if (hittenSamuraiN != null) {
				hittenSamuraiN.hit();
			}
			break;
		case SOUTH:
			Samurai hittenSamuraiS = isSamurai(this.getCurX(), this.getCurY() + 1);
			if (hittenSamuraiS != null) {
				hittenSamuraiS.hit();
			}
			break;
		}
	}
	
	private void action14Of2() {
		action11Of1();
	}
	
	private void action15Of1() {
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:
			Samurai hittenSamuraiW = isSamurai(this.getCurX() - 2, this.getCurY());
			if (hittenSamuraiW != null) {
				hittenSamuraiW.hit();
			}
			break;
		case EAST:
			Samurai hittenSamuraiE = isSamurai(this.getCurX() + 2, this.getCurY());
			if (hittenSamuraiE != null) {
				hittenSamuraiE.hit();
			}
			break;
		case NORTH:
			Samurai hittenSamuraiN = isSamurai(this.getCurX(), this.getCurY() - 2);
			if (hittenSamuraiN != null) {
				hittenSamuraiN.hit();
			}
			break;
		case SOUTH:
			Samurai hittenSamuraiS = isSamurai(this.getCurX(), this.getCurY() + 2);
			if (hittenSamuraiS != null) {
				hittenSamuraiS.hit();
			}
			break;
		}
	}
	
	private void action15Of2() {
		action13Of1();
	}
	
	private void action16Of2() {
		action14Of1();
	}
	
	private void action17Of2() {
		Direction thisDirection = this.direction;
		switch (thisDirection) {
		case WEST:
			Samurai hittenSamuraiW = isSamurai(this.getCurX() - 1, this.getCurY() - 1);
			if (hittenSamuraiW != null) {
				hittenSamuraiW.hit();
			}
			break;
		case EAST:
			Samurai hittenSamuraiE = isSamurai(this.getCurX() + 1, this.getCurY() + 1);
			if (hittenSamuraiE != null) {
				hittenSamuraiE.hit();
			}
			break;
		case NORTH:
			Samurai hittenSamuraiN = isSamurai(this.getCurX() + 1, this.getCurY() - 1);
			if (hittenSamuraiN != null) {
				hittenSamuraiN.hit();
			}
			break;
		case SOUTH:
			Samurai hittenSamuraiS = isSamurai(this.getCurX() - 1, this.getCurY() + 1);
			if (hittenSamuraiS != null) {
				hittenSamuraiS.hit();
			}
			break;
		}
	}
	

	/**
	 * checkè¿™ä¸ªä½ç½®ä¸Šæ˜¯å¦æœ‰æ­¦å£«å­˜åœ¨ï¼Œå¹¶è¿”å›žè¿™ä¸ªä½ç½®æ­¦å£«çš„é¥®ç”?
	 * @param x
	 * @param y
	 * @return
	 */
	private Samurai isSamurai(int x, int y) {
		for (int i = 0; i < game.samurais.length; i++) {
			if (game.samurais[i].getCurX() == x && game.samurais[i].getCurY() == y) {
				return game.samurais[i];
			}
		}
		return null;
	}

	/**
	 * è¢«æ”»å‡»ï¼Œå¯¿å‘½å‡ä¸€
	 */
	private void hit() {
		this.setLifeSpan(this.getLifeSpan() - 1);
	}

	
	/**
	 * æ£?æŸ¥æ­¤è¡ŒåŠ¨æ˜¯å¦å¯è¡Œ
	 * @param action
	 * @return
	 */
	private boolean isValid(int action) {
		int curX = this.getCurX();
		int curY = this.getCurY();

		if (action >= 0 && action <= 4){
			return true;
		}
		if (action == 5){
			//å…ˆå‡è®¾å·²ç»ç§»åŠ¨ï¼Œå†åˆ¤æ–­æ˜¯å¦åˆç?
			switch (this.getDirection()) {
			case WEST:this.setCurY(this.getCurY() - 1);break;
			case EAST:this.setCurY(this.getCurY() + 1);break;
			case NORTH:this.setCurX(this.getCurX() - 1);break;
			case SOUTH:this.setCurX(this.getCurX() + 1);break;
			}

			if (curX < 0 || game.width <= curX || curY < 0 || game.height <= curY){	
				return false;		
			}
			for (int i = 0; i < Game.PLAYER_NUM; i++){
				if (i != this.weapon && 
						(curX == game.samurais[i].getHomeX() &&
						curY == game.samurais[i].getHomeY())){		//ä¸èƒ½ç§»åŠ¨åˆ°åˆ«äººçš„å¤§æœ¬è?
					return false;
				}

				if ((curX == game.samurais[i].getCurX() &&
						curY == game.samurais[i].getCurY())){		//éšèº«çŠ¶æ?ä¸‹ï¼Œæ­¦å£«å¯ä»¥ç§»åŠ¨åˆ°å·²ç»æœ‰æ­¦å£«å­˜åœ¨çš„åŒºå—
//					if (this.getHidden() == true) {
//						return true;
//					}
//					if (this.getHidden() == false) {
//						return false;
//					}
					
					return this.getHidden() == true;
				}
			}
			return true;
		}
		if (action == 6){
			if (this.getHidden() == true){			//éšèº«çŠ¶æ??
				return false;
			}else  {
				return true;
			}

		}
		if (action == 7){
			if (this.getHidden() == false){			//éžéšèº«çŠ¶æ€?
				return false;
			}
			for (int i = 0; i < Game.PLAYER_NUM; ++i){		//è‹¥æœ‰ä¸?ä¸ªå¤„äºŽéžéšèº«çŠ¶æ?çš„æ­¦å£«å’Œå®ƒåœ¨åŒä¸?ä¸ªåŒºå—ï¼Œåˆ™ä¸èƒ½çŽ°èº?
				Samurai other = game.samurais[i];
				if (other.getHidden() == false && 
						(other.getCurX() == curX && other.getCurY() == curY)){
					return false;
				}
			}
			return true;
		}

		System.exit(-1);
		return false;	
	}
	
	//»æÖÆÎäÊ¿
	public  abstract void drawSamurai(Graphics g,JPanel i);
	//»æÖÆÑªÁ¿
	public abstract void drawBlood(Graphics g,JPanel i);
	//»æÖÆÄÜÁ¿
	public abstract void drawPower(Graphics g,JPanel i);
	
	/*
	 *ÓÉÓÚ¸üÐÂÎ»ÖÃÊÇÒ»ÑùµÄÂß¼­£¬Ôò²»ÓÃÖØÐ´£¬Ö±½Ó¼Ì³Ð¼´¿É 
	 */
	//¸üÐÂSamuraiµÄÎ»ÖÃ
	public void updateSamurai(){
		
	}
	//¸üÐÂBlood
	public void updateBlood(){
		
	}
	//¸üÐÂÄÜÁ¿
	public void updatePower(){
		
	}

}
