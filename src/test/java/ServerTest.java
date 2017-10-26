import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

// Created by Andreas Odegaard on 08.12.2016.

/**
 * Testclass for Server/client communication, bit simpler than Server, ThreadRunner and Client in main.
 * But based on the same principles and using the same values and functions.
 */
public class ServerTest {
    private Artist artist = new Artist("Jonny", "Song", "Album", "World", 2000);
    private Properties properties = DBloader.loadPropertiesFile();
    private int port = Integer.parseInt(properties.getProperty("SERVER.port"));
    private String adress = properties.getProperty("SERVER.address");

    @Test
    public void serverTest() throws Exception {
        ServerTestRunable sr = new ServerTestRunable();
        Thread serverThread = new Thread(sr);
        serverThread.start();

        Socket client = new Socket(adress, port);

        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String message = input.readLine();
        dos.writeUTF("Test");
        dos.flush();

        assertEquals(message, artist.toString());

        client.close();
    }
}