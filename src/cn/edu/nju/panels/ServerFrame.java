package cn.edu.nju.panels;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import cn.edu.nju.socket.ServerListener;

public class ServerFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JPanel server;
	JLabel ip;
	JTextArea ipAddress;
	JLabel port;
	JTextArea portNum;
	JButton connect;
	
	public ServerFrame() {
		super("Connect the Client");
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
		this.setResizable(true);
	}
	
	public void init() {
		server = new JPanel();
		server.setBounds(0, 0, 960, 720);
		server.setLayout(null);
		
//		ip = new JLabel("IP: ");
//		ip.setBounds(420, 400, 50, 20);
//		server.add(ip);
//		
//		ipAddress = new JTextArea(1, 15);
//		ipAddress.setBounds(460, 400, 110, 20);
//		server.add(ipAddress);
		
		port = new JLabel("Port: ");
		port.setBounds(410, 450, 50, 20);
		server.add(port);
		
		portNum = new JTextArea(1, 15);
		portNum.setBounds(460, 450, 110, 20);
		portNum.setText("8080");
		portNum.setEditable(false);
		server.add(portNum);
		
		connect = new JButton("connect");
		connect.setBounds(450, 520, 90, 40);
		server.add(connect);
		connect.addActionListener(new connectListener());
		server.updateUI();
	}
	
	
	public static void main(String args[]) {
		new ServerFrame();
	}
	
	class connectListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ServerListener serverListener = new ServerListener();
			Thread t = new Thread(serverListener);
			t.start();
		}

	}
}
