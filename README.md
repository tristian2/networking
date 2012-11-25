networking
==========


Overview
•  Two programs.  Client and server
•	Server has two classes, Server and Server Thread.  Client has one class, Client

Client
•	The program starts with a main method, the first index of the args parameter is checked for an IP address
•	User is informed if the IP address is not present
•	Program asks user for input, the integer
•	Java scanner used to handle input
•	The sendstuff method is executed on port 12345, with the input value and ip address passed to it
•	A datagram socket is setup and configured
•	The input is converted using a helper method to a byte array
•	The packet is setup with the input value as its content 
•	Another method receiveStuff is entered with the datagram configuration and value
•	A loop based on the value handles the N packets that are sent from the server.  Responses sent to the output
•	Back to the calling method, which closes the socket
•	Back to the main method, the user is informed.
•	End
Server
•	Server class main method starts a new thread, the Server Thread inherits from Thread
•	Thread is started
•	Datagram socket configured on port 12345
•	Datagram packet is setup to listen for requests
•	The request is received and converted from a byte array to an integer, using a helper method
•	The result is sent to the standard output
•	The send stuff method is invoked
•	Depending on the value sent from the client, a loop is setup to fire N packets back to the client, using the configured Datagram Packet
•	The calculated values (integers) require converting to a byte array for addition to the packet.  This is carried out by another helper method.
•	Return back to the calling method, which closed the socket and exits the program.
