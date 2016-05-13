package cn.edu.nju.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerListener implements Runnable {

	public static final int PORT_NUM = 8080;			//端口
	Socket socket;
	
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket();

			serverSocket.bind(new InetSocketAddress("172.26.197.99",8080)); 
			while (true) {
				//block
				socket = serverSocket.accept();
				//bulid the connnectin
				JOptionPane.showMessageDialog(null, "You have connected the the Client at the port 8080");
				//deliver socket to a new thread
				GameSocket gs = new GameSocket(socket);
				
				Thread t = new Thread(gs);
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
		}
	}
}
