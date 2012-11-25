/*
 * Server Thread
 * Birkbeck computer science networking excersice
 * student number 12410921
 * March 2012
 */
import java.io.IOException;

public class Server {

    /**
     *  the entry point
     */
    public static void main(String[] args) throws IOException {
        new ServerThread().start();
    }
}
