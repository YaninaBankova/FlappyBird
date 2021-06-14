package edu.smg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FlappyBirdControl extends JPanel{
	private FlappyBirdPanel flappyBirdPanel = new FlappyBirdPanel();
	static JButton btnPlayAgain = new JButton("Play again");
	static JLabel score = new JLabel("Score: 0");
	
	public FlappyBirdControl() {
		setLayout(new BorderLayout());
		add(flappyBirdPanel, BorderLayout.CENTER);
		
		flappyBirdPanel.add(score);
		flappyBirdPanel.add(btnPlayAgain);
		btnPlayAgain.setVisible(false);
		
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flappyBirdPanel.gameEnded = false;
				btnPlayAgain.setVisible(false);
			}
		});
		
	}
}
