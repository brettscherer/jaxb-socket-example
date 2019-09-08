package com.scherer.example.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.MessageFormat;

import com.scherer.example.jaxb.JAXBMarshallingOperations;
import com.scherer.example.jaxb.Platform;

/**
 * The Server is responsible for accepting a socket connection from the Client
 * and unmarshalling the received XML into a JAXB-annotated object.
 *
 * @author brettscherer
 */
public class Server {

	public static void main(String[] args) {
		try {
			String xml = waitForSocketConnection();

			System.out.println("Received XML from Client:\n");
			System.out.println(xml);

			// Unmarshal the XML String into a JAXB-annotated Platform object
			Platform platform = JAXBMarshallingOperations.unmarshalXMLStringToObject(xml, Platform.class);

			System.out.println("XML String converted to Platform object:\n");
			System.out.println(MessageFormat.format("ID: {0}", platform.getId()));
			System.out.println(MessageFormat.format("Name: {0}", platform.getName()));
			System.out.println(MessageFormat.format("Heading: {0}", platform.getHeading()));
			System.out.println(MessageFormat.format("Latitude: {0}", platform.getLatitude().toPlainString()));
			System.out.println(MessageFormat.format("Longitude: {0}", platform.getLongitude().toPlainString()));
			for (Long childPlatformId : platform.getChildPlatformIds()) {
				System.out.println(MessageFormat.format("Child Platform ID: {0}", childPlatformId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Waits for a connection from the Client. Upon accepting a connection, data is
	 * read from the socket and returned as a String.
	 */
	private static String waitForSocketConnection() throws IOException {
		String xml = null;
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(ConnectionConstants.PORT);
			Socket socket = serverSocket.accept();

			// Blocked, waiting here for a Client connection...

			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			xml = dataInputStream.readUTF();
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}

		return xml;
	}
}