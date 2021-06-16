package edu.smg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.PriorityQueue;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class FlappyBirdPanel extends JPanel{

	
	private int delay = 20;
	protected Timer timer = new Timer(delay, new TimerListener());
	
	private class TimerListener implements ActionListener {
		@Override /** Handle the action event */
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
	
	private PriorityQueue<Pipe> pipes = new PriorityQueue<Pipe>();
	
	Bird bird = new Bird(50, 20);
	
	Color blue = new Color(200, 252, 252);
	Color darkGreen = new Color(18, 213, 18);
	Color lightGreen = new Color(0, 255, 0);
	Color brown = new Color(255, 224, 122);
	
	public boolean gameEnded = false;
	public double velocity = 0;
	public int speed = getWidth() / 180;
	public double gravity = 0.9;
	public double lift = 17;
	public int distanceBetweenPipes = getWidth() / 4;
	public int score = 0;
	public int highestScore = 0;
	ImageIcon birdImage = new ImageIcon("C:\\Users\\Yaniiiiiii\\11e\\Project_FlappyBird\\src\\edu\\smg\\flappy_bird.png");
	Image birdCopy = birdImage.getImage();
	
	public FlappyBirdPanel() {
		timer.start();
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				velocity -= lift; //bird goes up
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();

		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2));
		
		//if the window is resized the bird and the pipes keep their form
		bird.height = height / 10;
		bird.width = height / 10;
		Pipe.pipeWidth = width / 13;
		
		if(distanceBetweenPipes / (width / 4) >= 1) { //add a new pipe every width/4 pixels
			pipes.offer(new Pipe(width, height));
			distanceBetweenPipes = 0;
		}
		
		setBackground(blue);
		
		for(Pipe pipe: pipes) {
			//fill the pipes
			g.setColor(lightGreen);
			g.fillRect(pipe.x, 0, Pipe.pipeWidth, pipe.top - height / 20); // top pipe
			g.fillRect(pipe.x ,height * 21 / 20 - pipe.bottom, Pipe.pipeWidth, pipe.bottom);
			g.fillRect(pipe.x - Pipe.pipeWidth / 6, pipe.top - height / 20, Pipe.pipeWidth * 8 / 6, height / 20); //bottom pipe
			g.fillRect(pipe.x - Pipe.pipeWidth / 6, height - pipe.bottom, Pipe.pipeWidth * 8 / 6, height / 20);
			//draw the outlines of the pipes
			g.setColor(Color.black);
			g2d.drawRect(pipe.x, 0, Pipe.pipeWidth, pipe.top - height / 20); //top pipe
			g2d.drawRect(pipe.x ,height * 21 / 20 - pipe.bottom, Pipe.pipeWidth, pipe.bottom);
			g2d.drawRect(pipe.x - Pipe.pipeWidth / 6, pipe.top - height / 20, Pipe.pipeWidth * 8 / 6, height / 20); //bottom pipe
			g2d.drawRect(pipe.x - Pipe.pipeWidth / 6, height - pipe.bottom, Pipe.pipeWidth * 8 / 6, height / 20);
		}
		
		
		g.setColor(darkGreen);
		g.fillRect(0, height * 9 / 10, width, height / 10); // grass
		g.setColor(brown);
		g.fillRect(0, height * 15 / 16, width, height / 15); //soil
		g.setColor(Color.black);
		g2d.drawLine(0, height * 9 / 10, width, height * 9 / 10); //outline of the ground
		
		//resize the picture of the bird depending of the size of the window and then draw it
		Image newimg = birdCopy.getScaledInstance(bird.width, bird.height,  java.awt.Image.SCALE_FAST);
		birdImage = new ImageIcon(newimg);
		birdImage.paintIcon(this, g, bird.x, bird.y);
		
		update();
		
	}
	
	public void update() {
		
		if(score > highestScore) { //checks for a new highest score
			highestScore = score;
			FlappyBirdControl.highestScore.setText("Highest score: " + highestScore);
		}
		
		//if the player clicked the button "play again", resume the game
		if(!gameEnded && speed == 0) {
			speed = getWidth() / 180;
			lift = 17;
			bird.x = 50;
			bird.y = 20;
			pipes.clear();
			score = 0;
			FlappyBirdControl.score.setText("Score: " + score);
		}
		
		for(Pipe pipe: pipes) { //if the bird have hit a pipe, the game ends
			if(bird.x + bird.width / 2 >= pipe.x && bird.x + bird.width / 2 <= pipe.x + Pipe.pipeWidth && 
					(bird.y  <= pipe.top || bird.y + bird.height >= getHeight() - pipe.bottom)) {
				gameEnded = true;
			}
		}
		
		
		distanceBetweenPipes += speed;
		
		for(Pipe pipe: pipes) { //moves the pipes right to left
			pipe.x -= speed;
		}
		
		//if the most left pipe is left of the bird and cannot be seen anymore, delete the pipe
		if(pipes.peek() != null && pipes.peek().x < - Pipe.pipeWidth) {
			pipes.poll();
			score++;
			FlappyBirdControl.score.setText("Score: " + score);
		}
		
		//bird falls due to gravity if it's not lifted by the player
		velocity += gravity;
		velocity *= 0.9;
		bird.y += velocity;
		
		//if the bird falls on the ground, the game ends
		if(bird.y > getHeight() * 11 / 12 - bird.height) {
			FlappyBirdControl.btnPlayAgain.setVisible(true);
			velocity = 0;
			bird.y = getHeight() * 11 / 12 - bird.height;
			gameEnded = true;
			
		}
		
		if(bird.y < 0) { //bird cannot fly too high
			velocity = 0;
			bird.y = 0;
		}
		
		
		if(gameEnded) { //if the game ends, stop all motion
			speed = 0;
			lift = 0;
		}
		
	}
	

}
