package cn.edu.nju.panels;

import javax.swing.JFrame;

public class Test extends JFrame{
	GamePanel s = new GamePanel();

	public Test(){
		
		super("Test");
		this.setSize(960,690);
		this.setLocation(100, 3);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(s);
	}	
	
	public static void main(String[] args) {
		 new Test();
	}
	
}
