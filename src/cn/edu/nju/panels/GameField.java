package cn.edu.nju.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.edu.nju.gameMusic.musicThread;
import cn.edu.nju.gameMusic.myAudioPlayer;
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

	private BufferedImage range1;
	private BufferedImage range2;
	private BufferedImage score1;
	private BufferedImage score2;
	private BufferedImage flag1;
	private BufferedImage flag2;

	private BufferedImage hero11up, hero11down, hero11left, hero11right, hide11up, hide11down, hide11left, hide11right;
	private BufferedImage hero12up, hero12down, hero12left, hero12right, hide12up, hide12down, hide12left, hide12right;
	private BufferedImage hero13up, hero13down, hero13left, hero13right, hide13up, hide13down, hide13left, hide13right;
	private BufferedImage hero21up, hero21down, hero21left, hero21right, hide14up, hide21down, hide21left, hide21right;
	private BufferedImage hero22up, hero22down, hero22left, hero22right, hide15up, hide22down, hide22left, hide22right;
	private BufferedImage hero23up, hero23down, hero23left, hero23right, hide16up, hide23down, hide23left, hide23right;

	public Game game = new Game(this);

	public CaptainAmerica team11 = (CaptainAmerica) game.samurais[0];
	public IronMan team21 = (IronMan) game.samurais[1];
	public Hulk team12 = (Hulk) game.samurais[2];
	public BlackWidow team22 = (BlackWidow) game.samurais[3];
	public Hawkeye team13 = (Hawkeye) game.samurais[4];
	public SpiderMan team23 = (SpiderMan) game.samurais[5];

	public static int curTurn = 0;
	public static int action;
	public static String actionString = ""; // exchange the string to connect
	
	protected JPanel settingsPanel;

	public GameField() {

		addJL();
		initImag();
		addListener();

	}

	private void addListener() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				musicThread hero = new musicThread();
				musicThread attackOfHero = new musicThread();
				if (game.turn2Samurai(curTurn) == 0) {
					hero.creatMT(myAudioPlayer.walkOfCaptainAmerica, 1);
					attackOfHero.creatMT(myAudioPlayer.attackOfCaptainAmerica, 1);
				} else if (game.turn2Samurai(curTurn) == 1) {
					hero.creatMT(myAudioPlayer.walkOfIronMan, 1);
					attackOfHero.creatMT(myAudioPlayer.attackOfIornMan, 1);
				} else if (game.turn2Samurai(curTurn) == 2) {
					hero.creatMT(myAudioPlayer.walkOfHulk, 1);
					attackOfHero.creatMT(myAudioPlayer.attackOfHulk, 1);
				} else if (game.turn2Samurai(curTurn) == 3) {
					hero.creatMT(myAudioPlayer.walkOfBlackWdiow, 1);
					attackOfHero.creatMT(myAudioPlayer.attackOfBlackWdiow, 1);
				} else if (game.turn2Samurai(curTurn) == 4) {
					hero.creatMT(myAudioPlayer.walkOfHawkeye, 1);
					attackOfHero.creatMT(myAudioPlayer.attackOfHawkeye, 1);
				} else if (game.turn2Samurai(curTurn) == 5) {
					hero.creatMT(myAudioPlayer.walkOfSpiderMan, 1);
					attackOfHero.creatMT(myAudioPlayer.attackOfSpiderMan, 1);
				}
				int tempPower = game.samurais[game.turn2Samurai(curTurn)].getPower();
				switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					action = 1;
					execute();
					if (tempPower > 1 && tempPower <= 7) {
						hero.start();
						hero.stop();
					}
					break;
				case KeyEvent.VK_LEFT:
					action = 1;
					execute();
					if (tempPower > 1 && tempPower <= 7) {
						hero.start();
						hero.stop();
					}
					break;
				case KeyEvent.VK_D:
					action = 2;
					execute();
					if (tempPower > 1 && tempPower <= 7) {
						hero.start();
						hero.stop();
					}
					break;
				case KeyEvent.VK_RIGHT:
					action = 2;
					execute();
					if (tempPower > 1 && tempPower <= 7) {
						hero.start();
						hero.stop();
					}
					break;
				case KeyEvent.VK_W:
					action = 3;
					execute();
					if (tempPower > 1 && tempPower <= 7) {
						hero.start();
						hero.stop();
					}
					break;
				case KeyEvent.VK_UP:
					action = 3;
					execute();
					if (tempPower > 1 && tempPower <= 7) {
						hero.start();
						hero.stop();
					}
					break;
				case KeyEvent.VK_S:
					action = 4;
					execute();
					if (tempPower > 1 && tempPower <= 7) {
						hero.start();
						hero.stop();
					}
					break;
				case KeyEvent.VK_DOWN:
					action = 4;
					execute();
					if (tempPower > 1 && tempPower <= 7) {
						hero.start();
						hero.stop();
					}
					break;
				case KeyEvent.VK_H:
					action = 6;
					execute();
					if (tempPower > 0) {
						musicThread hide = new musicThread();
						hide.creatMT(myAudioPlayer.hideMusic, 1);
						hide.start();
						hide.stop();
					}
					break;
				case KeyEvent.VK_C:
					action = 6;
					execute();
					if (tempPower > 0) {
						musicThread hide = new musicThread();
						hide.creatMT(myAudioPlayer.hideMusic, 1);
						hide.start();
						hide.stop();
					}
					break;
				case KeyEvent.VK_K:
					action = 6;
					execute();
					if (tempPower > 0) {
						musicThread hide = new musicThread();
						hide.creatMT(myAudioPlayer.hideMusic, 1);
						hide.start();
						hide.stop();
					}
					break;
				case KeyEvent.VK_B:
					action = 7;
					execute();

					break;
				case KeyEvent.VK_V:
					action = 7;
					execute();

					break;
				case KeyEvent.VK_M:
					action = 7;
					execute();

					break;
				case KeyEvent.VK_J:
					action = 10;
					execute();
					if (tempPower > 3) {
						attackOfHero.start();
						attackOfHero.stop();
					}
					break;
				case KeyEvent.VK_F:
					action = 10;
					execute();
					if (tempPower > 3) {
						attackOfHero.start();
						attackOfHero.stop();
					}
					break;
				case KeyEvent.VK_L:
					action = 10;
					execute();
					if (tempPower > 3) {
						attackOfHero.start();
						attackOfHero.stop();
					}
					break;
				case KeyEvent.VK_SPACE:
					action = 0;
					// transfer the actionString
					actionString = "";
					execute();
					musicThread space = new musicThread();
					space.creatMT(myAudioPlayer.spaceMusic, 1);
					space.start();
					space.stop();
					break;
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

		// btnSettings
		JButton pause = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/pause.png")));
		pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				settingsPanel.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				pause.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/pause.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pause.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/pause.png")));
			}
		});
		pause.setContentAreaFilled(false);
		pause.setBorderPainted(false);
		pause.setBounds(69, 275, 70, 70);
		add(pause);
		// test
		settingsPanel = new JPanel();
		settingsPanel.setBackground(Color.GRAY);
		settingsPanel.setBounds(397, 275, 208, 32);
		settingsPanel.setVisible(false);
		add(settingsPanel);
		settingsPanel.setLayout(null);

		JButton btnBack = new JButton("chongxingkaishi");
		btnBack.setContentAreaFilled(false);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartPanel.getCard().show(StartPanel.getCardPanel(), "mainPane");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		btnBack.setBounds(0, 0, 93, 32);
		settingsPanel.add(btnBack);

		JButton btnContinue = new JButton("jixuyouxi");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				settingsPanel.setVisible(false);
				Main.startPanel.gameFieldPanel.requestFocus();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		btnContinue.setBounds(117, 0, 93, 32);
		settingsPanel.add(btnContinue);

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
			range1 = ImageIO.read(this.getClass().getResource("/cn/picture/hero/range1.png"));
			range2 = ImageIO.read(this.getClass().getResource("/cn/picture/hero/range2.png"));
			flag1 = ImageIO.read(this.getClass().getResource("/cn/picture/hero/flag1.png"));
			flag2 = ImageIO.read(this.getClass().getResource("/cn/picture/hero/flag2.png"));

			// test should change again after get picture
			hero11down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/11.png"));
			hero12down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/11.png"));
			hero13down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/11.png"));
			hero21down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/66.png"));
			hero22down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/66.png"));
			hero23down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/66.png"));
			// test hiding
			hide11down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/111.png"));
			hide12down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/111.png"));
			hide13down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/111.png"));
			hide21down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/661.png"));
			hide22down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/661.png"));
			hide23down = ImageIO.read(this.getClass().getResource("/cn/picture/hero/661.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawField(g);
		drawSamurai(g);

		drawFlag(g);
		drawHitRange(g);
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
				// give the grass data to SCORE,change the grass to 3 to turn it

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
				} else if (Configure.map[i][j] == Configure.HOME_OF_CAP) {
					g.drawImage(homeOfCap, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT, null);
				} else if (Configure.map[i][j] == Configure.HOME_OF_IRON_MAN) {
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

	void drawSamurai(Graphics g) {
		for (int k = 0; k < 7; k++) {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 12; j++) {
					switch (game.samuraiField[k][i][j]) {
					case 1101: {
						g.drawImage(hero11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 1102: {
						g.drawImage(hero11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 1103: {
						g.drawImage(hero11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 1104: {
						g.drawImage(hero11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 2201: {
						g.drawImage(hero12down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 2202: {
						g.drawImage(hero12down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 2203: {
						g.drawImage(hero12down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 2204: {
						g.drawImage(hero12down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 3301: {
						g.drawImage(hero13down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 3302: {
						g.drawImage(hero13down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 3303: {
						g.drawImage(hero13down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 3304: {
						g.drawImage(hero13down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 4401: {
						g.drawImage(hero21down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 4402: {
						g.drawImage(hero21down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 4403: {
						g.drawImage(hero21down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 4404: {
						g.drawImage(hero21down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 5501: {
						g.drawImage(hero22down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 5502: {
						g.drawImage(hero22down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 5503: {
						g.drawImage(hero22down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 5504: {
						g.drawImage(hero22down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 6601: {
						g.drawImage(hero23down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 6602: {
						g.drawImage(hero23down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 6603: {
						g.drawImage(hero23down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 6604: {
						g.drawImage(hero23down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 1111: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 1112: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 1113: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 1114: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 2211: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 2212: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 2213: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 2214: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 3311: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 3312: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 3313: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 3314: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 4411: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 4412: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 4413: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 4414: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 5511: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 5512: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 5513: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 5514: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 6611: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 6612: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 6613: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					case 6614: {
						g.drawImage(hide11down, 55 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT - 33, null);
						break;
					}
					}
				}
			}
		}
	}

	void drawHitRange(Graphics g) {
		for (int k = 0; k < 6; k++) {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 12; j++) {
					if (Configure.hitRangeField[k][i][j] == Configure.CAP_ATTACK
							|| Configure.hitRangeField[k][i][j] == Configure.HULK_ATTACK
							|| Configure.hitRangeField[k][i][j] == Configure.HAWKEYE_ATTACK) {
						g.drawImage(range1, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT, null);
					} else if (Configure.hitRangeField[k][i][j] == Configure.IRON_MAN_ATTACK
							|| Configure.hitRangeField[k][i][j] == Configure.BLACK_WIDOW_ATTACK
							|| Configure.hitRangeField[k][i][j] == Configure.SPIDER_MAN_ATTACK) {
						g.drawImage(range2, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
								340 + i * Configure.HEIGHT, null);
					}
				}
			}
		}
	}

	void drawFlag(Graphics g) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (Configure.scoreField[i][j] == Configure.SIDE_OF_CAP) {
					g.drawImage(flag1, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 25, null);
				} else if (Configure.scoreField[i][j] == Configure.SIDE_OF_IRON_MAN) {
					g.drawImage(flag2, 40 + (12 - i) * Configure.DEVIATION + j * Configure.WIDTH,
							340 + i * Configure.HEIGHT - 25, null);
				}
			}
		}
	}
}