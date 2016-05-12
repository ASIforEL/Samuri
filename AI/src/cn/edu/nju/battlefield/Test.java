package cn.edu.nju.battlefield;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import cn.edu.nju.panels.GameField;

public class Test extends JFrame {
	private GameField s = new GameField();
	
	public Test() {

		super("Test");
		this.setSize(960, 720);
		this.setLocation(100, 3);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(s);
		this.setResizable(false);
	}

	public static void main(String[] args) {
		new Test();
	}
 
}
	