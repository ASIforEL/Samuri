package cn.edu.nju.panels;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import cn.edu.nju.logic.BlackWidow;
import cn.edu.nju.logic.CaptainAmerica;
import cn.edu.nju.logic.Configure;
import cn.edu.nju.logic.Game;
import cn.edu.nju.logic.Hawkeye;
import cn.edu.nju.logic.Hulk;
import cn.edu.nju.logic.IronMan;
import cn.edu.nju.logic.SpiderMan;

public class GameField extends BackGroundPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage grass;
	private BufferedImage homeOfCap;
	private BufferedImage homeOfIronMan;
	private BufferedImage mountain;
	private BufferedImage tree;
	private BufferedImage transfer31;
	private BufferedImage transfer32;
	private BufferedImage transfer33;
	private BufferedImage transfer34;
	private BufferedImage transfer35;
	private BufferedImage transfer36;
	private BufferedImage transfer37;
	private BufferedImage transfer38;
	private BufferedImage hero11;
	private BufferedImage hero12;
	private BufferedImage hero13;
	private BufferedImage hero21;
	private BufferedImage hero22;
	private BufferedImage hero23;

	public Game game = new Game(this);

	public CaptainAmerica team11 = (CaptainAmerica) game.samurais[0];
	public IronMan team21 = (IronMan) game.samurais[1];
	public Hulk team12 = (Hulk) game.samurais[2];
	public BlackWidow team22 = (BlackWidow) game.samurais[3];
	public Hawkeye team13 = (Hawkeye) game.samurais[4];
	public SpiderMan team23 = (SpiderMan) game.samurais[5];

	public static int curTurn = 0;
	public static int action;
	public static String actionString = "";		//exchange the string to connect

	public GameField() {

		addJL();
		initImag();

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_A:action = 1;execute();break;
				case KeyEvent.VK_D:action = 2;execute();break;
				case KeyEvent.VK_W:action = 3;execute();break;
				case KeyEvent.VK_S:action = 4;execute();break;
				case KeyEvent.VK_H:action = 6;execute();break;
				case KeyEvent.VK_B:action = 7;execute();break;
				case KeyEvent.VK_J:action = 10;execute();break;
				case KeyEvent.VK_SPACE:action = 0;
								//transfer the actionString
								actionString = "";
								execute();break;
				}
			}

		});
	}

	private void execute() {
		actionString = actionString + action + " ";
		game.execute(curTurn, action);
	}

	public void addJL() {
		JLabel lblCaptain = new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/logo1.png")));
		lblCaptain.setBounds(50, 75, 120, 150);
		add(lblCaptain);

		JLabel lblIronman = new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/logo2.png")));

		lblIronman.setBounds(790, 75, 120, 150);
		add(lblIronman);

		JLabel team11 = new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/11.png")));
		team11.setBounds(187, 20, 50, 60);
		add(team11);

		JLabel team12 = new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/11.png")));
		team12.setBounds(187, 110, 50, 60);
		add(team12);

		JLabel team13 = new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/11.png")));
		team13.setBounds(187, 200, 50, 60);
		add(team13);

		JLabel team21 = new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/11.png")));
		team21.setBounds(743, 20, 50, 60);
		add(team21);

		JLabel team22 = new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/11.png")));
		team22.setBounds(743, 110, 50, 60);
		add(team22);

		JLabel team23 = new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/11.png")));
		team23.setBounds(743, 200, 50, 60);
		add(team23);
	}

	public void initImag() {

		try {
			grass = ImageIO.read(this.getClass().getResource("/cn/picture/grass.png"));
			homeOfCap = ImageIO.read(this.getClass().getResource("/cn/picture/TeamCap.png"));
			homeOfIronMan = ImageIO.read(this.getClass().getResource("/cn/picture/TeamIronMan.png"));
			mountain = ImageIO.read(this.getClass().getResource("/cn/picture/mountain.png"));
			tree = ImageIO.read(this.getClass().getResource("/cn/picture/tree.png"));
			transfer31 = ImageIO.read(this.getClass().getResource("/cn/picture/transfer31.png"));
			transfer32 = ImageIO.read(this.getClass().getResource("/cn/picture/transfer32.png"));
			transfer33 = ImageIO.read(this.getClass().getResource("/cn/picture/transfer33.png"));
			transfer34 = ImageIO.read(this.getClass().getResource("/cn/picture/transfer34.png"));
			transfer35 = ImageIO.read(this.getClass().getResource("/cn/picture/transfer35.png"));
			transfer36 = ImageIO.read(this.getClass().getResource("/cn/picture/transfer36.png"));
			transfer37 = ImageIO.read(this.getClass().getResource("/cn/picture/transfer37.png"));
			transfer38 = ImageIO.read(this.getClass().getResource("/cn/picture/transfer38.png"));
			hero11 = ImageIO.read(this.getClass().getResource("/cn/picture/hero/11.png"));
			hero23 = ImageIO.read(this.getClass().getResource("/cn/picture/hero/66.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawField(g);
		drawSamurai(g);
		team11.drawBlood(g);
		team11.drawPower(g);

		team12.drawBlood(g);
		team12.drawPower(g);

		team13.drawBlood(g);
		team13.drawPower(g);

		team21.drawBlood(g);
		team21.drawPower(g);

		team22.drawBlood(g);
		team22.drawPower(g);

		team23.drawBlood(g);
		team23.drawPower(g);
	}

	void drawField(Graphics g) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				Configure.map[i][j] = Configure.mapField[MapSelectPanel.numOfMap - 1][i][j];
			}
		}
		for (int i = 0; i < Configure.map.length; i++) {
			for (int j = 0; j < Configure.map[i].length; j++) {

				if (Configure.map[i][j] == Configure.ROAD) {
					g.drawImage(grass, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT, null);
				} else if (Configure.map[i][j] == Configure.MOUN) {
					g.drawImage(mountain, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 15, null);
				} else if (Configure.map[i][j] == Configure.HOMEOFCAP) {
					g.drawImage(homeOfCap, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT, null);
				} else if (Configure.map[i][j] == Configure.HOMEOFIRONMAN) {
					g.drawImage(homeOfIronMan, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT, null);
				} else if (Configure.map[i][j] == Configure.TREE) {
					g.drawImage(tree, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 24, null);
				} else if (Configure.map[i][j] == Configure.TRANS1) {
					g.drawImage(transfer31, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 47, null);
				} else if (Configure.map[i][j] == Configure.TRANS2) {
					g.drawImage(transfer32, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 47, null);
				} else if (Configure.map[i][j] == Configure.TRANS3) {
					g.drawImage(transfer33, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 47, null);
				} else if (Configure.map[i][j] == Configure.TRANS4) {
					g.drawImage(transfer34, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 47, null);
				} else if (Configure.map[i][j] == Configure.TRANS5) {
					g.drawImage(transfer35, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 47, null);
				} else if (Configure.map[i][j] == Configure.TRANS6) {
					g.drawImage(transfer36, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 47, null);
				} else if (Configure.map[i][j] == Configure.TRANS7) {
					g.drawImage(transfer37, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 47, null);
				} else if (Configure.map[i][j] == Configure.TRANS8) {
					g.drawImage(transfer38, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 47, null);
				}
			}
		}
	}

	private void drawSamurai(Graphics g) {
		for (int i = 0; i < Configure.samuraiField.length; i++) {
			for (int j = 0; j < Configure.samuraiField[i].length; j++) {
				if (Configure.samuraiField[i][j] == Configure.CAPTAIN_AMERICA || Configure.samuraiField[i][j] == Configure.HULK
						|| Configure.samuraiField[i][j] == Configure.HAWKEYE) {
					g.drawImage(hero11, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 33, null);
				} else if (Configure.samuraiField[i][j] == Configure.IRON_MAN || Configure.samuraiField[i][j] == Configure.BLACK_WIDOW
						|| Configure.samuraiField[i][j] == Configure.SPIDER_MAN) {
					g.drawImage(hero23, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 33, null);
				}
			}
		}
	}

	// public void processParentEvent(AWTEvent e){
	// this.processEvent(e);
	// }
	// public void keyPress(KeyEvent e){
	// if(e.getKeyCode()==KeyEvent.VK_W){
	// team11.curY++;
	// System.out.println("wwwww");
	// }
	//
	// }
	// private class KeyCommand extends KeyAdapter
	// {
	// public void keyPressed(KeyEvent e)
	// {
	// System.out.println("OK");
	// if(e.getKeyCode()==KeyEvent.VK_W){
	// team11.curY++;
	// System.out.println("wwwww");
	// }
	// }
	// }

}