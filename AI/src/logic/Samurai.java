package logic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class Samurai {
	private int homeX, homeY;			//大本�?
	private int curX, curY;					//现在的位置，表示为地图上的区块x行y�?
	private int power;						//体力
	private Direction direction;			//方向
	private int lifespan;					//寿命
	private int score;						//分数
	private boolean hidden;					//隐身状�??
	private int side;						//队伍ID�?0为TeamCap�?1为TeamIronMan�?
	private int weapon;						//武士ID，用武器标识
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
		//��ʼ��ʱ��blood��power��ͼƬ���ؽ�ȥ
		try {
			bloodImg = ImageIO.read(this.getClass().getResource("/cn/picture/A.png"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		try {
			powerImg = ImageIO.read(this.getClass().getResource("/cn/picture/A.png"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
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
	 * 返回此武士是否已经死�?
	 */
	public boolean isKilled() {
		return this.getLifeSpan() == 0;
	}

	/**
	 * 做行�?
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
		//以下是几种攻击方�?
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
	 * 改变方向，旋�?
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
	 * 向面前方向移�?
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
	 * check这个位置上是否有武士存在，并返回这个位置武士的饮�?
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
	 * 被攻击，寿命减一
	 */
	private void hit() {
		this.setLifeSpan(this.getLifeSpan() - 1);
	}

	
	/**
	 * �?查此行动是否可行
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
			//先假设已经移动，再判断是否合�?
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
						curY == game.samurais[i].getHomeY())){		//不能移动到别人的大本�?
					return false;
				}

				if ((curX == game.samurais[i].getCurX() &&
						curY == game.samurais[i].getCurY())){		//隐身状�?�下，武士可以移动到已经有武士存在的区块
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
			if (this.getHidden() == true){			//隐身状�??
				return false;
			}else  {
				return true;
			}

		}
		if (action == 7){
			if (this.getHidden() == false){			//非隐身状�?
				return false;
			}
			for (int i = 0; i < Game.PLAYER_NUM; ++i){		//若有�?个处于非隐身状�?�的武士和它在同�?个区块，则不能现�?
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
	
	//������ʿ
	public  abstract void drawSamurai(Graphics g,JPanel i);
	//����Ѫ��
	public abstract void drawBlood(Graphics g,JPanel i);
	//��������
	public abstract void drawPower(Graphics g,JPanel i);
	
	/*
	 *���ڸ���λ����һ�����߼���������д��ֱ�Ӽ̳м��� 
	 */
	//����Samurai��λ��
	public void updateSamurai(){
		
	}
	//����Blood
	public void updateBlood(){
		
	}
	//��������
	public void updatePower(){
		
	}

}
