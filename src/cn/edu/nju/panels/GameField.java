package cn.edu.nju.panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cn.edu.nju.logic.Configure;
import cn.edu.nju.panels.MapSelectPanel;

public class GameField extends JPanel {
	
	private static final long serialVersionUID = 1L;
	// private JPanel JP;
	// private JLabel back;
	private BufferedImage grass;
	private BufferedImage background;
	
	private BufferedImage homeOfCap;
	private BufferedImage homeOfIronMan;
	private BufferedImage mountain;
	private BufferedImage transform;
	private BufferedImage tree;
	
	public GameField() {
		// TODO Auto-generated constructor stub

		super();
		this.setSize(960, 720);

		initImag();


	}

	public void initImag() {

		try {
			grass = ImageIO.read(this.getClass().getResource("/cn/picture/grass.png"));
			background = ImageIO.read(this.getClass().getResource("/cn/picture/background1.jpg"));
			
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
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// super.paint(g);
		this.initMap(g);
		this.repaint();
	}

	public void initMap(Graphics g) {
		g.drawImage(background, 0, 0, null);
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
