package cn.edu.nju.logic;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Hawkeye extends Samurai {

	
	public Hawkeye() {
		setSide(0);
		setWeapon(3);
		setLifeSpan(2);
		setDirection(Direction.WEST);

		setHomeX(9);
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
