package cn.edu.nju.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import cn.edu.nju.gameMusic.musicThread;
import cn.edu.nju.gameMusic.myAudioPlayer;
import cn.edu.nju.panels.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import cn.edu.nju.gameMusic.*;

public class ModeSelectPanel extends BackGroundPanel {
	static int team1hero=1;
	static int team2hero=1;
	ImageIcon hero1JL[]=new ImageIcon[3];
	ImageIcon hero2JL[]=new ImageIcon[3];
	ImageIcon hero1JL0[]=new ImageIcon[3];
	ImageIcon hero2JL0[]=new ImageIcon[3];
	/**
	 * Create the panel.
	 */
	public ModeSelectPanel() {
		setSize(960, 720);
		setLayout(null);

		JLabel vs = new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/vs.png")));
		vs.setBounds(400, 70, 160, 160);
		add(vs);
		 
		JButton btnFight=new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/next.png")));
		
		btnFight.setContentAreaFilled(false);
		btnFight.setBorderPainted(false);
		btnFight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
/////////if you get it£¬   				StartPanel.getCard().show(StartPanel.getCardPanel(), "gameFieldPanel");
//if not .... how to dispose the fore Frame......
				new GameFrame();
				musicThread nextButton =  new musicThread();
				nextButton.creatMT(myAudioPlayer.enterSprites, 1);
				nextButton.start();
				nextButton.stop();
				}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnFight.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/next2.png")));
				musicThread modelEnter =  new musicThread();
				modelEnter.creatMT(myAudioPlayer.mapEnter,1);
				modelEnter.start();
				modelEnter.stop();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnFight.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/next.png")));
			}
		});
		btnFight.setBounds(565, 600, 70, 70);
		add(btnFight);
		
		 
		JButton btnBack = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/return.png")));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartPanel.getCard().show(StartPanel.getCardPanel(), "mapSelectPanel");
				requestFocus(true);
				addKeyListener(new KeyAdapter()
				{
				public void keyPressed(KeyEvent e)
				{
				System.out.println("ÊµÏÖ¼àÌý");
				 if(e.getKeyCode()==KeyEvent.VK_UP){
				    System.out.println("up");
				 }
				}
				});
				musicThread back =  new musicThread();
				back.creatMT(myAudioPlayer.modelBack, 1);
				back.start();
				back.stop();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			
				btnBack.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/return2.png")));
				musicThread modelEnter =  new musicThread();
				modelEnter.creatMT(myAudioPlayer.mapEnter,1);
				modelEnter.start();
				modelEnter.stop();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBack.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/return.png")));
			}
		});
		btnBack.setBounds(300, 605, 120, 60);
		add(btnBack);

		//load hero JL of each team 
		
		for (int i = 1; i <= 3; i++) {
		    hero1JL[i-1] = new ImageIcon(this.getClass().getResource("/cn/picture/hero/team1"+i+".png"));
		    hero1JL0[i-1] = new ImageIcon(this.getClass().getResource("/cn/picture/hero/team1"+i+"0.png"));

		}
		for (int i = 1; i <= 3; i++) {
			hero2JL[i-1] = new ImageIcon(this.getClass().getResource("/cn/picture/hero/team2"+i+".png"));
			hero2JL0[i-1] = new ImageIcon(this.getClass().getResource("/cn/picture/hero/team2"+i+"0.png"));
		}
		//play the information of team
		JButton team1 = new JButton(hero1JL[0]);
		team1.setBorderPainted(false);
		team1.setContentAreaFilled(false);
		team1.setBounds(75, 80, 280,500);
		team1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				team1.setIcon(hero1JL0[team1hero-1]);
				musicThread team1 =  new musicThread();
				if(team1hero==1){
					team1.creatMT(myAudioPlayer.voiceOfCaptainAmerica, 1);
					team1.start();
					team1.stop();
				}else if(team1hero==2){
					team1.creatMT(myAudioPlayer.voiceOfHulk, 1);
					team1.start();
					team1.stop();
				}else if(team1hero==3){
					team1.creatMT(myAudioPlayer.voiceOfHawkeye, 1);
					team1.start();
					team1.stop();
				}
			}
			public void mouseExited(MouseEvent e) {
				team1.setIcon(hero1JL[team1hero-1]);
			}
		});
		JButton team2 = new JButton(hero2JL[0]);
		team2.setBorderPainted(false);
		team2.setContentAreaFilled(false);
		team2.setBounds(605, 80, 280,500);
		team2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				team2.setIcon(hero2JL0[team2hero-1]);
				musicThread team2 =  new musicThread();
				if(team2hero==1){
					team2.creatMT(myAudioPlayer.voiceOfIronMan, 1);
					team2.start();
					team2.stop();
				}else if(team2hero==2){
					team2.creatMT(myAudioPlayer.voiceOfBlackWdiow, 1);
					team2.start();
					team2.stop();
				}else if(team2hero==3){
					team2.creatMT(myAudioPlayer.voiceOfSpiderMan, 1);
					team2.start();
					team2.stop();
				}
			}
			public void mouseExited(MouseEvent e) {
				team2.setIcon(hero2JL[team2hero-1]);
			}
		});
		add(team1);
		add(team2);
