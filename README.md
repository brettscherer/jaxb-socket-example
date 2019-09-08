# jaxb-socket-example

This Java project shows an example of transmitting XML data over a socket connection and marshaling the XML to and from a Java object.

## Instructions

1) If running the Server on a different machine than the Client, change the SERVER_HOST value in ConnectionConstants to the Server machine's IP address.

2) Compile and Run Server (server will wait for a socket connection from client)

```bash
javac Server.java
java Server
```

3) Compile and Run Client (client will connect to server via a socket connection and send the xml data)

```bash
javac Client.java
java Client
```

## Output and Termination

The Server will unmarshal the xml data and print the values to the console. Both Client and Server will terminate after the xml data has been sent and received.