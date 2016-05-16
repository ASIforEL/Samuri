package cn.edu.nju.logic;

import java.awt.Graphics;

import cn.edu.nju.panels.GameField;

public class CaptainAmerica extends Samurai{

	
	public CaptainAmerica(Game game, GameField gameField) {
		super(game, gameField);
		setSide(0);
		setWeapon(0);
		setLifeSpan(2);
		setDirection(Direction.EAST);
		
		setHomeX(0);
		setHomeY(2);
		this.setCurX(this.getHomeX());
		this.setCurY(this.getHomeY());
		System.out.println("Initializing the Cap");
		
		homeSamuraiFieldNum = Configure.CAPTAIN_AMERICA * 100 + 2;
		hitRangeFieldNum = Configure.CAP_ATTACK;
		manor = this.getSide() + 1;
		
		samuraiField[getHomeY()][getHomeX()] = samuraiFieldNum;
	}

	@Override
	public void drawBlood(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getLifeSpan(); i++) {
			g.drawImage(bloodImg, 250+i*40, 20, null);
		}
	}

	@Override
	public void drawPower(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getPower(); i++) {
			g.drawImage(powerImg, 250+i*30, 50, null);
		}
	}
}
