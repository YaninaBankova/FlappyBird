package main;

public class Pipe implements Comparable<Pipe>{
	public int top;
	public int bottom;
	public int spaceBetweenPipes;
	public int x;
	public static int pipeWidth = 20;
	
	public Pipe(int width, int height) {
		x = width;
		//choosing a random size for the top and bottom pipe
		spaceBetweenPipes = (int)(height / 5 + Math.random() * height / 5);
		top = (int)(height * Math.random()/3 + height / 10);
		bottom = height - (top + spaceBetweenPipes);
	}
	
	public int compareTo(Pipe arg0) {
		return x - arg0.x;
	}
}
