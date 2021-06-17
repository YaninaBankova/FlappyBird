package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FlappyBirdApp {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		
		frame.setTitle("Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JPanel startingPanel = new JPanel();
		frame.add(startingPanel);
		startingPanel.setBackground(new Color(200, 252, 252));
		JTextField nameField = new JTextField();
		JLabel lblName = new JLabel("Name: ");
		JButton btnPlay = new JButton("Play");
		JLabel invalidName = new JLabel("Invalid name! Your name should start with a letter!");
		invalidName.setForeground(Color.red);
		
		int height = startingPanel.getHeight();
		int width = startingPanel.getWidth();
		
		//put the labels, text field and button in a specific place
		startingPanel.setLayout(null); 
		startingPanel.add(lblName);
		lblName.setBounds(width / 3, height / 3, width / 6, height / 9);
		startingPanel.add(nameField);
		nameField.setBounds(width / 2, height / 3, width / 6, height / 9);
		startingPanel.add(invalidName);
		invalidName.setBounds(width / 4, height * 4 / 9, width / 2, height / 9);
		invalidName.setVisible(false);
		startingPanel.add(btnPlay);
		btnPlay.setBounds(width * 5 / 12, height * 5 / 9, width / 6, height / 9);
		
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = nameField.getText();
					frame.add(new FlappyBirdControl(name));
					startingPanel.setVisible(false);
				} catch (InvalidNameException ex) {
					invalidName.setVisible(true);
					nameField.setText("");
				} catch (StringIndexOutOfBoundsException ex) {
					invalidName.setVisible(true);
					nameField.setText("");
				}
			}
		});
	}
}
