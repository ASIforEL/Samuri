package cn.edu.nju.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.swing.JOptionPane;

import cn.edu.nju.logic.Game;

public class GameSocket implements Runnable{
	Socket socket;
	BufferedReader reader;
	BufferedWriter writer;
	Game game;
	

	public GameSocket(Socket s) {
		this.socket = s;
	}

	@Override
	public void run(){
		try {
			//get the input from the client
			reader = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream(), "UTF-8"));
			String line = null;
			while (((line = reader.readLine().trim()) !=null)) {
				int rivalSamurai = -1;
				int rivalAction = 0;
				if (line.startsWith("#")) {
					String[] input = line.split(" ");
					rivalSamurai = Integer.parseInt(input[2]);
					rivalAction = Integer.parseInt(input[3]);
				}
				
				if (game.cheakWin()) {
					if (game.winSide() == 0) {
						JOptionPane.showMessageDialog(null, "TeamCap已获胜!游戏结束！");
					}
					if (game.winSide() == 1) {
						JOptionPane.showMessageDialog(null, "TeamIronMan已获胜！游戏结束！");
					}
				}else {
					//做出对方所做的动作
				}

				//实现自己的操作及传输此信息给对方

			}
			reader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * output the information tp the Client
	 * @param output
	 */
	public void send(String output) {
		try {
			socket.getOutputStream().write(output.getBytes("UTF-8"));
			
			//			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			//			writer.write(output);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
