package cn.edu.nju.panels;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import cn.edu.nju.battlefield.GameField;

public class StartPanel extends JFrame {  

	private JLayeredPane mainPane;  
	private JLabel background;  
	private JButton startBt, helpBt;
	private static JPanel cardPanel;
	private static CardLayout card;

	public StartPanel(){  
		init();
		screen();
		setTitle("AI");
		getContentPane().setLayout(new BorderLayout(0, 0));

		cardPanel = new JPanel();
		getContentPane().add(cardPanel,BorderLayout.CENTER);
		card = new CardLayout();
		cardPanel.setLayout(card);
		//初始界面的组件 
		MapSelectPanel mapSelectPanel = new MapSelectPanel();
		HelpPanel helpPanel = new HelpPanel();
		GamePanel gamePanel = new GamePanel();
		ModeSelectPanel modeSelectPanel = new ModeSelectPanel();
		GameField gameFieldPanel = new GameField();

		
		//将面板加入到cardPanel中
		mainPane = new JLayeredPane();
		cardPanel.add(mainPane, "mainPane");
		cardPanel.add(mapSelectPanel, "mapSelectPanel");
		cardPanel.add(helpPanel, "helpPanel");
		cardPanel.add(gamePanel, "gamePanel");
		cardPanel.add(modeSelectPanel, "modeSelectPanel");
		cardPanel.add(gameFieldPanel, "gameFieldPanel");

		background=new JLabel(new ImageIcon(this.getClass().getResource("/cn/picture/start.jpg")));  
		background.setBounds(0,0,960,690); 
		//创建按钮  
		startBt=new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/startIcon.jpg")));  
		startBt.setBorderPainted(false);
		startBt.setBounds(400,253,163,49); 

		//Button start 监听
		startBt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				startBt.setBorderPainted(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				startBt.setBorderPainted(true);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(cardPanel,"mapSelectPanel");
			}

		});


		helpBt=new JButton(new ImageIcon(this.getClass().getResource("/cn/picture/helpIcon.jpg")));
		helpBt.setBorderPainted(false); 
		helpBt.setBounds(400,334,163,49); 

		//Button Help 监听     可与上面的合并。。。不想想了，在下面建一个自己的类  接口 &……明天再写
		helpBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				helpBt.setBorderPainted(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				helpBt.setBorderPainted(true);
			}

			public void mouseClicked(MouseEvent e){
				card.show(cardPanel, "helpPanel");
			}
		});

		mainPane.add(background,JLayeredPane.DEFAULT_LAYER);      
		mainPane.add(startBt,JLayeredPane.MODAL_LAYER);  
		mainPane.add(helpBt,JLayeredPane.MODAL_LAYER);  
		
		JButton serverBt = new JButton();
		serverBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		serverBt.setBounds(400, 415, 163, 49);
		mainPane.add(serverBt);
		
		JButton clientBt = new JButton();
		clientBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		clientBt.setBounds(400, 495, 163, 49);
		mainPane.add(clientBt);
	}  

	

	public void init(){
	}
	public void screen(){
		//屏幕的大小 及开始时的位置
		Toolkit tool=Toolkit.getDefaultToolkit();
		Dimension dim = tool.getScreenSize();
		int width=(int)dim.getWidth();
		int height=(int)dim.getHeight();
		this.setLocation((width-960)/2,3);  
		this.setSize(960,720); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

		this.setVisible(true);
		this.setResizable(false);
	}

	public static JPanel getCardPanel() {
		return cardPanel;
	}
	public static CardLayout getCard() {
		return card;
	}
}  
