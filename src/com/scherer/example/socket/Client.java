package com.scherer.example.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.List;

import com.scherer.example.jaxb.JAXBMarshallingOperations;
import com.scherer.example.jaxb.Platform;

/**
 * The Client is responsible for marshalling a JAXB-annotated object into xml
 * and then transmitting the xml to the server over a socket connection.
 *
 * @author brettscherer
 */
public class Client {

	public static void main(String[] args) {
		// Create a JAXB-annotated Platform object
		Platform platform = createPlatform();

		Socket socket = null;
		DataOutputStream dataOutputStream = null;
		DataInputStream dataInputStream = null;

		try {
			// Marshal the JAXB-annotated Platform object into an XML String
			String xml = JAXBMarshallingOperations.marshalObjectToXMLString(platform, Platform.class);

			// Create the socket connection
			socket = new Socket(ConnectionConstants.SERVER_HOST, ConnectionConstants.PORT);

			// Create the output stream for sending data to the server
			dataOutputStream = new DataOutputStream(socket.getOutputStream());

			// Create the input stream for receiving data from the server
			dataInputStream = new DataInputStream(socket.getInputStream());

			// Send the XML String to the server over a socket connection
			dataOutputStream.writeUTF(xml);

			// Blocked, waiting here for a response from the server
			String response = dataInputStream.readUTF();
			// When we get a response, we'll print it out
			System.out.println(MessageFormat.format("Response from server: {0}", response));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dataOutputStream != null) {
				try {
					dataOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dataInputStream != null) {
				try {
					dataInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Creates a JAXB-annotated Platform object.
	 */
	private static Platform createPlatform() {
		Platform platform = new Platform();

		platform.setId(1);
		platform.setName("NBCRV-01");
		platform.setHeading(180);
		platform.setLatitude(BigDecimal.valueOf(39.448449D));
		platform.setLongitude(BigDecimal.valueOf(-76.139505D));

		List<Long> childPlatformIds = platform.getChildPlatformIds();
		childPlatformIds.add(2L);
		childPlatformIds.add(3L);
		childPlatformIds.add(4L);

		return platform;
	}
}