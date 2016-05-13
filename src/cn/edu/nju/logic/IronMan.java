package cn.edu.nju.logic;

import java.awt.Graphics;

import javax.swing.JPanel;

public class IronMan extends Samurai{

	
	public IronMan() {
		setSide(1);
		setWeapon(0);
		setLifeSpan(4);
		setDirection(Direction.EAST);

		setHomeX(3);
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
