package cn.edu.nju.panels;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeamModePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TeamModePanel() {
		setSize(960,720);
		setLayout(null);
		
		JLabel captainTeam = new JLabel("CaptainTeam");
		captainTeam.setBounds(107, 128, 307, 418);
		add(captainTeam);
		
		JLabel ironmanTeam = new JLabel("IronmanTeam");
		ironmanTeam.setBounds(549, 128, 307, 418);
		add(ironmanTeam);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartPanel.getCard().show(StartPanel.getCardPanel(), "modeSelectPanel");
			}
		});
		btnBack.setBounds(321, 601, 93, 23);
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
		btnFight.setBounds(549, 601, 93, 23);
		add(btnFight);
		
	}
}