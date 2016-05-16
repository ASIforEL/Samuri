package cn.edu.nju.logic;

import java.awt.Graphics;

import cn.edu.nju.panels.GameField;

public class Hulk extends Samurai{

	
	public Hulk(Game game, GameField gameField) {
		super(game, gameField);
		setSide(0);
		setWeapon(1);
		setLifeSpan(4);
		setDirection(Direction.EAST);
		
		setHomeX(0);
		setHomeY(5);
		this.setCurX(this.getHomeX());
		this.setCurY(this.getHomeY());
		System.out.println("Initializing the Hulk");
		
		homeSamuraiFieldNum = Configure.HULK * 100 + 2;
		hitRangeFieldNum = Configure.HULK_ATTACK;
		manor = this.getSide() + 1;

		samuraiField[getHomeY()][getHomeX()] = samuraiFieldNum;
	}

	@Override
	public void drawBlood(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getLifeSpan(); i++) {
			g.drawImage(bloodImg, 250+i*40, 110, null);
		}
	}

	@Override
	public void drawPower(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getPower(); i++) {
			g.drawImage(powerImg, 250+i*30, 140, null);
		}
	}
}
