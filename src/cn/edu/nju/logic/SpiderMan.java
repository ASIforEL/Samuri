package cn.edu.nju.logic;

import java.awt.Graphics;

import javax.swing.JPanel;

public class SpiderMan extends Samurai{

	
	public SpiderMan() {
		setSide(1);
		setWeapon(2);
		setLifeSpan(2);
		setDirection(Direction.EAST);

		setHomeX(10);
		setHomeY(12);
	}

	@Override
	public void drawSamurai(Graphics g, JPanel i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawBlood(Graphics g, JPanel i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawPower(Graphics g, JPanel i) {
		// TODO Auto-generated method stub
		
	}
}
