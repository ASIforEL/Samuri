package cn.edu.nju.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cn.edu.nju.gameMusic.musicThread;
import cn.edu.nju.gameMusic.myAudioPlayer;

public class HelpPanel extends BackGroundPanel {
	Image help ;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HelpPanel() {
		setLayout(null);
		
		this.setSize(960,720);
		

		JButton nextBt = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/next.png")));
		nextBt.setBorderPainted(false);
		nextBt.setBounds(700, 515, 70, 70);
		nextBt.setContentAreaFilled(false);
		nextBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				nextBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/next2.png")));
				musicThread mapEnter =  new musicThread();
				mapEnter.creatMT(myAudioPlayer.mapEnter,1);
				mapEnter.start();
				mapEnter.stop();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nextBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/next.png")));
			}
			@Override

			public void mouseClicked(MouseEvent e) {
				StartPanel.getCard().show(StartPanel.getCardPanel(), "mapSelectPanel");
				musicThread nextButton =  new musicThread();
				nextButton.creatMT(myAudioPlayer.chooseMap, 1);
				nextButton.start();
				nextButton.stop();
			}
		});
		add(nextBt);
//		
		JButton backBt = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/return.png")));
		backBt.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				StartPanel.getCard().show(StartPanel.getCardPanel(), "mainPane");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				backBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/return2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/return.png")));
			}
		});
		backBt.setBounds(530, 520, 120, 60);
	    backBt.setContentAreaFilled(false);
	    backBt.setBorderPainted(false);
		this.add(backBt);
		
		
	}


		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		help= new ImageIcon(this.getClass().getResource("/cn/picture/help.png")).getImage();
		g.drawImage(help,0,0, null);
	
	}



}
