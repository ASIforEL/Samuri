package cn.edu.nju.logic;

import java.awt.Graphics;

import cn.edu.nju.panels.GameField;

public class IronMan extends Samurai{

	
	public IronMan(Game game, GameField gameField) {
		super(game, gameField);
		setSide(1);
		setWeapon(0);
		setLifeSpan(4);
		setDirection(Direction.WEST);

		setHomeX(11);
		setHomeY(3);
		this.setCurX(this.getHomeX());
		this.setCurY(this.getHomeY());
		System.out.println("Initializing the IronMan");
		
		homeSamuraiFieldNum = Configure.IRON_MAN * 100 + 1;
		hitRangeFieldNum = Configure.IRON_MAN_ATTACK;
		manor = this.getSide() + 1;

		samuraiField[getHomeY()][getHomeX()] = samuraiFieldNum;
	}

	@Override
	public void drawBlood(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getLifeSpan(); i++) {
			g.drawImage(bloodImg, (960-250-25)-i*40, 20, null);
		}
	}

	@Override
	public void drawPower(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getPower(); i++) {
			g.drawImage(powerImg,  (960-250-25)-i*30, 50, null);
		}
	}
}
