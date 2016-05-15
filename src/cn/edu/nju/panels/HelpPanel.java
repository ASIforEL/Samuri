package cn.edu.nju.panels;

import java.awt.Color;
import java.awt.Graphics;
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

public class HelpPanel extends BackGroundPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HelpPanel() {
		setLayout(null);
		
		this.setSize(960,720);
		

//		JLabel helpBt = new JLabel("HELP");
//		helpBt.setHorizontalAlignment(SwingConstants.CENTER);
//		helpBt.setBounds(393, 63, 108, 39);
//		add(helpBt);
		
//		JTextArea helpText = new JTextArea();
//		helpText.setBounds(253, 148, 394, 448);
//		add(helpText);
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
		backBt.setBounds(500, 500, 120, 60);
	    backBt.setContentAreaFilled(false);
	    backBt.setBorderPainted(false);
		this.add(backBt);
		
	}


		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	
	}



}
