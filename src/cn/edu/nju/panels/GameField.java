package cn.edu.nju.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import cn.edu.nju.logic.Configure;

public class GameField extends BackGroundPanel{
	
	private static final long serialVersionUID = 1L;
	// private JPanel JP;
	// private JLabel back;
	private BufferedImage grass;
	private BufferedImage homeOfCap;
	private BufferedImage homeOfIronMan;
	private BufferedImage mountain;
	private BufferedImage transform;
	private BufferedImage tree;
	
	public GameField() {
		// TODO Auto-generated constructor stu

		JLabel lblCaptain = new JLabel("Captain");
		lblCaptain.setForeground(Color.RED);
		lblCaptain.setBounds(163, 43, 106, 81);
		add(lblCaptain);
		
		JLabel lblIronman = new JLabel("Ironman");
		lblIronman.setForeground(Color.RED);
		lblIronman.setBounds(713, 43, 106, 81);
		add(lblIronman);
		
		JLabel ironmanLabel = new JLabel("New label");
		ironmanLabel.setForeground(Color.RED);
		ironmanLabel.setBounds(743, 147, 36, 34);
		add(ironmanLabel);
		
		JLabel widowLabel = new JLabel("New label");
		widowLabel.setForeground(Color.RED);
		widowLabel.setBounds(743, 191, 36, 34);
		add(widowLabel);
		
		JLabel spidermanLabel = new JLabel("New label");
		spidermanLabel.setForeground(Color.RED);
		spidermanLabel.setBounds(743, 235, 36, 34);
		add(spidermanLabel);
		
		JLabel label = new JLabel("New label");
		label.setForeground(Color.RED);
		label.setBounds(187, 147, 36, 34);
		add(label);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setForeground(Color.RED);
		label_1.setBounds(187, 191, 36, 34);
		add(label_1);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setForeground(Color.RED);
		label_2.setBounds(187, 235, 36, 34);
		add(label_2);
		initImag();


	}

	public void initImag() {

		try {
			grass = ImageIO.read(this.getClass().getResource("/cn/picture/grass.png"));
			homeOfCap = ImageIO.read(this.getClass().getResource("/cn/picture/TeamCap.png"));
			homeOfIronMan = ImageIO.read(this.getClass().getResource("/cn/picture/TeamIronMan.png"));
			mountain = ImageIO.read(this.getClass().getResource("/cn/picture/mountain.png"));
			transform = ImageIO.read(this.getClass().getResource("/cn/picture/chuansong.png"));
			tree = ImageIO.read(this.getClass().getResource("/cn/picture/tree.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	 @Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.initMap(g);
			/*
			new Samurai().drawBlood(g, this);
			new Samurai().drawPower(g, this);
			*/
		}

	public void initMap(Graphics g) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				Configure.map[i][j]=Configure.mapField[MapSelectPanel.numOfMap-1][i][j];
			}
		}
		for (int i = 0; i < Configure.map.length; i++) {
			for (int j = 0; j < Configure.map[i].length; j++) {

				if (Configure.map[i][j] == Configure.ROAD) {
					g.drawImage(grass, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							300 + i * Configure.HEIGHT, null);
				} else if (Configure.map[i][j] == Configure.MOUN) {
					g.drawImage(mountain, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							300 + i * Configure.HEIGHT - 15, null);
				} else if (Configure.map[i][j] == Configure.TRANS1 || Configure.map[i][j] == Configure.TRANS2 ||
						Configure.map[i][j] == Configure.TRANS3 || Configure.map[i][j] == Configure.TRANS4 ||
						Configure.map[i][j] == Configure.TRANS5 || Configure.map[i][j] == Configure.TRANS6 || 
						Configure.map[i][j] == Configure.TRANS7 || Configure.map[i][j] == Configure.TRANS8) {
					g.drawImage(transform, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							300 + i * Configure.HEIGHT - 15, null);
				}else if (Configure.map[i][j] == Configure.HOMEOFCAP) {
					g.drawImage(homeOfCap, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							300 + i * Configure.HEIGHT , null);
				}else if (Configure.map[i][j] == Configure.HOMEOFIRONMAN) {
					g.drawImage(homeOfIronMan, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							300 + i * Configure.HEIGHT , null);
				}
				else if (Configure.map[i][j] == Configure.TREE) {
					g.drawImage(tree, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							300 + i * Configure.HEIGHT-24 , null);
				}
			}
		}
	}
}
