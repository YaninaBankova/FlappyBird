package edu.smg;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class FlappyBirdControl extends JPanel{
	private FlappyBirdPanel flappyBirdPanel = new FlappyBirdPanel();
	
	public FlappyBirdControl() {
		setLayout(new BorderLayout());
		add(flappyBirdPanel, BorderLayout.CENTER);
	}
}
