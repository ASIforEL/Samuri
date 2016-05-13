package cn.edu.nju.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FreeModePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public FreeModePanel() {
		setSize(960,720);
		setLayout(null);
		
		JLabel captainLabel = new JLabel("New label");
		captainLabel.setBounds(110, 73, 180, 131);
		add(captainLabel);
		
		
		JLabel hulkLabel = new JLabel("New label");
		hulkLabel.setBounds(378, 73, 180, 131);
		add(hulkLabel);
		
		JLabel hawkeyeLabel = new JLabel("New label");
		hawkeyeLabel.setBounds(652, 73, 180, 131);
		add(hawkeyeLabel);
		
		JLabel ironmanLabel = new JLabel("New label");
		ironmanLabel.setBounds(110, 368, 180, 131);
		add(ironmanLabel);
		
		JLabel widowLabel = new JLabel("New label");
		widowLabel.setBounds(378, 368, 180, 131);
		add(widowLabel);
		
		JLabel spidermanLabel = new JLabel("New label");
		spidermanLabel.setBounds(652, 368, 180, 131);
		add(spidermanLabel);
		
		JButton btnCaptain = new JButton("New button");
		btnCaptain.setBounds(149, 240, 93, 23);
		add(btnCaptain);
		
		JButton btnHulk = new JButton("New button");
		btnHulk.setBounds(424, 240, 93, 23);
		add(btnHulk);
		
		JButton btnHawkeye = new JButton("New button");
		btnHawkeye.setBounds(698, 240, 93, 23);
		add(btnHawkeye);
		
		JButton btnIronman = new JButton("New button");
		btnIronman.setBounds(149, 535, 93, 23);
		add(btnIronman);
		
		JButton btnWidow = new JButton("New button");
		btnWidow.setBounds(424, 535, 93, 23);
		add(btnWidow);
		
		JButton btnSpiderman = new JButton("New button");
		btnSpiderman.setBounds(698, 535, 93, 23);
		add(btnSpiderman);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartPanel.getCard().show(StartPanel.getCardPanel(), "modeSelectPanel");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		btnBack.setBounds(279, 642, 93, 23);
		add(btnBack);
		
		JButton btnFight = new JButton("FIGHT");
		btnFight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartPanel.getCard().show(StartPanel.getCardPanel(), "gameFieldPanel");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		btnFight.setBounds(569, 642, 93, 23);
		add(btnFight);
	}
}