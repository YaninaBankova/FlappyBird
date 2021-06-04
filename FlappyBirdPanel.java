package edu.smg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.PriorityQueue;

import javax.swing.JPanel;
import javax.swing.Timer;




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
	public double velocity = 0;
	public double gravity = 0.6;
	public double lift = 15;
	public int speed = 2;
	public int distanceBetweenPipes = 70;
	
	
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
//		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.ORANGE);
//		g2d.setStroke(new BasicStroke(2));
		g.fillOval(bird.x, bird.y, bird.width, bird.height);
		update();
		
		if(distanceBetweenPipes % 70 == 0) {
			pipes.offer(new Pipe(getWidth(), getHeight()));
			distanceBetweenPipes = 0;
		}
		
		for(Pipe pipe: pipes) {
			g.fillRect(pipe.x, 0, Pipe.pipeWidth, pipe.top);
			g.fillRect(pipe.x ,getHeight() - pipe.bottom, Pipe.pipeWidth, pipe.bottom);
		}
	}
	
	public void update() {
		distanceBetweenPipes += speed;
		for(Pipe pipe: pipes) {
			pipe.x -= speed;
		}
//		for(Pipe pipe: pipes) {
//			if(pipe.x < 0){
//				pipes.remove(pipe);
//			}
//		}
		if(pipes.peek() != null && pipes.peek().x < - Pipe.pipeWidth) {
			pipes.poll();
		}
		
		velocity += gravity;
		velocity *= 0.9;
		bird.y += velocity;
		
		if(bird.y > getHeight() - bird.height) {
			velocity = 0;
			bird.y = getHeight() - bird.height;
		}
		if(bird.y < 0) {
			velocity = 0;
			bird.y = 0;
		}
		
		
	}

}
