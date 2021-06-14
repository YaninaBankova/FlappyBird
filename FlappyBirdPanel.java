package edu.smg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.PriorityQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;




@SuppressWarnings("serial")
public class FlappyBirdPanel extends JPanel{

	
	
	private int delay = 17;
	protected Timer timer = new Timer(delay, new TimerListener());
	
	private class TimerListener implements ActionListener {
		@Override /** Handle the action event */
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
	
	private PriorityQueue<Pipe> pipes = new PriorityQueue<Pipe>();
	
	Bird bird = new Bird(50, 20);
	
	Color blue = new Color(153, 255, 255);
	
	public boolean gameEnded = false;
	public double velocity = 0;
	public double gravity = 0.7;
	public double lift = 17;
	public int speed = 2;
	public int distanceBetweenPipes = getWidth() / 4;
	public int score = 0;
	ImageIcon image = new ImageIcon("C:\\Users\\Yaniiiiiii\\Pictures\\transparent_bird.png");
	Image imageTemp = image.getImage(); // transform it 
	
	public FlappyBirdPanel() {
		timer.start();
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				velocity -= lift;
				
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
		
		
		bird.height = height / 10;
		bird.width = height / 10;
		Pipe.pipeWidth = width / 13;
		
		
		if(distanceBetweenPipes % (width / 4) == 0) {
			pipes.offer(new Pipe(width, height));
			distanceBetweenPipes = 0;
		}
		setBackground(blue);
		
		g.setColor(Color.GREEN);
		for(Pipe pipe: pipes) {
			g.fillRect(pipe.x, 0, Pipe.pipeWidth, pipe.top);
			g.fillRect(pipe.x ,height - pipe.bottom, Pipe.pipeWidth, pipe.bottom);
		}
		g.fillRect(0, height * 12 / 13, width, height / 12);
		
//		g.setColor(Color.yellow);
//		g.fillOval(bird.x, bird.y, bird.width, bird.height);
		
		
		Image newimg = imageTemp.getScaledInstance(bird.width, bird.height,  java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(newimg);
		image.paintIcon(this, g, bird.x, bird.y);
		
		update();
		
	}
	
	public void update() {
		
		if(!gameEnded && speed ==0) {
			lift = 17;
			speed = 2;
			bird.x = 50;
			bird.y = 20;
			pipes.clear();
			score = 0;
			FlappyBirdControl.score.setText("Score: " + score);
		}
		
		for(Pipe pipe: pipes) {
			if(bird.x + bird.width / 2 >= pipe.x && bird.x + bird.width / 2 <= pipe.x + Pipe.pipeWidth && 
					(bird.y  <= pipe.top || bird.y + bird.height >= getHeight() - pipe.bottom)) {
				gameEnded = true;
			}
		}
		
		
		distanceBetweenPipes += speed;
		for(Pipe pipe: pipes) {
			pipe.x -= speed;
		}
		if(pipes.peek() != null && pipes.peek().x < - Pipe.pipeWidth) {
			pipes.poll();
			score++;
			FlappyBirdControl.score.setText("Score: " + score);
		}
		
		velocity += gravity;
		velocity *= 0.9;
		bird.y += velocity;
		
		if(bird.y > getHeight() - bird.height) {
			FlappyBirdControl.btnPlayAgain.setVisible(true);
			velocity = 0;
			bird.y = getHeight() - bird.height;
			gameEnded = true;
			
		}
		
		if(bird.y < 0) {
			velocity = 0;
			bird.y = 0;
		}
		
		
		if(gameEnded) {
			speed = 0;
			lift = 0;
		}
		
	}
	

}
