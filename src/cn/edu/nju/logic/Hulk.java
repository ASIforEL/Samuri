package cn.edu.nju.logic;

import java.awt.Graphics;

public class Hulk extends Samurai{

	
	public Hulk(Game game) {
		super(game);
		setSide(0);
		setWeapon(1);
		setLifeSpan(4);
		setDirection(Direction.EAST);
		
		setHomeX(0);
		setHomeY(5);
		this.setCurX(this.getHomeX());
		this.setCurY(this.getHomeY());
		System.out.println("Initializing the Hulk");

		samuraiFieldNum = Configure.HULK;
		samuraiFiledAttackNum = Configure.HULK_ATTACK;

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
