import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

// Created by Andreas Odegaard on 08.12.2016.

/**
 * Simple run class for servertesting.
 */
public class ServerTestRunable implements Runnable {

    private ServerSocket server;
    private Artist artist = new Artist("Jonny", "Song", "Album", "World", 2000);

    public ServerTestRunable() throws IOException {
        Properties properties = DBloader.loadPropertiesFile();
        int port = Integer.parseInt(properties.getProperty("SERVER.port"));
        server = new ServerSocket(port);
    }

    public void run()  {

        while (true) {

            try {
            Socket sock = server.accept();

            DataInputStream in = new DataInputStream(sock.getInputStream());
            PrintWriter messageOut = new PrintWriter(sock.getOutputStream(), true);

            messageOut.println(artist.toString());
            String message = in.readUTF();
            assertEquals(message, "Test");

            sock.close();

            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
