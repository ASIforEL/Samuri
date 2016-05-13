package cn.edu.nju.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

//�������
public class HelpPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public HelpPanel() {
		setLayout(null);
		
		this.setSize(960,720);
		

		JLabel helpBt = new JLabel("HELP");
		helpBt.setHorizontalAlignment(SwingConstants.CENTER);
		helpBt.setBounds(393, 63, 108, 39);
		add(helpBt);
		
//		JTextArea helpText = new JTextArea();
//		helpText.setBounds(253, 148, 394, 448);
//		add(helpText);
//		
		JButton backBt = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/return.png")));
		backBt.addMouseListener(new MouseAdapter() {
			//���back��ť֮���˻ص�������
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
		backBt.setBounds(10, 10, 100, 70);
	    backBt.setBackground(Color.BLACK);
	    backBt.setBorderPainted(false);
		this.add(backBt);
		
	}


		
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		BufferedImage back;
		BufferedImage backBt;
		try {
			back = ImageIO.read(this.getClass().getResource("/cn/picture/background2.jpg"));
			backBt=ImageIO.read(this.getClass().getResource("/cn/picture/return.png"));
			g.drawImage(back, 0, 0, null);
			g.drawImage(backBt,10,10,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}





}
