package com.scherer.example.socket;

/**
 * Connection constants for transmitting/receiving data over a socket connection.
 *
 * @author brettscherer
 */
public class ConnectionConstants {

	/**
	 * The host for the socket connection. If running the Client and Server on
	 * different machines, change this value to the Server machine's IP address.
	 */
	public static final String SERVER_HOST = "localhost";

	/**
	 * The port for the socket connection.
	 */
	public static final int PORT = 6666;

	private ConnectionConstants() {
		// There is no need to call this constructor.
		throw new AssertionError();
	}
}