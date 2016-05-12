package cn.edu.nju.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

//地图选择面板
public class MapSelectPanel extends BackGroundPanel{
	 
	 ImageIcon mapJL[]=new ImageIcon[4];
	 //方便地图的导入
	 public static int numOfMap=1;
	 
	 public MapSelectPanel() {
		setLayout(null);
		
		setSize(960,720);
		setBackground(Color.black);
		
		JLabel title= new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/mapTitle.png")));
		title.setBounds(50, 40, 390, 80);
		add(title);
		
		for (int i = 1; i <= 4; i++) {
			mapJL[i-1] = new ImageIcon(this.getClass().getResource("/cn/picture/map"+i+".png"));
		}
		JLabel mapLabel=new JLabel(mapJL[0]);
		mapLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mapLabel.setBounds(130, 180, 703, 320);
		add(mapLabel);
		
		JButton leftBt = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/left.png")));
		leftBt.setContentAreaFilled(false);
		leftBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/left2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/left.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(numOfMap==1){
					mapLabel.setIcon(mapJL[3]);
					numOfMap=4;
				}else if(numOfMap==2){
					mapLabel.setIcon(mapJL[0]);
					numOfMap=1;
				}else if(numOfMap==3){
					mapLabel.setIcon(mapJL[1]);
					numOfMap=2;
				}else if(numOfMap==4){
					mapLabel.setIcon(mapJL[2]);
					numOfMap=3;
				}
				
			}
		});
		leftBt.setBounds(30, 310, 40, 80);
		leftBt.setBorderPainted(false);
		add(leftBt);
		
		JButton rightBt = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/right.png")));
		rightBt.setContentAreaFilled(false);
		rightBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/right2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/right.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(numOfMap==1){
					mapLabel.setIcon(mapJL[1]);
					numOfMap=2;
				}else if(numOfMap==2){
					mapLabel.setIcon(mapJL[2]);
					numOfMap=3;
				}else if(numOfMap==3){
					mapLabel.setIcon(mapJL[3]);
					numOfMap=4;
				}else if(numOfMap==4){
					mapLabel.setIcon(mapJL[0]);
					numOfMap=1;
				}
				
			}
		});
		rightBt.setBounds(860, 310, 40, 80);
		rightBt.setBorderPainted(false);
		add(rightBt);		
		
		JButton nextBt = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/next.png")));
		nextBt.setContentAreaFilled(false);
		nextBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				nextBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/next2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nextBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/next.png")));
			}
			@Override
			
			//点击next按钮之后进入人物选择界面
			public void mouseClicked(MouseEvent e) {
				//点击next按钮之后进入人物选择界面
				StartPanel.getCard().show(StartPanel.getCardPanel(), "modeSelectPanel");
			}
		});
		nextBt.setBounds(565, 550, 70, 70);
		nextBt.setBorderPainted(false);
		add(nextBt);
		
		JButton backBt = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/return.png")));
		backBt.setContentAreaFilled(false);;
		backBt.addMouseListener(new MouseAdapter() {
			@Override
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
		backBt.setBounds(300, 555, 120, 60);
		backBt.setBorderPainted(false);
		add(backBt);
	}
	 
	 @Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}

}


