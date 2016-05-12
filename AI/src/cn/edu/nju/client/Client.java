package cn.edu.nju.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import cn.edu.nju.views.ClientFrame;

public class Client {
	//鍗曚緥鍖�
	private Client() {
	}

	public static final int PORT_NUM = 8080;
	private static Client instance = new Client();
	public String ip;
	ClientFrame connectUI;
	Socket socket;
	BufferedReader reader;
	PrintWriter writer;
	
	public void setUI(ClientFrame connectUI) {
		this.connectUI = connectUI;
	}
	
	public static Client getClient() {
		return instance;
	}

	
	/**
	 * 瀹㈡埛绔粠鏈嶅姟鍣ㄨ鍙栨秷鎭�
	 */
	public void connect() {
		new Thread(){

			@Override
			public void run() {
				try {
					socket = new Socket(ip, PORT_NUM);
					JOptionPane.showMessageDialog(null, "浣犲凡缁忚繛鎺ヤ笂锛�");
					writer = new PrintWriter(
							new OutputStreamWriter(
									socket.getOutputStream()));
					reader = new BufferedReader(
							new InputStreamReader(
									socket.getInputStream()));		
					String line;
					while ((line = reader.readLine().trim()) != null) {
						
						writer.close();
						reader.close();

						writer = null;
						reader = null;	
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * 瀹㈡埛绔彂閫佹秷鎭埌鏈嶅姟鍣�
	 * @param output
	 */
	public void send(String output) {
		if (writer != null) {
			writer.write(output + "\n");
			writer.flush();
		}else {
			JOptionPane.showInternalMessageDialog(null, "宸叉柇寮�杩炴帴锛�");
		}
	}
}
