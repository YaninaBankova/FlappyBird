package edu.smg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FlappyBirdControl extends JPanel{
	private FlappyBirdPanel flappyBirdPanel = new FlappyBirdPanel();
	private JLabel lblName = new JLabel();
	private JPanel bottomPanel = new JPanel();
	
	static JLabel highestScore = new JLabel("Highest score: 0");
	static JButton btnPlayAgain = new JButton("Play again");
	static JLabel score = new JLabel("Score: 0");
	
	public FlappyBirdControl(String name) throws InvalidNameException{
		setPlayersName(name);
		
		setLayout(new BorderLayout());
		
		add(flappyBirdPanel, BorderLayout.CENTER);
		flappyBirdPanel.add(score);
		flappyBirdPanel.add(btnPlayAgain);
		btnPlayAgain.setVisible(false);

		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setBackground(new Color(255, 224, 122));
		
		bottomPanel.add(lblName);
		bottomPanel.add(highestScore);
		
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flappyBirdPanel.gameEnded = false;
				btnPlayAgain.setVisible(false);
			}
		});
		
	}
	
	public void setPlayersName(String name) throws InvalidNameException{
		if(validName(name)) {
			lblName.setText("Player: " + name);
		} else {
			throw new InvalidNameException(name);
		}
	}
	
	public boolean validName(String name) {
		char firstChar = name.charAt(0);
		//if the first char is a letter the name is valid
		if((firstChar >= 65 && firstChar <= 90) || (firstChar >= 97 && firstChar <= 122))
			return true;
		
		return false;
	}
}
