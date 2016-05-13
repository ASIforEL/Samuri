package cn.edu.nju.logic;

import java.awt.Graphics;

public class BlackWidow extends Samurai{

	
	public BlackWidow(Game game) {
		super(game);
		setSide(1);
		setWeapon(1);
		setLifeSpan(2);
		setDirection(Direction.WEST);

		setHomeX(11);
		setHomeY(6);
		this.setCurX(this.getHomeX());
		this.setCurY(this.getHomeY());
		System.out.println("Initializing the BlackWidow");

		samuraiField[getHomeX()][getHomeY()] = 55;
	}

	@Override
	public void drawBlood(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getLifeSpan(); i++) {
			g.drawImage(bloodImg, (960-250-25)-i*40, 110, null);
		}
	}

	@Override
	public void drawPower(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getPower(); i++) {
			g.drawImage(powerImg,  (960-250-25)-i*30, 140, null);
		}
	}
}
