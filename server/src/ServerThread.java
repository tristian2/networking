/*
 * Server Thread
 * Birkbeck computer science networking excersice
 * student number 12410921
 * March 2012
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerThread extends Thread {

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;

    /**
    *  constructor
    */
    public ServerThread() throws IOException {
        this("ServerThread");
    }

    /**
    *  overload - setup the socket on port 4445
    * @param name
    */
    public ServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(12345);
    }

    /**
     *  start the processes, receive and send
     */
    public void run() {

        int received = 0;
        DatagramPacket packet;

        byte[] buf = new byte[256];

        // receive request
        packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
            received = byteToInt(packet.getData());
            System.out.print("integer received is.." + received);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendStuff(received, packet);

        socket.close();
        System.exit(0);
    }

    /**
     *  sendstuff to the client
     * @param received the number of times to send the values to the server
     * @param packet the packet to pass to this method
     * @return void
     */
    private void sendStuff(int received, DatagramPacket packet) {
        byte[] buf = new byte[256];
        
        for (int i = 0; i < received; i++) {
            try {
                int valueToSend = (i + 1) * 3;
                // send request
                byte[] buf2 = intToByteArray(valueToSend);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf2, buf2.length, address, port);
                socket.send(packet);
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
    private int byteToInt(byte[] b) {
        int val = 0;
        for (int i = b.length - 1, j = 0; i >= 0; i--, j++) {
            val += (b[i] & 0xff) << (8 * j);
        }
        return val;
    }

    /**
     *  convert int to byte array
     * @param value the value to convert
     * @return byte[] the result
     */
    private byte[] intToByteArray(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
    }

}





