package cn.edu.nju.logic;

import java.awt.Graphics;

public class IronMan extends Samurai{

	
	public IronMan(Game game) {
		super(game);
		setSide(1);
		setWeapon(0);
		setLifeSpan(4);
		setDirection(Direction.WEST);

		setHomeX(11);
		setHomeY(3);
		this.setCurX(this.getHomeX());
		this.setCurY(this.getHomeY());
		System.out.println("Initializing the IronMan");

		samuraiFieldNum = Configure.IRON_MAN;
		samuraiFiledAttackNum = Configure.IRON_MAN_ATTACK;

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
