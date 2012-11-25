/*
 * Server Thread
 * Birkbeck computer science networking excersice
 * student number 12410921
 * March 2012
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
             System.out.println("Usage: java Client <hostname>");
             return;
        }

        System.out.println("okay, enter a number to get your three times table from the server!");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        sendStuff(input, args[0]);
        System.out.println("Bye");
    }

    /**
     *  sendstuff to the server
     * @param input the value to the server
     * @param addressString the address to use
     * @return void
     */
    private static void sendStuff(int input, String addressString) {
        try {
            // get a datagram socket
            DatagramSocket socket = new DatagramSocket() ;
            // send request
            byte[] buf = intToByteArray(input);

            InetAddress address = InetAddress.getByName(addressString);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 12345);
            socket.send(packet);
            receiveStuff(input, packet, buf, socket);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  handle the response from the server
     * @param input number of times to check for responses
     * @param packet the packet invoked earlier is passed
     * @param buf the buffer used earlier
     * @param socket the socket used earlier
     * @return void
     */
    private static void receiveStuff(int input, DatagramPacket packet, byte[] buf, DatagramSocket socket){
        for(int i=1;i<input+1;i++) {
            // get response
            try {
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // display response
                int received = byteToInt( packet.getData());
                System.out.println("response " + i + ": " + received);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     *  convert byte array to int
     * @param b the byte array to convert
     * @return int the result
     */
    private static int byteToInt(byte[] b)
    {
        int val=0;
        for (int i=b.length-1, j = 0; i >= 0; i--,j++)
        {
            val += (b[i] & 0xff) << (8*j);
        }
        return val;
    }

    /**
     *  convert int to byte array
     * @param value the value to convert
     * @return byte[] the result
     */
    private static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }
    
    
    
}
