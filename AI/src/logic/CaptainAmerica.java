package logic;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CaptainAmerica extends Samurai{
	
	
	public CaptainAmerica() {
		
		setSide(0);
		setWeapon(0);
		setLifeSpan(2);
		//初始化时 即将英雄的图片加载进去
		try {
			samuraiImg = ImageIO.read(this.getClass().getResource(""));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	
	//绘制血量
	public void drawBlood(Graphics g,JPanel i){
		initBlood_X = 150;	//每个英雄的初始位置不一样
		initBlood_Y = 150;	
		//根据图片大小设置间距，y值不变
		for (int j = 0; j < this.getLifeSpan(); j++) {
			g.drawImage(bloodImg, initBlood_X+j*50, initBlood_Y, (ImageObserver)i);
		}
	}

	//绘制英雄
	public void drawSamurai(Graphics g, JPanel i) {
		// TODO 自动生成的方法存根
		//每个英雄加载不同的图片
		
	}

	//绘制能量
	public void drawPower(Graphics g, JPanel i) {
		//同绘制血量一样
	}
}
