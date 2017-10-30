package org.commiecoin.util.exceptions;

public class PortUsedException extends Exception {

	public PortUsedException(int port) {
		super("Port " + port + " is already in use!");
	}
	
}
