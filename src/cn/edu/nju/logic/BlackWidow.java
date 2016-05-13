package cn.edu.nju.logic;

import java.awt.Graphics;

import javax.swing.JPanel;

public class BlackWidow extends Samurai{

	
	public BlackWidow() {
		setSide(1);
		setWeapon(1);
		setLifeSpan(2);
		setDirection(Direction.EAST);

		setHomeX(7);
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
