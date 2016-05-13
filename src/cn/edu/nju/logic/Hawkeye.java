package cn.edu.nju.logic;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Hawkeye extends Samurai {

	
	public Hawkeye(Game game) {
		super(game);
		setSide(0);
		setWeapon(2);
		setLifeSpan(2);
		setDirection(Direction.EAST);

		setHomeX(0);
		setHomeY(8);
		this.setCurX(this.getHomeX());
		this.setCurY(this.getHomeY());
		System.out.println("Initializing the Hawkeye");

		samuraiField[getHomeX()][getHomeY()] = 33;
	}

	@Override
	public void drawBlood(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getLifeSpan(); i++) {
			g.drawImage(bloodImg, 250+i*40, 200, null);
		}
	}

	@Override
	public void drawPower(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getPower(); i++) {
			g.drawImage(powerImg, 250+i*30, 230, null);
		}
	}
}
