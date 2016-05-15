package cn.edu.nju.panels;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackGroundPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public BackGroundPanel() {
		setLayout(null);
		setOpaque(false);
		setSize(960,720);
	}
	
	public void paintComponent(Graphics g){
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("/cn/picture/background1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(img, 0, 0, img.getWidth(this),img.getHeight(this), this);
	}

}
