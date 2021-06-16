package edu.smg;

@SuppressWarnings("serial")
public class InvalidNameException extends Exception{

	public InvalidNameException(String name) {
		super("Invalid name: " + name);
	}
}