//	
//		

		//add two group button
		
		//group1
		JButton leftBt1 = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/left.png")));
		leftBt1.setContentAreaFilled(false);
		leftBt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftBt1.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/left2.png")));
				musicThread modelEnter =  new musicThread();
				modelEnter.creatMT(myAudioPlayer.mapEnter,1);
				modelEnter.start();
				modelEnter.stop();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftBt1.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/left.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(team1hero==1){
					team1.setIcon(hero1JL[2]);
					team1hero=3;
				}else if(team1hero==2){
					team1.setIcon(hero1JL[0]);	
					team1hero=1;
				}else if(team1hero==3){
					team1.setIcon(hero1JL[1]);
					team1hero=2;
				}
				musicThread leftButton1 =  new musicThread();
				leftButton1.creatMT(myAudioPlayer.modelLeft, 1);
				leftButton1.start();
				leftButton1.stop();
			}
		});
		leftBt1.setBounds(20, 310, 40, 80);
		leftBt1.setBorderPainted(false);
		add(leftBt1);
		
		JButton rightBt1 = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/right.png")));
		rightBt1.setContentAreaFilled(false);
		rightBt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightBt1.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/right2.png")));
				musicThread modelEnter =  new musicThread();
				modelEnter.creatMT(myAudioPlayer.mapEnter,1);
				modelEnter.start();
				modelEnter.stop();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightBt1.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/right.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(team1hero==1){
					team1.setIcon(hero1JL[1]);	
					team1hero=2;
				}else if(team1hero==2){
					team1.setIcon(hero1JL[2]);			
					team1hero=3;
				}else if(team1hero==3){
					team1.setIcon(hero1JL[0]);
					team1hero=1;
				}
				musicThread rightButton1 =  new musicThread();
				rightButton1.creatMT(myAudioPlayer.modelRight, 1);
				rightButton1.start();
				rightButton1.stop();
			}
		});
	
		rightBt1.setBounds(360, 310, 40, 80);
		rightBt1.setBorderPainted(false);
		
		add(rightBt1);
		//group2
		
		JButton leftBt2 = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/left.png")));
		leftBt2.setContentAreaFilled(false);
		leftBt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftBt2.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/left2.png")));
				musicThread modelEnter =  new musicThread();
				modelEnter.creatMT(myAudioPlayer.mapEnter,1);
				modelEnter.start();
				modelEnter.stop();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftBt2.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/left.png")));
			
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(team2hero==1){
					team2.setIcon(hero2JL[2]);
					team2hero=3;
				}else if(team2hero==2){
					team2.setIcon(hero2JL[0]);	
					team2hero=1;
				}else if(team2hero==3){
					team2.setIcon(hero2JL[1]);
					team2hero=2;
				}
				musicThread leftButton2 =  new musicThread();
				leftButton2.creatMT(myAudioPlayer.modelLeft, 1);
				leftButton2.start();
				leftButton2.stop();
			}
		});
		leftBt2.setBounds( 550, 310, 40, 80);
		leftBt2.setBorderPainted(false);
		add(leftBt2);
		
		JButton rightBt2 = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/right.png")));
		rightBt2.setContentAreaFilled(false);
		rightBt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightBt2.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/right2.png")));
				musicThread modelEnter =  new musicThread();
				modelEnter.creatMT(myAudioPlayer.mapEnter,1);
				modelEnter.start();
				modelEnter.stop();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightBt2.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/right.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(team2hero==1){
					team2.setIcon(hero2JL[1]);	
					team2hero=2;
				}else if(team2hero==2){
					team2.setIcon(hero2JL[2]);			
					team2hero=3;
				}else if(team2hero==3){
					team2.setIcon(hero2JL[0]);
					team2hero=1;
				}
				musicThread rightButton2 =  new musicThread();
				rightButton2.creatMT(myAudioPlayer.modelRight, 1);
			    rightButton2.start();
				rightButton2.stop();
			}
		});
	
		rightBt2.setBounds(890, 310, 40, 80);
		rightBt2.setBorderPainted(false);
		
			
		add(rightBt2);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
