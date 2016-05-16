package cn.edu.nju.logic;

import java.awt.Graphics;

import javax.swing.JPanel;

import cn.edu.nju.panels.GameField;

public class SpiderMan extends Samurai{

	
	public SpiderMan(Game game, GameField gameField) {
		super(game, gameField);
		setSide(1);
		setWeapon(2);
		setLifeSpan(2);
		setDirection(Direction.WEST);

		setHomeX(11);
		setHomeY(9);
		this.setCurX(this.getHomeX());
		this.setCurY(this.getHomeY());
		System.out.println("Initializing the SpiderMan");

		homeSamuraiFieldNum = Configure.SPIDER_MAN * 100 + 1;
		hitRangeFieldNum = Configure.SPIDER_MAN_ATTACK;
		manor = this.getSide() + 1;

		samuraiField[getHomeY()][getHomeX()] = samuraiFieldNum;
	}

	@Override
	public void drawBlood(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getLifeSpan(); i++) {
			g.drawImage(bloodImg, (960-250-25)-i*40, 200, null);
		}
	}

	@Override
	public void drawPower(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getPower(); i++) {
			g.drawImage(powerImg,  (960-250-25)-i*30, 230, null);
		}
	}
}
