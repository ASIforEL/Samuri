package cn.edu.nju.panels;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public GameField gameField;

	public GameFrame(){
		screen();
		setTitle("AI");
		gameField=new GameField();
		add(gameField);
		gameField.requestFocus(); 

	}
	public void screen(){

		Toolkit tool=Toolkit.getDefaultToolkit();
		Dimension dim = tool.getScreenSize();
		int width=(int)dim.getWidth();
		this.setLocation((width-960)/2,3);  
		this.setSize(960,720); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

		this.setVisible(true);
		this.setResizable(false);
	}


}
