package com.scherer.example.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;
import java.net.UnknownHostException;
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

		try {
			// Marshal the JAXB-annotated Platform object into an XML String
			String xml = JAXBMarshallingOperations.marshalObjectToXMLString(platform, Platform.class);

			// Send the XML String to the server over a socket connection
			sendXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
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

	/*
	 * Sends the given XML to the server over a socket connection.
	 */
	private static void sendXML(String xml) throws UnknownHostException, IOException {
		Socket socket = null;

		try {
			socket = new Socket(ConnectionConstants.SERVER_HOST, ConnectionConstants.PORT);

			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataOutputStream.writeUTF(xml);
			dataOutputStream.flush();
			dataOutputStream.close();
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
}