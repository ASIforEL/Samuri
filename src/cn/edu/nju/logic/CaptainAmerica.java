package cn.edu.nju.logic;

import java.awt.Graphics;

import javax.swing.JPanel;

public class CaptainAmerica extends Samurai{


	public CaptainAmerica() {
		setSide(0);
		setWeapon(0);
		setLifeSpan(2);
		setDirection(Direction.WEST);
		
		setHomeX(3);
		setHomeY(1);
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
