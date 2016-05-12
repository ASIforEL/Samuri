package cn.edu.nju.contect;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ServerFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	JPanel server;
	JLabel ip;
	JTextArea ipAddress;
	JLabel port;
	JTextArea portNum;
	JButton connect;
	
	public ServerFrame() {
		super("连接客户端");
		this.setLayout(null);
		screen();
		init();
		add(server);
		this.setVisible(true);
	}
	
	public void screen() {
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dim = tool.getScreenSize();
		int width = (int) dim.getWidth();
		this.setLocation((width - 960) / 2, 3);
		this.setSize(960, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		this.setResizable(false);
		
	}
	
	public void init() {
		server = new JPanel();
		server.setBounds(0, 0, 960, 720);
		server.setLayout(null);
		
		ip = new JLabel("IP: ");
		ip.setBounds(400, 400, 50, 30);
		server.add(ip);
		
		ipAddress = new JTextArea();
		ipAddress.setBounds(460, 400, 90, 30);
		server.add(ipAddress);
		
		port = new JLabel("Port: ");
		port.setBounds(400, 450, 50, 30);
		server.add(port);
		
		portNum = new JTextArea();
		portNum.setBounds(460, 450, 90, 30);
		portNum.setText("47747");
		portNum.setEditable(false);
		server.add(portNum);
		
		connect = new JButton("连接");
		connect.setBounds(450, 520, 90, 40);
		server.add(connect);

	}
	
	
	public static void main(String args[]) {
		new ServerFrame();
	}
}
