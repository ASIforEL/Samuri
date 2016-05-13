package cn.edu.nju.logic;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Hulk extends Samurai{

	
	public Hulk() {
		setSide(0);
		setWeapon(1);
		setLifeSpan(4);
		setDirection(Direction.WEST);
		
		setHomeX(6);
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
