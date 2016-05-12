package cn.edu.nju.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

 	private BufferedImage grass;
	public GamePanel(){
		super();
		this.setSize(960,720);
		initImag();

		this.setVisible(true);
		
	}
	public void initImag(){

		
		try {
			grass=ImageIO.read(this.getClass().getResource("/cn/picture/A.png"));
		} catch (IOException e) {
			System.out.println("Í¼Æ¬³ö´í");
			e.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(grass, 150, 50, null);
		g.drawImage(grass, 165, 50, null);
	}
	
}


