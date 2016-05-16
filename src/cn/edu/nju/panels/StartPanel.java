package cn.edu.nju.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import cn.edu.nju.gameMusic.*;

public class StartPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLayeredPane mainPane;
	private JLabel background;
	private JButton startBt, serverBt, setBt, bt1, bt2, bt3, bt4, bt5, bt6;
	private static JPanel cardPanel;
	private static CardLayout card;
	private Image logo;
	public GameField gameFieldPanel ;
	public StartPanel() {
		
	
		logo=(new ImageIcon(this.getClass().getResource("/cn/picture/logo.png")).getImage());

		screen();
		setIconImage(logo);
		setTitle("AI");
		getContentPane().setLayout(new BorderLayout(0, 0));
		cursor();
		cardPanel = new JPanel();
		getContentPane().add(cardPanel, BorderLayout.CENTER);
		card = new CardLayout();
		cardPanel.setLayout(card);
		// ��ʼ��������
		MapSelectPanel mapSelectPanel = new MapSelectPanel();
		HelpPanel helpPanel = new HelpPanel();
		ModeSelectPanel modeSelectPanel = new ModeSelectPanel();
		gameFieldPanel = new GameField();
		
		// �������뵽cardPanel��
		mainPane = new JLayeredPane();
		cardPanel.add(mainPane, "mainPane");
		cardPanel.add(mapSelectPanel, "mapSelectPanel");
		cardPanel.add(helpPanel, "helpPanel");
		cardPanel.add(modeSelectPanel, "modeSelectPanel");
		cardPanel.add(gameFieldPanel, "gameFieldPanel");


		background = new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/start.jpg")));
		background.setBounds(0, 0, 960, 690);
		// ������ť

		addBt();

		mainPane.add(background, JLayeredPane.DEFAULT_LAYER);
		
	}

	public void addBt() {

		startBt = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/startIcon.png")));
		startBt.setBorderPainted(false);
		startBt.setBounds(420, 190, 120, 120);
		startBt.setContentAreaFilled(false);
		startBt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				startBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/startIcon.png")));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				startBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/startIcon2.png")));
				musicThread start = new musicThread();
				start.creatMT(myAudioPlayer.Enter, 1);
				start.start();
				start.stop();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				musicThread start = new musicThread();
				start.creatMT(myAudioPlayer.chooseMap, 1);
				start.start();
				start.stop();
				card.show(cardPanel, "helpPanel");
			}

		});

		serverBt = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/connectIcon.png")));
		serverBt.setBorderPainted(false);
		serverBt.setBounds(375, 325, 70, 70);
		serverBt.setContentAreaFilled(false);
		serverBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				serverBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/connectIcon.png")));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				serverBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/connectIcon2.png")));
				musicThread help = new musicThread();
				help.creatMT(myAudioPlayer.Enter, 1);
				help.start();
				help.stop();
			}

			public void mouseClicked(MouseEvent e) {
				musicThread start = new musicThread();
				start.creatMT(myAudioPlayer.chooseMap, 1);
				start.start();
				start.stop();
				card.show(cardPanel, "helpPanel");
			}
		});

		setBt = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/setIcon.png")));
		setBt.setBorderPainted(false);
		setBt.setBounds(515, 325, 70, 70);
		setBt.setContentAreaFilled(false);
		setBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/setIcon.png")));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setBt.setIcon(new ImageIcon(this.getClass().getResource("/cn/picture/setIcon2.png")));
				musicThread help = new musicThread();
				help.creatMT(myAudioPlayer.Enter, 1);
				help.start();
				help.stop();
			}

			public void mouseClicked(MouseEvent e) {
				musicThread start = new musicThread();
				start.creatMT(myAudioPlayer.chooseMap, 1);
				start.start();
				start.stop();
				card.show(cardPanel, "helpPanel");
			}
		});

		//add small logo
		bt1 = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/bt1.png")));
		bt1.setBorderPainted(false);
		bt1.setBounds(410, 415, 40, 40);
		bt1.setContentAreaFilled(false);
		bt2 = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/bt2.png")));
		bt2.setBorderPainted(false);
		bt2.setBounds(465, 415, 40, 40);
		bt2.setContentAreaFilled(false);
		bt3 = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/bt3.png")));
		bt3.setBorderPainted(false);
		bt3.setBounds(520, 415, 40, 40);
		bt3.setContentAreaFilled(false);
		bt4 = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/bt4.png")));
		bt4.setBorderPainted(false);
		bt4.setBounds(410, 460, 40, 40);
		bt4.setContentAreaFilled(false);
		bt5 = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/bt5.png")));
		bt5.setBorderPainted(false);
		bt5.setBounds(465, 460, 40, 40);
		bt5.setContentAreaFilled(false);
		bt6 = new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/bt6.png")));
		bt6.setBorderPainted(false);
		bt6.setBounds(520, 460, 40, 40);
		bt6.setContentAreaFilled(false);
		
		mainPane.add(startBt, JLayeredPane.MODAL_LAYER);
		mainPane.add(serverBt, JLayeredPane.MODAL_LAYER);
		mainPane.add(setBt, JLayeredPane.MODAL_LAYER);
		mainPane.add(bt1, JLayeredPane.MODAL_LAYER);
		mainPane.add(bt2, JLayeredPane.MODAL_LAYER);
		mainPane.add(bt3, JLayeredPane.MODAL_LAYER);
		mainPane.add(bt4, JLayeredPane.MODAL_LAYER);
		mainPane.add(bt5, JLayeredPane.MODAL_LAYER);
		mainPane.add(bt6, JLayeredPane.MODAL_LAYER);
	}

	public void screen() {

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dim = tool.getScreenSize();
		int width = (int) dim.getWidth();
		this.setLocation((width - 960) / 2, 3);
		this.setSize(960, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
		this.setResizable(false);
	}
   public void cursor(){
	  
	   Toolkit tk = Toolkit.getDefaultToolkit(); 
	   Image image = new ImageIcon(this.getClass().getResource("/cn/picture/cursor.png")).getImage(); 
	   Cursor cursor = tk.createCustomCursor(image, new Point(10,10), "stick"); 
	   setCursor(cursor); 
   }
	public static JPanel getCardPanel() {
		return cardPanel;
	}

	public static CardLayout getCard() {
		return card;
	}
	
	
	
}