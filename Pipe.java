package edu.smg;

public class Pipe implements Comparable<Pipe>{
	public int top;
	public int bottom;
	public int x;
	public static int pipeWidth = 20;
	
	public Pipe(int width, int height) {
		x = width;
		bottom = (int)(height * Math.random()/2);
		top = (int)(height * Math.random()/2);
	}
	
	public int compareTo(Pipe arg0) {
		return x - arg0.x;
	}
}
